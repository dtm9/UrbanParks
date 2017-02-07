package test;

import backend.Datastore;
import backend.Job;
import backend.Park;
import backend.ParkManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Basic unit tests for the Datastore class for sunny-day scenarios.
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

    /** Job 1 test fixture. */
    private Job myJob1;

    /** Job 2 test fixture. */
    private Job myJob2;

    /** Job 3 test fixture. */
    private Job myJob3;

    /** Job 4 test fixture. */
    private Job myJob4;

    /**
     * Sets up the test fixtures used by the unit tests.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        ParkManager manager1 = new ParkManager("billy@tacomaparks.com", "2535550000", "Billy Bob");
        ParkManager manager2 = new ParkManager("jane@gmail.com", "2536661111", "Jane Doe");
        ParkManager manager3 = new ParkManager("john@seattleparks.gov", "2067772222", "John Doe");

        myPark1 = new Park(manager1, "Wapato Park", "Tacoma", "WA", "98406");
        myPark2 = new Park(manager2, "Jefferson Park", "Tacoma", "WA", "98406");
        myPark3 = new Park(manager3, "Discovery Park", "Seattle", "WA", "98199");

        myJob1 = new Job(myPark1.getName(), "2017/03/01", "1030", "Raking leaves.");
        myJob2 = new Job(myPark2.getName(), "2017/03/05", "1345", "Pick up litter.");
        myJob3 = new Job(myPark3.getName(), "2017/04/01", "1500", "Build fence.");
        myJob4 = new Job(myPark1.getName(), "2017/03/02", "1440", "Paint fence.");

        myDatastore = new Datastore();

        myDatastore.addPark(myPark1);
        myDatastore.addPark(myPark2);
        myDatastore.addPark(myPark3);

        myDatastore.addJob(myJob1);
        myDatastore.addJob(myJob2);
        myDatastore.addJob(myJob3);
        myDatastore.addJob(myJob4);
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Tests to see if the current number of pending jobs is as expected for the test fixture.
     *
     * @author Walter Weeks
     */
    @Test
    public void testNumberOfPendingJobs1() {
        assertEquals(4, myDatastore.getNumberOfJobs());
    }

    /**
     * Tests to see if the number of previous jobs is as expected after a job has been removed from the pending list.
     */
    @Test
    public void testNumberOfPreviousJobs() {
        myDatastore.removeJob(myJob1);
        assertEquals(1, myDatastore.getNumberOfPreviousJobs());
    }

    @Test
    public void testGetJobsByCity() {
        List<Job> jobsInTacoma = myDatastore.getJobsByCity("Tacoma", "WA");
        assertEquals(3, jobsInTacoma.size());
    }
}
