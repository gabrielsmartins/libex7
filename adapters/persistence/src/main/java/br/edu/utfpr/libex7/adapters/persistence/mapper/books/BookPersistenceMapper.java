package br.edu.utfpr.libex7.adapters.persistence.mapper.books;

import br.edu.utfpr.libex7.adapters.persistence.entity.books.BookEntity;
import br.edu.utfpr.libex7.adapters.persistence.entity.books.BookTypeData;
import br.edu.utfpr.libex7.adapters.persistence.mapper.AbstractMapper;
import br.edu.utfpr.libex7.application.domain.books.Book;
import br.edu.utfpr.libex7.application.domain.books.BookType;

public class BookPersistenceMapper implements AbstractMapper<Book, BookEntity> {

    @Override
    public Book mapToDomain(BookEntity bookEntity) {
        Long id = bookEntity.getId();
        String title = bookEntity.getTitle();
        BookTypeData type = bookEntity.getType();
        Integer year = bookEntity.getYear();
        Book book = new Book(id);
        book.setTitle(title);
        book.setType(type.getBookType());
        book.setYear(year);
        return book;
    }

    @Override
    public BookEntity mapToEntity(Book book) {
        Long id = book.getId();
        String title = book.getTitle();
        BookType type = book.getType();
        Integer year = book.getYear();
        BookEntity bookEntity = new BookEntity(id);
        bookEntity.setTitle(title);
        bookEntity.setType(BookTypeData.fromValue(type));
        bookEntity.setYear(year);
        return bookEntity;
    }
}
