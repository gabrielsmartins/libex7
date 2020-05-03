package br.edu.utfpr.libex7.application.ports.out.books;

import br.edu.utfpr.libex7.application.domain.books.Book;

public interface SaveBookPort {
    Book save(Book book);
}
