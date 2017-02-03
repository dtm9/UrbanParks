package test;

import org.junit.Test;

import backend.OfficeStaff;

public class GoodAccountEmail implements TestConstants {
	  
  @Test
  public final void goodEmail() {
    OfficeStaff goodUser = new OfficeStaff(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
  }
}
