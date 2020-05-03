package br.edu.utfpr.libex7.application.service.books;

import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.ports.in.books.SaveBookUseCase;
import br.edu.utfpr.libex7.application.ports.out.books.SaveBookPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveBookService implements SaveBookUseCase {

    private final SaveBookPort saveBookPort;

    @Override
    public Book save(Book book) {
        return saveBookPort.save(book);
    }
}
