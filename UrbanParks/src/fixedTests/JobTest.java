/**
 * 
 */
package fixedTests;

import static org.junit.Assert.*;

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
	 * Test for setting the time with a good time.
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

}
