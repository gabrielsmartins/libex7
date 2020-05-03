package br.edu.utfpr.libex7.adapters.persistence.repository.phones;

import br.edu.utfpr.libex7.adapters.persistence.entity.phones.PhoneEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.EmployeeEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.users.UserEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.ConnectionFactory;
import br.edu.utfpr.libex7.adapters.persistence.repository.users.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PhoneRepositoryTest {

    private EmployeeRepository userRepository;
    private PhoneRepository repository;

    @BeforeEach
    public void setup(){
        Connection connection = ConnectionFactory.getInstance();
        this.userRepository = new EmployeeRepository(connection);
        this.repository = new PhoneRepository(connection);
    }

    @Test
    @DisplayName("Given New Phone When Save Then Return Saved Phone")
    public void givenNewPhoneWhenSaveThenReturnSavedPhone(){
        Long id = 1L;
        String name = "Foo";
        LocalDate dob = LocalDate.of(1973, 05, 21);

        Long employeeNumber = 1L;
        EmployeeEntity userEntity = new EmployeeEntity(id, name, dob,employeeNumber);
        UserEntity savedUser = this.userRepository.save(userEntity);

        PhoneEntity phone = new PhoneEntity(savedUser, 12345L);
        PhoneEntity savedPhone = repository.save(phone);
        assertThat(savedPhone).isNotNull();
    }


    @Test
    @DisplayName("Given Existing User When Find By User Id Then Phone List")
    public void givenExistingUserWhenFindByUserIdThenReturnPhoneList(){
        Long id = 1L;
        String name = "Foo";
        LocalDate dob = LocalDate.of(1973, 05, 21);

        Long employeeNumber = 2L;
        EmployeeEntity userEntity = new EmployeeEntity(id, name, dob,employeeNumber );
        UserEntity savedUser = this.userRepository.save(userEntity);

        PhoneEntity phone = new PhoneEntity(savedUser, 12346L);
        repository.save(phone);
        List<PhoneEntity> phoneEntities = repository.findByUserId(savedUser.getId());
        assertFalse(phoneEntities.isEmpty());
    }

}
