package tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

/**
 * Basic unit tests for the Volunteer class.
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

    /**Volunteer account test fixture.*/
    private Volunteer myValidVolunteerAccount;
    
    /**Park Manager object for simulation.*/
    private ParkManager simulatedParkManager;
    
    /**Park object for simulation.*/
    private Park simulatedPark;
    
    /**Job object for the test user to sign up for.*/
    private Job simulatedJob;
    
    /**Datastore object for simulation.*/
    private Datastore testDatastore;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
	
    /**
     * Sets up the test fixture.
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(new Date());
        myCal.add(Calendar.DAY_OF_MONTH, 1);
        
        myValidVolunteerAccount = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
        simulatedParkManager = new ParkManager("Rusty Shackleford", "3606211432", "Rusty Shackleford");
        simulatedPark = new Park(simulatedParkManager, "Savikko Park", "504 St Anns Ave", "Douglas", "AK", "99824");
        simulatedJob = new Job(simulatedPark, "12:30", "Arm Traps", "Re-arm Bear Traps.", 1,
                               myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
        
        testDatastore = new Datastore();
        testDatastore.addAccount(myValidVolunteerAccount);
        testDatastore.addAccount(simulatedParkManager);
        testDatastore.addPark(simulatedPark);
        testDatastore.addJob(simulatedJob);
        
        simulatedJob.setVolunteers(myValidVolunteerAccount.getUsername());
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
    
    /**
     * Tests to see if the number of jobs a particular volunteers is as expected.
     * @author Walter Weeks
     * @author Dylan Miller
     */
    @Test
    public void getJobsByVolunteer_UnmodifiedDatastore_ShouldBeOne() {
      List<Job> jobsByVolunteer = myValidVolunteerAccount.getJobsByVolunteer(testDatastore);
      assertEquals(1, jobsByVolunteer.size());
    }
    
    //TODO some rainy-day scenarios for getJobsByVolunteer() for the input domains. Shoudn't be many since it takes no args except datastore.
}
