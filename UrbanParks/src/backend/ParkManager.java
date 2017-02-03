package backend;
/**
 * Park Manager User. Other classes will check instance of account 
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 */
public class ParkManager extends Account {

  /**
   * Creates an Park Manager user. Moderate permissions
   * @param theUsername email address of the user that is used to log into the system.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public ParkManager(String theUsername, String thePhone, String theRealName) {
    super(theUsername, thePhone, theRealName);
  }
}
