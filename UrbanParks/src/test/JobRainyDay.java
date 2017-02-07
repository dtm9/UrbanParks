package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.Job;
import backend.ParkManager;
import backend.Volunteer;
import backend.Park;

public class JobRainyDay implements TestConstants{

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
		testPark = new Park(testManager, "Test Name", "Test City", "Test State", "Test ZIP");
		testVolunteer = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME,Volunteer.WorkGrade.LIGHT);
		testJob = new Job();
		
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setDateTest() {
	    testJob.setMyDate(BAD_STRING);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyTimeTest() {
		testJob.setMyTime(BAD_STRING);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyParkNameTest() {
		testJob.setMyParkName(P_NULL);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyDescriptionTest() {
		testJob.setMyDescription(BAD_STRING);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMaxVolunteersTest() {
		testJob.setMyMaxVolunteers(BAD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinLightTest() {
		testJob.setMyMinLight(BAD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinMediumTest() {
		testJob.setMyMinMedium(BAD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyMinHeavyTest() {
		testJob.setMyMinHeavy(BAD_INT);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyVolunteersTest() {
		testJob.setMyVolunteers(BAD_STRING);
	}
	/**
	 * 
	 * @author VG Gnome
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMyNotesTest() {
		testJob.setMyNotes(BAD_STRING);
	}

}
