/**
 * Test case for sunny-day scenario with phone number on a user.
 */
package test;

import org.junit.Test;

import backend.OfficeStaff;

/**
 * Uses the constants for the other tests to assert creation works.
 * @author Dylan Miller
 */
public class GoodAccountPhone implements TestConstants {

  /**
   * All three account types use the abstract Account.java's constructor
   * for these parameters so this test should hold for all valid data examples.
   * @author Dylan Miller
   */
  @Test
  public final void sunnyDayScenario() {

    OfficeStaff goodUser = new OfficeStaff(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
  }

}
