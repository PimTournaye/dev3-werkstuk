package be.ehb.mct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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


    public void insert(String name, String lastName, int birthdate) {
        String sql = "INSERT INTO author(name,lastName,birthdate) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setDouble(3, birthdate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database test = new Database();
        test.insert("Pim", "Tournaye", 1977);
    }
}