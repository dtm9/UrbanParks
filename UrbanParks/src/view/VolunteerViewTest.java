package view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

/**
 * Test cases for the VolunterView class.
 * Changed visibility of minDaysAway to package during testing.
 * @author Peter Park (ragnarok@uw.edu)
 */
public class VolunteerViewTest {
    
    private Volunteer myVolunteer;
    private ParkManager myParkManager;
    private VolunteerView myView;
    private Datastore myDatastore;
    private Park myPark;
    private StringBuilder mySB;
    
    /**
     * Sets up the text fixtures.
     * @author Peter Park
     */
    @Before
    public void setUp() throws Exception {
        myDatastore = new Datastore();
        myVolunteer = new Volunteer("peterpark@gmail.com", "5551112222", "Peter Park");
        myParkManager = new ParkManager("batman@tacomaparks.com", "5551112222", "Dylan Miller");
        myPark = new Park(myParkManager, "Franklin Park", "1201 S Puget Sound Ave", "Tacoma", "WA", "98408");
        myView = new VolunteerView(myVolunteer);
        mySB = new StringBuilder();
        
        myDatastore.addAccount(myVolunteer);
        myDatastore.addAccount(myParkManager);
    }
    
    /**
     * Tests for a valid/good job passed in.
     * @author Peter Park
     */
    @Test
    public void minDaysAway_GoodJob_ListThisJob() {
        ZoneId z = ZoneId.of("America/Los_Angeles");
        LocalDate today = LocalDate.now(z);
        today = today.plusDays(2); // 2 days away
        Job testJob1 = new Job(myPark, "10:00", "Test VolunteerViewTest Case 1","Testing", 1, today.getDayOfMonth(), today.getMonthValue(), today.getYear());
        myDatastore.addJob(testJob1);
        assertTrue(myView.minDaysAway(testJob1));
    }

    /**
     * Tests for a job that starts sooner than the minimum number of days away.
     * @author Peter Park
     */
    @Test
    public void minDaysAway_BadJob_DoNotListThisJob() {
        ZoneId z = ZoneId.of("America/Los_Angeles");
        LocalDate today = LocalDate.now(z);
        today = today.plusDays(1); // 1 day away
        Job testJob2 = new Job(myPark, "10:00", "Test VolunteerViewTest Case 2","Testing", 1, today.getDayOfMonth(), today.getMonthValue(), today.getYear());
        myDatastore.addJob(testJob2);
        assertFalse("This will fail", myView.minDaysAway(testJob2));
    }
    
    /**
     * Tests for a past job passed in.
     * @author Peter Park
     */
    @Test
    public void minDaysAway_PastJob_DoNotListThisJob() {
        ZoneId z = ZoneId.of("America/Los_Angeles");
        LocalDate today = LocalDate.now(z);
        today = today.minusDays(1); // 1 day in the past
        Job testJob3 = new Job(myPark, "10:00", "Test VolunteerViewTest Case 3","Testing", 1, today.getDayOfMonth(), today.getMonthValue(), today.getYear());
        myDatastore.addJob(testJob3);
        assertFalse("This will fail", myView.minDaysAway(testJob3));
    }
    /**
     * Tests if a same day sign up will return a false flag 
     * @author Ethan Young
     */
    public void isSameDayJob_SameDay_ReturnFalseFlag(){
        Job testJob4 = new Job(myPark, "11:00", "Test VolunteerViewTest case 4", "testing", 1, 23,3,2017);
        Job testJob5 = new Job(myPark, "11:00", "Test VolunteerViewTest case 5", "testing2", 1, 23,3,2017);
        List<Job> jobList = new ArrayList<Job>();
        jobList.add(testJob4);
        assertFalse("This will fail", myView.isSameDayJob(testJob5.getDay(), testJob5.getMonth(), jobList, mySB));
    }
    
    /**
     * Tests if a different day sign up will return a true flag
     * @author Ethan Young
     */
    public void isSameDayJob_NotSameDay_ReturnTrueFlag(){
        Job testJob6 = new Job(myPark, "11:00", "Test VolunteerViewTest case 4", "testing", 1, 23,3,2017);
        Job testJob7 = new Job(myPark, "11:00", "Test VolunteerViewTest case 5", "testing2", 1, 24,3,2017);
        List<Job> jobList = new ArrayList<Job>();
        jobList.add(testJob6);
        assertTrue("This will be true", myView.isSameDayJob(testJob7.getDay(), testJob7.getMonth(), jobList, mySB));
    }
}
