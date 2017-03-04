package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;

/**
 * Basic unit tests for the ParkManager class.
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class ParkManagerTest {

    //***** Constant(s) ************************************************************************************************

	/** Address that passes the email REGEX validation. */
	String GOOD_EMAIL = "john@tacomaparks.com";

	/** 10 digit phone number that meets our phone REGEX validation. */
	String GOOD_PHONE = "2535551234";

	/** There is no REGEX for real names, it just needs to not be blank. */
	String GOOD_NAME = "John Doe";

	//***** Test fixture(s), setUp(), etc. *****************************************************************************

	/**Park manager account test fixture.*/
    private ParkManager validParkManagerAccount;
    
    /**Park test fixture.*/
    private Park validPark;
    
    /**Calendar test fixture.*/
    private Calendar myCal;
    
    /**Test datastore test fixture.*/
    private Datastore testDatastore;
    
    /**
     * Sets up the test fixture.
     * @author Walter Weeks
     * @author Dylan Miller
     */
    @Before
    public void setUp() {
        validParkManagerAccount = new ParkManager(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
        validPark = new Park(validParkManagerAccount, "Raab Park", "18349 Caldart Ave", "Poulsbo", "WA", "98370");
        
        myCal = Calendar.getInstance();
        myCal.setTime(new Date()); //today
        myCal.add(Calendar.DAY_OF_MONTH, 3); //three days from today
        
        Job testJob1 = new Job(validPark, "10:30", "Mowing the lawn", "Mowing", 1, 
                              myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
        
        myCal.add(Calendar.DAY_OF_MONTH, 1);
        Job testJob2 = new Job(validPark, "10:30", "Painting over graphiti", "Painting", 1, 
                myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
        
        myCal.add(Calendar.DAY_OF_MONTH, 1);
        Job testJob3 = new Job(validPark, "10:30", "Booth setup for Viking Fest", "Viking Fest", 1, 
                myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
        
        myCal.add(Calendar.DAY_OF_MONTH, 1);
        Job testJob4 = new Job(validPark, "10:30", "Water the plants", "Watering", 1, 
                myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), myCal.get(Calendar.YEAR));
        
        testDatastore = new Datastore();
        testDatastore.addAccount(validParkManagerAccount);
        testDatastore.addPark(validPark);
        testDatastore.addJob(testJob1);
        testDatastore.addJob(testJob2);
        testDatastore.addJob(testJob3);
        testDatastore.addJob(testJob4);
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Tests for equality comparing two separate objects with the same data.
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void equals_CreateComparedObjectFromSameSources_ShouldReturnTrue() {
        ParkManager otherManager = new ParkManager(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
        assertTrue(otherManager.equals(validParkManagerAccount));
    }

    /**
     * Tests for inequality comparing two separate objects with only the username field different.
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void equals_CreateComparedObjectFromDifferentSources_ShouldReturnFalse() {
        ParkManager otherManager = new ParkManager("jimmy@tacomaparks.com", "2535551234", "John Doe");
        assertFalse(otherManager.equals(validParkManagerAccount));
    }
    
    /**
    * Tests to see if the number of jobs a particular park manager is as expected.
    * @author Walter Weeks
    * @author Dylan Miller
    */
    @Test
    public void getJobsByParkManager_UnmodifiedDatastore_ShouldBe4Jobs() {
      ArrayList<Job> jobList = (ArrayList<Job>)validParkManagerAccount.getJobsByParkManager(testDatastore);
      assertTrue(jobList.size() == 4);
    }
}
