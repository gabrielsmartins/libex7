package br.edu.utfpr.libex7.adapters.persistence.repository.users;

import br.edu.utfpr.libex7.adapters.persistence.entity.users.EmployeeEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeRepository extends UserRepository<EmployeeEntity> {

    public EmployeeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public EmployeeEntity save(EmployeeEntity entity) {
        return super.save(entity);
    }

    @Override
    protected EmployeeEntity parse(ResultSet resultSet) {
        try{
            EmployeeEntity user = super.parse(resultSet);
            Long employeeNumber = resultSet.getLong("MATRICULA_SERVIDOR");
            user.setEmployeeNumber(employeeNumber);
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar informacoes de funcionario",e);
        }
    }

    @Override
    protected Map<String, Object> getColumnMap(EmployeeEntity employeeEntity) {
        Map<String, Object> columMap = new LinkedHashMap<>();
        columMap.put("CODIGO_USUARIO", employeeEntity.getId());
        columMap.put("MATRICULA_SERVIDOR", employeeEntity.getEmployeeNumber());
        return columMap;
    }

    @Override
    protected EmployeeEntity newInstance() {
        return new EmployeeEntity();
    }
}
