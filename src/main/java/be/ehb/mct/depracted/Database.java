package be.ehb.mct.depracted;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/* Followed from the SQLite Java tutorial: https://www.sqlitetutorial.net/sqlite-java/ */

public class Database {
    /**
     * Connect to a sample database
     */

    private Connection connect() {
        Connection conn = null;
        //String url = "jdbc:sqlite:src/main/java/be/ehb/mct/books.db";
        String url = "jdbc:sqlite:C://sqlite/database.db";


        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connect to database succesful.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void create(){

        //Author ding - FOREIGN KEY(author) REFERENCES authors(author_id))
        String AuthorSQL = "CREATE TABLE IF NOT EXISTS authors (book_id INTEGER, name TEXT, lastName TEXT, birthdate NUMERIC, FOREIGN KEY(book_id) REFERENCES books(isbn))";
        String GenreSQL = "CREATE TABLE IF NOT EXISTS genres (book_id INTEGER, genre TEXT, FOREIGN KEY(book_id) REFERENCES books(isbn))";
        String BookSQL = "CREATE TABLE IF NOT EXISTS books (isbn INTEGER, title TEXT, price INTEGER, totalPages INTEGER, language TEXT, PRIMARY KEY(isbn))";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            // create new tables
            stmt.execute(BookSQL);
            stmt.execute(GenreSQL);
            stmt.execute(AuthorSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


        public void insertCSV() throws IOException {

        String BookInsertsql = "INSERT INTO books(title,price,totalPages,language) VALUES(?,?,?,?)";

        String GenreInsertsql = "INSERT INTO genres(book_id, genre) VALUES(" + BookInsertsql + "`,?)";
        String AuthorInsertsql = "INSERT INTO authors(book_id, name, lastName, birthdate) VALUES (" + BookInsertsql + ",?,?,?)";
        try (Connection conn = this.connect();
             //TODO: Statement opkuisen, onderverdelen


           PreparedStatement pstmt = conn.prepareStatement(GenreInsertsql)) {

            String fileName = "src/main/java/be/ehb/mct/books.csv";
            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
            try(CSVReader reader = new CSVReaderBuilder(
                    new FileReader(fileName))
                    .withCSVParser(csvParser)   // custom CSV parser
                    .withSkipLines(1)           // skip the first line, header info
                    .build())
            {
                List<String[]> r = reader.readAll();
                r.forEach(x -> System.out.println(Arrays.toString(x)));
            }



            pstmt.executeUpdate();
           System.out.println("Insert successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Database test = new Database();
        //test.insert("Neil", "Gaiman", 1960);
        //test.connect();
        test.insertCSV();


    }
}