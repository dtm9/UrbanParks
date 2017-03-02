package model;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract account object for users of the UrbanParks system.
 * @author Dylan Miller
 * @author Ethan Young
 */
public abstract class AbstractAccount implements Serializable {
	
  /**Default serialVersionUID for serialization.*/
  private static final long serialVersionUID = 1L;

  /**Regular expression for letters and symbols. Will be negated for phone numbers so there aren't any symbols.*/
  private static final String PHONE_REGEX = "[a-zA-Z]+";

  

  /**Maximum length a phone number can be.*/
  private static final int PHONE_LENGTH = 10;

  /**The email address of the user that is used to log in.*/
  private String myUsername;

  /**Phone number of the user.*/
  private String myPhone;

  /**Legal name of the user.*/
  private String myRealName;

  /**
  * Constructor for the generic Account user.
  * All other constructors will call this one
  * on the final step.
  * @author Dylan Miller
  * @param theUsername email address of the user that is used
  * to log into the system.
  * @param thePhone phone number of the user.
  * @param theRealName legal name of the user.
  */
  public AbstractAccount(final String theUsername, final String thePhone,
                 final String theRealName) {
    super();
    this.setUsername(theUsername);
    this.setPhoneNumber(thePhone);
    this.setRealName(theRealName);
  }

  /**
   * Override constructor for when only username and real name are provided.
   * Phone number will be null until edited by UrbanParks staff.
   * @param theUsername email address of the user that is used
   * to log into the system.
   * @param theRealName legal name of the user.
   */
  public AbstractAccount(final String theUsername, final String theRealName) {
    this(theUsername, null, theRealName);
  }

  /**
   * Override constructor when only the username is provided.
   * Will use null values for phone number and real name.
   * @param theUsername email address of the user that is used
   * to log into the system.
   */
  public AbstractAccount(final String theUsername) {
    this(theUsername, null, null);
  }

  /**
   * Fetches the real name of this user. Will return null
   * if no real name provided.
   * @return the real name.
   * @author Dylan Miller
   */
  public String getRealName() {
    return this.myRealName;
  }

  /**
   * Fetches the phone number of this user. Will return null
   * if no phone number is provided.
   * @return the number
   * @author Dylan Miller
   */
  public String getPhoneNumber() {
    return this.myPhone;
  }

  /**
   * Fetches the email address of this user used to log in.
   * @return the username
   * @author Dylan Miller
   */
  public String getUsername() {
    return this.myUsername;
  }

  /**
   * Method to set the username.
   * @param theUsername email address of the user used to log in.
   * @author Dylan Miller
   */
  private void setUsername(final String theUsername) {
    this.myUsername = theUsername;
  }

  /**
   * Change the phone number for this user.
   * @param thePhoneNumber 10 digit phone number.
   *                       No spaces, parenthesis, or hyphens.
   * @author Dylan Miller
   * @throws IllegalArgumentException if phone number is not
   *         10 digits long or contains characters, spaces, and symbols.
   */
  public void setPhoneNumber(final String thePhoneNumber) {
    if (!Pattern.matches(PHONE_REGEX, thePhoneNumber)
                        && thePhoneNumber.length() == PHONE_LENGTH) {
      this.myPhone = thePhoneNumber;
    } else {
      throw new IllegalArgumentException("Phone number must be 10 digits "
                                  + "long and only contain number characters.");
    }
  }

  /**
   * Change the real name on record for this user.
   * @param theName first and last name
   * @author Dylan Miller
   * @throws IllegalArgumentException if the string is empty.
   */
  public void setRealName(final String theName) {
    if (theName.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be blank.");
    } else {
      this.myRealName = theName;
    }
  }
  
  /**
   * This method is required to maintain the equals contract.
   * @author Walter Weeks
   */
  @Override
  public int hashCode() {
	  return Objects.hash(myUsername);
  }
  
  /**
   * Returns true if both accounts have the same username.
   * @author Dylan Miller
   */
  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj instanceof AbstractAccount && myUsername.equals(((AbstractAccount)obj).myUsername)) {
      result = true;
    }
    return result;
  }
  public abstract String AccountType();
}
