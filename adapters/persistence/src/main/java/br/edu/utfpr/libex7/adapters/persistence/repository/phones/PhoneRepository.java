package br.edu.utfpr.libex7.adapters.persistence.repository.phones;

import br.edu.utfpr.libex7.adapters.persistence.entity.phones.PhoneEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.GenericRepository;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneRepository extends GenericRepository<PhoneEntity, Long> {


    public PhoneRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected PhoneEntity parse(ResultSet resultSet) {
        try{
            PhoneEntity phoneEntity = new PhoneEntity();
            Long phoneNumber = resultSet.getLong("NUMERO_TELEFONE");
            phoneEntity.setNumber(phoneNumber);
            return phoneEntity;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar informacoes de telefone",e);
        }
    }

    @Override
    protected Map<String, Object> getColumnMap(PhoneEntity entity) {
        Map<String, Object> columMap = new LinkedHashMap<>();
        columMap.put("CODIGO_USUARIO", entity.getUser().getId());
        columMap.put("NUMERO_TELEFONE", entity.getNumber());
        return columMap;
    }

    public List<PhoneEntity> findByUserId(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TELEFONE_USUARIO WHERE CODIGO_USUARIO = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<PhoneEntity> phoneEntities = new LinkedList<>();
            if(resultSet.next()){
                Long phoneNumber = resultSet.getLong("NUMERO_TELEFONE");
                PhoneEntity phoneEntity = new PhoneEntity();
                phoneEntity.setNumber(phoneNumber);
                phoneEntities.add(phoneEntity);
            }
            return phoneEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar telefones",e);
        }
    }
}
