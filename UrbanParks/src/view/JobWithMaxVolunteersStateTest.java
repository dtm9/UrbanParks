package view;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;
/**
 * Tests the state of a Job with Max Volunteers and one Job Without max volunteers.
 * @author Gardner Gomes
 */
public class JobWithMaxVolunteersStateTest {
    public final static int MAX_VOLUNTEERS = 10;
    public String[] VolunteerNames = {"John","Travis","Jack", "Peter", "Walter", "Dylan", "Ethan","Gardner","Stephanie"};
    public Datastore testDatastore;
    public ParkManager myManager;
    public Job JobWithMaxVolunteers;
    public Job JobWithLessThanMaxVolunteers;
    public Volunteer testVolunteer;
    public Park testPark;
    
    @Before
    public void setUp() throws Exception {
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(new Date());
        myCal.add(Calendar.DAY_OF_MONTH, 2); //two days from today
        
        testDatastore = new Datastore();
        myManager = new ParkManager("batman@tacomaparks.com", "5551112222", "Dylan Miller");
        testDatastore.addAccount(myManager);
        testPark = new Park(myManager, "Franklin Park", "1201 S Puget Sound Ave", "Tacoma", "WA", "98408");
        testDatastore.addPark(testPark);
        testVolunteer = new Volunteer("GardnerGomes@gmail.com", "5559998888", "Gardner Gomes");
        testDatastore.addAccount(testVolunteer);
        JobWithMaxVolunteers = new Job(testPark, "10:00", "Empty all trash and recycling bins.","Raking", 1,
                myCal.get(Calendar.DATE), myCal.get(Calendar.MONTH)+1, myCal.get(Calendar.YEAR));
        JobWithLessThanMaxVolunteers = new Job(testPark, "10:00", "Emptying YardWaste Bins","Mowing", 1,
                myCal.get(Calendar.DATE), myCal.get(Calendar.MONTH)+1, myCal.get(Calendar.YEAR));
        testDatastore.addJob(JobWithLessThanMaxVolunteers);
        testDatastore.addJob(JobWithMaxVolunteers);
        for(int i = 0; i < MAX_VOLUNTEERS; i++) {
            JobWithMaxVolunteers.setVolunteers(VolunteerNames[i%(VolunteerNames.length)]);
        }
        for(int i = 0; i < MAX_VOLUNTEERS - 1; i++) {
            JobWithLessThanMaxVolunteers.setVolunteers(VolunteerNames[i%(VolunteerNames.length)]);
        }
        
        
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
    public void test() {
        assertTrue("Datastore prepped to test jobWithMaxVolunteers for the Demo", true);
    }

}
