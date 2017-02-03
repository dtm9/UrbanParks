package backend;
/**
 * Urban Parks Office Staff User. Other classes will check instancof account
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 *
 */
public class OfficeStaff extends Account {

  /**
   * Creates an Urban Parks office staff user. High permissions
   * @param theUsername email address of the user that is used
   * to log into the system.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public OfficeStaff(final String theUsername,
                     final String thePhone, final String theRealName) {
    super(theUsername, thePhone, theRealName);
  }
}
