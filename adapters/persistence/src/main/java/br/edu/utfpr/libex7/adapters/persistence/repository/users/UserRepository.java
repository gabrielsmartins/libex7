package br.edu.utfpr.libex7.adapters.persistence.repository.users;

import br.edu.utfpr.libex7.adapters.persistence.entity.phones.PhoneEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.EmployeeEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.StudentEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.UserEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.GenericRepository;
import br.edu.utfpr.libex7.adapters.persistence.repository.phones.PhoneRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class UserRepository<T extends UserEntity> extends GenericRepository<T, Long> {

    private final PhoneRepository phoneRepository;

    public UserRepository(Connection connection) {
        super(connection);
        this.phoneRepository = new PhoneRepository(connection);
    }


    @Override
    public List<T> findAll() {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM USUARIO U " +
                                                                   "LEFT JOIN SERVIDOR S " +
                                                                             "ON U.CODIGO_USUARIO = S.CODIGO_USUARIO " +
                                                                   "LEFT JOIN ALUNO A " +
                                                                             "ON U.CODIGO_USUARIO = A.CODIGO_USUARIO");
            var resultSet = preparedStatement.executeQuery();

            List<T> users = new ArrayList<>();
            while(resultSet.next()){
                T user = parse(resultSet);
                if(user instanceof EmployeeEntity){
                    Long employeeNumber = resultSet.getLong("MATRICULA_SERVIDOR");
                    ((EmployeeEntity) user).setEmployeeNumber(employeeNumber);
                }else if(user instanceof StudentEntity){
                    Long studentNumber = resultSet.getLong("REGISTRO_ALUNO");
                    ((StudentEntity) user).setStudentNumber(studentNumber);
                }
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar usuarios",e);
        }
    }

    @Override
    protected Map<String, Object> getColumnMap(UserEntity userEntity) {
        Map<String, Object> columMap = new LinkedHashMap<>();
        columMap.put("NOME_USUARIO", userEntity.getName());
        columMap.put("DATA_NASCIMENTO_USUARIO", userEntity.getDob());
        return columMap;
    }

    @Override
    protected T parse(ResultSet resultSet) {
        try{
            T user = this.newInstance();
            Long userId = resultSet.getLong("CODIGO_USUARIO");
            String userName = resultSet.getString("NOME_USUARIO");
            LocalDate dob = resultSet.getDate("DATA_NASCIMENTO_USUARIO").toLocalDate();
            user.setId(userId);
            user.setName(userName);
            user.setDob(dob);
            List<PhoneEntity> phoneEntities = phoneRepository.findByUserId(user.getId());
            phoneEntities.forEach(ph -> user.addPhone(ph));
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar informacoes de usuario",e);
        }
    }

    protected abstract T newInstance();


}
