package persistantData.order;
/**
 * Enumerated type PaymentMethod that defines the different payment methods
 * that can be used in a transaction. It is either possible to use
 * credit card payment or credit request.
 *
 * @author Christian
 *
 */
public enum PaymentMethod {
	/** Credit card payment method*/
	CREDIT_CARD("Credit Card"),
	/** Credit request payment method*/
	CREDIT_REQUEST("Credit Request");

	/**
	* Private string that represents the converted value of the enumerated type PaymentMethod
	*/
	private String string;

	/**
    * Private constructor of the enumerated type PaymentMethod
    * It cannot be used outside of this class
    *
    * @param string the string associated to the PaymentMethod
    */
	private PaymentMethod(String string) {
		this.string = string;
	}

	 /**
     * Getter getString()
     * <p>
     * Allows to retrieve the string value associated to the enumerate
     * PaymentMethod
     *
     * @return a string that corresponds
     * to the two available payment method
     */
	public String getString() {
		return this.string;
	}
}
