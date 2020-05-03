package br.edu.utfpr.libex7.adapters.persistence.adapters.books;

import br.edu.utfpr.libex7.adapters.persistence.entity.books.BookEntity;
import br.edu.utfpr.libex7.adapters.persistence.mapper.books.BookPersistenceMapper;
import br.edu.utfpr.libex7.adapters.persistence.service.books.BookPersistenceService;
import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.ports.out.books.RemoveBookPort;
import br.edu.utfpr.libex7.application.ports.out.books.SaveBookPort;
import br.edu.utfpr.libex7.application.ports.out.books.SearchBookPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookPersistenceAdapter implements SaveBookPort, SearchBookPort, RemoveBookPort {

    private final BookPersistenceService service;
    private final BookPersistenceMapper mapper;

    @Override
    public void remove(Long id) {
        service.remove(id);
    }

    @Override
    public Book save(Book book) {
        BookEntity bookEntity = mapper.mapToEntity(book);
        service.save(bookEntity);
       return mapper.mapToDomain(bookEntity);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }
}
