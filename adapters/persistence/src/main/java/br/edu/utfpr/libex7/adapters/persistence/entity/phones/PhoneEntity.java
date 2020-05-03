package br.edu.utfpr.libex7.adapters.persistence.entity.phones;


import br.edu.utfpr.libex7.adapters.persistence.entity.users.UserEntity;
import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Id;
import br.edu.utfpr.libex7.adapters.persistence.util.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TELEFONE_USUARIO")
public class PhoneEntity {

    @Id
    private UserEntity user;

    @Id
    private Long number;
}
