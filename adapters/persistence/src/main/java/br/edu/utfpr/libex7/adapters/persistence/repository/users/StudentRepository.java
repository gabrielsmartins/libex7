package br.edu.utfpr.libex7.adapters.persistence.repository.users;

import br.edu.utfpr.libex7.adapters.persistence.entity.users.StudentEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentRepository extends UserRepository<StudentEntity> {

    public StudentRepository(Connection connection) {
        super(connection);
    }


    @Override
    protected StudentEntity parse(ResultSet resultSet) {
        try{
            StudentEntity user = super.parse(resultSet);
            Long studentNumber = resultSet.getLong("REGISTRO_ALUNO");
            user.setStudentNumber(studentNumber);
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar informacoes de funcionario",e);
        }
    }

    @Override
    protected Map<String, Object> getColumnMap(StudentEntity studentEntity) {
        Map<String, Object> columMap = new LinkedHashMap<>();
        columMap.put("CODIGO_USUARIO", studentEntity.getId());
        columMap.put("REGISTRO_ALUNO", studentEntity.getStudentNumber());
        return columMap;
    }

    @Override
    protected StudentEntity newInstance() {
        return new StudentEntity();
    }
}
