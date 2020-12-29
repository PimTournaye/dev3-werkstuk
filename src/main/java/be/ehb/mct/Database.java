package be.ehb.mct;

import java.sql.*;

/* Followed from the SQLite Java tutorial: https://www.sqlitetutorial.net/sqlite-java/ */

public class Database {
    /**
     * Connect to a sample database
     */

    private Connection connect() {
        Connection conn = null;
        //TODO: figure out the relative path to the file
        //String url = "jdbc:sqlite:database.db";
        String url = "jdbc:sqlite:C://sqlite/database.db";


        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void create(){
        String BookSQL = "CREATE TABLE books (title TEXT, price INTEGER, author INTEGER, isbn INTEGER UNIQUE, totalPages INTEGER, language TEXT, PRIMARY KEY(isbn), FOREIGN KEY(author) REFERENCES authors(author_id))";
        String AuthorSQL = "CREATE TABLE authors (isbn INTEGER, name TEXT, lastName TEXT, birthdate NUMERIC, author_id INTEGER, FOREIGN KEY(isbn) REFERENCES books(isbn), PRIMARY KEY(author_id))";
        String GenreSQL = "CREATE TABLE genres (isbn INTEGER, genre TEXT, FOREIGN KEY(isbn) REFERENCES books(isbn))";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(BookSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insert(String name, String lastName, int birthdate) {
        String sql = "INSERT INTO authors(name,lastName,birthdate) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setDouble(3, birthdate);
            pstmt.executeUpdate();
            System.out.println("Insert successful:" + name + lastName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database test = new Database();
        //test.insert("Neil", "Gaiman", 1960);
        test.create();
    }
}