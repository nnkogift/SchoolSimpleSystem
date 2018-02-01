package Database;

import java.sql.*;

public class DatabaseConnect {
    static Connection conn;
    public static String connectionString = "\\Database\\SchoolDB";


    public static Connection connectDb() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:Database/SchoolDB","SuperAdmin","gian");

        } catch (Throwable e) {
            //will deal with this later
            System.out.println("Error connecting to database. Error log: " + e.getLocalizedMessage());
        }
        return conn;
    }

    public static ResultSet connectAndAccessDB(String query) throws SQLException {
        DatabaseConnect connect = new DatabaseConnect();
        Connection connection = connect.connectDb();
            connection.createStatement().executeUpdate("SET DATABASE SQL SYNTAX MYS TRUE");
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.clearParameters();
        return  preparedStatement.executeQuery();
    }
    public static void closeConnection(ResultSet resultSet){
        //method to close the connection
        try {
            resultSet.close();
        }
        catch (Exception e){
            System.out.println("resultset failed to close");
        }
        
    }
    public static int writeDB(String query){
        int rowCount = 0;
        try {
            conn = connectDb();
            conn.createStatement().executeUpdate("SET DATABASE SQL SYNTAX MYS TRUE");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.clearParameters();
             rowCount = preparedStatement.executeUpdate();

        }
        catch (Exception e){
            System.out.println("Houston we have a problem! " + e.getLocalizedMessage());
        }
        return rowCount;
    }
}


