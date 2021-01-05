package be.ehb.mct.data;

import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;
import be.ehb.mct.model.Genre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    @Override
    public List<Book> getStudents(String nameFilter, boolean caseSensitive) {
        if(nameFilter == null) nameFilter = "";
        nameFilter = nameFilter.trim();
        List<Book> books = new ArrayList<>();
        String SQL_SELECT_STUDENTS = "select * from students where concat(first_name, ' ', last_name) like concat('%',?,'%')";
        if(caseSensitive) SQL_SELECT_STUDENTS = "select * from students where concat(first_name, ' ', last_name) like binary concat('%',?,'%')";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_STUDENTS)) {

            stmt.setString(1, nameFilter);

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
    public Book getStudent(int id) {
        Book book = null;
        final String SQL_SELECT_STUDENT = "select * from students where id = ?";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_STUDENT)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    book = resultsSetBook(rs);
            }

        } catch(SQLException e) {
            System.err.println(e);
        }
        return book;
    }

    @Override
    public void removeStudent(Book book) {
        final String SQL_DELETE_STUDENT = "DELETE FROM books WHERE id = ?";
        try(Connection con = SQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_DELETE_STUDENT)) {

            stmt.setInt(1, book.getIsbn());
            int affectedRows = stmt.executeUpdate();
            //System.out.println(affectedRows);

        } catch(SQLException e) {
            System.err.println(e);
        }
    }

    @Override //TODO: THIS
    public void addStudent(Book book) {
        final String SQL_INSERT_STUDENT = "insert into students(first_name, last_name, birthday, gender, address) values(?, ?, ?, ?, ?)";
        try(Connection con = SQLConnection.getConnection();
            //PreparedStatement stmt = con.prepareStatement(SQL_INSERT_STUDENT, new String[] {"id", "created"})) {
            PreparedStatement stmt = con.prepareStatement(SQL_INSERT_STUDENT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            if(book.getAddress() != null)
                Repositories.getAddressRepository().addAddress(book.getAddress());
            student2PreparedStatement(student, stmt);

            int affectedRows = stmt.executeUpdate();
            //System.out.println(affectedRows);
            try(ResultSet rsKey = stmt.getGeneratedKeys()) {
                if (rsKey.next()) student.setId(rsKey.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void bookPreparedStatement(Book book, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, book.getTitle());
        stmt.setDouble(2, book.getPrice());
        stmt.setObject(3, book.getAuthor());
        stmt.setInt(4, book.getIsbn());
        stmt.setInt(5, book.getTotalPages());
        stmt.setString(6, book.getGenres().toString());
        stmt.setString(7, book.getLanguage());
    }

    private Book resultsSetBooks(ResultSet rs) throws SQLException {
        String title = rs.getString("first_name");
        double price = rs.getDouble("last_name");
        //Author author = (Author) rs.getObject("birthday");
        int isbn = rs.getInt("id");
        int totalPages = rs.getInt("totalPages");
        Genre genre = (Genre) rs.getObject("genre");
        String language = rs.getString("language");

        Author author = Repositories.getAddressRepository().getAddress(rs.getInt("address"));
        Book book = new Book(title, price, author, isbn, totalPages, genre, language);
        student.setAddress(address);
        return student;
    }
}
