package Library.Db;
/**
 * @author Jokubas Ramanauskas
 * @date 2013-10-28
 * Class created to connect to a PostgreSQL database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Items.Interface.IItem;
import Items.Items.Item;


public class Manager extends Item {

    private String url          = "jdbc:postgresql://localhost:5432/library";
    private String user         = "root";
    private String password     = "labas";


    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Manager(String name) {
        super(name);
    }

    public ResultSet fetchAll(String query) {
        if (this instanceof IItem) {
            try {
                connection = DriverManager.getConnection(url, user, password);

                statement = connection.createStatement();

                resultSet = statement.executeQuery(query);
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }

                    if (statement != null) {
                        statement.close();
                    }

                    if (resultSet != null) {
                        return resultSet;
                    }
                }
                catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return null;
    }
}