 /**
 * @author Peter Park
 * @version 1.0
 * Date 2/3/2017
 * Park Class for Urban Parks
 */
package backend;
public class Park {
    /**
     * The park manager object for the park.
     */
    private ParkManager manager;
    
    /**
     * The name of the park.
     */
    private String name;
    
    /**
     * The city of the park.
     */
    private String city;

    /**
     * The state of the park.
     */
    private String state;

    /**
     * The zipcode of the park.
     */
    private String zipcode;
    

    /**
     * The constructor for the Park object.
     *
     * @param theCity The park's city.
     * @param theManager The park's manager.
     * @param theName The park's name.
     * @param theState The park's state.
     * @param theZipcode The park's ZIP Code.
     */
    public Park(final ParkManager theManager, final String theName, final String theCity,
                final String theState, final String theZipcode) {
//    	if ((theManager instanceof ParkManager) && (theName instanceof String) &&
//    			(theCity instanceof String) && (theZipcode instanceof String)) {
            manager = theManager;
            name = theName;
            city = theCity;
            state = theState;
            zipcode = theZipcode;
//    	} else {
//            throw new IllegalArgumentException("Parameters hold an incorrect class");
//    	}
    }
	
    /**
     * Setter for the park manager. It is assumed that each park only has one manager.
     *
     * @param theManager the park's manager.
     */
    public void setManager(ParkManager theManager) {
    	if (theManager instanceof ParkManager) {
            manager = theManager;
    	} else {
    	    throw new IllegalArgumentException("Parameter is not of type ParkManager");
    	}
    }
	
    /**
     * Getter for the park manager.
     *
     * @return the park's manager.
     */
    public ParkManager getManager() {
        return manager;
    }
	
    /**
     * Setter for the park's name.
     *
     * @param theName park's name.
     */
    public void setName (String theName) {
    	if (theName instanceof String) {
            name = theName;
    	} else {
    	    throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	
    /**
     * Getter for the park's name.
     *
     * @return the park's name.
     */
    public String getName() {
        return name;
    }
	
    /**
     * Setter for the park's city.
     *
     * @param theCity the park's city.
     */
    public void setCity(String theCity) {
    	if (theCity instanceof String) {
            city = theCity;
    	} else {
            throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	
    /**
     * Getter for the park's city.
     *
     * @return the city of the park.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the park's state.
     *
     * @param theState the park's city.
     */
    public void setState(String theState) {
    	if (theState instanceof String) {
            state = theState;
    	} else {
            throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }

    /**
     * Getter for the park's state.
     *
     * @return the state of the park.
     */
    public String getState() {
        return state;
    }


    /**
     * Setter for the park's zipcode.
     *
     * @param theZipcode The ZIP Code for the park.
     */
    public void setZipcode(String theZipcode) {
    	if (theZipcode instanceof String) {
            zipcode = theZipcode;
    	} else {
            throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	
    /**
     * Getter for the park's ZIP Code.
     *
     * @return the ZIP Code of the park.
     */
    public String getZipcode() {
        return zipcode;
    }
	
    /**
     * Compares two park objects for equivalence.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param obj The other object we are comparing.
     * @return True if the parks are equal, i.e., park name, city, and ZIP Codes are
     * the same. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Park)) {
            return false;
        }

        Park otherPark = (Park) obj;

        return name.equals(otherPark.name) && city.equals(otherPark.city) &&
	               zipcode.equals(otherPark.zipcode);
    }
}
