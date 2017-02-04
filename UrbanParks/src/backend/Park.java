
/**
 * @author Peter Park
 * @version 1.0
 * Date 2/3/2017
 * Park Class for Urban Parks
 */
public class Park {

	/*
	 * Park manager for the park.
	 */
   private ParkManager manager;
    
    /*
     * Name of the park.
     */
    private String name;
    
    /*
     * Address of the park.
     */
    private String address;
    
    /*
    private String city;
    private String zipcode;
    */

    /*
     * Constructor for a Park object.
     */
    public Park(final ParkManager theManager, final String theName, final String theAddress) {
        manager = theManager;
        name = theName;
        address = theAddress;
    }
	
    /*
     * Setter for the Park's Manager
     */
	public void setManager(ParkManager theManager) {
		manager = theManager;
    }
	
	/*
	 * Getter for the Park's Manager.
	 */
	public ParkManager getManager() {
		return manager;
	}
	
	/*
	 * Setter for the Park's name
	 */
	public void setName (String theName) {
		name = theName;
	}
	
	/*
	 * Getter for the Park's name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Setter for the Park's address
	 */
    public void setAddress(String theAddress) {
		address = theAddress;
    }
	
    /*
     * Getter for the Park's address
     */
	public String getAddress() {
		return address;
	}
	
	/*
	 * Compares two Park objects
	 */
	public boolean compareTo(Park park1, Park park2) {
		return park1.name.equals(park2.name);
	}
}
