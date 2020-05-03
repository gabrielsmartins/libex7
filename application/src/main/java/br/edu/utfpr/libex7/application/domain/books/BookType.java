package br.edu.utfpr.libex7.application.domain.books;

public enum BookType {

    NATIONAL("Nacional"),
    INTERNATIONAL("Internacional");

    private String description;

    BookType(String description){
        this.description = description;
    }

    public static BookType fromValue(String description){
        for(BookType bookType : BookType.values()){
            if(bookType.getDescription().equalsIgnoreCase(description)){
                return bookType;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
