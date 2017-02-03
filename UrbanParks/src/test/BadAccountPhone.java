package test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.OfficeStaff;

public class BadAccountPhone implements TestConstants {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public final void phoneNumValidButWithHyphens() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser1 = new OfficeStaff(GOOD_EMAIL, "555-123-0000", GOOD_NAME);
  }
  
  @Test
  public final void phoneNumValidButWithHyphensAndParens() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser2 = new OfficeStaff(GOOD_EMAIL, "(555)123-0000", GOOD_NAME);
  }
  
  @Test
  public final void phoneNumValidButWithSpaces() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser3 = new OfficeStaff(GOOD_EMAIL, "555 123 0000", GOOD_NAME);
  }
  
  @Test
  public final void phoneNumTooLong() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser4 = new OfficeStaff(GOOD_EMAIL, "55511122223333", GOOD_NAME);
  }
  
  @Test
  public final void phoneNumTooLongByOne() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser5 = new OfficeStaff(GOOD_EMAIL, "55511122223", GOOD_NAME);
  }
  
  @Test
  public final void phoneNumTooShort() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser6 = new OfficeStaff(GOOD_EMAIL, "5551111", GOOD_NAME);
  }
  
  @Test
  public final void phoneNumTooShortByOne() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Phone number must be 10 digits long and only contain number characters.");

    OfficeStaff badUser7 = new OfficeStaff(GOOD_EMAIL, "555111222", GOOD_NAME);
  }
}
