package br.edu.utfpr.libex7.adapters.persistence.service.books;

import br.edu.utfpr.libex7.adapters.persistence.entity.books.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookPersistenceService {
    BookEntity save(BookEntity book);
    void remove(Long id);
    Optional<BookEntity> findById(Long id);
    List<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthorName(String authorName);
    List<BookEntity> findAll();
}
