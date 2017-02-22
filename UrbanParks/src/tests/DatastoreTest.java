package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic unit tests for the Datastore class.
 *
 * @author Walter Weeks
 */
public class DatastoreTest {

	//***** Test fixture(s) and setUp() ********************************************************************************

	/** Datastore test fixture. */
	private Datastore myDatastore;

	/** Park list auxiliary text fixture. */
	private List<Park> myParks;

	/** Job list auxiliary text fixture. */
	private List<Job> myJobs;

	/** Account list of every person participating in the Urban Parks system. */
	private List<AbstractAccount> myAccounts;

	/** ParkManager list auxiliary test fixture. */
	private List<ParkManager> myParkManagers;

	/** Volunteer list auxiliary test fixture. */
	private List<Volunteer> myVolunteers;

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
		myAccounts = new ArrayList<>();
		myParks = new ArrayList<>();
		myJobs = new ArrayList<>();
		myDatastore = new Datastore();
		// populate the test fixtures
		populateAuxiliaryTestFixtures();
		populateDatastoreTestFixture();
	}

	//***** Helper method(s) *******************************************************************************************

	/**
	 * Helper method that populates the auxiliary test fixtures.
	 *
	 * @author Walter Weeks
	 */
	private void populateAuxiliaryTestFixtures() {
		// populate myParkManagers list data structure w/ 4 total managers
		myParkManagers.add(new ParkManager("billy@tacomaparks.com", "2535550000", "Billy Bob"));
		myParkManagers.add(new ParkManager("jane@gmail.com", "2536661111", "Jane Doe"));
		myParkManagers.add(new ParkManager("john@seattleparks.gov", "2067772222", "John Doe"));

		// populate myParks list data structure w/ 5 total Parks where 1 ParkManager has 3 Parks under management
		myParks.add(new Park(myParkManagers.get(0),"Wapato Park","6500 S Sheridan Ave", "Tacoma", "WA", "98406"));
		myParks.add(new Park(myParkManagers.get(1),"Jefferson Park","801 N Mason Ave","Tacoma", "WA", "98406"));
		myParks.add(new Park(myParkManagers.get(2),"Discovery Park","3801 Discovery Park Blvd","Seattle", "WA", "98199"));
		myParks.add(new Park(myParkManagers.get(0), "Baltimore Park", "4716 N Baltimore St","Tacoma", "WA", "98407"));
		myParks.add(new Park(myParkManagers.get(0), "South Park","4851 S Tacoma Way","Tacoma", "WA", "98409"));

		// populate myJobs list data structure w/ 8 total Jobs where Wapato Park has 4 Jobs and a Park Manager that manages 4 parks
		myJobs.add(new Job(myParks.get(0), "1030", "We will be raking leaves.",
				"Raking leaves", 1, 1, 3, 2017));
		myJobs.add(new Job(myParks.get(0), "1345", "We will be picking up litter.",
				"Pick up litter", 1, 5, 3, 2017));
		myJobs.add(new Job(myParks.get(1), "1500", "We will be building a fence.",
				"Build fence", 1, 1, 4, 2017));
		myJobs.add(new Job(myParks.get(3), "1400", "We will be painting a fence.",
				"Paint fence", 1, 2, 3, 2017));
		myJobs.add(new Job(myParks.get(4), "1640", "We will be clearing a trail",
				"Trail clearing", 1, 15, 4, 2017));
		myJobs.add(new Job(myParks.get(0), "1145", "We will be digging ditches.",
				"Digging ditches", 1, 10, 3, 2017));
		myJobs.add(new Job(myParks.get(0), "1145", "We will be digging ditches again.",
				"More digging ditches", 1, 15, 3, 2017));
		myJobs.add(new Job(myParks.get(2), "1200", "We will be constructing a new building",
				"Construct building", 1, 1, 5, 2017));

		// populate myVolunteers list data structure w/ 2 Volunteers
		myVolunteers.add(new Volunteer("steve@gmail.com", "2538883333",
				"Steve Jones"));
		myVolunteers.add(new Volunteer("nancy@yahoo.com", "2069995432",
				"Nancy Hawkins"));

		// Set the 1st Job @ Wapato Park with 2 Volunteers and 1 volunteer having 2 pending jobs
		myJobs.get(0).setVolunteers(myVolunteers.get(0).getUsername());
		myJobs.get(0).setVolunteers(myVolunteers.get(1).getUsername());
		myJobs.get(1).setVolunteers(myVolunteers.get(0).getUsername());

		// Populate myOfficeStaff list data structure w/ 1 Office Staff
		//myOfficeStaff.add(new OfficeStaff("randy@urbanparks.com", "2065551234", "Randy Johnson"));

		// Populate the accounts list
        for (int i = 0; i < myVolunteers.size(); i++) { 	// Volunteers
            myAccounts.add(myVolunteers.get(i));
        }
        for (int i = 0; i < myParkManagers.size(); i++) { 	// Park Managers
            myAccounts.add(myParkManagers.get(i));
        }
	}

	/**
	 * Helper method that populates the myDatastore text fixture.
	 *
	 * @author Walter Weeks
	 */
	private void populateDatastoreTestFixture() {
		// Add the Accounts to myDatastore
		for (int i = 0; i < myVolunteers.size(); i++) { 	// Volunteers
			myDatastore.addAccount(myVolunteers.get(i));
		}
		for (int i = 0; i < myParkManagers.size(); i++) { 	// Park Managers
			myDatastore.addAccount(myParkManagers.get(i));
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
	 * @author Walter Weeks
	 */
	@Test
	public void getNumberOfJobs_UnmodifiedDatastore_ShouldBeSameAsJobsListTestFixture() {
		assertEquals(myJobs.size(), myDatastore.getNumberOfJobs());
	}

	/**
	 * Tests to see if the number of previous jobs is as expected after twos jobs have
	 * been removed from the pending list.
	 *
	 * @author Walter Weeks
	 */
	@Test
	public void removeJob_RemovalOf2Jobs_ShouldBe2() {
		myDatastore.removeJob(myJobs.get(0));
		myDatastore.removeJob(myJobs.get(1));
		assertEquals(2, myDatastore.getNumberOfPreviousJobs());
	}

	//TODO these tests are broken due to datastore changes. Are they necessary? Should we migrate them to other test classes?
//	/**
//	 * Tests to see if the number of expected jobs within a particular city is as expected.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test
//	public void getJobsByCity_UnmodifiedDatastore_ShouldBe7JobsInTacoma() {
//		List<Job> jobsInTacoma = myDatastore.getJobsByCity("Tacoma", "WA");
//		assertEquals(7, jobsInTacoma.size());
//	}
//
//	/**
//	 * Tests to see if the number of jobs at particular park is as expected.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test
//	public void getJobsByPark_UnmodifiedDatastore_ShouldBe4Jobs() {
//		List<Job> jobsAtWapato = myDatastore.getJobsByPark(myParks.get(0));
//		assertEquals(4, jobsAtWapato.size());
//	}
//
//	/**
//	 * Tests to see if the number of jobs a particular volunteers is as expected.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test
//	public void getJobsByVolunteer_UnmodifiedDatastore_ShouldBe2() {
//		List<Job> jobsByVolunteer = myDatastore.getJobsByVolunteer(myVolunteers.get(0));
//		assertEquals(2, jobsByVolunteer.size());
//	}
//
//	/**
//	 * Tests to see if the number of jobs a particular park manager is as expected.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test
//	public void getJobsByParkManager_UnmodifiedDatastore_ShouldBe6Jobs() {
//		List<Job> jobsByParkManager = myDatastore.getJobsByParkManager(myParkManagers.get(0));
//		assertEquals(6, jobsByParkManager.size());
//	}

	/**
	 * Tests to see if the number of accounts is as expected.
	 *
	 * @author Walter Weeks
	 */
	@Test
	public void getNumberOfAccounts_UnmodifiedDatastore_ShouldBeSumOfAccountTestFixturesSizes() {
		assertEquals(myVolunteers.size() + myParkManagers.size(), myDatastore.getNumberOfAccounts());
	}

	/**
	 * Tests to see if the number of parks is as expected.
	 *
	 * @author Walter Weeks
	 */
	@Test
	public void getNumberOfParks_UnmodifiedDatastore_ShouldBeSizeOfParksTestFixture() {
		assertEquals(myParks.size(), myDatastore.getNumberOfParks());
	}

	/**
	 * Tests to see if the Datastore#addJob(Job) works as expected for adding 1 pending job.
	 *
	 * @author Walter Weeks
	 */
	@Test
	public void addJob_AddingASingleJob_ShouldBeSizeOfJobsTextFixurePlus1() {
		Job newJob = new Job(myParks.get(0), "0600", "We will be building a job",
				"Build dome", 1, 18, 5, 2017);
		myDatastore.addJob(newJob);
		assertEquals(myJobs.size() + 1, myDatastore.getNumberOfJobs());
	}

    /**
     * Test to see if the default value of maximum number of pending jobs allowed by system is set properly.
     *
     * @author Walter Weeks
     */
	@Test
    public void getMaxPendingJobs_UnmodifiedDatastore_ShouldBeDefaultValue() {
	    assertEquals(Datastore.MAX_PENDING_JOBS_DEFAULT, myDatastore.getMaxPendingJobs());
    }

    /**
     * Tests to see if max pending jobs can be changed to an expected value;
     *
     * @author Walter Weeks
     */
    @Test
    public void setMaxPendingJobs_ModifyingMaxPendingJobs_ShouldBeChangedToExpectedValue() {
        myDatastore.setMaxPendingJobs(myDatastore.getMaxPendingJobs() - 2);
        assertEquals(Datastore.MAX_PENDING_JOBS_DEFAULT - 2, myDatastore.getMaxPendingJobs());
    }

    /**
     * Tests to see if the Park list is as expected.
     *
     * @author Walter Weeks
     */
    @Test
    public void getAllParks_UnmodifedDatatstore_ShouldBe8() {
        for (int i = 0; i < myDatastore.getAllParks().size(); i++) {
            assertEquals(myParks.get(i), myDatastore.getAllParks().get(i));
        }
    }

    /**
     * Tests to see if the Accounts list is as expected.
     *
     * @author Walter Weeks
     */
    @Test
    public void getAllAccounts_UnmodifedDatatstore_ShouldBe8() {
        for (int i = 0; i < myDatastore.getAllAccounts().size(); i++) {
            assertEquals(myAccounts.get(i), myDatastore.getAllAccounts().get(i));
        }
    }

    //***** Unit test(s) looking or thrown exceptions for improper data *****************************************************

	/**
	 * Tests for NullPointerException when adding a null job.
	 *
	 * @author Walter Weeks
	 */
	@Test(expected = NullPointerException.class)
	public void addJob_AddingNullJob_ExceptionThrown() {
		myDatastore.addJob(null);
	}

	/**
	 * Tests for NullPointerException when adding a null account.
	 *
	 * @author Walter Weeks
	 */
	@Test(expected = NullPointerException.class)
	public void addAccount_AddingNullAccount_ExceptionThrown() {
		myDatastore.addAccount(null);
	}

	/**
	 * Tests for NullPointerException when adding a null park.
	 *
	 * @author Walter Weeks
	 */
	@Test(expected = NullPointerException.class)
	public void addPark_AddingANullPark_ExceptionThrown() {
		myDatastore.addPark(null);
	}

	//TODO these tests were broken by datastore changes. Do we need them? Should we migrate them to other test classes?
//	/**
//	 * Tests for NullPointerException when getting jobs by city, for null city.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test(expected = NullPointerException.class)
//	public void getJobsByCity_SearchingForNullCity_ExceptionThrown() {
//		myDatastore.getJobsByCity(null, "WA");
//	}
//
//	/**
//	 * Tests for NullPointerException when getting jobs by city, for null state.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test(expected = NullPointerException.class)
//	public void getJobsByCity_SearchingForNullState_ExceptionThrown() {
//		myDatastore.getJobsByCity("Tacoma", null);
//	}
//
//	/**
//	 * Tests for IllegalArgumentException when getting jobs by city, for empty city string.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test(expected = IllegalArgumentException.class)
//	public void getJobsByCity_SearchForEmptyStringCity_ExceptionThrown() {
//		myDatastore.getJobsByCity("", "WA");
//	}
//
//	/**
//	 * Tests for IllegalArgumentException when getting jobs by city, for 1 character state string.
//	 *
//	 * @author Walter Weeks
//	 */
//	@Test(expected = IllegalArgumentException.class)
//	public void getJobsByCity_SearchFor1CharState_ExceptionThrown() {
//		myDatastore.getJobsByCity("Tacoma", "W");
//	}

	/**
	 * Tests for IllegalStateException when attempting to exceed the maximum number of pending jobs by 1.
	 *
	 * @author Walter Weeks
	 */
	@Test(expected = IllegalStateException.class)
	public void addJob_AddMaxPendingJobsPlus1_ExceptionThrown() {
		Datastore datastore = new Datastore();
		for (int i = 0; i < datastore.getMaxPendingJobs() + 1; i++) {
			datastore.addJob(new Job(myParks.get(0), Integer.toString(1200 + i), "We will be raking leaves",
					"Raking leaves", 1, 1, (i % 12) + 1, 2017));
		}
	}

    /**
     * Test for IllegalStateException when attempting to exceed the maximum number of pending jobs per
     * day by 1.
     *
     * @author Walter Weeks
     */
	@Test(expected = IllegalStateException.class)
    public void addJob_AddMaxPendingJobsPerDayPlus1_ExceptionExpected() {
	    // Add a second job on 3 Mar 2017
        myDatastore.addJob(new Job(myParks.get(0), "1130", "We will be raking leaves.",
                "Raking leaves", 1, 1, 3, 2017));
        // Add a third job on 3 Mar 2017
        myDatastore.addJob(new Job(myParks.get(0), "1230", "We will be raking leaves.",
                "Raking leaves", 1, 1, 3, 2017));
        // Add a fourth job on 3 Mar 2017
        myDatastore.addJob(new Job(myParks.get(0), "1330", "We will be raking leaves.",
                "Raking leaves", 1, 1, 3, 2017));
    }

    /**
     * Tests for IllegalArgrumentException when attempting to set the maximum number of pending jobs to 0.
     *
     * @author Walter Weeks
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMaxPendingJobs_SetMaxPendingJobsTo0_ExceptionExpected() {
        myDatastore.setMaxPendingJobs(0);
    }
}
