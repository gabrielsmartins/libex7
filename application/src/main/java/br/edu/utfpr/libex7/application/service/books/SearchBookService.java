package br.edu.utfpr.libex7.application.service.books;

import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.ports.in.books.SearchBookUseCase;
import br.edu.utfpr.libex7.application.ports.out.books.SearchBookPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SearchBookService implements SearchBookUseCase {

    private final SearchBookPort searchBookPort;

    @Override
    public Optional<Book> findById(Long id) {
        return searchBookPort.findById(id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return searchBookPort.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return searchBookPort.findByAuthorName(authorName);
    }

    @Override
    public List<Book> findAll() {
        return searchBookPort.findAll();
    }
}
