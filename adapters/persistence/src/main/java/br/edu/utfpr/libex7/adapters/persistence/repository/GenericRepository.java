package br.edu.utfpr.libex7.adapters.persistence.repository;

import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Id;
import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Table;
import br.edu.utfpr.libex7.adapters.persistence.util.builder.PreparedStatementBuilder;
import br.edu.utfpr.libex7.adapters.persistence.util.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;

public abstract class GenericRepository<T, K> {

    protected Connection connection;

    public GenericRepository(Connection connection){
        this.connection = connection;
    }

    public T save(T entity){
        String tableName = getTableName();
        try {
            Map<String, Object> columnMap = getColumnMap(entity);

            PreparedStatement preparedStatement = new PreparedStatementBuilder(connection)
                                                    .forType(PreparedStatementBuilder.QueryType.INSERT)
                                                    .forTable(tableName)
                                                    .withColumnMap(columnMap)
                                                    .build();
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if(generatedKeys.next()){
                Class<?> clazz = entity.getClass();
                List<Field> fields = ReflectionUtils.getFields(clazz, Id.class);
                for (Field field : fields) {
                    field.setAccessible(true);
                    Id annotation = field.getAnnotation(Id.class);
                    Class<?> keyType = field.getType();
                    Object key = generatedKeys.getObject(annotation.columnName(), keyType);
                    field.set(entity, key);
                }
               
            }

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir registro na tabela " + tableName,e);
        }
    }

    public  Optional<T> findById(K id){
        String tableName = getTableName();
        List<String> fieldsKey = getFieldsKeyName();
        try {

            Map<String, Object> columnMap = new LinkedHashMap<>();
            fieldsKey.forEach(f -> columnMap.put(f, id));
            PreparedStatement preparedStatement = new PreparedStatementBuilder(connection)
                                                        .forType(PreparedStatementBuilder.QueryType.SELECT)
                                                        .forTable(tableName)
                                                        .withColumnMap(columnMap)
                                                        .build();
            var resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                T obj = parse(resultSet);
                return Optional.ofNullable(obj);
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar tabela " + tableName,e);
        }
    }

    public List<T> findAll(){
        String tableName = getTableName();
        try {

            PreparedStatement preparedStatement = new PreparedStatementBuilder(connection)
                                                        .forType(PreparedStatementBuilder.QueryType.SELECT)
                                                        .forTable(tableName)
                                                        .build();

            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objectList = new ArrayList<>();
            while(resultSet.next()){
                T obj = parse(resultSet);
                objectList.add(obj);
            }
            return objectList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar tabela " + tableName,e);
        }
    }

    public void remove(K id){
        String tableName = getTableName();
        List<String> fieldsKey = getFieldsKeyName();
        try {

            Map<String, Object> columnMap = new LinkedHashMap<>();
            fieldsKey.forEach(f -> columnMap.put(f, id));

            PreparedStatement preparedStatement = new PreparedStatementBuilder(connection)
                                                    .forType(PreparedStatementBuilder.QueryType.DELETE)
                                                    .forTable(tableName)
                                                    .withColumnMap(columnMap)
                                                    .build();

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao remover registro da tabela " + tableName,e);
        }
    }

    protected abstract T parse(ResultSet resultSet);

    protected abstract Map<String, Object> getColumnMap(T entity);

    private String getTableName(){
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
        Table annotation = theType.getAnnotation(Table.class);
        return annotation.name();
    }

    private List<String> getFieldsKeyName(){
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
        List<Field> fields = ReflectionUtils.getFields(theType, Id.class);
        List<String> fieldKeysValue = new LinkedList<>();
        fields.stream()
                 .forEach(f -> fieldKeysValue.add(f.getAnnotation(Id.class).columnName()));
        return fieldKeysValue;
    }




}
