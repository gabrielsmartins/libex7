package br.edu.utfpr.libex7.application.service.books;

import br.edu.utfpr.libex7.application.domain.authors.Author;
import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.domain.books.BookType;
import br.edu.utfpr.libex7.application.ports.out.books.SaveBookPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveBookServiceTest {

    private SaveBookPort port;
    private SaveBookService service;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveBookPort.class);
        this.service = new SaveBookService(port);
    }

    @Test
    @DisplayName("Given New Book When Save Then Return Saved Book")
    public void givenNewBookWhenSaveThenReturnSavedBook(){
        Book book = new Book();
        book.setTitle("Foo Titlte");
        book.setType(BookType.fromValue("Nacional"));
        book.setYear(1994);
        book.addAuthor(new Author());
        when(port.save(any(Book.class))).thenReturn(book);
        Book savedBook = this.service.save(book);
        assertThat(savedBook.getId()).isEqualTo(book.getId());
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getYear()).isEqualTo(book.getYear());
        assertThat(savedBook.getType()).isEqualTo(book.getType());
        assertThat(savedBook.getAuthors()).isEqualTo(book.getAuthors());
    }
}
