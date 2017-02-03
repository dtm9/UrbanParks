package test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.OfficeStaff;
/**
 * Test case for when the phone number is invalid on user creation.
 * @author Dylan Miller
 *
 */
public class BadAccountPhone implements TestConstants {

  /**
   * This rule will allow us to test exception handling.
   * @author Dylan Miller
   */
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * Scenario for a phone number that is 10 digits but uses hyphens.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumValidButWithHyphens() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser1 = new OfficeStaff(GOOD_EMAIL,
                                           "555-123-0000", GOOD_NAME);
  }

  /**
   * Scenario for a phone using parenthesis
   * on the area code and hypens like a phone book.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumValidButWithHyphensAndParens() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser2 = new OfficeStaff(GOOD_EMAIL,
                                           "(555)123-0000", GOOD_NAME);
  }

  /**
   * Scenario for a phone number that has spaces
   * but is otherwise valid.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumValidButWithSpaces() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser3 = new OfficeStaff(GOOD_EMAIL,
                                           "555 123 0000", GOOD_NAME);
  }

  /**
   * Scenario for a phone number longer than 10 digits.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumTooLong() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser4 = new OfficeStaff(GOOD_EMAIL,
                                           "55511122223333", GOOD_NAME);
  }

  /**
   * Scenario for a phone number 11 digits long
   * which is only one over the limit.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumTooLongByOne() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser5 = new OfficeStaff(GOOD_EMAIL,
                                           "55511122223", GOOD_NAME);
  }

  /**
   * Scenario for a phone number that is too short.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumTooShort() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser6 = new OfficeStaff(GOOD_EMAIL, "5551111", GOOD_NAME);
  }

  /**
   * Scenario for a phone number that is 9 digits long
   * which is one under the minimum.
   * @author Dylan Miller
   */
  @Test
  public final void phoneNumTooShortByOne() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long "
                          + "and only contain number characters.");

    OfficeStaff badUser7 = new OfficeStaff(GOOD_EMAIL, "555111222", GOOD_NAME);
  }
}
