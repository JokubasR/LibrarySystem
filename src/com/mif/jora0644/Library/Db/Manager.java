package Library.Db;
/**
 * @author Jokubas Ramanauskas
 * @date 2013-10-28
 * Class created to connect to a PostgreSQL database
 */

import Models.Resources.Role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;


public class Manager {

    private String url          = "jdbc:postgresql://localhost/library";
    private String user         = "root";
    private String password     = "labas";

    /**
     * Database table name
     */
    private String table;

    /**
     * Sets database table name
     * @param name database table name
     */
    public void setTable(String name) {
        this.table = name;
    }

    /**
     * Get database table name
     * @return
     */
    public String getTable() {
        return table;
    }

    /**
     * Loads driver
     * @param name table name
     */
    public Manager(String name) {
        if (false == loadDriver()) {
            System.exit(1);
        }

        setTable(name);
    }

    /**
     * Default constructor
     */
    public Manager() {
        if (false == loadDriver()) {
            System.exit(1);
        }

        if (getTable() == null) {
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

    private ResultSet fetch(HashMap<String, Object> filter) {
        Connection connection = getConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (null != connection) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT * FROM " + getTable() + " WHERE ");
                stringBuilder.append(filter.remove(0));

                for (Map.Entry<String, Object> entry : filter.entrySet()) {
                    stringBuilder.append(" AND ");
                    stringBuilder.append(String.format("%s = ?", entry.getKey()));
                }

                preparedStatement = connection.prepareStatement(stringBuilder.toString());

                int index = 0;
                for (Map.Entry<String, Object> entry : filter.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        preparedStatement.setString(++index, (String) entry.getValue());
                    } else if(entry.getValue() instanceof Integer) {
                        preparedStatement.setInt(++index, (Integer)entry.getValue());
                    } else if(entry.getValue() instanceof Role) {
                        preparedStatement.setString(++index, (String)entry.getValue().toString());
                    }
                }

                statement = connection.createStatement();
                resultSet = statement.executeQuery(preparedStatement.toString());
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

                    if (preparedStatement != null) {
                        preparedStatement.close();
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

    private final int executeQuery(HashMap<String, Object> data) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        if (null != connection) {
            try {
                String query = String.format("INSERT INTO %s(", getTable());

                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    query += String.format("%s,", entry.getKey());
                }
                query = query.substring(0, query.length() - 1) + ')';

                query += (String)" VALUES(";
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    if (entry.getValue() instanceof Role) {
                        query += (String)"CAST(? AS roles),";
                    } else {
                        query += (String)"?,";
                    }
                }
                query = query.substring(0, query.length() - 1) + ')';

                preparedStatement = connection.prepareStatement(query);

                int index = 0;
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        preparedStatement.setString(++index, (String) entry.getValue());
                    } else if(entry.getValue() instanceof Integer) {
                        preparedStatement.setInt(++index, (Integer)entry.getValue());
                    } else if(entry.getValue() instanceof Role) {
                        preparedStatement.setString(++index, (String)entry.getValue().toString());
                    }
                }

                System.out.println(preparedStatement.toString());

                Statement statement = connection.createStatement();
                statement.executeUpdate(preparedStatement.toString(), Statement.RETURN_GENERATED_KEYS);

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }

                return 0;
            }
            catch (SQLException exception) {
                Logger logger = Logger.getLogger(Manager.class.getName());
                logger.log(Level.SEVERE, exception.getMessage(), exception);
                return 0;
            }
            finally {

                try {
                    if (connection != null) {
                        connection.close();
                    }

                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
                catch (SQLException exception) {
                    Logger lgr = Logger.getLogger(Manager.class.getName());
                    lgr.log(Level.WARNING, exception.getMessage(), exception);
                }
            }
        }

        return 1;
    }

    /*protected final ResultSet fetchAll(String query) {
        return fetch(query);
    }
    */
    protected HashMap<String, Object> fetchRow(HashMap<String, Object> filter) {
        //ResultSet result = fetch(filter);

        return new HashMap<String, Object>();
    }

    protected final int insert(HashMap<String, Object> data) {
        return executeQuery(data);
    }
}