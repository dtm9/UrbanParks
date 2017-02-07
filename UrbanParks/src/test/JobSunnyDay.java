package test;

import org.junit.Before;
import org.junit.Test;

import backend.Job;
import backend.ParkManager;
import backend.Volunteer;
import backend.Park;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Basic unit tests for the Job class for sunny-day scenarios, i.e., good/proper data.
 *
 * @author Gardner Gomes
 */
public class JobSunnyDay implements TestConstants {

	/** The park manager test fixture. */
	private ParkManager testManager;

	/** The park test fixture. */
	private Park testPark;

	/** The job test fixture. */
	private Job testJob;

	/** The volunteer test fixture. */
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
	 * Tests for setting a job with a proper date.
	 *
	 * @author Gardner Gomes
	 */
	@Test
	public void setDateTest() {
	    testJob.setMyDate("2017/03/05");
	    assertEquals("2017/03/05", testJob.getMyDate());
	}

	/**
	 * 
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyTimeTest() {
		testJob.setMyTime("1130");
		assertEquals("1130", testJob.getMyTime());
	}

	/**
	 * Tests for proper setting of park name.
	 * 
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyParkNameTest() {
		testJob.setMyParkName(testPark);
		assertEquals(testPark.getName(), testJob.getMyParkName());
	}

	/**
	 * Tests for proper setting of the job description.
	 * 
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyDescriptionTest() {
		testJob.setMyDescription(GOOD_STRING);
		assertEquals(GOOD_STRING, testJob.getMyDescription());
	}

	/**
	 * Test for proper setting of maximum number of volunteers.
	 *
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyMaxVolunteersTest() {
		testJob.setMyMaxVolunteers(GOOD_INT);
		assertEquals(GOOD_INT, testJob.getMyMaxVolunteers());
	}

	/**
	 * Test for proper setting of minimum light workers.
	 * 
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyMinLightTest() {
		testJob.setMyMinLight(GOOD_INT);
		assertEquals(GOOD_INT, testJob.getMyMinLight());
	}

	/**
	 * Test for proper setting of minimum medium workers.
	 * 
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyMinMediumTest() {
		testJob.setMyMinMedium(GOOD_INT);
		assertEquals(GOOD_INT, testJob.getMyMinMedium());
	}

	/**
	 * Test for proper setting of minimum heavy workers.
	 *
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyMinHeavyTest() {
		testJob.setMyMinHeavy(GOOD_INT);
		assertEquals(GOOD_INT, testJob.getMyMinHeavy());
	}

	/**
	 * Test for proper setting of the volunteer list.
	 *
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyVolunteersTest() {
		testJob.setMyVolunteers(testVolunteer.getUsername());
		List<String> vList = testJob.getMyVolunteers();
		assertEquals(testVolunteer.getUsername(), vList.get(0));
	}

	/**
	 * Tests for proper setting of the job notes.
	 *
	 * @author Gardner Gomes
	 */
	@Test
	public void setMyNotesTest() {
		testJob.setMyNotes(GOOD_STRING);
		assertEquals(GOOD_STRING, testJob.getMyNotes());
	}

	/**
	 * Tests two separate Job objects for equality.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void testEquality() {
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
	public void testInequality() {
		Job job1 = new Job("Wapato Park", "2017/03/01", "1200", "Rake leaves");
		Job job2 = new Job("Jefferson Park", "2017/03/01", "1200", "Rake leaves");
		assertFalse(job1.equals(job2));
	}


}
