package br.edu.utfpr.libex7.adapters.persistence.entity.books;

import br.edu.utfpr.libex7.application.domain.books.BookType;

public enum  BookTypeData {

    NATIONAL(1, BookType.NATIONAL),
    INTERNATIONAL(2, BookType.INTERNATIONAL);

    private Integer code;
    private BookType bookType;

    BookTypeData(Integer code, BookType bookType){
        this.code = code;
        this.bookType = bookType;
    }

    public static BookTypeData fromValue(BookType bookType){
        for(BookTypeData bookTypeData : BookTypeData.values()){
            if(bookTypeData.getBookType().equals(bookType)){
                return bookTypeData;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public BookType getBookType() {
        return bookType;
    }
}
