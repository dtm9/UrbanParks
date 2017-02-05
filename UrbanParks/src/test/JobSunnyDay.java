package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import backend.Job;
import backend.ParkManager;
import backend.Volunteer;
import backend.Park;

public class JobSunnyDay implements TestConstants {
	private ParkManager testManager;
	private Park testPark;
	private Job testJob;
	private Volunteer testVolunteer;
	/**
	 * @author VG Gnome
	 */
	@Before
	public void setUp() {
		testManager = new ParkManager(GOOD_EMAIL,GOOD_PHONE,GOOD_NAME);
		testPark = new Park(testManager, "Test Name", "Test City", "Test Zip");
		testVolunteer = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME,Volunteer.WorkGrade.LIGHT);
		testJob = new Job();
		
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setDateTest() {
	    testJob.setMyDate(GOOD_STRING);
	    testJob.getMyDate();
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyTimeTest() {
		testJob.setMyTime(GOOD_STRING);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyParkNameTest() {
		testJob.setMyParkName(testPark);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyDescriptionTest() {
		testJob.setMyDescription(GOOD_STRING);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyMaxVolunteersTest() {
		testJob.setMyMaxVolunteers(GOOD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyMinLightTest() {
		testJob.setMyMinLight(GOOD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyMinMediumTest() {
		testJob.setMyMinMedium(GOOD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyMinHeavyTest() {
		testJob.setMyMinHeavy(GOOD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyVolunteersTest() {
		testJob.setMyVolunteers(testVolunteer.getRealName());
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test
	public void setMyNotesTest() {
		testJob.setMyNotes(GOOD_STRING);
	}

}
