package br.edu.utfpr.libex7.adapters.persistence.repository.users;

import br.edu.utfpr.libex7.adapters.persistence.entity.phones.PhoneEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.StudentEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.UserEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentRepositoryTest {

    private UserRepository repository;

    @BeforeEach
    public void setup(){
        Connection connection = ConnectionFactory.getInstance();
        this.repository = new StudentRepository(connection);
    }

    @Test
    @DisplayName("Given New User When Save Then Return Saved User")
    public void givenNewUserWhenSaveThenReturnSavedUser(){
        Long id = 1L;
        String name = "Foo";
        LocalDate dob = LocalDate.of(1973, 05, 21);
        Long studentNumber = 1L;
        StudentEntity userEntity = new StudentEntity(id, name, dob,studentNumber );
        PhoneEntity phone = new PhoneEntity(userEntity, 99989898L);
        userEntity.addPhone(phone);
        UserEntity savedUser = this.repository.save(userEntity);
        assertThat(savedUser.getId()).isNotNull();
    }


    @Test
    @DisplayName("Given Existing User When Find By Id Then Return User")
    public void givenExistingUserWhenFindByIdThenReturnUser(){
        Long id = 1L;
        String name = "Foo";
        LocalDate dob = LocalDate.of(1973, 05, 21);
        Long studentNumber = 2L;
        StudentEntity userEntity = new StudentEntity(id, name, dob,studentNumber );
        PhoneEntity phone = new PhoneEntity(userEntity, 99989898L);
        userEntity.addPhone(phone);
        UserEntity savedUser = this.repository.save(userEntity);

        Optional<UserEntity> optionalUserEntity = repository.findById(savedUser.getId());
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    @DisplayName("Given Existing User When Remove By Id Then Do Nothing")
    public void givenExistingUserWhenRemoveThenDoNothing(){
        Long id = 1L;
        String name = "Foo";
        LocalDate dob = LocalDate.of(1973, 05, 21);
        Long studentNumber = 3L;
        StudentEntity userEntity = new StudentEntity(id, name, dob,studentNumber );
        PhoneEntity phone = new PhoneEntity(userEntity, 99989898L);
        userEntity.addPhone(phone);
        UserEntity savedUser = this.repository.save(userEntity);
        repository.remove(savedUser.getId());
    }


    @Test
    @DisplayName("Given Existing Users When Find All Then Return User List")
    public void givenExistingUsersWhenFindAllThenReturnUserList(){
        Long id = 1L;
        String name = "Foo";
        LocalDate dob = LocalDate.of(1973, 05, 21);
        Long studentNumber = 4L;
        StudentEntity userEntity = new StudentEntity(id, name, dob,studentNumber );
        PhoneEntity phone = new PhoneEntity(userEntity, 99989898L);
        userEntity.addPhone(phone);
        this.repository.save(userEntity);

        List<UserEntity> userEntities = repository.findAll();
        assertFalse(userEntities.isEmpty());
    }
}
