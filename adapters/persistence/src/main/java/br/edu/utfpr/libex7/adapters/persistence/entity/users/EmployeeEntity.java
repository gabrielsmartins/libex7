package br.edu.utfpr.libex7.adapters.persistence.entity.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity extends UserEntity {

    public EmployeeEntity(Long id, String name, LocalDate dob, Long employeeNumber) {
        super(id, name, dob);
        this.employeeNumber = employeeNumber;
    }

    private Long employeeNumber;

}
