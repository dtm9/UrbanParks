package test;

import org.junit.Before;
import org.junit.Test;

import backend.Job;
import backend.ParkManager;
import backend.Volunteer;
import backend.Park;

/**
 * Basic unit tests for the Job class for rainy-day scenarios, i.e., bad/improper data.
 *
 * @author Gardner Gomes
 */
public class JobRainyDay implements TestConstants{

	/** Park manager test fixture. */
	private ParkManager testManager;

	/** Park test fixture. */
	private Park testPark;

	/** Job test fixture. */
	private Job testJob;

	/** Volunteer test fixture. */
	private Volunteer testVolunteer;
	
	/**
	 * Sets up the test fixtures used by the unit tests.
	 *
	 * @author Gardner Gomes
	 */
	@Before
	public void setUp() {
		testManager = new ParkManager(GOOD_EMAIL,GOOD_PHONE,GOOD_NAME);
		testPark = new Park(testManager, "Test Name", "Test City", "Test State", "Test ZIP");
		testVolunteer = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME,Volunteer.WorkGrade.LIGHT);
		testJob = new Job();
	}

	/**
	 * Test for bad date setting.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setDateTest() {
	    testJob.setMyDate(BAD_STRING);
	}

	/**
	 * Tests for bad time setting.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyTimeTest() {
		testJob.setMyTime(BAD_STRING);
	}

	/**
	 * Tests for null park name setting.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyParkNameTest() {
		testJob.setMyParkName(P_NULL);
	}

	/**
	 * Tests for bad description string setting.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyDescriptionTest() {
		testJob.setMyDescription(BAD_STRING);
	}

	/**
	 * Tests for bad max volunteers setting.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMaxVolunteersTest() {
		testJob.setMyMaxVolunteers(BAD_INT);
	}

	/**
	 * Tests for bad minimum light worker count.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinLightTest() {
		testJob.setMyMinLight(BAD_INT);
	}

	/**
	 * Tests for bad minimum medium worker count.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinMediumTest() {
		testJob.setMyMinMedium(BAD_INT);
	}

	/**
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinHeavyTest() {
		testJob.setMyMinHeavy(BAD_INT);
	}

	/**
	 * Tests adding a bad volunteer to the volunteers list of a job.
	 *
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyVolunteersTest() {
		testJob.setMyVolunteers(BAD_STRING);
	}

	/**
	 * Tests adding a bad note to the notes list.
	 * 
	 * @author Gardner Gomes
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyNotesTest() {
		testJob.setMyNotes(BAD_STRING);
	}

}
