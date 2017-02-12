/**
 * 
 */
package view;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import backend.Datastore;
import backend.ParkManager;
import backend.Job;
import backend.Park;

/**
 * @author dylan
 *
 */
public class ParkManagerViewTest {
	private ParkManager testParkManager;
	private ParkManagerView testParkManagerView;
	private Datastore testDatastore;
    private ParkManager testPM1;
    private Job testJob1;
    private Job testJob2;
    private Job testJob3;
    private Job testJob4;
    private Park testPark1;
    private Job testJob5;
    private Job testJob6;
    private Job testJob7;
    private Job testJob8;
    private Job testJob9;
    private Job testJob10;
    private Job testJob11;
    private Job testJob12;
    private Job testJob13;
    private Job testJob14;
    private Job testJob15;
    private Job testJob16;
    private Job testJob17;
    private Job testJob18;
    private Job testJob19;
    private Job testJob20;
    private Job testJob21;
    private Job testJob22;
    private Job testJob23;
    private Job testJob24;
    private Job testJob25;
    private Job testJob26;
    private Job testJob27;
    private Job testJob28;
    private Job testJob29;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testDatastore = new Datastore();
		
		testPM1 = new ParkManager("dmiller@tacomaparks.com", "5551112222", "Dylan Miller");
		testParkManagerView = new ParkManagerView(testPM1, testDatastore);
	    testDatastore.addAccount(testPM1);
	    testPark1 = new Park(testPM1, "Franklin Park", "1201 S Puget Sound Ave", "Tacoma", "WA", "98408");
	    testDatastore.addPark(testPark1);
		testJob1 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Recycling", 1, 13, 2 ,2017);
	    testDatastore.addJob(testJob1);
	    testJob2 = new Job(testPark1, "09:00", "Paint over graphiti.","Painting", 1, 14, 2 , 2017);
	    testDatastore.addJob(testJob2);
	    testJob3 = new Job(testPark1, "11:00", "Feed the ducks.","Feeding", 1,  15, 2, 2017);
	    testDatastore.addJob(testJob3);
	    testJob4 = new Job(testPark1, "14:00", "Mow grass.","Mowing", 1, 16, 2, 2017);
	    testDatastore.addJob(testJob4);
	    testJob5 = new Job(testPark1, "10:00", "Cleaning up leaves.","Raking", 1, 17, 2 ,2017);
        testDatastore.addJob(testJob5);
        testJob6 = new Job(testPark1, "10:00", "Cutting the Grass.","Mowing", 1, 18, 2 ,2017);
        testDatastore.addJob(testJob6);
        testJob7 = new Job(testPark1, "10:00", "Looking for rare Birds.","Bird Watching", 1, 19, 2 ,2017);
        testDatastore.addJob(testJob7);
        testJob8 = new Job(testPark1, "10:00", "Setting up Sprinklers for testing.","Sprinkler Set Up", 1, 20, 2 ,2017);
        testDatastore.addJob(testJob8);
        testJob9 = new Job(testPark1, "10:00", "Po-Go tournament whoever owns the Park at 5pm wins.","Pokemon-Go", 1, 21, 2 ,2017);
        testDatastore.addJob(testJob9);
        testJob10 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Trash Pick up", 1, 22, 2 ,2017);
        testDatastore.addJob(testJob10);
        testJob11 = new Job(testPark1, "10:00", "Looking for trash in the pond and cleaning it.","Pond Cleaning", 1, 23, 2 ,2017);
        testDatastore.addJob(testJob11);
        testJob12 = new Job(testPark1, "10:00", "need help inventorying our chairs and tables.","Inventory", 1, 24, 2 ,2017);
        testDatastore.addJob(testJob12);
        testJob13 = new Job(testPark1, "10:00", "Cleaning up Leaves.","Raking", 1, 25, 2 ,2017);
        testDatastore.addJob(testJob13);
        testJob14 = new Job(testPark1, "10:00", "Cutting the Grass.","Mowing", 1, 26, 2 ,2017);
        testDatastore.addJob(testJob14);
        testJob15 = new Job(testPark1, "10:00", "Cleaning out the Dog Park.","Dog Park Cleanup", 1, 27, 2 ,2017);
        testDatastore.addJob(testJob15);
        testJob16 = new Job(testPark1, "10:00", "Need people to watch Children and bring baked Goods.","Bake Sale", 1, 28, 2 ,2017);
        testDatastore.addJob(testJob16);
        testJob17 = new Job(testPark1, "10:00", "Cleaning the Swimming Pool.","Cleaning Swimming pool", 1, 1, 3 ,2017);
        testDatastore.addJob(testJob17);
        testJob18 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Recycling", 1, 2, 3 ,2017);
        testDatastore.addJob(testJob18);
        testJob19 = new Job(testPark1, "10:00", "Need Help Picking up and finding trash.","Trash Pick up", 1, 3, 3 ,2017);
        testDatastore.addJob(testJob19);
        testJob20 = new Job(testPark1, "10:00", "Picking up leaves.","Raking", 1, 4, 3 ,2017);
        testDatastore.addJob(testJob20);
        testJob21 = new Job(testPark1, "10:00", "Cutting the Lawns.","Mowing", 1, 5, 3 ,2017);
        testDatastore.addJob(testJob21);
        testJob22 = new Job(testPark1, "10:00", "People to hand out water bottles.","FunRun", 1, 6, 3 ,2017);
        testDatastore.addJob(testJob22);
        testJob23 = new Job(testPark1, "10:00", "Need help digging holes and moving trees.","Planting Trees", 1, 7, 3 ,2017);
        testDatastore.addJob(testJob23);
        testJob24 = new Job(testPark1, "10:00", "Volunteers to not only taste wine but pour it too.","Wine tasting", 1, 8, 3 ,2017);
        testDatastore.addJob(testJob24);
        testJob25 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Recycling", 1, 9, 3 ,2017);
        testDatastore.addJob(testJob25);
        testJob26 = new Job(testPark1, "10:00", "Repainting the Gazebo.","Painting", 1, 10, 3 ,2017);
        testDatastore.addJob(testJob26);
        testJob27 = new Job(testPark1, "10:00", "People to bring their dogs and go for a walk!","Dog Walk", 1, 11, 3 ,2017);
        testDatastore.addJob(testJob27);
        testJob28 = new Job(testPark1, "10:00", "Cleaning Leaves.","Raking", 1, 12, 3 ,2017);
        testDatastore.addJob(testJob28);
        testJob29 = new Job(testPark1, "10:00", "Cutting the Lawn.","Mowing", 1, 13, 3 ,2017);
        testDatastore.addJob(testJob29);
		
	} 
	// TODO Test for 31 Jobs
	//TODO test 3 Jobs 1 Day Conflict
	//TODO Max 30 VOlunteers pear Job
	//TODO test Job more than 1 month in future 
	//TODO VIEW Volunteers for past and present Jobs.
	//TODO no Job Longer Than 2 Days.
	@Test
	public final void launchGUI_goodCases_runsParkManagerView() {
		testParkManagerView.launchGUI();
	}
//	@Test
//    public final void launchGUI_BadCases_ExceptionsTrownOrHandeled() {
//	    Job testJob30 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Raking", 1, 14, 3 ,2017);
//        testDatastore.addJob(testJob30);
//        Job testJob31 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Raking", 1, 15, 3 ,2017);
//        testDatastore.addJob(testJob31);
//    }

}
