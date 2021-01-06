package be.ehb.mct.data;

import be.ehb.mct.model.Author;

import java.sql.*;
import java.util.List;

public class AuthorRepository implements RepositoryInterface {
    @Override
    public List<Author> getAuthor(String nameFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Author> getAddresses(String nameFilter) {
        return null;
    }

    @Override
    public Author getAuthor(int id) {
        Author author = null;
        final String SQL_SELECT_AUTHOR = "SELECT * FROM author WHERE id = ?";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_AUTHOR)) {

            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    author = resultsSet2Author(rs);
            }

        } catch(SQLException e) {
            System.err.println(e);
        }
        return author;
    }

    @Override
    public void removeAuthor(Author author) {
        final String SQL_DELETE_AUTHOR = "DELETE FROM authors WHERE id = ?";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_DELETE_AUTHOR)) {

            stmt.setInt(1, author.getAuthor_id());
            int affectedRows = stmt.executeUpdate();
            //System.out.println(affectedRows);

        } catch(SQLException e) {
            System.err.println(e + "\n while trying to remove " + author);
        }
    }

    @Override
    public void addAuthor(Author author) {
        final String SQL_INSERT_AUTHOR = "INSERT INTO `authors` (`book_id`, `id`, `firstName`, `lastName`, `birthdate`) VALUES (?, ?, ?,?,?);";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_INSERT_AUTHOR, PreparedStatement.RETURN_GENERATED_KEYS)) {

            author2PreparedStatement(author, stmt);

            int affectedRows = stmt.executeUpdate();
            //System.out.println(affectedRows);
            try(ResultSet rsKey = stmt.getGeneratedKeys()) {
                if (rsKey.next()) author.setAuthor_id(rsKey.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void author2PreparedStatement(Author author, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1,author.getBook_id());
        stmt.setInt(2,author.getAuthor_id());
        stmt.setString(3, author.getFirstName());
        stmt.setString(4, author.getName());
        stmt.setObject( 5 , author.getBirthdate());

    }

    private Author resultsSet2Author(ResultSet rs) throws SQLException {
        int book_id = rs.getInt("book_id");
        int author_id = rs.getInt("number");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        Date birth = rs.getDate("birthdate");
        return new Author(book_id, author_id, firstName, lastName, birth);
    }
}



