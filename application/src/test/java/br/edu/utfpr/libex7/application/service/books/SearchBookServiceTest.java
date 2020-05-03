package br.edu.utfpr.libex7.application.service.books;

import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.ports.out.books.SearchBookPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchBookServiceTest {

    private SearchBookPort port;
    private SearchBookService service;

    @BeforeEach
    public void setup(){
        this.port = mock(SearchBookPort.class);
        this.service = new SearchBookService(port);
    }

    @Test
    @DisplayName("Given Existing Book When Find By Id Then Return Book")
    public void giveExistingBookWhenFindByIdThenReturnBook(){
        Book book = new Book();
        when(port.findById(anyLong())).thenReturn(Optional.ofNullable(book));
        Optional<Book> optionalBook = this.service.findById(1L);
        assertTrue(optionalBook.isPresent());
    }


    @Test
    @DisplayName("Given Existing Books When Find All Then Return Book List")
    public void giveExistingBooksWhenFindAllThenReturnBook(){
        Book book = new Book();
        when(port.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = this.service.findAll();
        assertFalse(books.isEmpty());
    }

    @Test
    @DisplayName("Given Existing Books When Find By Title Then Return Book List")
    public void giveExistingBooksWhenFindByTitleThenReturnBook(){
        Book book = new Book();
        when(port.findByTitle(anyString())).thenReturn(Arrays.asList(book));
        List<Book> books = this.service.findByTitle("Foo");
        assertFalse(books.isEmpty());
    }

    @Test
    @DisplayName("Given Existing Books When Find By Author Name Then Return Book List")
    public void giveExistingBooksWhenFindByAuthorNameThenReturnBook(){
        Book book = new Book();
        when(port.findByAuthorName(anyString())).thenReturn(Arrays.asList(book));
        List<Book> books = this.service.findByAuthorName("Bar");
        assertFalse(books.isEmpty());
    }
}
