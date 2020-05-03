package br.edu.utfpr.libex7.adapters.persistence.entity.users;

import br.edu.utfpr.libex7.adapters.persistence.entity.phones.PhoneEntity;
import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Id;
import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
@Table(name = "USUARIO")
public abstract class UserEntity {

    @Setter
    @Id(columnName = "CODIGO_USUARIO")
    protected Long id;
    @Setter
    protected String name;
    @Setter
    protected LocalDate dob;
    protected List<PhoneEntity> phones = new LinkedList<>();

    public UserEntity(Long id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public UserEntity() {
        super();
    }

    public Integer addPhone(PhoneEntity phone){
        phone.setUser(this);
        this.phones.add(phone);
        return this.phones.size();
    }

}
