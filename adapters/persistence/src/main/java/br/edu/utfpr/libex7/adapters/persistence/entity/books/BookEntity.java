package br.edu.utfpr.libex7.adapters.persistence.entity.books;


import br.edu.utfpr.libex7.adapters.persistence.entity.authors.AuthorEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BookEntity {

    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Integer year;

    @Getter
    @Setter
    private BookTypeData type;

    @Getter
    private List<AuthorEntity> authors = new ArrayList<>();

    public BookEntity(Long id) {
        this.id = id;
    }

    public Integer addAuthor(AuthorEntity author){
        this.authors.add(author);
        return this.authors.size();
    }
}
