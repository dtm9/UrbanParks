package backend;

import java.io.Serializable;
import java.util.Objects;

/**
 * Immutable Park class for Urban Parks.
 *
 * @author Peter Park
 * @author Walter Weeks
 * @version 2017 Feb 10
 */
public final class Park implements Serializable {

    //***** Field(s) ***************************************************************************************************

    /** Default serialVersionUID for serialization. */
	private static final long serialVersionUID = 1L;

	/** The park manager object for the park. */
    private final ParkManager myManager;

    /** The name of the park. */
    private final String myName;

    /** The street address of the park. */
    private final String myStreet;
    
    /** The city of the park. */
    private final String myCity;

    /** The state abbreviation enumeration of the park. */
    private final US myState;

    /** The ZIP Code of the park. */
    private final String myZipcode;

    //***** Constructor(s) *********************************************************************************************

    /**
     * The constructor for the Park object.
     *
     * @author Peter Park
     * @author Walter Weeks
     * @param theManager The park manager.
     * @param theName The park name.
     * @param theStreet The park's street address.
     * @param theCity The park's city
     * @param theState The park's state ANSI abbreviation two-character string.
     * @param theZipcode The park's ZIP Code.
     */
    public Park(final ParkManager theManager, final String theName, final String theStreet,
                final String theCity, final String theState, final String theZipcode) {
        validateData(theManager, theName, theStreet, theCity, theState, theZipcode);
        myManager = theManager;
        myName    = theName;
        myStreet  = theStreet;
        myCity    = theCity;
        myState   = US.parse(theState); // Convert state string into an enum
        myZipcode = theZipcode;
    }

    //***** Accessor method(s) *****************************************************************************************

    /**
     * Getter for the park manager.
     *
     * @author Peter Park
     * @return the park's manager.
     */
    public ParkManager getManager() {
        return myManager;
    }
	
    /**
     * Getter for the park's name.
     *
     * @author Peter Park
     * @return the park's name.
     */
    public String getName() {
        return myName;
    }

    /**
     * Getter for the park's street.
     *
     * @return the park's street.
     */
    public String getStreet() {
        return myStreet;
    }

    /**
     * Getter for the park's city.
     *
     * @author Peter Park
     * @return the city of the park.
     */
    public String getCity() {
        return myCity;
    }

    /**
     * Getter for the park's state abbreviation enumeration.
     *
     * @author Peter Park
     * @return the state of the park.
     */
    public US getState() {
        return myState;
    }
	
    /**
     * Getter for the park's ZIP Code.
     *
     * @author Peter Park
     * @return the ZIP Code of the park.
     */
    public String getZipcode() {
        return myZipcode;
    }

    //***** Overridden method(s) ***************************************************************************************

    /**
     * Computes the hash code for this method. Remember, this method is required to maintain Java's equals
     * "contract."
     *
     * @return The hash code representing this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myStreet, myCity, myZipcode);
    }

    /**
     * Compares two park objects for equivalence.
     *
     * @author Walter Weeks
     * @param theObj The other object we are comparing.
     * @return True if the parks are equal, i.e., park name, street address, city, and
     * ZIP Codes are the same. False otherwise.
     */
    @Override
    public boolean equals(Object theObj) {
        if (theObj == null) {
            return false;
        }
        if (theObj == this) { // the identity
            return true;
        }
        if (!(theObj instanceof Park)) { // not the same object types
            return false;
        }

        Park otherPark = (Park) theObj;

        return myName.equals(otherPark.myName) && myStreet.equals(otherPark.myStreet) &&
                myCity.equals(otherPark.myCity) &&  myZipcode.equals(otherPark.myZipcode);
    }

    //***** Helper(s) **************************************************************************************************

    /**
     * Helper method that checks for proper data passed to the constructor.
     *
     * @author Walter Weeks
     * @param theManager The park manager.
     * @param theName The park name.
     * @param theStreet The park's street address.
     * @param theCity The park's city.
     * @param theState The park's state ANSI abbreviation two-character string.
     * @param theZipcode The park's ZIP Code.
     * @throws NullPointerException if any argument is null.
     * @throws IllegalArgumentException if any String value is empty except theState value, which must be exactly
     * two characters.
     */
    private void validateData(final ParkManager theManager, final String theName, final String theStreet,
                              final String theCity, final String theState, final String theZipcode) {
        // Check for nulls
        if (theManager == null || theName == null || theStreet == null ||
                theCity == null || theState == null || theZipcode == null) {
            throw new NullPointerException("No argument can be null.");
        }
        // Check for empty Strings
        if (theName.length() == 0 || theStreet.length()  == 0 ||  theCity.length() == 0 ||
                theZipcode.length() == 0) {
            throw new IllegalArgumentException("No String argument can be empty.");
        }
        // The state must be exactly two chars
        if (theState.length() != 2) {
            throw new IllegalArgumentException("The state must be exactly 2 characters.");
        }
    }
}
