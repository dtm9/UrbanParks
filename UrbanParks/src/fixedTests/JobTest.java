package fixedTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import backend.Job;
import backend.Park;
import backend.ParkManager;
import backend.Volunteer;

/**
 * Unit tests for the Job class.
 *
 * @author Gardner Gomes
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class JobTest implements TestConstants  {

    //***** Test fixture(s) and setUp() *****************************************************************************

	/** The park manager test fixture. */
	private ParkManager theManager;

	/** The park test fixture. */
	private Park thePark;

	/** The job test fixture. */
	private Job myJob;

	/** The volunteer test fixture. */
	private Volunteer theVolunteer;

	/**
	 * Sets up the test fixtures.
     *
	 * @author Gardner Gomes
	 */
	@Before
	public void setUp() throws Exception{
		theManager = new ParkManager(GOOD_EMAIL,GOOD_PHONE,GOOD_NAME);
		thePark = new Park(theManager, "Test Name", "123 E St","Test City", "WA", "Test ZIP");
		theVolunteer = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME,Volunteer.WorkGrade.LIGHT);
		myJob = new Job();
	}

	//***** Unit test(s) *********************************************************************************************

	/**
	 * Test for Good date on setDate().
     *
	 * @author Gardner Gomes
	 */
	@Test
	public final void setDate_goodDate_dateSet() {
		myJob.setDate("2017/03/05");
	    assertEquals("2017/03/05", myJob.getDate());
	}

	/**
	 * Test for bad date setting.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setDate_badDate_exceptionThrown() {
	    myJob.setDate(BAD_STRING);
	}

	/**
	 * Test for proper setting of time.
     *
	 * @author Gardner Gomes
	 */
	@Test
	public void setTime_goodTime_timeSet() {
		myJob.setTime("1130");
		assertEquals("1130", myJob.getTime());
	}

	/**
	 * Tests for bad time setting.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setTime_badTime_Exceptionthrown() {
		myJob.setTime(BAD_STRING);
	}

	/**
	 * Tests for proper setting of park name.
     *
	 * @author Gardner Gomes
	 */
	@Test
	public void setPark_goodPark_parkSet() {
		myJob.setPark(thePark);
		assertEquals(thePark, myJob.getPark());
	}

	/**
	 * Tests for null park name setting.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setPark_badName_ExceptionThrown() {
		myJob.setPark(P_NULL);
	}

	/**
	 * Tests for proper setting of the job description.
     *
	 * @author Gardner Gomes
	 */
	@Test
	public void setDescription_goodDescription_descriptionSet() {
		myJob.setDescription(GOOD_STRING);
		assertEquals(GOOD_STRING, myJob.getDescription());
	}

	/**
	 * Tests for bad description string setting.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setDescription_badDescription_ExceptionThrown() {
		myJob.setDescription(BAD_STRING);

	}

	/**
	 * Test for proper setting of maximum number of volunteers.
     *
	 *@author Gardner Gomes
	 */
	@Test
	public void setMaxVolunteers_goodInt_VolunteerMaxSet() {
		myJob.setMaxVolunteers(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMaxVolunteers());
	}

	/**
	 * Tests for bad max volunteers setting.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMaxVolunteers_badInt_ExceptionThrown() {
		myJob.setMaxVolunteers(BAD_INT);
	}

	/**
	 * Test for proper setting of minimum light workers.
     *
	 * @author Gardner Gomes
	 */
	@Test
	public void setMinLight_goodInt_MinLightSet() {
		myJob.setMinLight(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMinLight());
	}

	/**
	 * Tests for bad minimum light worker count.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMinLight_badInt_ExceptionThrown() {
		myJob.setMinLight(BAD_INT);
	}

	/**
	 * Test for proper setting of minimum medium workers.
     *
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyMinMedium_goodInt_MinMediumSet() {
		myJob.setMinMedium(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMinMedium());
	}

	/**
	 * Tests for bad minimum medium worker count.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMinMedium_badInt_ExceptionThrown() {
		myJob.setMinMedium(BAD_INT);
	}

	/**
	 * Test for proper setting of minimum heavy workers.
     *
	 *@author Gardner Gomes
	 */
	@Test
	public void setMinHeavy_goodInt_MinHeavySet() {
		myJob.setMinHeavy(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMinHeavy());
	}

	/**
	 * Tests for bad min of heavy workers.
     *
	 *@author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMinHeavy_badInt_ExceptionThrown() {
		myJob.setMinHeavy(BAD_INT);
	}

	/**
	 * Test for proper setting of the volunteer list.
     *
	 *@author Gardner Gomes
	 */
	@Test
	public void setVolunteers_goodVolunteer_volunteerAdded() {
		myJob.setVolunteers(theVolunteer.getUsername());
		List<String> vList = myJob.getVolunteers();
		assertEquals(theVolunteer.getUsername(), vList.get(0));
	}

	/**
	 * Tests adding a bad volunteer to the volunteers list of a job.
     *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setVolunteers_badVolunteerName_ExceptionThrown() {
		myJob.setVolunteers(BAD_STRING);
	}

	/**
	 * Tests for proper setting of the job notes.
     *
	 * @author VG Gnome
	 */
	@Test
	public void setNotes_goodNotes_NotesSet() {
		myJob.setNotes(GOOD_STRING);
		assertEquals(GOOD_STRING, myJob.getNotes());
	}
	/**
	 * Tests adding a bad note to the notes list.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyNotes_BadNotes_ExceptionThrown() {
		myJob.setNotes(BAD_STRING);
	}

	/**
	 * Tests two separate Job objects for equality.
	 *
	 * @author Walter Weeks
	 */
	@Test
	public void testEquality_EqualJobs_True() {
		Park park = new Park(theManager, "Wapato Park", "6500 S Sheridan Ave", "Tacoma", "WA", "98406");
		Job job1 = new Job(park, "2017/03/01", "1200", "Rake leaves");
		Job job2 = new Job(park, "2017/03/01", "1200", "Rake leaves");
		assertTrue(job1.equals(job2));
	}

	/**
	 * Tests two separate Job objects for inequality.
	 *
	 * @author Walter Weeks
	 */
	@Test
	public void testInequality_DifferentJobs_False() {
        Park park1 = new Park(theManager, "Wapato Park", "6500 S Sheridan Ave", "Tacoma", "WA", "98406");
		Park park2 = new Park(theManager, "Jefferson Park","801 N Mason Ave","Tacoma", "WA", "98406");
        Job job1 = new Job(park1, "2017/03/01", "1200", "Rake leaves");
		Job job2 = new Job(park2, "2017/03/01", "1200", "Rake leaves");
		assertFalse(job1.equals(job2));
	}
}
