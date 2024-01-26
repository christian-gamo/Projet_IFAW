package persistantData.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB_Connection class that allows to establish the connection with the MySQL database
 * Follows the Singleton design pattern
 * @author Darlene
 *
 */

public class DB_Connection {
	/**The URL (path) to the database*/
    private static final String URL = "jdbc:mysql://localhost/db_vehicles";
    /**The username which allows to identify oneself and to perform operations on the database*/
    private static final String username = "root";
    /**The password which allows to identify oneself and to perform operations on the database*/
    private static final String password = "root";
    /**An object which symbolizes the connection with the database */
    private static final Connection connection;

    /**
     * Method that allows to instantiate the connection at the start of the application
     */
    static {
        connection = getConnection();
    }
    /**
     * Empty private constructor of the DB_Connection class
     */
    private DB_Connection() {

    }
    /**
     * getInstance() method
     * <p>
     * Retrieves the current instance of the connection to the database
     * @return A connection object that represents the current instance of the connection to the database
     */
    public static Connection getInstance() {
        return connection;
    }
    /**
     * getConnection() method
     * <p>
     * This method allows you to identify yourself in the database using username and password which are private attributes
     *
     * @return a connection to the database validated by the JDBC driver manager or null if no connection to the database has been established
     */
    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, username,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}