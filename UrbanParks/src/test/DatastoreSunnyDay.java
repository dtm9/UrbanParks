package test;

import backend.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Basic unit tests for the Datastore class for sunny-day scenarios, i.e., good/proper data.
 *
 * TODO: Clean up these unit tests with Lists instead of a bunch of test fixtures.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class DatastoreSunnyDay {

    //***** Test fixture(s) and setUp() ********************************************************************************

    /** Datastore test fixture. */
    private Datastore myDatastore;

    /** Park 1 test fixture. */
    private Park myPark1;

    /** Park 2 test fixture. */
    private Park myPark2;

    /** Park 3 test fixture. */
    private Park myPark3;

    /** Park 4 test fixture. */
    private Park myPark4;

    /** Job 1 test fixture. */
    private Job myJob1;

    /** Job 2 test fixture. */
    private Job myJob2;

    /** Job 3 test fixture. */
    private Job myJob3;

    /** Job 4 test fixture. */
    private Job myJob4;

    /** Park Manager 1 test fixture. */
    private ParkManager myParkManager1;

    /** Volunteer 1 test fixture. */
    private Volunteer myVolunteer1;

    /**
     * Sets up the test fixtures used by the unit tests.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        myParkManager1 = new ParkManager("billy@tacomaparks.com", "2535550000", "Billy Bob");
        ParkManager parkManager2 = new ParkManager("jane@gmail.com", "2536661111", "Jane Doe");
        ParkManager parkManager3 = new ParkManager("john@seattleparks.gov", "2067772222", "John Doe");

        myPark1 = new Park(myParkManager1, "Wapato Park", "Tacoma", "WA", "98406");
        myPark2 = new Park(parkManager2, "Jefferson Park", "Tacoma", "WA", "98406");
        myPark3 = new Park(parkManager3, "Discovery Park", "Seattle", "WA", "98199");
        myPark4 = new Park(myParkManager1, "Baltimore Park", "Tacoma", "WA", "98407");

        myJob1 = new Job(myPark1.getName(), "2017/03/01", "1030", "Raking leaves.");
        myJob2 = new Job(myPark2.getName(), "2017/03/05", "1345", "Pick up litter.");
        myJob3 = new Job(myPark3.getName(), "2017/04/01", "1500", "Build fence.");
        myJob4 = new Job(myPark1.getName(), "2017/03/02", "1440", "Paint fence.");

        myVolunteer1 = new Volunteer("steve@gmail.com", "2538883333",
                "Steve Jones", Volunteer.WorkGrade.MEDIUM);

        myJob1.setMyVolunteers(myVolunteer1.getUsername());
        myJob2.setMyVolunteers(myVolunteer1.getUsername());

        myDatastore = new Datastore();

        // Add the accounts to the datastore
        myDatastore.addAccount(myVolunteer1);
        myDatastore.addAccount(myParkManager1);
        myDatastore.addAccount(parkManager2);
        myDatastore.addAccount(parkManager3);

        // Add the parks to the datastore
        myDatastore.addPark(myPark1);
        myDatastore.addPark(myPark2);
        myDatastore.addPark(myPark3);
        myDatastore.addPark(myPark4);

        // Total of 6 pending jobs; 5 in Tacoma where 4 are at Wapato Park.
        myDatastore.addJob(myJob1);
        myDatastore.addJob(myJob2);
        myDatastore.addJob(myJob3);
        myDatastore.addJob(myJob4);
        myDatastore.addJob(new Job(myPark1.getName(), "2017/03/10", "1145", "Digging ditches."));
        myDatastore.addJob(new Job(myPark1.getName(), "2017/03/11", "1145", "More digging ditches."));
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Tests to see if the current number of pending jobs is as expected for the test fixture.
     *
     * @author Walter Weeks
     */
    @Test
    public void testNumberOfPendingJobs1() {
        assertEquals(6, myDatastore.getNumberOfJobs());
    }

    /**
     * Tests to see if the number of previous jobs is as expected after a job has been removed from the pending list.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testNumberOfPreviousJobs() {
        myDatastore.removeJob(myJob1);
        assertEquals(1, myDatastore.getNumberOfPreviousJobs());
    }

    /**
     * Tests to see if the number of expected jobs within a particular city is as expected.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testGetJobsByCity() {
        List<Job> jobsInTacoma = myDatastore.getJobsByCity("Tacoma", "WA");
        assertEquals(5, jobsInTacoma.size());
    }

    /**
     * Test to see if the number of jobs at particular park is as expected.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testGetJobsByPark() {
        List<Job> jobsAtWapato = myDatastore.getJobsByPark(myPark1);
        assertEquals(4, jobsAtWapato.size());
    }

    /**
     * Test to see if the number of jobs a particular volunteers is as expected.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testGetJobsByVolunteer() {
        List<Job> jobsByVolunteer = myDatastore.getJobsByVolunteer(myVolunteer1);
        assertEquals(2, jobsByVolunteer.size());
    }

    /**
     * Test to see if the number of jobs a particular park manager is as expected.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testGetJobsByParkManager() {
        List<Job> jobsByParkManager = myDatastore.getJobsByParkManager(myParkManager1);
        assertEquals(4, jobsByParkManager.size());
    }

    /**
     * Test to see if the number of accounts is as expected.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testNumberOfAccounts() {
        assertEquals(4, myDatastore.getNumberOfAccounts());
    }

    /**
     * Test to see if the number of parks is as expected.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testNumberOfParks() {
        assertEquals(4, myDatastore.getNumberOfParks());
    }

    /**
     * Test Datastore#addJob(Job).
     */
    @Test
    public void testAddJob() {
        Job newJob = new Job(myPark1.getName(), "2017/05/18", "0600", "Build building.");
        myDatastore.addJob(newJob);
        assertEquals(7, myDatastore.getNumberOfJobs());
    }

}
