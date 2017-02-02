/**
 * Abstract account object for users of the UrbanParks system.
 * @author Dylan Miller
 * @author Ethan Young
 */
public class Account {
  /**The email address of the user that is used to log in.*/
  private String myUsername;
  /**Phone number of the user.*/
  private String myPhone;
  /**Legal name of the user.*/
  private String myRealName;

/**
 * @author Dylan Miller
 * @param theUsername email address of the user that is used to log into the system.
 * @param thePhone phone number of the user.
 * @param theRealName legal name of the user.
 */
  public Account(String theUsername, String thePhone, String theRealName) {
    super();
    this.myUsername = theUsername;
    this.myPhone = thePhone;
    this.myRealName = theRealName;
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
   * Method to view all pending jobs this user is attached to by iterating over the collection of current jobs.
   */
  public void viewPendingJobs() {
	//TODO to be implemented after next meeting re: API.
  }
  
  /**
   *  Method to iterate over the datastore for old jobs this user was attached to and return a collection of those jobs.
   */
  public void viewPastJobs() {
	//TODO to be implemented after next meeting re: API.
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
   * Change the phone number for this user.
   * @param thePhoneNumber 10 digit phone number
   * @author Dylan Miller
   */
  public void setPhoneNumber(String thePhoneNumber) {
    //TODO data validation (ten digits, numbers ONLY).
    this.myPhone = thePhoneNumber;
  }
  
  /**
   * Change the real name on record for this user.
   * @param theName first and last name
   * @author Dylan Miller
   */
  public void setRealName(String theName) {
    this.myRealName = theName;
  }

}
