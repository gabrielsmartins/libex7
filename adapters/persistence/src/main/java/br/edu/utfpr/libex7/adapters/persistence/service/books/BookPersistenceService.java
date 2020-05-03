package br.edu.utfpr.libex7.adapters.persistence.service.books;

import br.edu.utfpr.libex7.adapters.persistence.entity.books.BookEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.books.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookPersistenceService implements IBookPersistenceService  {

    private final BookRepository repository;

    @Override
    public BookEntity save(BookEntity book) {
        return repository.save(book);
    }

    @Override
    public void remove(Long id) {
        repository.remove(id);
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<BookEntity> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<BookEntity> findByAuthorName(String authorName) {
        return repository.findByAuthorName(authorName);
    }

    @Override
    public List<BookEntity> findAll() {
        return repository.findAll();
    }
}
