package be.ehb.mct.data;

import be.ehb.mct.model.Book;

import java.util.List;

public interface BookInterface {
    List<Book> getBooks(String titleFilter);
    Book getBook(int isbn);
    void removeBook(Book book);
    void addBook(Book book);
}
