package be.ehb.mct.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bookshop";
    private static final String USER = "root";
    private static final String PWD = "";
    private static final String SQL_FILE = "bookshop.sql";

    private SQLConnection(){

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
        
    }
}
