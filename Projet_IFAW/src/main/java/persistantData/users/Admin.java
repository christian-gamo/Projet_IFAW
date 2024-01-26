package persistantData.users;
/**
 * Class that models users with an administrator status in the app.
 * Since an administrator is by extent a user, the admin class inherits from the user class.
 * What differentiates a simple user from an administrator is the boolean attribute isAdmin.
 * If isAdmin is 1 in the user table of the database, it means that the user is an administrator,
 * otherwise it is a simple user. Note that it is impossible to instantiate a new admin directly
 * from the user interface, for security reasons a user can only get admin rights from the database.
 *
 * @author Darlene
 *
 */
public class Admin extends User {

	/**
	 * First public constructor of the admin class which allows to create a new admin object,
	 * this constructor allows to take in parameter the user (admin) ID.
	 *
	 * @param id_u corresponds to the user (admin) ID
	 * @param email corresponds to the user (admin) email
	 * @param password corresponds to the user (admin) password
	 * @param lastName corresponds to the user (admin) last name
	 * @param firstName corresponds to the user (admin) first name
	 */
	public Admin(int id_u,String email, String password, String lastName, String firstName) {
		super(id_u,email,password,lastName,firstName);
	}

	/**
	 * Second public constructor of the admin class which allows to create a new admin object,
	 * this constructor doesn't take the user (admin) ID as a parameter.
	 *
	 * @param email corresponds to the user (admin) email
	 * @param password corresponds to the user (admin) password
	 * @param lastName corresponds to the user (admin) last name
	 * @param firstName corresponds to the user (admin) first name
	 */
	public Admin(String email, String password, String lastName, String firstName) {
		super(email,password,lastName,firstName);
	}

	/**
	 * isAdmin() method
	 * <p>
	 * This method allows to change the value of the boolean attribute isAdmin to true
	 * in order to indicate that the user created is an administrator.
	 *
	 * @return true (which corresponds to Admin = 1 in the database)
	 */
	@Override
	public boolean isAdmin() {
	        return true;
	    }
	}
