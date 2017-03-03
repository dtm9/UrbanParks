package tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Volunteer;

/**
 * Basic unit tests for the Volunteer class.
 *
 * @author Ethan Young
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class VolunteerTest {

    //***** Constants **************************************************************************************************

	/**Example username.*/
	private static final String GOOD_EMAIL = "joe@yahoo.com";

	/**10 digit phone number that meets our phone REGEX validation.*/
	private static final String GOOD_PHONE = "5551112222";

	/**There is no REGEX for real names, it just needs to not be blank.*/
	private static final String GOOD_NAME = "Joe Smith";

	//***** Test fixture(s), setUp(), etc. *****************************************************************************

    /**Volunteer manager account test fixture.*/
    private Volunteer myValidVolunteerAccount;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
	
    /**
     * Sets up the test fixture.
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        myValidVolunteerAccount = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Scenario for a phone number that is 10 digits but uses hyphens.
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberContainsHyphens_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      new Volunteer(GOOD_EMAIL,"555-123-0000", GOOD_NAME);
    }

    /**
     * Scenario for a phone using parenthesis on the area code and hypens like a phone book.
     *
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberContainsParensAndHyphens_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      new Volunteer(GOOD_EMAIL,"(555)123-0000", GOOD_NAME);
    }

    /**
     * Scenario for a phone number that has spaces but is otherwise valid.
     *
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberWithSpaces_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      new Volunteer(GOOD_EMAIL,"555 123 0000", GOOD_NAME);
    }

    /**
     * Scenario for a phone number longer than 10 digits.
     *
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberTooLong_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      new Volunteer(GOOD_EMAIL,"55511122223333", GOOD_NAME);
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

      new Volunteer(GOOD_EMAIL,"55511122223", GOOD_NAME);
    }

    /**
     * Scenario for a phone number that is too short.
     *
     * @author Dylan Miller
     */
    @Test
    public final void volunteerConstructor_PhoneNumberTooShort_ShouldThrowException() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Phone number must be 10 digits long "
                            + "and only contain number characters.");

      new Volunteer(GOOD_EMAIL, "5551111", GOOD_NAME);
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

      new Volunteer(GOOD_EMAIL, "555111222", GOOD_NAME);
    }
    
//  /**
//  * Tests to see if the number of jobs a particular volunteers is as expected.
//  *
//  * @author Walter Weeks
//  */
// @Test
// public void getJobsByVolunteer_UnmodifiedDatastore_ShouldBe2() {
//     List<Job> jobsByVolunteer = myDatastore.getJobsByVolunteer(myVolunteers.get(0));
//     assertEquals(2, jobsByVolunteer.size());
// }
}
