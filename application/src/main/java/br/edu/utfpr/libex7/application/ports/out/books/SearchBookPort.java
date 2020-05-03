package br.edu.utfpr.libex7.application.ports.out.books;

import br.edu.utfpr.libex7.application.domain.books.Book;

import java.util.List;
import java.util.Optional;

public interface SearchBookPort {
    Optional<Book> findById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthorName(String authorName);
    List<Book> findAll();
}
