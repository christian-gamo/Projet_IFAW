package persistantData.dao;

import java.sql.Connection;
import java.util.List;
/**
 * Class that allows to retrieve all the connections established with the database
 * in order to get informations from DAO side queries
 *
 * @author Darlene
 *
 * @param <T> List of connections established with the database in the context of data manipulation in the database
 * (SQL queries)
 */
public abstract class DAO<T> {
	/** Connection attribute to connect to the database */
    private final Connection connection;
    /**
     * Public constructor of the DAO class
     * @param connection object that represents a connection established with the database
     */
    public DAO(Connection connection) {
        this.connection = connection;
    }
    /**
     * insert() method
     * <p>
     * Abstract method that can be redefined within the various DAO classes to insert entities of DAO object instances
     * Bill, User or Vehicle
     *
     * @param entity that we want to insert in the DB
     * @return 0 if if the insertion failed, the ID of the inserted object entity otherwise (Bill, User or Vehicle ID)
     */
    public abstract int insert(T entity);
    /**
     * getAll() method
     * <p>
     * Abstract method that can be redefined within the various DAO classes to retrieve lists of DAO object instances
     * @return  DAO object instance lists (vehicle lists, bills lists...)
     */
    public abstract List<T> getAll();

    /**
     * get() method
     * <p>
     * Abstract method that can be redefined within the various DAO classes to retrieve specific DAO object instances (by id)
     * @param id of the DAO object
     * @return the DAO object that corresponds to the given id (Bill, User or Vehicle ID)
     */
    public abstract T get(int id);

    /**
     * delete() method
     * <p>
     * Abstract method that can be redefined within the various DAO classes to delete a specific DAO object in function
     * of a given id (Bill, User or Vehicle ID)
     * @param id of the DAO object
     * @return true if the object has been successfully deleted, false otherwise
     */
    public abstract boolean delete(int id);

    /**
     * getConnection() method
     * <p>
     * Allows to retrieve the valid connection that has been established with the database within the DAO class
     * in order to not to pass by the DAO class to respect the Single responsibility principle of the SOLID model
     *
     * @return a connection object that represents a connection to the DB
     */
    public Connection getConnection() {
        return connection;
    }
}