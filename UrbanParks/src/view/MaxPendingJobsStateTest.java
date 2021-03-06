package view;

import static org.junit.Assert.assertTrue;

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
/**
 * This creates a state of the datastore to test if the max jobs has been created. 
 * @author Gardner Gomes
 * @author Dylan Miller 
 *
 */
public class MaxPendingJobsStateTest {
    
    //***** Test fixture(s), setUp(), etc. *****************************************************************************
    
    public int NUMBER_OF_JOBS = 19;
    public int YEAR;
    public int MONTH;
    public String[] JobTitles = {"Mowing","Raking","Sprinkler Set Up","Trash Pick up","Pond Cleaning"
                                ,"Dog Park Cleanup","Cleaning Swimming pool","Recycling","Trash Pick up"};
    public String[] Times = {"11:00","14:00","10:00","09:00","11:00","14:00"};
    public int[] Duration = {1,2,3};
    public int[] Day = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
    public String DESCRIPTION = "No Description";
    
    public Datastore testDatastore;
    public ParkManager myManager;
    public Job tempJob;
    public Park testPark;
    
    @Before
    public void setUp() throws Exception {
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(new Date());
        YEAR = myCal.get(Calendar.YEAR);
        MONTH = myCal.get(Calendar.MONTH) + 2; //next month
        
        testDatastore = new Datastore();
        myManager = new ParkManager("batman@tacomaparks.com", "5551112222", "Dylan Miller");
        testDatastore.addAccount(myManager);
        testPark = new Park(myManager, "Franklin Park", "1201 S Puget Sound Ave", "Tacoma", "WA", "98408");
        testDatastore.addPark(testPark);
        int theDay;
        String theTime;
        String theName;
        int theDuration;
        for(int i = 0; i < NUMBER_OF_JOBS; i++) {
            theDay = Day[i%(Day.length - 1)];
            theTime = Times[i%(Times.length - 1)];
            theDuration = Duration[i%(Duration.length - 1)];
            theName = JobTitles[i%(JobTitles.length - 1)];
            tempJob = new Job(testPark,theTime,DESCRIPTION,theName,theDuration,theDay,MONTH,YEAR);
            testDatastore.addJob(tempJob);
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
    
    //***** Unit test(s) ***********************************************************************************************
    
    @Test
    public void test() {
        assertTrue("Datastore ready to test MaxPendingJobsState", true);
    }

}
