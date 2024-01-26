package persistantData.users;

import java.util.Base64;

/**
 * Super class that models users in the app.
 * By default, all instances created in connection with this class are simple users.
 * What differentiates a simple user from an administrator is the boolean attribute isAdmin.
 * If isAdmin is 1 in the user table of the database, it means that the user is an administrator,
 * otherwise it is a simple user. The same goes for companies, what differentiates a simple user from a company
 * is the boolean attribute isCompany. If isCompany is 1 in the user table of the database, it means that the
 * user is a company, otherwise it is a simple user. Note that this is possible to create a new user directly
 * from the user interface by clicking on the "register" button on the right side of the navbar,
 * then on "register as a simple user".
 *
 * @author Darlene
 *
 */
public class User {
	/** ID of the user */
	private int ID_U;
	/** Last name of the user */
	private String lastName;
	/** First name of the user */
	private String firstName;
	/** Email of the user */
	private String email;
	/** Password of the user */
	private String password;

	/**
	 * First public constructor of the user class which allows to create a new user object,
	 * this constructor allows to take in parameter the use ID.
	 *
	 * @param id_u corresponds to the user ID
	 * @param email corresponds to the user email
	 * @param password corresponds to the user password
	 * @param lastName corresponds to the user last name
	 * @param firstName corresponds to the user first name
	 */
	public User(int id_u, String email, String password, String lastName, String firstName) {
		this.setID_U(id_u);
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	/**
	 * Second public constructor of the user class which allows to create a new user object,
	 * this constructor doesn't take the user ID as a parameter.
	 *
	 * @param email corresponds to the user email
	 * @param password corresponds to the user password
	 * @param lastName corresponds to the user last name
	 * @param firstName corresponds to the user first name
	 */
	public User(String email, String password,String lastName, String firstName) {
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	/**
	 * Getter getID_U()
	 * <p>
	 * Allows to get the ID of a given user
	 *
	 * @return an integer, that represents the user ID
	 */
	public int getID_U() {
		return ID_U;
	}
    /**
     * Setter setID_U()
     * <p>
     * Allows to set the ID of a given user
     *
     * @param iD_U, the user ID
     */
	public void setID_U(int iD_U) {
		ID_U = iD_U;
	}
	/**
	 * Getter getEmail()
	 * <p>
	 * Allows to get the email of a given user
	 *
	 * @return a string, that represents the user name
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Getter getPassword()
	 * <p>
	 * Allows to get the password of a given user
	 *
	 * @return a string, that represents the user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter getLastName()
	 * <p>
	 * Allows to get the last name of a given user
	 *
	 * @return a string, that represents the user last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Getter getFirstName()
	 * <p>
	 * Allows to get the first name of a given user
	 *
	 * @return a string, that represents the user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * isAdmin() method
	 * <p>
	 * This method allows to change the value of the boolean attribute isAdmin to false
	 * in order to indicate that the user created is not an admin.
	 *
	 * @return false (which corresponds to Admin = 0 in the database)
	 */
	public boolean isAdmin() {
		return false;
	}
	/**
	 * isCompany() method
	 * <p>
	 * This method allows to change the value of the boolean attribute isAdmin to false
	 * in order to indicate that the user created is not an admin.
	 *
	 * @return false (which corresponds to Admin = 0 in the database)
	 */

	public boolean isCompany() {
		return false;
	}

	/**
	 * isStringOnlyAlphabet() method
	 * <p>
	 * This method allows to check if a string of characters in parameters contains only alphabetical characters or not.
	 * This function is useful to check if the first or last name contains no special characters or numbers.
	 *
	 * @param str which is a string such as the user first name or last name...
	 * @return true if the string in parameters contains only alphabetical characters, false otherwise
	 */
	public static boolean isStringOnlyAlphabet(String str){
        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
	 }

	/**
	 * data() method
	 * <p>
	 * This method allows to get more easily, at each index of the table, the information related to a given user.
	 *
	 * @return a user data array object[]
	 * [0] : the user email
	 * [1] : the user password
	 * [2] : the user last name
	 * [3] : the user first name
	 * [4] : a boolean value (1 or 0) whether the given user is an admin or not
	 * [5] : a boolean value (1 or 0) whether the given user is a company or not
	 */
	public Object[] data() {
		return new Object[]{ email, password,lastName,firstName,isAdmin(),isCompany()};
	}

	/**
	 * encryptPassword() method
	 * <p>
	 * This method returns an encrypted string
	 *
	 * @param pwd which is the non encrypted password string
	 * @return the encrypted password string
	 */
	public static String encryptPassword(String pwd) {
		return Base64.getEncoder().encodeToString(pwd.getBytes());
	}
}
