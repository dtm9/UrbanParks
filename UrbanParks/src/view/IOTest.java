/**
 * JUnit test for IO operations from the mainline to the datastore.
 * Run this test to get a basic datastore.bin file saved to your local machine
 * for any manual testing purposes.
 * @author Dylan Miller
 */
package view;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import backend.Account;
import backend.Datastore;
import backend.Job;
import backend.OfficeStaff;
import backend.Park;
import backend.ParkManager;
import backend.Volunteer;
import backend.Volunteer.WorkGrade;

/**
 * This is a integration test for saving and loading.
 * @author Dylan Miller
 */
public class IOTest {

    Datastore testDatastore;
    ParkManager testPM1;
    ParkManager testPM2;
    ParkManager testPM3;
    Park testPark1;
    Park testPark2;
    Park testPark3;
    Job testJob1;
    Job testJob2;
    Volunteer testVolunteer1;
    OfficeStaff testStaff1;
	
    /**
    * @throws java.lang.Exception
    */
    @Before
    public void setUp() throws Exception {
    testDatastore = new Datastore();

    testPM1 = new ParkManager("dmiller@tacomaparks.com", "5551112222", "Dylan Miller");
    testDatastore.addAccount(testPM1);
    
    testPM2 = new ParkManager("wweeks@tacomaparks.com", "5553334444", "Walter Weeks");
    testDatastore.addAccount(testPM2);
    
    testPM3 = new ParkManager("eyoung@tacomaparks.com", "5555556666", "Ethan Young");
    testDatastore.addAccount(testPM3);

    testPark1 = new Park(testPM1, "Franklin Park", "1201 S Puget Sound Ave", "Tacoma", "WA", "98408");
    testDatastore.addPark(testPark1);
    
    testPark2 = new Park(testPM2, "Wapato Park", "6500 S Sheridan Ave", "Tacoma", "WA", "98408");
    testDatastore.addPark(testPark2);
    
    testPark3 = new Park(testPM3, "Wright Park", "501 S I St", "Tacoma", "WA", "98408");
    testDatastore.addPark(testPark3);

	testJob1 = new Job(testPark1, "02/18/2017", "10:00", "Empty all trash and recycling bins.","Raking", 2, 10);
	testDatastore.addJob(testJob1);
	
	testJob2 = new Job(testPark2, "02/19/2017", "09:00", "Paint over graphiti.","Painting",2 ,11 );
	testDatastore.addJob(testJob2);
	
	testVolunteer1 = new Volunteer("NotAProfessionalEmail@gmail.com", "5559998888", "Gardner Gomes", WorkGrade.HEAVY);
	testDatastore.addAccount(testVolunteer1);
	
	testStaff1 = new OfficeStaff("ppark@urbanparks.org", "5555555555", "Peter Park");
	testDatastore.addAccount(testStaff1);
	
	//write out the test datastore as the same filename that main loads.
	try {
  	  FileOutputStream outfile = new FileOutputStream("datastore.bin");
  	  ObjectOutputStream out = new ObjectOutputStream(outfile);
  	  out.writeObject(testDatastore);
  	  out.close();
  	  outfile.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public final void save_DefaultState_NoExceptionsExpected() {

    Main.datastore = testDatastore;
    Main.save();
  }
  
  @Test
  public final void load_DefaultState_NoExceptionsExpected() {

    Main.load();
  }
  
  @Test
  public final void load_DefaultState_DataIntegrityPreserved() {

    Main.load();
    
    //users
    List<Account> testAccounts = testDatastore.getAllAccounts();
    List<Account> loadedAccounts = Main.datastore.getAllAccounts();
    if (!testAccounts.equals(loadedAccounts)) fail("Loaded account list does not match original account list!");
    
    //jobs
    List<Job> testJobs = testDatastore.getPendingJobs();
    List<Job> loadedJobs = Main.datastore.getPendingJobs();
    if (!testJobs.equals(loadedJobs)) fail("Loaded job list does not match original job list!");
    
    //parks
    List<Park> testParks = testDatastore.getAllParks();
    List<Park> loadedParks = Main.datastore.getAllParks();
    if (!testParks.equals(loadedParks)) fail("Loaded park list does not match original park list!");
    
    //sanity test (debugging this test case)
    /*
    for (Account account : testAccounts) {
      System.out.println("test account list -- " + account.getUsername() + "\n");
    }

    for (Account account : loadedAccounts) {
      System.out.println("loaded account list -- " + account.getUsername() + "\n");	
    } 
    */
  }

}
