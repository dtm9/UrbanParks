/**
 * JUnit test for IO operations from the mainline to the datastore.
 * Run this test to get a basic datastore.bin file saved to your local machine
 * for any manual testing purposes.
 * @author Dylan Miller
 */
package view;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.AbstractAccount;
import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

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
    Job testJob3;
    Job testJob4;
    Volunteer testVolunteer1;
    Volunteer testVolunteer2;
	
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

	testJob1 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Raking", 1, 18, 2 ,2017);
	testDatastore.addJob(testJob1);
	
	testJob2 = new Job(testPark2, "09:00", "Paint over graphiti.","Painting", 1, 19, 2 , 2017);
	testDatastore.addJob(testJob2);
	
	testJob3 = new Job(testPark3, "11:00", "Feed the ducks.","Feeding", 1,  28, 2, 2017);
	testDatastore.addJob(testJob3);
	
	testJob4 = new Job(testPark3, "14:00", "Mow grass.","Mowing", 1, 28, 2, 2017);
	testDatastore.addJob(testJob4);
	
	testVolunteer1 = new Volunteer("NotAProfessionalEmail@gmail.com", "5559998888", "Gardner Gomes");
	testDatastore.addAccount(testVolunteer1);
	testJob1.setVolunteers(testVolunteer1.getRealName());
	
	testVolunteer2 = new Volunteer("AncientAliens@gmail.com", "5559998888", "Giorgio A. Tsoukalos");
	testDatastore.addAccount(testVolunteer2);
	
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
    List<AbstractAccount> testAccounts = testDatastore.getAllAccounts();
    List<AbstractAccount> loadedAccounts = Main.datastore.getAllAccounts();
    if (!testAccounts.equals(loadedAccounts)) fail("Loaded account list does not match original account list!");
    
    //jobs
    List<Job> testJobs = testDatastore.getPendingJobs();
    List<Job> loadedJobs = Main.datastore.getPendingJobs();
    if (!testJobs.equals(loadedJobs)) fail("Loaded job list does not match original job list!");
    
    //parks
    List<Park> testParks = testDatastore.getAllParks();
    List<Park> loadedParks = Main.datastore.getAllParks();
    if (!testParks.equals(loadedParks)) fail("Loaded park list does not match original park list!");
  }

}
