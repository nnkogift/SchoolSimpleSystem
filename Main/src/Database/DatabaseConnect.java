package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
    static Connection conn;
    public static String connectionString = "\\Database\\SchoolDB";


    public  Connection connectDb() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:Database/SchoolDB","SuperAdmin","gian");

        } catch (Throwable e) {
            //will deal with this later
            System.out.println("Error connecting to database. Error log: " + e.getLocalizedMessage());
        }
        return conn;
    }
}


