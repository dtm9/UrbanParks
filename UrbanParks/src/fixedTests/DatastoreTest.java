package fixedTests;

import static org.junit.Assert.*;

import backend.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic unit tests for the Datastore class.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class DatastoreTest {

	//***** Test fixture(s) and setUp() ********************************************************************************

	/** Datastore test fixture. */
	private Datastore myDatastore;

	/** Park list auxiliary text fixture. */
	private List<Park> myParks;

	/** Job list auxiliary text fixture. */
	private List<Job> myJobs;

	/** ParkManager list auxiliary test fixture. */
	private List<ParkManager> myParkManagers;

	/** Volunteer list auxiliary test fixture. */
	private List<Volunteer> myVolunteers;

	/** Urban Parks Office Staff auxiliary test fixture. */
	private List<OfficeStaff> myOfficeStaff;

	/**
	 * Sets up the test fixture(s) used by the unit tests. This method is fairly long because of the requirement of
	 * building a small amount of data used by the Datastore methods to verify their proper working order.
	 *
	 * @author Walter Weeks
	 */
	@Before
	public void setUp() {
		// initialize the test fixtures
		myParkManagers = new ArrayList<>();
		myVolunteers = new ArrayList<>();
		myOfficeStaff = new ArrayList<>();
		myParks = new ArrayList<>();
		myJobs = new ArrayList<>();
		myDatastore = new Datastore();
		// populate the test fixtures
		populateAuxiliaryTestFixtures();
		populateDatastoreTestFixture();
	}

	//***** Helper method(s) *******************************************************************************************

	/**
	 * Helper method that populates the auxililary test fixture.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	private void populateAuxiliaryTestFixtures() {
		// populate myParkManagers list data structure w/ 4 total managers
		myParkManagers.add(new ParkManager("billy@tacomaparks.com", "2535550000", "Billy Bob"));
		myParkManagers.add(new ParkManager("jane@gmail.com", "2536661111", "Jane Doe"));
		myParkManagers.add(new ParkManager("john@seattleparks.gov", "2067772222", "John Doe"));

		// populate myParks list data structure w/ 5 total Parks where 1 ParkManager has 3 Parks under management
		myParks.add(new Park(myParkManagers.get(0), "Wapato Park", "Tacoma", "WA", "98406"));
		myParks.add(new Park(myParkManagers.get(1), "Jefferson Park", "Tacoma", "WA", "98406"));
		myParks.add(new Park(myParkManagers.get(2), "Discovery Park", "Seattle", "WA", "98199"));
		myParks.add(new Park(myParkManagers.get(0), "Baltimore Park", "Tacoma", "WA", "98407"));
		myParks.add(new Park(myParkManagers.get(0), "South Park", "Tacoma", "WA", "98409"));

		// populate myJobs list data structure w/ 8 total Jobs where Wapato Park has 4 Jobs and a Park Manager that manages 4 parks
		myJobs.add(new Job(myParks.get(0).getName(), "2017/03/01", "1030", "Raking leaves."));
		myJobs.add(new Job(myParks.get(0).getName(), "2017/03/05", "1345", "Pick up litter."));
		myJobs.add(new Job(myParks.get(1).getName(), "2017/04/01", "1500", "Build fence."));
		myJobs.add(new Job(myParks.get(3).getName(), "2017/03/02", "1440", "Paint fence."));
		myJobs.add(new Job(myParks.get(4).getName(), "2017/04/15", "1640", "Trail clearing"));
		myJobs.add(new Job(myParks.get(0).getName(), "2017/03/10", "1145", "Digging ditches."));
		myJobs.add(new Job(myParks.get(0).getName(), "2017/03/11", "1145", "More digging ditches."));
		myJobs.add(new Job(myParks.get(2).getName(), "2017/05/01", "1200", "Construct building."));

		// populate myVolunteers list data structure w/ 2 Volunteers
		myVolunteers.add(new Volunteer("steve@gmail.com", "2538883333",
				"Steve Jones", Volunteer.WorkGrade.MEDIUM));
		myVolunteers.add(new Volunteer("nancy@yahoo.com", "2069995432",
				"Nancy Hawkins", Volunteer.WorkGrade.HEAVY));

		// Set the 1st Job @ Wapato Park with 2 Volunteers and 1 volunteer having 2 pending jobs
		myJobs.get(0).setMyVolunteers(myVolunteers.get(0).getUsername());
		myJobs.get(0).setMyVolunteers(myVolunteers.get(1).getUsername());
		myJobs.get(1).setMyVolunteers(myVolunteers.get(0).getUsername());

		// Populate myOfficeStaff list data structure w/ 1 Office Staff
		myOfficeStaff.add(new OfficeStaff("randy@urbanparks.com", "2065551234", "Randy Johnson"));
	}

	/**
	 * Helper method that populates the myDatastore text fixture.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	private void populateDatastoreTestFixture() {
		// Add the Accounts to myDatastore
		for (int i = 0; i < myVolunteers.size(); i++) { 	// Volunteers
			myDatastore.addAccount(myVolunteers.get(i));
		}
		for (int i = 0; i < myParkManagers.size(); i++) { 	// Park Managers
			myDatastore.addAccount(myParkManagers.get(i));
		}
		for (int i = 0; i < myOfficeStaff.size(); i++) {    // Office Staff
			myDatastore.addAccount(myOfficeStaff.get(i));
		}
		// Add the Parks to myDatastore
		for (int i = 0; i < myParks.size(); i++) {
			myDatastore.addPark(myParks.get(i));
		}
		// Add the Jobs to myDatastore
		for (int i = 0; i < myJobs.size(); i++) {
			myDatastore.addJob(myJobs.get(i));
		}
	}

	//***** Unit test(s) ***********************************************************************************************

	/**
	 * Tests to see if the current number of pending jobs is as expected for the test fixture.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getNumberOfJobs_UnmodifiedDatastore_ShouldBeSameAsJobsListTestFixture() {
		assertEquals(myJobs.size(), myDatastore.getNumberOfJobs());
	}

	/**
	 * Tests to see if the number of previous jobs is as expected after twos jobs have
	 * been removed from the pending list.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void removeJob_RemovalOf2Jobs_ShouldBe2() {
		myDatastore.removeJob(myJobs.get(0));
		myDatastore.removeJob(myJobs.get(1));
		assertEquals(2, myDatastore.getNumberOfPreviousJobs());
	}

	/**
	 * Tests to see if the number of expected jobs within a particular city is as expected.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getJobsByCity_UnmodifiedDatastore_ShouldBe7JobsInTacoma() {
		List<Job> jobsInTacoma = myDatastore.getJobsByCity("Tacoma", "WA");
		assertEquals(7, jobsInTacoma.size());
	}

	/**
	 * Test to see if the number of jobs at particular park is as expected.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getJobsByPark_UnmodifiedDatastore_ShouldBe4Jobs() {
		List<Job> jobsAtWapato = myDatastore.getJobsByPark(myParks.get(0));
		assertEquals(4, jobsAtWapato.size());
	}

	/**
	 * Test to see if the number of jobs a particular volunteers is as expected.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getJobsByVolunteer_UnmodifiedDatastore_ShouldBe2() {
		List<Job> jobsByVolunteer = myDatastore.getJobsByVolunteer(myVolunteers.get(0));
		assertEquals(2, jobsByVolunteer.size());
	}

	/**
	 * Test to see if the number of jobs a particular park manager is as expected.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getJobsByParkManager_UnmodifiedDatastore_ShouldBe6Jobs() {
		List<Job> jobsByParkManager = myDatastore.getJobsByParkManager(myParkManagers.get(0));
		assertEquals(6, jobsByParkManager.size());
	}

	/**
	 * Test to see if the number of accounts is as expected.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getNumberOfAccounts_UnmodifiedDatastore_ShouldBeSumOfAccountTestFixturesSizes() {
		assertEquals(myVolunteers.size() + myParkManagers.size() + myOfficeStaff.size(), myDatastore.getNumberOfAccounts());
	}

	/**
	 * Test to see if the number of parks is as expected.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void getNumberOfParks_UnmodifiedDatastore_ShouldBeSizeOfParksTestFixture() {
		assertEquals(myParks.size(), myDatastore.getNumberOfParks());
	}

	/**
	 * Test to see if the Datastore#addJob(Job) works as expected for adding 1 pending job.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test
	public void addJob_AddingASingleJob_ShouldBeSizeOfJobsTextFixurePlus1() {
		Job newJob = new Job(myParks.get(0).getName(), "2017/05/18", "0600", "Build dome");
		myDatastore.addJob(newJob);
		assertEquals(myJobs.size() + 1, myDatastore.getNumberOfJobs());
	}

	//***** Unit test(s) looking or thrown exceptions for improper data *****************************************************

	/**
	 * Tests for NullPointerException when adding a null job.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = NullPointerException.class)
	public void addJob_AddingNullJob_ExceptionThrown() {
		myDatastore.addJob(null);
	}

	/**
	 * Tests for NullPointerException when adding a null account.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = NullPointerException.class)
	public void addAccount_AddingNullAccount_ExceptionThrown() {
		myDatastore.addAccount(null);
	}

	/**
	 * Tests for NullPointerException when adding a null park.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = NullPointerException.class)
	public void addPark_AddingANullPark_ExceptionThrown() {
		myDatastore.addPark(null);
	}

	/**
	 * Tests for NullPointerException when getting jobs by city, for null city.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = NullPointerException.class)
	public void getJobsByCity_SearchingForNullCity_ExceptionThrown() {
		myDatastore.getJobsByCity(null, "WA");
	}

	/**
	 * Tests for NullPointerException when getting jobs by city, for null state.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = NullPointerException.class)
	public void getJobsByCity_SearchingForNullState_ExceptionThrown() {
		myDatastore.getJobsByCity("Tacoma", null);
	}

	/**
	 * Tests for IllegalArgumentException when getting jobs by city, for empty city string.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getJobsByCity_SearchForEmptyStringCity_ExceptionThrown() {
		myDatastore.getJobsByCity("", "WA");
	}

	/**
	 * Tests for IllegalArgumentException when getting jobs by city, for 1 character state string.
	 *
	 * @author Walter Weeks (ww3@uw.edu)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getJobsByCity_SearchFor1CharState_ExceptionThrown() {
		myDatastore.getJobsByCity("Tacoma", "W");
	}
}
