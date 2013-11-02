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

import java.util.logging.Level;
import java.util.logging.Logger;

import Items.Interface.IItem;
import Items.Items.Item;


public class Manager extends Item {

    private String url          = "jdbc:postgresql://localhost/library";
    private String user         = "root";
    private String password     = "labas";


    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Manager(String name) {
        super(name);

        if (false == loadDriver()) {
            System.exit(1);
        }
    }

    /**
     * Loads org.postgresql.Driver
     * @return TURE if driver was loaded succesfully
     */
    private boolean loadDriver()
    {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException exception) {
            System.out.println("Couldn't find driver class!");
            exception.printStackTrace();
            return false;
        }
        finally {
            return true;
        }
    }

    /**
     * Gets postgres connection
     * @return Connection postgres connection
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password) ;
        }
        catch (SQLException exception) {
            Logger logger = Logger.getLogger(Manager.class.getName());
            logger.log(Level.SEVERE, exception.getMessage(), exception);
            return null ;
        }
        finally {
            System.out.println("Successfully connected to Postgres Database");
        }

        return connection;
    }

    private ResultSet executeQuery(String query) {
        connection = getConnection();

        if (null != connection) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
            }
            catch (SQLException exception) {
                Logger logger = Logger.getLogger(Manager.class.getName());
                logger.log(Level.SEVERE, exception.getMessage(), exception);
                return null ;
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }

                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (SQLException exception) {
                    Logger lgr = Logger.getLogger(Manager.class.getName());
                    lgr.log(Level.WARNING, exception.getMessage(), exception);
                }
                finally {
                    if (resultSet != null) {
                        return resultSet;
                    }
                }
            }
        }

        return null;
    }

    protected final ResultSet fetchAll(String query) {
        return executeQuery(query);
    }

    protected final ResultSet fetchRow(String query) {
        ResultSet result = executeQuery(query);

        return result;
    }
}