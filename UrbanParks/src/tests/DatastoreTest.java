package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.AbstractAccount;
import model.Datastore;
import model.InvalidJobDurationException;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

/**
 * Basic unit tests for the Datastore class.
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
	
	/** Used for dynamically selecting current system dates. */
	Calendar myCal;

	/**
	 * Sets up the test fixture(s) used by the unit tests. This method is fairly long because of the requirement of
	 * building a small amount of data used by the Datastore methods to verify their proper working order.
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
		myCal = Calendar.getInstance();
		myCal.setTime(new Date());
		myDatastore = new Datastore();
		// populate the test fixtures
		populateAuxiliaryTestFixtures();
		populateDatastoreTestFixture();
		
	}

	//***** Helper method(s) *******************************************************************************************

	/**
	 * Helper method that populates the auxiliary test fixtures.
	 * @author Walter Weeks
	 * @author Dylan Miller
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
		
		myCal.add(Calendar.DATE, 5); //5 days from today
		myJobs.add(new Job(myParks.get(0), "1030", "We will be raking leaves.",
                "Raking leaves", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
		
		myCal.add(Calendar.DATE, 1); //6 days from today
        myJobs.add(new Job(myParks.get(0), "1345", "We will be picking up litter.",
                "Pick up litter", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
        
        myCal.add(Calendar.DATE, 1); //7 days from today
        myJobs.add(new Job(myParks.get(1), "1500", "We will be building a fence.",
                "Build fence", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
        myJobs.add(new Job(myParks.get(3), "1400", "We will be painting a fence.",
                "Paint fence", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
        
        myCal.add(Calendar.DATE, 1); //8 days from today
        myJobs.add(new Job(myParks.get(4), "1640", "We will be clearing a trail",
                "Trail clearing", 1, myCal.get(Calendar.DATE), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
        
        myCal.add(Calendar.DATE, 1); //9 days from today
        myJobs.add(new Job(myParks.get(0), "1145", "We will be digging ditches.",
                "Digging ditches", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
        myJobs.add(new Job(myParks.get(1), "1145", "We will be clearing pathways.",
                "Clearing pathways", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
        myJobs.add(new Job(myParks.get(2), "1200", "We will be constructing a new building",
                "Construct building", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));

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
	 * @author Walter Weeks
	 */
	@Test
	public void getNumberOfJobs_AddMultipleJobs_AllJobsAdded() {
		assertEquals(myJobs.size(), myDatastore.getNumberOfJobs());
	}

	/**
	 * Tests to see if the number of accounts is as expected.
	 * @author Walter Weeks
	 */
	@Test
	public void getNumberOfAccounts_AddMultipleAccounts_AllTestFixtureAccountsShouldBeAdded() {
		assertEquals(myVolunteers.size() + myParkManagers.size(), myDatastore.getNumberOfAccounts());
	}

	/**
	 * Tests to see if the number of parks is as expected.
	 * @author Walter Weeks
	 */
	@Test
	public void getNumberOfParks_AddMultipleParks_AllTestFixtureParksShouldBeAdded() {
		assertEquals(myParks.size(), myDatastore.getNumberOfParks());
	}

	/**
	 * Tests to see if the Datastore#addJob(Job) works as expected for adding 1 pending job.
	 * @author Walter Weeks
	 */
	@Test
	public void addJob_AddingASingleJob_ShouldBeSizeOfJobsTextFixurePlus1() {
		myCal.add(Calendar.DATE, 1); // 10 Days from today
		Job newJob = new Job(myParks.get(0), "0600", "We will be building a green house",
				"Building green house", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
		myDatastore.addJob(newJob);
		assertEquals(myJobs.size() + 1, myDatastore.getNumberOfJobs());
	}
	
	/**
	 * Tests to see if the Datastore#hasMaxJobsAlreadyOnJobDate(Job) returns true if a particular day has exactly
	 * the maximum number of allowed pending jobs.
	 * NOTE: This was added for the Unit Testing 2 homework.
	 * @author Walter Weeks
	 */
	@Test
	public void hasMaxJobsAlreadyOnJobDate_AddingExactlyTheMaximumPendingJobsAllowedOnGivenDay_ShouldReturnTrue() {
		myCal.add(Calendar.DATE, 1); // 10 Days from today
		
		for (int i = 0; i < Datastore.MAX_PENDING_JOBS_PER_DAY_DEFAULT; i++) {
			myDatastore.addJob(new Job(myParks.get(i % myDatastore.getNumberOfParks()), "1145", "We will be digging ditches.",
	                "Digging ditches", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
		}
		
		Job anotherJob = new Job(myParks.get(4), "1145", "We will be digging ditches.",
                "Digging ditches", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
		LocalDate startDate = LocalDate.of(anotherJob.getYear(), anotherJob.getMonth(), anotherJob.getDay());
		assertTrue(myDatastore.hasMaxJobsAlreadyOnJobDate(startDate, anotherJob.getDuration()));
	}
	
	/**
	 * Tests to see if the Datastore#hasMaxJobsAlreadyOnJobDate(Job) returns false if a particular day has exactly
	 * the maximum number of allowed pending jobs minus one, i.e., one more job can be placed on that day.
	 * NOTE: This was added for the Unit Testing 2 homework.
	 * @author Walter Weeks
	 */
	@Test
	public void hasMaxJobsAlreadyOnJobDate_AddingTheMaximumPendingJobsAllowedOnGivenDayMinusOne_ShouldReturnFalse() {
		myCal.add(Calendar.DATE, 1); // 10 Days from today
		
		for (int i = 0; i < Datastore.MAX_PENDING_JOBS_PER_DAY_DEFAULT - 1; i++) {
			myDatastore.addJob(new Job(myParks.get(i % myDatastore.getNumberOfParks()), "1145", "We will be digging ditches.",
	                "Digging ditches", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
		}
		
		Job anotherJob = new Job(myParks.get(4), "1145", "We will be digging ditches.",
                "Digging ditches", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
		LocalDate startDate = LocalDate.of(anotherJob.getYear(), anotherJob.getMonth(), anotherJob.getDay());
		assertFalse(myDatastore.hasMaxJobsAlreadyOnJobDate(startDate, anotherJob.getDuration()));
	}


    /**
     * Test to see if the default value of maximum number of pending jobs allowed by system is set properly.
     * @author Walter Weeks
     */
	@Test
    public void getMaxPendingJobs_InitializeMaxPendingJobsToDefaultValue_ShouldBeDefaultValue() {
	    assertEquals(Datastore.MAX_PENDING_JOBS_DEFAULT, myDatastore.getMaxPendingJobs());
    }

    /**
     * Tests to see if max pending jobs can be changed to an expected value;
     * @author Walter Weeks
     */
    @Test
    public void setMaxPendingJobs_ModifyingMaxPendingJobs_ShouldBeChangedToNewValue() {
        myDatastore.setMaxPendingJobs(myDatastore.getMaxPendingJobs() - 2);
        assertEquals(Datastore.MAX_PENDING_JOBS_DEFAULT - 2, myDatastore.getMaxPendingJobs());
    }

    /**
     * Tests to see if the Park list is as expected.
     * @author Walter Weeks
     */
    @Test
    public void getAllParks_AddMultipleParks_AllParksShouldBeAdded() {
        for (int i = 0; i < myDatastore.getAllParks().size(); i++) {
            assertEquals(myParks.get(i), myDatastore.getAllParks().get(i));
        }
    }

    /**
     * Tests to see if the Accounts list is as expected.
     * @author Walter Weeks
     */
    @Test
    public void getAllAccounts_AddMultipleAccounts_AllAccountsShouldBeAdded() {
        for (int i = 0; i < myDatastore.getAllAccounts().size(); i++) {
            assertEquals(myAccounts.get(i), myDatastore.getAllAccounts().get(i));
        }
    }

    //***** Unit test(s) looking or thrown exceptions for improper data *****************************************************

	/**
	 * Tests for NullPointerException when adding a null job.
	 * @author Walter Weeks
	 */
	@Test(expected = NullPointerException.class)
	public void addJob_AddingNullJob_ExceptionThrown() {
		myDatastore.addJob(null);
	}

	/**
	 * Tests for NullPointerException when adding a null account.
	 * @author Walter Weeks
	 */
	@Test(expected = NullPointerException.class)
	public void addAccount_AddingNullAccount_ExceptionThrown() {
		myDatastore.addAccount(null);
	}

	/**
	 * Tests for NullPointerException when adding a null park.
	 * @author Walter Weeks
	 */
	@Test(expected = NullPointerException.class)
	public void addPark_AddingANullPark_ExceptionThrown() {
		myDatastore.addPark(null);
	}

	/**
	 * Tests to see if the Datastore#addJob(Job) does not allow for adding more than the
	 * maximum number of pending jobs on a given date.
	 * NOTE: This was added for the Unit Testing 2 homework.
	 * @author Walter Weeks
	 */
	@Test (expected = InvalidJobDurationException.class)
	public void addJob_AddingMoreThanMaximumPendingJobsOnDate_ExpectsException() {
		myCal.add(Calendar.DATE, 1); // 10 Days from today
		
		for (int i = 0; i <= myDatastore.getMaxPendingJobs(); i++) {
			myDatastore.addJob(new Job(myParks.get(i % myDatastore.getNumberOfParks()), "1145", "We will be digging ditches.",
	                "Digging ditches", 1, myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR)));
		}
	}

    /**
     * Tests for IllegalArgrumentException when attempting to set the maximum number of pending jobs to 0.
     * @author Walter Weeks
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMaxPendingJobs_SetMaxPendingJobsTo0_ExceptionExpected() {
        myDatastore.setMaxPendingJobs(0);
    }
    
    /**
     * Sunny-day scenario for business rule for how far in the future a job can be made.
     * @author Dylan Miller
     */
    @Test
    public void isJobWithinMaxFutureDate_jobIsInTheRange_NoExceptionsExpected() {
      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date()); //today
      cal.add(Calendar.DATE, Datastore.MAX_FUTURE_JOB_START_DATE - 1); //one day before max allowed day
      
      Job jobInRange = new Job(myParks.get(0), "1030", "Painting over graffiti in bathrooms", "Painting", 1, 
                               cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
      
      assertTrue(myDatastore.isJobWithinMaxFutureDate(jobInRange));
    }
    
    /**
     * Tests future start date business rule for when job is beyond the upper bound.
     * @author Dylan Miller
     */
    @Test
    public void isJobWithinMaxFutureDate_jobIsAfterMaxAllowed_ShouldReturnFalse() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); //today
        cal.add(Calendar.DATE, Datastore.MAX_FUTURE_JOB_START_DATE + 1); //max allowed into the future + 1 from today
        
        Job jobInRange = new Job(myParks.get(0), "1030", "Painting over graffiti in bathrooms", "Painting", 1, 
                                 cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
        
        assertFalse(myDatastore.isJobWithinMaxFutureDate(jobInRange));
    }
    
    /**
     * Tests future start date business rule for when the job starts within the range but it's duration ends outside the upper bound.
     * @author Dylan Miller
     */
    @Test
    public void isJobWithinMaxFutureDate_jobEndsOutOfBounds_ShouldReturnFalse() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); //today
        cal.add(Calendar.DATE, Datastore.MAX_FUTURE_JOB_START_DATE); //starts on the last allowed day
        
        Job jobEndingOutOfRange = new Job(myParks.get(0), "1030", "Painting over graffiti in bathrooms", "Painting", Job.getMaxDuration(), 
                                 cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)); //job with maximum duration
        
        assertFalse(myDatastore.isJobWithinMaxFutureDate(jobEndingOutOfRange));
    }
    
    /**
     * Tests future start date business rule for when the job starts in the past.
     * @author Dylan Miller
     */
    @Test
    public void isJobWithinMaxFutureDate_jobStartsInPast_ShouldReturnFalse() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); //today
        cal.add(Calendar.DATE, -1); //set to yesterday
        
        Job jobStartingInThePast = new Job(myParks.get(0), "1030", "Painting over graffiti in bathrooms", "Painting", 1, 
                                 cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)); 
        
        assertFalse(myDatastore.isJobWithinMaxFutureDate(jobStartingInThePast));
    }
}
