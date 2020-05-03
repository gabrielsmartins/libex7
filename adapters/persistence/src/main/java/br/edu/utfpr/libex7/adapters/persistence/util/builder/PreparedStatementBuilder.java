package br.edu.utfpr.libex7.adapters.persistence.util.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreparedStatementBuilder {


    private StringBuilder query = new StringBuilder();
    private String table;
    private QueryType type;
    private Map<String, Object> columnMap = new LinkedHashMap<>();
    private Connection connection;

    public PreparedStatementBuilder(Connection connection){
        this.connection = connection;
    }

    public PreparedStatementBuilder forType(QueryType type){
        this.type = type;
        return this;
    }

    public PreparedStatementBuilder forTable(String table){
        this.table = table + " ";
        return this;
    }

    public PreparedStatementBuilder withColumnMap(Map<String, Object> columnMap){
        this.columnMap = columnMap;
        return this;
    }

    public PreparedStatement build() {
        try {

            switch (type){
                case INSERT:
                    return createInsertPreparedStatement();

                case UPDATE:
                    return createUpdatePreparedStatement();

                case SELECT:
                    return createSelectPreparedStatement();

                case DELETE:
                    return createDeletePreparedStatement();

                default:
                    throw new IllegalArgumentException("Tipo de Query não informado");
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao gerar query",e);
        }
    }

    private PreparedStatement createDeletePreparedStatement() throws SQLException {
        query.append(type.command)
                .append(table);

        if(columnMap.isEmpty()) {
            return connection.prepareStatement(query.toString());
        }
            query.append("WHERE ");

            for (Map.Entry<String, Object> entry : columnMap.entrySet()) {
                query.append(entry.getKey() + "=? AND ");
            }

            query.delete(query.toString().length() -5, query.toString().length()-1);

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

        int index = 0;
        for(Map.Entry<String, Object> entry : columnMap.entrySet()){
            index++;
            preparedStatement.setObject(index, entry.getValue());
        }

        return preparedStatement;

    }

    private PreparedStatement createSelectPreparedStatement() throws SQLException {
        query.append(type.command)
                .append(table);

        if(columnMap.isEmpty()){
            return connection.prepareStatement(query.toString());
        }



        query.append("WHERE ");

        for (Map.Entry<String, Object> entry : columnMap.entrySet()) {
            query.append(entry.getKey() + "=? AND ");
        }

        query.delete(query.toString().length() -5, query.toString().length()-1);

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

        int index = 0;
        for(Map.Entry<String, Object> entry : columnMap.entrySet()){
            index++;
            preparedStatement.setObject(index, entry.getValue());
        }

        return preparedStatement;

    }

    private PreparedStatement createUpdatePreparedStatement() throws SQLException {
        query.append(type.command)
                .append(table);

        query.append("SET (" + createColumnNames() + ") ")
                .append("VALUES(" + createColumnParameters() + ")");
        return connection.prepareStatement(query.toString());
    }

    private PreparedStatement createInsertPreparedStatement() throws SQLException {
        query.append(type.command)
                .append(table);
        query.append("(" + createColumnNames() + ") ")
                .append("VALUES(" + createColumnParameters() + ")");
        PreparedStatement preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);

        int index = 0;
        for(Map.Entry<String, Object> entry : columnMap.entrySet()){
            index++;
            preparedStatement.setObject(index, entry.getValue());
        }

        return preparedStatement;
    }

    private String createColumnNames(){
        StringBuilder columnNames = new StringBuilder();
        columnMap.entrySet()
                .stream()
                .forEach(entry -> columnNames.append(entry.getKey()).append(","));
        columnNames.deleteCharAt(columnNames.toString().length()-1);
        return columnNames.toString();
    }

    private String createColumnParameters(){
        StringBuilder columnParameters = new StringBuilder();
        columnMap.entrySet()
                .stream()
                .forEach(entry -> columnParameters.append("?").append(","));
        columnParameters.deleteCharAt(columnParameters.toString().length()-1);
        return columnParameters.toString();
    }



    public enum QueryType{
        INSERT("INSERT INTO "),
        SELECT("SELECT * FROM "),
        UPDATE("UPDATE "),
        DELETE("DELETE FROM ");

        private String command;

        QueryType(String command){
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }

}
