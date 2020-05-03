package br.edu.utfpr.libex7.adapters.persistence.entity.authors;


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
@Table(name = "AUTOR")
public class AuthorEntity {

    @Id(columnName="CODIGO_AUTOR")
    private Long id;
    private String name;
}
