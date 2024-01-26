package persistantData.users;
/**
 * Class that models users with a company status in the app.
 * Since an company is by extent a user, the company class inherits from the user class.
 * What differentiates a simple user from a company is the boolean attribute isCompany.
 * If isCompany is 1 in the user table of the database, it means that the user is a company,
 * otherwise it is a simple user. Note that this is possible to create a new company directly
 * from the user interface by clicking on the "register" button on the right side of the navbar,
 * then on "register as a company". The company class also has an additional attribute compared
 * to the user class, the company name attribute.
 *
 * @author Darlene
 *
 */
public class Company extends User {

	/** Name of the company */
	private String name;

	/**
	 * First public constructor of the company class which allows to create a new company object,
	 * this constructor allows to take in parameter the user (company) ID.
	 *
	 * @param id_u corresponds to the user (company) ID
	 * @param email corresponds to the user (company) email
	 * @param password corresponds to the user (company) password
	 * @param lastName corresponds to the user (company) last name
	 * @param firstName corresponds to the user (company) first name
	 * @param name corresponds to the user (company) company name
	 */
	public Company(int id_u, String email, String password, String lastName, String firstName, String name) {
		super(id_u,email,password,lastName,firstName);
		this.name = name;
	}

	/**
	 * Second public constructor of the company class which allows to create a new company object,
	 * this constructor doesn't take the user (company) ID as a parameter.
	 *
	 * @param email corresponds to the user (company) email
	 * @param password corresponds to the user (company) password
	 * @param lastName corresponds to the user (company) last name
	 * @param firstName corresponds to the user (company) first name
	 * @param name corresponds to the user (company) company name
	 */
	public Company(String email, String password, String lastName, String firstName, String name) {
		super(email,password,lastName,firstName);
		this.name = name;
	}

	/**
	 * isCompany() method
	 * <p>
	 * This method allows to change the value of the boolean attribute isCompany to true
	 * in order to indicate that the user created is a company.
	 *
	 * @return true (which corresponds to Company = 1 in the database)
	 */
	@Override
	public boolean isCompany() {
		return true;
	}

	/**
	 * Getter getName()
	 * <p>
	 * Allows to get the company name of a given user (company)
	 *
	 * @return a string that represents the company name of a given user (company)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter setName()
	 * <p>
	 * Allows to set the company name of a given user (company)
	 *
	 * @param name, the company name of a given user (company)
	 */
	public void setName(String name) {
		this.name = name;
	}
}
