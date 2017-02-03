/**
 * Sunny-day scenario test case for creating a user account.
 * @author Dylan Miller
 */

package test;

import org.junit.Test;

import backend.OfficeStaff;


/**
 * Confirms a user created with known good parameters does not throw an error.
 * @author Dylan
 *
 */
public class GoodAccountEmail implements TestConstants {

  /**
   * Test using the known good values from the TestConstants.java interface.
   * @author Dylan Miller
   */
  @Test
  public final void goodEmail() {
    OfficeStaff goodUser = new OfficeStaff(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
  }
}
