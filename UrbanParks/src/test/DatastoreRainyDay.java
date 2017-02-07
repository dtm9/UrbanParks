package test;

import backend.Datastore;
import backend.Job;
import backend.Park;
import backend.ParkManager;
import org.junit.Before;
import org.junit.Test;

import static test.TestConstants.BAD_STRING;

/**
 * Basic unit tests for the Datastore class for rainy-day scenarios, i.e., bad/improper data.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class DatastoreRainyDay {

    //***** Test fixture(s) and setUp() ********************************************************************************

    /** Datastore test fixture. */
    private Datastore myDatastore;

    /**
     * Sets up the test fixtures used by the unit tests.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        ParkManager parkManager = new ParkManager("billy@tacomaparks.com", "2535550000", "Billy Bob");
        Park wapatoPark = new Park(parkManager, "Wapato Park", "Tacoma", "WA", "98406");
        Job job = new Job(wapatoPark.getName(), "2017/03/01", "1030", "Raking leaves.");

        myDatastore = new Datastore();
        myDatastore.addJob(job);
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Tests for NullPointerException when adding a null job.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test(expected = NullPointerException.class)
    public void testAddJobNull() {
        myDatastore.addJob(null);
    }

    /**
     * Tests for NullPointerException when adding a null account.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test(expected = NullPointerException.class)
    public void testAddAccountNull() {
        myDatastore.addAccount(null);
    }

    /**
     * Tests for NullPointerException when adding a null park.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test(expected = NullPointerException.class)
    public void testAddParkNull() {
        myDatastore.addPark(null);
    }

    /**
     * Tests for NullPointerException when removing a null Job.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveJobNull() {
        myDatastore.removeJob(null);
    }

    /**
     * Tests for NullPointerException when getting jobs by park with a null Park object.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test(expected = NullPointerException.class)
    public void testGetJobsByParkNull() {
        myDatastore.getJobsByPark(null);
    }

    /**
     * Tests for NullPointerException when getting jobs by city, for null city.
     *
     * @author Walter Weeks
     */
    @Test(expected = NullPointerException.class)
    public void testGetJobsByCityNullX() {
        myDatastore.getJobsByCity(null, "WA");
    }

    /**
     * Tests for NullPointerException when getting jobs by city, for null state.
     *
     * @author Walter Weeks
     */
    @Test(expected = NullPointerException.class)
    public void testGetJobsByCityXNull() {
        myDatastore.getJobsByCity("Tacoma", null);
    }


    /**
     * Tests for IllegalArgumentException when getting jobs by city, for empty city string.
     *
     * @author Walter Weeks
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetJobsByCityBadX() {
        myDatastore.getJobsByCity("", "WA");
    }

    /**
     * Tests for IllegalArgumentException when getting jobs by city, for 1 character state string.
     *
     * @author Walter Weeks
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetJobsByCityXBad() {
        myDatastore.getJobsByCity("Tacoma", "W");
    }
}
