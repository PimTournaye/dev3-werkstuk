package be.ehb.mct.data;

import be.ehb.mct.model.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorInterface {

    List<Author> getAuthors(String nameFilter);

    Author getAuthor(int id);

    void removeAuthor(Author author) throws SQLException;


    void addAuthor(int author);

    void addAuthor(Author author);
}
