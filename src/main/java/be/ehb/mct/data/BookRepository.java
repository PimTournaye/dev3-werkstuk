package be.ehb.mct.data;

import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;
import be.ehb.mct.model.Genre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements BookInterface{

    @Override
    public List<Book> getBooks(String titleFilter) {
        if(titleFilter == null) titleFilter = "";
        titleFilter = titleFilter.trim();
        List<Book> books = new ArrayList<>();
        String SQL_SELECT_BOOKS = "SELECT * FROM books WHERE title LIKE CONCAT('%', ?, '%')";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_BOOKS)) {
            System.out.println("Trying to get books");

            stmt.setString(1, titleFilter);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next())
                    books.add(resultsSetBooks(rs));
            }

        } catch(SQLException e) {
            System.err.println(e);
        }
        return books;
    }

    @Override
    public Book getBook(int id) {
        Book book = null;
        final String SQL_SELECT_BOOK = "SELECT * FROM books WHERE id = ?";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_BOOK)) {

            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    book = resultsSetBooks(rs);
            }

        } catch(SQLException e) {
            System.err.println(e);
        }
        return book;
    }

    @Override
    public void removeBook(Book book) {
        final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id = ?";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_DELETE_BOOK)) {

            stmt.setInt(1, book.getIsbn());
            int affectedRows = stmt.executeUpdate();
            System.out.println(affectedRows);
        } catch(SQLException e) {
            System.err.println(e);
        }
    }

    @Override //TODO: THIS
    public void addBook(Book book) {
        final String SQL_INSERT_BOOK = "INSERT INTO books(`id`, `title` ,`price` ,`author_id` ,`totalPages` ,`lang` ,`genre`) VALUES (?,?,?,?,?,?,?);";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_INSERT_BOOK, PreparedStatement.RETURN_GENERATED_KEYS)) {

            Repositories.getAuthorRepository().addAuthor(book.getAuthor());
            bookPreparedStatement(book, stmt);
            stmt.executeUpdate();
            System.out.println("Insert done.");

            try(ResultSet rsKey = stmt.getGeneratedKeys()) {
                if (rsKey.next()) book.setId(rsKey.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void bookPreparedStatement(Book book, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, book.getIsbn());
        stmt.setString(2, book.getTitle());
        stmt.setDouble(3, book.getPrice());
        stmt.setInt(4, book.getAuthor());
        stmt.setInt(5, book.getTotalPages());
        stmt.setString(6, book.getLanguage());
        stmt.setString(7, book.getGenre());
    }

    private Book resultsSetBooks(ResultSet rs) throws SQLException {
        int isbn = rs.getInt("id");
        String title = rs.getString("title");
        double price = rs.getDouble("price");
        int author_id = rs.getInt("author_id");
        int totalPages = rs.getInt("totalPages");
        Genre genre = Genre.valueOf(rs.getString("genre"));
        String language = rs.getString("lang");

        Author author = Repositories.getAuthorRepository().getAuthor(rs.getInt("author_id"));
        int author_selectedID = author.getAuthor_id();

        Book book = new Book(isbn,title, price, author_id, totalPages, genre, language);
        book.setAuthor(author_selectedID);
        return book;
    }
}
