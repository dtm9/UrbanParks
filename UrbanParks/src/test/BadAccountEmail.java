package test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.OfficeStaff;

public class BadAccountEmail implements TestConstants {
  
  @Rule
  public final ExpectedException exception = ExpectedException.none();
  
  @Test
  public final void emailOneWordnoSymbols() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Must be a valid email address.");
    
    OfficeStaff badUser1 = new OfficeStaff("Jim", GOOD_PHONE, GOOD_NAME);
  }
  
  @Test
  public final void validAddressFakeWebsite() {
    
    OfficeStaff badUser2 = new OfficeStaff("Jim@fakeemailprovider.fake", GOOD_PHONE, GOOD_NAME);
  }
  
  @Test
  public final void emailNoAtSign() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Must be a valid email address.");
    
    OfficeStaff badUser3 = new OfficeStaff("Jimhotmail.com", GOOD_PHONE, GOOD_NAME);
  }
  
  @Test
  public final void emailNoTLD() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Must be a valid email address.");
    
    OfficeStaff badUser4 = new OfficeStaff("Jim@hotmail", GOOD_PHONE, GOOD_NAME);
  }
}
