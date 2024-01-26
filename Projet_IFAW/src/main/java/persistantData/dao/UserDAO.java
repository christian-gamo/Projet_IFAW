package persistantData.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistantData.users.Admin;
import persistantData.users.Company;
import persistantData.users.User;

/**
 * Class that links the user table of the database to the user class of the java application
 * As DAO stands for Data Access Object, this class function is to access User table (and also Company table) in the database
 * @author Yassine
 *
 */
public class UserDAO extends DAO<User> {

	/**
	 * Constructor of the UserDAO class which allows to create a new UserDAO object
	 */
	public UserDAO() {
		super(DB_Connection.getInstance());
	}
	/**
	 * insert() method
	 * <p>
	 * This method allows to insert a User who has the same attributes as the user given as a parameter.
	 * If it's a company, this method also updates the Company table.
	 *
	 * @param user which corresponds to the user which we want to put in the User table
	 *
	 * @return the id of the newly created user.
	 * Otherwise, returns 0 if user has not been created.
	 *
	 */
	@Override
	public int insert(User user) {
		String query = "INSERT INTO user (Email,Password,LastName,FirstName,Admin,Company) VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, User.encryptPassword(user.getPassword()));
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getFirstName());
			preparedStatement.setBoolean(5, user.isAdmin());
			preparedStatement.setBoolean(6, user.isCompany());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			int id_u = 0;
			if(resultSet.next())
				id_u=resultSet.getInt(1);

			if(user.isCompany()) {
				String query2 = "INSERT INTO company (ID_U, Name) VALUES (?,?)";
				Company company = (Company)user;

				PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
				preparedStatement2.setInt(1, id_u);
				preparedStatement2.setString(2, company.getName());
				preparedStatement2.executeUpdate();

			}
			return id_u;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * getAll() method
	 * <p>
	 * This method allows to retrieve every user in the User table.
	 * @return a list of User which contains every user in the User table.
	 */
	@Override
	public List<User> getAll() {
		String query = "SELECT * FROM user";
		User u = null;
		List<User> l = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			ResultSet set = preparedStatement.executeQuery();

			while (set.next())
			{
				int id_u = set.getInt("ID_U");
				String email = set.getString("Email");
				String password = set.getString("Password");
				String lastName  = set.getString("LastName");
				String firstName = set.getString("FirstName");
				Boolean isAdmin= set.getBoolean("Admin");
				Boolean isCompany = set.getBoolean("Company");
				if (isAdmin == true && !isCompany)
				{
					u = new Admin(id_u,email,password,lastName,firstName);
				}
				else if (!isAdmin && isCompany == true) {
					String query2 = "SELECT * FROM company WHERE ID_U = ?";
					PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
					preparedStatement2.setInt(1, id_u);
					ResultSet set2 = preparedStatement2.executeQuery();
					if (set2.next())
					{
						String companyName = set2.getString("Name");
						u = new Company(id_u,email,password,lastName,firstName,companyName);
					}
				}
				else
				{
					u = new User(id_u,email,password,lastName,firstName);
				}
				l.add(u);
			}

		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		return l;
	}

	/**
	 * get() method
	 * <p>
	 * This method allows to retrieve from the User table an user (if it does exist) who has the same id as the id given as a parameter.
	 *
	 * @param id, the user ID associated to the user
	 * @return User u, the user object associated to the Integer id.
	 */
	@Override
	public User get(int id) {
		String query = "SELECT * FROM user WHERE ID_U = ?";
		User u = null;
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);

			ResultSet set = preparedStatement.executeQuery();

			if (set.next()) {
				int id_u = set.getInt("ID_U");
				String email = set.getString("Email");
				String password = set.getString("Password");
				String lastName  = set.getString("LastName");
				String firstName = set.getString("FirstName");
				Boolean isAdmin= set.getBoolean("Admin");
				Boolean isCompany = set.getBoolean("Company");
				if (isAdmin == true && !isCompany)
				{
					u = new Admin(id_u,email,password,lastName,firstName);
				}
				else if (!isAdmin && isCompany == true) {
					String query2 = "SELECT * FROM company WHERE ID_U = ?";
					PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
					preparedStatement2.setInt(1, id_u);
					ResultSet set2 = preparedStatement2.executeQuery();
					if (set2.next())
					{
						String companyName = set2.getString("Name");
						u = new Company(id_u,email,password,lastName,firstName,companyName);
					}
				}
				else
				{
					u = new User(id_u,email,password,lastName,firstName);
				}
			}
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return u;
	}


	/**
	 * delete() method
	 * <p>
	 * This method allows to delete from the User table an user (if it does exist) who has the same id as the id given as a parameter.
	 *
	 * @param id, the user ID associated to the user
	 * @return boolean true if deletion has been successful, false otherwise
	 */
	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM user WHERE ID_U = ?";
		try
		{
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		return false;
	}

	/**
	 * getByLogin() method
	 * <p>
	 * This method allows to retrieve from the User table an user (if it does exist) who has the same login and password as those given as parameters.
	 *
	 * @param login, the Email address associated to the user
	 * @param pwd, the password associated to the user
	 * @return User u, the user associated to the Email login, to the password pwd.
	 */
	public User getByLogin(String login, String pwd) {
		String query = "SELECT * FROM user WHERE Email = ? AND Password = ?";
		User u = null;
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, User.encryptPassword(pwd));

			ResultSet set = preparedStatement.executeQuery();

			if (set.next()) {
				int id_u = set.getInt("ID_U");
				String email = set.getString("Email");
				String password = set.getString("Password");
				String lastName  = set.getString("LastName");
				String firstName = set.getString("FirstName");
				Boolean isAdmin= set.getBoolean("Admin");
				Boolean isCompany = set.getBoolean("Company");
				if (isAdmin == true && !isCompany)
				{
					u = new Admin(id_u,email,password,lastName,firstName);
				}
				else if (!isAdmin && isCompany == true) {
					String query2 = "SELECT * FROM company WHERE ID_U = ?";
					PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
					preparedStatement2.setInt(1, id_u);
					ResultSet set2 = preparedStatement2.executeQuery();
					if (set2.next())
					{
						String companyName = set2.getString("Name");
						u = new Company(id_u,email,password,lastName,firstName,companyName);
					}
				}
				else
				{
					u = new User(id_u,email,password,lastName,firstName);
				}
			}
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return u;
	}

}
