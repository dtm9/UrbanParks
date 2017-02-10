package backend;

import java.io.Serializable;
import java.util.Objects;

/**
 * Park Manager User. Other classes will check instance of account
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class ParkManager extends Account implements Serializable {

  /** Default serialVersionUID for serialization. */
  private static final long serialVersionUID = 1L;

  /**
   * Creates an Park Manager user. Moderate permissions
   * @param theUsername email address used for login.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public ParkManager(final String theUsername, final String thePhone,
                     final String theRealName) {
    super(theUsername, thePhone, theRealName);
  }


  //***** Overridden method(s) *****************************************************************************************

  /**
   * Computes the hash code for this method. Remember, this method is required to maintain Java's equals
   * "contract."
   *
   * @author Walter Weeks
   * @return  The hash code representing this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getUsername());
  }

  /**
   * Equals operation comparing two ParkManagers together. Note that user names on the Urban Parks
   * system must be unique.
   *
   * @author Walter Weeks
   * @param obj The other object we are comparing.
   * @return True if the ParkManager objects are equal, i.e., unique username, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) { // the identity
      return true;
    }
    if (!(obj instanceof ParkManager)) { // not the same object types
      return false;
    }
    ParkManager otherManager = (ParkManager) obj;

    return otherManager.getUsername().equals(getUsername());
  }
}
