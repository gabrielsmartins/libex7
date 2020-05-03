package br.edu.utfpr.libex7.application.ports.in.books;

import br.edu.utfpr.libex7.application.domain.books.Book;

public interface SaveBookUseCase {
    Book save(Book book);
}
