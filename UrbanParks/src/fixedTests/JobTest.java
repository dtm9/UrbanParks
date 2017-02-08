/**
 * 
 */
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
 * @author VG Gnome
 * @author dylan
 *
 */
public class JobTest implements TestConstants  {
	
	/** The park manager test fixture. */
	private ParkManager theManager;

	/** The park test fixture. */
	private Park thePark;

	/** The job test fixture. */
	private Job myJob;

	/** The volunteer test fixture. */
	private Volunteer theVolunteer;


	/**
	 * @throws java.lang.Exception
	 * @author VG Gnome
	 */
	@Before
	public void setUp() throws Exception{
		theManager = new ParkManager(GOOD_EMAIL,GOOD_PHONE,GOOD_NAME);
		thePark = new Park(theManager, "Test Name", "Test City", "Test State", "Test ZIP");
		theVolunteer = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME,Volunteer.WorkGrade.LIGHT);
		myJob = new Job();
	}
	/**
	 * test for Good date on setDate()
	 * @author VG Gnome
	 */
	@Test
	public final void setDate_goodDate_dateSet() {
		myJob.setMyDate("2017/03/05");
	    assertEquals("2017/03/05", myJob.getMyDate());
	}
	/**
	 * Test for bad date setting.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setDate_badDate_exceptionThrown() {
	    myJob.setMyDate(BAD_STRING);
	}
	/**
	 * Test for proper setting of time.
	 * @author VG Gnome
	 */
	@Test
	public void setMyTime_goodTime_timeSet() {
		myJob.setMyTime("1130");
		assertEquals("1130", myJob.getMyTime());
	}
	/**
	 * Tests for bad time setting.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyTime_badTime_Exceptionthrown() {
		myJob.setMyTime(BAD_STRING);
	}
	/**
	 * Tests for proper setting of park name.
	 * @author VG Gnome
	 */
	@Test
	public void setMyParkName_goodPark_parkNameSet() {
		myJob.setMyParkName(thePark);
		assertEquals(thePark.getName(), myJob.getMyParkName());
	}
	/**
	 * Tests for null park name setting.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyParkName_badName_ExceptionThrown() {
		myJob.setMyParkName(P_NULL);
	}
	/**
	 * Tests for proper setting of the job description.
	 * @author VG Gnome
	 */
	@Test
	public void setMyDescription_goodDescription_descriptionSet() {
		myJob.setMyDescription(GOOD_STRING);
		assertEquals(GOOD_STRING, myJob.getMyDescription());
	}
	/**
	 * Tests for bad description string setting.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyDescription_badDescription_ExceptionThrown() {
		myJob.setMyDescription(BAD_STRING);
	}
	/**
	 * Test for proper setting of maximum number of volunteers.
	 *@author VG Gnome
	 */
	@Test
	public void setMyMaxVolunteers_goodInt_VolunteerMaxSet() {
		myJob.setMyMaxVolunteers(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMyMaxVolunteers());
	}
	/**
	 * Tests for bad max volunteers setting.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMaxVolunteers_badInt_ExceptionThrown() {
		myJob.setMyMaxVolunteers(BAD_INT);
	}
	/**
	 * Test for proper setting of minimum light workers.
	 * @author VG Gnome
	 */
	@Test
	public void setMyMinLight_goodInt_MinLightSet() {
		myJob.setMyMinLight(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMyMinLight());
	}
	/**
	 * Tests for bad minimum light worker count.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinLight_badInt_ExceptionThrown() {
		myJob.setMyMinLight(BAD_INT);
	}
	/**
	 * Test for proper setting of minimum medium workers.
	 * @author VG Gnome
	 */
	@Test
	public void setMyMinMedium_goodInt_MinMediumSet() {
		myJob.setMyMinMedium(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMyMinMedium());
	}
	/**
	 * Tests for bad minimum medium worker count.
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinMedium_badInt_ExceptionThrown() {
		myJob.setMyMinMedium(BAD_INT);
	}
	/**
	 * Test for proper setting of minimum heavy workers.
	 *@author VG Gnome
	 */
	@Test
	public void setMyMinHeavy_goodInt_MinHeavySet() {
		myJob.setMyMinHeavy(GOOD_INT);
		assertEquals(GOOD_INT, myJob.getMyMinHeavy());
	}
	/**
	 * Tests for bad min of heavy workers
	 *@author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinHeavy_badInt_ExceptionThrown() {
		myJob.setMyMinHeavy(BAD_INT);
	}
	/**
	 * Test for proper setting of the volunteer list.
	 *@author VG Gnome
	 */
	@Test
	public void setMyVolunteers_goodVolunteer_volunteerAdded() {
		myJob.setMyVolunteers(theVolunteer.getUsername());
		List<String> vList = myJob.getMyVolunteers();
		assertEquals(theVolunteer.getUsername(), vList.get(0));
	}
	/**
	 * Tests adding a bad volunteer to the volunteers list of a job.
	 *@author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyVolunteers_badVolunteerName_ExceptionThrown() {
		myJob.setMyVolunteers(BAD_STRING);
	}
	/**
	 * Tests for proper setting of the job notes.
	 *@author VG Gnome
	 */
	@Test
	public void setMyNotes_goodNotes_NotesSet() {
		myJob.setMyNotes(GOOD_STRING);
		assertEquals(GOOD_STRING, myJob.getMyNotes());
	}
	/**
	 * Tests adding a bad note to the notes list.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyNotes_BadNotes_ExceptionThrown() {
		myJob.setMyNotes(BAD_STRING);
	}
	/**
	 * Tests two separate Job objects for equality.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void testEquality_EqualJobs_True() {
		Job job1 = new Job("Wapato Park", "2017/03/01", "1200", "Rake leaves");
		Job job2 = new Job("Wapato Park", "2017/03/01", "1200", "Rake leaves");
		assertTrue(job1.equals(job2));
	}

	/**
	 * Tests two separate Job objects for inequality.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void testInequality_DifferentJobs_False() {
		Job job1 = new Job("Wapato Park", "2017/03/01", "1200", "Rake leaves");
		Job job2 = new Job("Jefferson Park", "2017/03/01", "1200", "Rake leaves");
		assertFalse(job1.equals(job2));
	}

}
