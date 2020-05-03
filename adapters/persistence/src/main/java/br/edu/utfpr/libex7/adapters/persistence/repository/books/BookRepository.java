package br.edu.utfpr.libex7.adapters.persistence.repository.books;

import br.edu.utfpr.libex7.adapters.persistence.entity.books.BookEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.GenericRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class BookRepository extends GenericRepository<BookEntity, Long> {


    public BookRepository(Connection connection) {
        super(connection);
    }


    @Override
    protected BookEntity parse(ResultSet resultSet) {
        return null;
    }

    @Override
    protected Map<String, Object> getColumnMap(BookEntity entity) {
        return null;
    }


    public List<BookEntity> findByTitle(String title) {
        return null;
    }

    public List<BookEntity> findByAuthorName(String authorName) {
        return null;
    }
}
