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
  /**
   * Scenario for when there is a space.
   * @author Ethan Young
   */
  @Test
  public final void emailWithSpace(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser4 = new OfficeStaff("Jim Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there are multiple @.
   * @author Ethan Young
   */
  @Test
  public final void emailMultipleAts(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser5 = new OfficeStaff("Jim@Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there are two dots.
   * @author Ethan Young
   */
  @Test
  public final void emailWithDoubleDot(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser6 = new OfficeStaff("Jim..Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a Quote.
   * @author Ethan Young
   */
  @Test
  public final void emailWithQuote(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser7 = new OfficeStaff("Jim\"Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a comma.
   * @author Ethan Young
   */
  @Test
  public final void emailWithComma(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser8 = new OfficeStaff("Jim,Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a backslash.
   * @author Ethan Young
   */
  @Test
  public final void emailWithBackslash(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser9 = new OfficeStaff("Jim\\Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a colon.
   * @author Ethan Young
   */
  @Test
  public final void emailWithColon(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser10 = new OfficeStaff("Jim:Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a semi-colon.
   * @author Ethan Young
   */
  @Test
  public final void emailWithSemiColon(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser11 = new OfficeStaff("Jim;Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a paranethesis.
   * @author Ethan Young
   */
  @Test
  public final void emailWithParen(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser12 = new OfficeStaff("Jim)Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  /**
   * Scenario for when there is a inequality symbol.
   * @author Ethan Young
   */
  @Test
  public final void emailWithInequ(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser13 = new OfficeStaff("Jim<>Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  /**
   * Scenario for when there is a brackets.
   * @author Ethan Young
   */
  @Test
  public final void emailWithBrackets(){
	  exception.expect(IllegalArgumentException.class);
	  exception.expectMessage("Must be a valid email address.");
	  
	  OfficeStaff badUser14 = new OfficeStaff("[Jim]Jim@hotmail.com",
			  									GOOD_PHONE, GOOD_NAME);
	  
  }
  
  
}
