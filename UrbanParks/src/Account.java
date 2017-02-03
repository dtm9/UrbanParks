import java.util.regex.Pattern;

/**
 * Abstract account object for users of the UrbanParks system.
 * @author Dylan Miller
 * @author Ethan Young
 */
public abstract class Account {
  /**Regular expression for phone number format.*/
  private static final String PHONE_REGEX = "[a-zA-Z]+";

  /**Regular expression for email address format.*/
  private static final String EMAIL_REGEX = 
      "^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$";
  
  /**The email address of the user that is used to log in.*/
  private String myUsername;
  
  /**Phone number of the user.*/
  private String myPhone;
  
  /**Legal name of the user.*/
  private String myRealName;

  /**
  * Constructor for the generic Account user. All other constructors will call this one
  * on the final step.
  * @author Dylan Miller
  * @param theUsername email address of the user that is used to log into the system.
  * @param thePhone phone number of the user.
  * @param theRealName legal name of the user.
  */
  public Account(String theUsername, String thePhone, String theRealName) {
    super();
    this.setUsername(theUsername);
    this.setPhoneNumber(thePhone);
    this.setRealName(theRealName);
  }
  
  /**
   * Override constructor for when only username and real name are provided. 
   * Phone number will be null until edited by UrbanParks staff.
   * @param theUsername email address of the user that is used to log into the system.
   * @param theRealName legal name of the user.
   */
  public Account(String theUsername, String theRealName) {
    this(theUsername, null, theRealName);
  }
  
  /**
   * Override constructor when only the username is provided. 
   * Will use null values for phone number and real name.
   * @param theUsername email address of the user that is used to log into the system.
   */
  public Account(String theUsername) {
    this(theUsername, null, null);
  }
  
  /**
   * Fetches the real name of this user. Will return null if no real name provided.
   * @return the real name.
   * @author Dylan Miller
   */
  public String getRealName() {
    return this.myRealName;
  }
  
  /**
   * Fetches the phone number of this user. Will return null if no phone number is provided.
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
   * Method to set the username. Checks for valid email address format.
   * @param theUsername email address of the user used to log in.
   * @author Dylan Miller
   * @throws IllegalArgumentException if the email address is invalid.
   */
  private void setUsername(String theUsername) {
    if (Pattern.matches(EMAIL_REGEX, theUsername)) {
      this.myUsername = theUsername;
    } else {
      throw new IllegalArgumentException("Must be a valid email address.");
    }
  }
  
  /**
   * Change the phone number for this user.
   * @param thePhoneNumber 10 digit phone number.
   *                       No spaces, parenthesis, or hyphens.
   * @author Dylan Miller
   * @throws IllegalArgumentException if phone number is not
   *         10 digits long or contains characters, spaces, and symbols.
   */
  public void setPhoneNumber(String thePhoneNumber) {
    if (Pattern.matches(PHONE_REGEX, thePhoneNumber) == false && thePhoneNumber.length() == 10) {
      this.myPhone = thePhoneNumber;
    } else {
      throw new IllegalArgumentException("Phone number must be 10 digits long and "
                                         + "only contain number characters.");
    }
  }
  
  /**
   * Change the real name on record for this user.
   * @param theName first and last name
   * @author Dylan Miller
   * @throws IllegalArgumentException if the string is empty.
   */
  public void setRealName(String theName) {
    if (theName.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be blank.");
    } else {
      this.myRealName = theName;
    }
  }
}
