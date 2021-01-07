package be.ehb.mct.data;

import be.ehb.mct.model.Author;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class AuthorRepository implements AuthorInterface {

    @Override
    public List<Author> getAuthors(String nameFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Author getAuthor(int id) {
        Author author = null;
        final String SQL_SELECT_AUTHOR = "SELECT * FROM authors WHERE id = ?";
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
            System.out.println(affectedRows);

        } catch(SQLException e) {
            System.err.println(e + " while trying to remove " + author);
        }
    }

    @Override
    public void addAuthor(int author) {

    }


    @Override
    public void addAuthor(Author author) {
        final String SQL_INSERT_AUTHOR = "INSERT INTO `authors` (`id`, `firstName`, `lastName`, `birthdate`) VALUES (?,?,?,?);";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_INSERT_AUTHOR, PreparedStatement.RETURN_GENERATED_KEYS)) {

            author2PreparedStatement(author, stmt);
            stmt.execute();
            System.out.println("Insert done.");

            try(ResultSet rsKey = stmt.getGeneratedKeys()) {
                if (rsKey.next()) author.setAuthor_id(rsKey.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void author2PreparedStatement(Author author, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1,author.getAuthor_id());
        stmt.setString(2, author.getFirstName());
        stmt.setString(3, author.getName());
        stmt.setDate( 4 , Date.valueOf(author.getBirthdate()));

    }

    private Author resultsSet2Author(ResultSet rs) throws SQLException {
        int author_id = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        LocalDate birth = rs.getDate("birthdate").toLocalDate();
        return new Author(author_id, firstName, lastName, birth);
    }
}



