 /**
 * @author Peter Park
 * @version 1.0
 * Date 2/3/2017
 * Park Class for Urban Parks
 */
package backend;
public class Park {

	/*
	 * The park manager object for the park
	 */
   private ParkManager manager;
    
    /*
     * The name of the park
     */
    private String name;
    
    /*
     * The city of the park
     */
    private String city;
    
    /*
     * The zipcode of the park
     */
    private String zipcode;
    

    /*
     * The constructor for the Park object
     */
    public Park(final ParkManager theManager, final String theName, final String theCity, final String theZipcode) {
        manager = theManager;
        name = theName;
        city = theCity;
        zipcode = theZipcode;
    }
	
    /*
     * Setter for the park manager
     */
	public void setManager(ParkManager theManager) {
    	if (theManager instanceof ParkManager) {
            manager = theManager;
    	} else {
    		throw new IllegalArgumentException("Parameter is not of type ParkManager");
    	}
    }
	
	/*
	 * Getter for the park manager
	 */
	public ParkManager getManager() {
		return manager;
	}
	
	/*
	 * Setter for the park's name
	 */
	public void setName (String theName) {
    	if (theName instanceof String) {
            name = theName;
    	} else {
    		throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	
	/*
	 * Getter for the park's name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Setter for the park's city
	 */
    public void setCity(String theCity) {
    	if (theCity instanceof String) {
            city = theCity;
    	} else {
            throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	
    /*
     * Getter for the park's city
     */
	public String getCity() {
		return city;
	}
	
	/*
	 * Setter for the park's zipcode
	 */
    public void setZipcode(String theZipcode) {
    	if (theZipcode instanceof String) {
            zipcode = theZipcode;
    	} else {
            throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	
    /*
     * Getter for the park's zipcode
     */
	public String getZipcode() {
		return zipcode;
	}
	
	/*
	 * Compares two park objects for equivalence
	 */
	public boolean equals(Park thePark) {
		return name.equals(thePark.name) && city.equals(thePark.city) &&
				zipcode.equals(thePark.zipcode);
	}
}
