 /**
 * @author Peter Park
 * @version 1.0
 * Date 2/3/2017
 * Park Class for Urban Parks
 */
package backend;
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
     * City that the park is in.
     */
    private String city;
    
    /*
     * Zipcode of the park.
     */
    private String zipcode;
    

    /*
     * Constructor for a Park object.
     */
    public Park(final ParkManager theManager, final String theName, final String theCity, final String theZipcode) {
        manager = theManager;
        name = theName;
        city = theCity;
        zipcode = theZipcode;
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
	 * Setter for the Park's city
	 */
    public void setCity(String theCity) {
		city = theCity;
    }
	
    /*
     * Getter for the Park's city
     */
	public String getCity() {
		return city;
	}
	
	/*
	 * Setter for the Park's zipcode
	 */
    public void setZipcode(String theZipcode) {
		zipcode = theZipcode;
    }
	
    /*
     * Getter for the Park's zipcode
     */
	public String getZipcode() {
		return zipcode;
	}
	
	/*
	 * Compares two Park objects
	 */
	public boolean compareTo(Park park1, Park park2) {
		return park1.name.equals(park2.name);
	}
}
