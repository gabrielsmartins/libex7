package br.edu.utfpr.libex7.application.domain.books;


import br.edu.utfpr.libex7.application.domain.authors.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Book {

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
    private BookType type;

    @Getter
    private List<Author> authors = new ArrayList<>();

    public Book(Long id) {
        this.id = id;
    }

    public Integer addAuthor(Author author){
        this.authors.add(author);
        return this.authors.size();
    }
}
