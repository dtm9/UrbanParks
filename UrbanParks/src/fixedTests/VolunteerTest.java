package fixedTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.OfficeStaff;
import backend.Volunteer;
import backend.Volunteer.WorkGrade;

public class VolunteerTest {
	/**Address that passes the email REGEX validation.*/
	String GOOD_EMAIL = "joe@yahoo.com";

	/**10 digit phone number that meets our phone REGEX validation.*/
	String GOOD_PHONE = "5551112222";

	/**There is no REGEX for real names, it just needs to not be blank.*/
	String GOOD_NAME = "Joe Smith";
	
	WorkGrade GRADE = Volunteer.WorkGrade.LIGHT;
	
    /** Volunteer manager account test fixture. */
    private Volunteer validVolunteerAccount;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
	
    /**
     * Sets up the test fixture.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        validVolunteerAccount = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME, GRADE);
    }
	

//   /** Consider removing this ***
//	* All three account types use the abstract Account.java's constructor
//	* for these parameters so this test should hold for all valid data examples.
//	* @author Dylan Miller
//	*/
//	@Test
//	public final void sunnyDayScenario() {
//    OfficeStaff goodUser = new OfficeStaff(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
//  }

	
    /**
     * Scenario for when there are no special characters.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_FirstNameAsEmail_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Must be a valid email address.");

      Volunteer badUser = new Volunteer("Jim", GOOD_PHONE, GOOD_NAME, GRADE);
    }

    /**
     * Scenario for when the at sign is missing in the email.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_NoAtSignInEmail_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Must be a valid email address.");

      Volunteer badUser = new Volunteer("Jimhotmail.com",
                                             GOOD_PHONE, GOOD_NAME, GRADE);
    }
    
    /**
     * Scenario for when there is a space.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_SpaceInEmailAddress_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser = new Volunteer("Jim Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there are multiple @.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_MultipleAtSigns_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser = new Volunteer("Jim@Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there are two dots.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_multipleConsecutiveDots_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser6 = new Volunteer("Jim..Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a Quote.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailContainsQuotes_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser7 = new Volunteer("Jim\"Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a comma.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailContainsComma_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser8 = new Volunteer("Jim,Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a backslash.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailContainsBackslash_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser9 = new Volunteer("Jim\\Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a colon.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailContainsColon_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser10 = new Volunteer("Jim:Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a semi-colon.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailContainsSemiColon_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser11 = new Volunteer("Jim;Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a paranethesis.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailContainsParenthesis_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser12 = new Volunteer("Jim)(Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    
    /**
     * Scenario for when there is a inequality symbol.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailWithInequality_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	Volunteer badUser13 = new Volunteer("Jim<>Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }
    /**
     * Scenario for when there is a brackets.
     * @author Ethan Young
     */
    @Test
    public final void volunteerConstructor_emailWithBrackets_ShouldThrowException(){
  	  exception.expect(IllegalArgumentException.class);
  	  exception.expectMessage("Must be a valid email address.");
  	  
  	  Volunteer badUser14 = new Volunteer("[Jim]Jim@hotmail.com",
  			  									GOOD_PHONE, GOOD_NAME, GRADE);
  	  
    }

    /**
     * Scenario for a phone number that is 10 digits but uses hyphens.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberContainsHyphens_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL,
                                             "555-123-0000", GOOD_NAME, GRADE);
    }

    /**
     * Scenario for a phone using parenthesis
     * on the area code and hypens like a phone book.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberContainsParensAndHyphens_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL,
                                             "(555)123-0000", GOOD_NAME, GRADE);
    }

    /**
     * Scenario for a phone number that has spaces
     * but is otherwise valid.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberWithSpaces_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL,
                                             "555 123 0000", GOOD_NAME, GRADE);
    }

    /**
     * Scenario for a phone number longer than 10 digits.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberTooLong_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL,
                                             "55511122223333", GOOD_NAME, GRADE);
    }

    /**
     * Scenario for a phone number 11 digits long
     * which is only one over the limit.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberTooLongByOne_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL,
                                             "55511122223", GOOD_NAME, GRADE);
    }

    /**
     * Scenario for a phone number that is too short.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberTooShort_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL, "5551111", GOOD_NAME, GRADE);
    }

    /**
     * Scenario for a phone number that is 9 digits long
     * which is one under the minimum.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberTooShortByOne_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      Volunteer badUser = new Volunteer(GOOD_EMAIL, "555111222", GOOD_NAME, GRADE);
    }
    
    /**
     * Tests to see if the IllegalArgumentException is thrown for negative values of absence count.
     *
     * @author Walter Weeks
     */
    @Test (expected =  IllegalArgumentException.class)
    public void setAbsenceCount_NegativeNumber_ShouldThrowException() {
        validVolunteerAccount.setAbsenceCount(-1);
    }

    /**
     * Tests to see if the IllegalArgumentException is thrown for negative values of cancellation count.
     *
     * @author Walter Weeks
     */
    @Test (expected =  IllegalArgumentException.class)
    public void setBadCancellationCount_NegativeNumber_ShouldThrowException() {
        validVolunteerAccount.setBadCancellationCount(-1);
    }

    /**
     * Tests to see if the isBlackballed default value of false is set.
     *
     * @author Walter Weeks
     */
    @Test
    public void isBlackballed_InitialState_ShouldNotThrowException() {
        assertFalse(validVolunteerAccount.isBlackballed());
    }

    /**
     * Tests the setBlackballed method.
     *
     * @author Walter Weeks
     */
    @Test
    public void setBlackballed_SetToTrue_ShouldNotThrowException() {
        validVolunteerAccount.setBlackballed(true);
        assertTrue(validVolunteerAccount.isBlackballed());
    }

    /**
     * Tests the expected work grade value of the test fixture.
     *
     * @author Walter Weeks
     */
    @Test
    public void getWorkGrade_CheckModelForLight_ShouldNotThrowException() {
        assertEquals(Volunteer.WorkGrade.LIGHT, validVolunteerAccount.getWorkGrade());
    }

    /**
     * Tests the setAbsenceCount method.
     *
     * @author Walter Weeks
     */
    @Test
    public void getAbsenceCount_CheckModelForValue_ShouldNotThrowException() {
        validVolunteerAccount.setAbsenceCount(20);
        assertEquals(20, validVolunteerAccount.getAbsenceCount());
    }

    /**
     * Tests the setBadCancellationCount method.
     *
     * @author Walter Weeks
     */
    @Test
    public void getBadCancellationCount_CheckModelForValue_ShouldNotThrowException() {
        validVolunteerAccount.setBadCancellationCount(42);
        assertEquals(42, validVolunteerAccount.getBadCancellationCount());
    }

    /**
     * Tests to see if an IllegalArgumentException is thrown when passing null as an account note.
     */
    @Test (expected = IllegalArgumentException.class)
    public void addAccountNote_NullNote_ShouldThrowException() {
        validVolunteerAccount.addAccountNote(null);
    }

    /**
     * Tests to see if an IllegalArgumentException is thrown when passing an empty string as an account note.
     */
    @Test (expected = IllegalArgumentException.class)
    public void addAccountNote_EmptyNote_ShouldThrowException() {
        validVolunteerAccount.addAccountNote("");
    }
}
