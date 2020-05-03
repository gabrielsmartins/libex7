package br.edu.utfpr.libex7.adapters.persistence.entity.users;

import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "ALUNO")
public class StudentEntity extends UserEntity{

    public StudentEntity(Long id, String name, LocalDate dob, Long studentNumber) {
        super(id, name, dob);
        this.studentNumber = studentNumber;
    }

    private Long studentNumber;

}
