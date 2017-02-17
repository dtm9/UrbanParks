package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

/**
 * Unit tests for the Job class.
 *
 * @author Gardner Gomes
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class JobTest implements TestConstants {

    // ***** Test fixture(s) and setUp() *****************************************************************************

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
    public void setUp() throws Exception {
        theManager = new ParkManager(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
        thePark = new Park(theManager, "Test Name", "123 E St", "Test City", "WA", "Test ZIP");
        theVolunteer = new Volunteer(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME, Volunteer.WorkGrade.LIGHT);
        myJob = new Job();
    }

    // ***** Unit test(s) **********************************************************************************************

    /**
     * Test for Good day on setDay().
     *
     * @author Gardner Gomes
     */
    @Test
    public final void setDay_GoodDay_DaySet() {
        myJob.setDay(GOOD_INT);
        assertEquals(GOOD_INT, myJob.getDay());
    }

    /**
     * Test for bad day setting.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setDay_BadDay_ExceptionThrown() {
        myJob.setDay(BAD_INT);
    }

    /**
     * Test for Good Month on setMonth().
     *
     * @author Gardner Gomes
     */
    @Test
    public final void setMonth_GoodMonth_MonthSet() {
        myJob.setMonth(GOOD_INT);
        assertEquals(GOOD_INT, myJob.getMonth());
    }

    /**
     * Test for bad Month setting.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMonth_BadMonth_ExceptionThrown() {
        myJob.setMonth(BAD_INT);
    }

    /**
     * Test for proper setting of time.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setTime_GoodTime_TimeSet() {
        myJob.setTime("1130");
        assertEquals("1130", myJob.getTime());
    }

    /**
     * Tests for bad time setting.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setTime_BadTime_ExceptionThrown() {
        myJob.setTime(BAD_STRING);
    }

    /**
     * Tests for proper setting of park name.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setPark_GoodPark_ParkSet() {
        myJob.setPark(thePark);
        assertEquals(thePark, myJob.getPark());
    }

    /**
     * Tests for null park name setting.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setPark_BadName_ExceptionThrown() {
        myJob.setPark(P_NULL);
    }

    /**
     * Tests for proper setting of the job description.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setDescription_GoodDescription_DescriptionSet() {
        myJob.setDescription(GOOD_STRING);
        assertEquals(GOOD_STRING, myJob.getDescription());
    }

    /**
     * Tests for bad description string setting.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setDescription_BadDescription_ExceptionThrown() {
        myJob.setDescription(BAD_STRING);

    }

    /**
     * Test for proper setting of maximum number of volunteers.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setMaxVolunteers_GoodInt_VolunteerMaxSet() {
        myJob.setMaxVolunteers(GOOD_INT);
        assertEquals(GOOD_INT, myJob.getMaxVolunteers());
    }

    /**
     * Tests for bad max volunteers setting.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMaxVolunteers_BadInt_ExceptionThrown() {
        myJob.setMaxVolunteers(BAD_INT);
    }

    /**
     * Test for proper setting of minimum light workers.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setMinLight_GoodInt_MinLightSet() {
        myJob.setMinLight(GOOD_INT);
        assertEquals(GOOD_INT, myJob.getMinLight());
    }

    /**
     * Tests for bad minimum light worker count.
     * 
     * @author VG Gnome
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMinLight_BadInt_ExceptionThrown() {
        myJob.setMinLight(BAD_INT);
    }

    /**
     * Test for proper setting of minimum medium workers.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setMyMinMedium_GoodInt_MinMediumSet() {
        myJob.setMinMedium(GOOD_INT);
        assertEquals(GOOD_INT, myJob.getMinMedium());
    }

    /**
     * Tests for bad minimum medium worker count.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMinMedium_BadInt_ExceptionThrown() {
        myJob.setMinMedium(BAD_INT);
    }

    /**
     * Test for proper setting of minimum heavy workers.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setMinHeavy_GoodInt_MinHeavySet() {
        myJob.setMinHeavy(GOOD_INT);
        assertEquals(GOOD_INT, myJob.getMinHeavy());
    }

    /**
     * Tests for bad min of heavy workers.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMinHeavy_BadInt_ExceptionThrown() {
        myJob.setMinHeavy(BAD_INT);
    }

    /**
     * Test for proper setting of the volunteer list.
     *
     * @author Gardner Gomes
     */
    @Test
    public void setVolunteers_GoodVolunteer_VolunteerAdded() {
        myJob.setVolunteers(theVolunteer.getUsername());
        List<String> vList = myJob.getVolunteers();
        assertEquals(theVolunteer.getUsername(), vList.get(0));
    }

    /**
     * Tests adding a bad volunteer to the volunteers list of a job.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setVolunteers_BadVolunteerName_ExceptionThrown() {
        myJob.setVolunteers(BAD_STRING);
    }

    /**
     * Tests for proper setting of the job notes.
     *
     * @author VG Gnome
     */
    @Test
    public void setNotes_GoodNotes_NotesSet() {
        myJob.setNotes(GOOD_STRING);
        assertEquals(GOOD_STRING, myJob.getNotes());
    }

    /**
     * Tests adding a bad note to the notes list.
     * 
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNotes_BadNotes_ExceptionThrown() {
        myJob.setNotes(BAD_STRING);
    }

    /**
     * Tests adding a good Name.
     * 
     * @author Gardner Gomes
     */
    public void setName_GoodName_NameSet() {
        myJob.setName(GOOD_STRING);
        assertEquals(GOOD_STRING, myJob.getName());
    }

    /**
     * Tests adding a good Name.
     * 
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setName_BadName_ExceptionThrown() {
        myJob.setName(BAD_STRING);
    }

    /**
     * Tests Duration.
     * 
     * @author Gardner Gomes
     */
    public void setDuration_GoodInt_DurationSet() {
        myJob.setDuration(GOOD_INT);
        assertEquals(myJob.getDuration(), GOOD_INT);
    }

    /**
     * Tests Duration.
     * 
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setDuration_IntLessThan1_ExceptionThrown() {
        myJob.setDuration(BAD_INT);
    }

    /**
     * Tests Duration.
     * 
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setDuration_IntGreaterThan2_ExceptionThrown() {
        myJob.setDuration(GOOD_INT);
    }

    /**
     * Tests Year.
     * 
     * @author Gardner Gomes
     */
    public void setYear_GoodYear_YearSet() {
        myJob.setYear(2017);
        assertEquals(myJob.getYear(), 2017);
    }

    /**
     * Tests Job#setYear(int) for bad int data.
     *
     * @author Gardner Gomes
     */
    @Test(expected = IllegalArgumentException.class)
    public void setYear_BadYear_ExceptionThrown() {
        myJob.setYear(BAD_INT);
        myJob.setYear(2099);
    }

    /**
     * Tests two separate Job objects for equality.
     *
     * @author Walter Weeks
     */
    @Test
    public void equals_EqualJobs_True() {
        Park park = new Park(theManager, "Wapato Park", "6500 S Sheridan Ave", "Tacoma", "WA", "98406");
        Job job1 = new Job(park, "12:00", "Rake leaves", "Raking", 1, 2, 10, 2017);
        Job job2 = new Job(park, "12:00", "Rake leaves", "Raking", 1, 2, 10, 2017);
        assertTrue(job1.equals(job2));
    }

    /**
     * Tests two separate Job objects for inequality.
     *
     * @author Walter Weeks
     */
    @Test
    public void equals_DifferentJobs_False() {
        Park park1 = new Park(theManager, "Wapato Park", "6500 S Sheridan Ave", "Tacoma", "WA", "98406");
        Park park2 = new Park(theManager, "Jefferson Park", "801 N Mason Ave", "Tacoma", "WA", "98406");
        Job job1 = new Job(park1, "1200", "Rake leaves", "Raking", 1, 2, 10, 2017);
        Job job2 = new Job(park2, "1200", "Rake leaves", "Raking", 1, 2, 10, 2017);
        assertFalse(job1.equals(job2));
    }
}
