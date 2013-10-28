package Library.Db;
/**
 * @author Jokubas Ramanauskas
 * @date 2013-10-28
 * Class created to connect to a PostgreSQL database
 */

import  java.sql.*;


public class Manager {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resulSet = null;

    public static void fetchAll(String query) {
        connection = DriverManager.getConnection();
    }
}