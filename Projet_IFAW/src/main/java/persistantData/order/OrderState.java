package persistantData.order;
/**
 * Enumerated type OrderState which defines the different states of an order
 * (an order can be ongoing if a credit request has been made and payment has not been processed ;
 *  it can be validated if payment has been approved by credit request or directly by credit card payment;
 *  it can be shipped from the date of delivery of the order).
 *
 * @author Christian
 *
 */
public enum OrderState {

	/** Default state of the order. Awaiting payment and validation*/
	ONGOING("Ongoing"),
	/** Validated state of the order. Payment processed and awaiting shipment*/
	VALIDATED("Validated"),
	/** Final state of the order. Order has been shipped*/
	SHIPPED("Shipped");

	/**
	* Private string that represents the converted value of the enumerated type OrderState
	*/
	private String string ;

    /**
     * Private constructor of the enumerated type OrderState
     * It cannot be used outside of this class
     *
     * @param string the string associated to the OrderState
     */
    private OrderState(String string) {
         this.string = string ;
    }
     /**
      * Getter getString()
      * <p>
      * Allows to retrieve the string value associated to the enumerate
      * OrderState
      *
      * @return a string that corresponds
      * to the three different states of the order
      */
     public String getString() {
         return this.string ;
    }
}

