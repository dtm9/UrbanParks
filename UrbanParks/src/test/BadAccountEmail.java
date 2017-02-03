package test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.OfficeStaff;
/**
 * Test case for bad email states.
 * This is high severity because email is the GUID.
 * @author Dylan Miller
 */
public class BadAccountEmail implements TestConstants {

  /**
   * This rule allows us to test exception handling.
   * @author Dylan Miller
   */
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * Scenario for when there are no special characters.
   * @author Dylan Miller
   */
  @Test
  public final void emailOneWordNoSymbols() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Must be a valid email address.");

    OfficeStaff badUser1 = new OfficeStaff("Jim", GOOD_PHONE, GOOD_NAME);
  }

  /**
   * Scenario for when the at sign is missing in the email.
   * @author Dylan Miller
   */
  @Test
  public final void emailNoAtSign() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Must be a valid email address.");

    OfficeStaff badUser2 = new OfficeStaff("Jimhotmail.com",
                                           GOOD_PHONE, GOOD_NAME);
  }

  /**
   * Scenario for when there is no top level domain after
   * a period (example: .com).
   */
  @Test
  public final void emailNoTLD() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Must be a valid email address.");

    OfficeStaff badUser3 = new OfficeStaff("Jim@hotmail",
                                           GOOD_PHONE, GOOD_NAME);
    //TODO Ethan this test is failing, please look at the regex and see if we can fix it or get rid of this test. -Dylan
  }
}
