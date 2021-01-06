package be.ehb.mct.data;

import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryInterface {

    List<Author> getAuthor(String nameFilter);

    List<Author> getAddresses(String nameFilter);

    Author getAuthor(int id);

    void removeAddress(Author author) throws SQLException;

    void addAddress(Author author);

    List<Book> getBook(String nameFilter);
    Book getBook(int id);
    void removeSBook(Book book);
    void addBook(Book book);
}
