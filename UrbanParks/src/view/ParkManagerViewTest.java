/**
 * 
 */
package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

import org.junit.Before;

/**
 * @author Dylan Miller
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
    private Job testPastJob5;
    private Job testPastJob4;
    private Job testPastJob3;
    private Job testPastJob2;
    private Job testPastJob1;
    private Volunteer testVolunteer12;
    private Volunteer testVolunteer11;
    private Volunteer testVolunteer10;
    private Volunteer testVolunteer9;
    private Volunteer testVolunteer8;
    private Volunteer testVolunteer7;
    private Volunteer testVolunteer6;
    private Volunteer testVolunteer5;
    private Volunteer testVolunteer4;
    private Volunteer testVolunteer3;
    private Volunteer testVolunteer2;
    private Volunteer testVolunteer1;
    private Job testJob30;
    private Job testJob31;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testDatastore = new Datastore();
		
		testPM1 = new ParkManager("batman@tacomaparks.com", "5551112222", "Dylan Miller");
		testParkManagerView = new ParkManagerView(testPM1);
		
		
		testVolunteer1 = new Volunteer("GardnerGomes@gmail.com", "5559998888", "Gardner Gomes");
	    testDatastore.addAccount(testVolunteer1);
	    testVolunteer2 = new Volunteer("Sinefield@gmail.com", "5559998887", "Jerry Sienfield");
        testDatastore.addAccount(testVolunteer2);
        testVolunteer3 = new Volunteer("ShelGod@gmail.com", "5559997588", "Sheldon Garret");
        testDatastore.addAccount(testVolunteer3);
        testVolunteer4 = new Volunteer("SwanMan@gmail.com", "5559698888", "Darren Swanson");
        testDatastore.addAccount(testVolunteer4);
        testVolunteer5 = new Volunteer("Rudy@gmail.com", "5559693888", "Rudy Larson");
        testDatastore.addAccount(testVolunteer5);
        testVolunteer6 = new Volunteer("SDvenny@gmail.com", "5543693888", "Stephanie Devenny");
        testDatastore.addAccount(testVolunteer6);
        testVolunteer7 = new Volunteer("LiZZ2002@gmail.com", "5559654588", "Julie Lindsey");
        testDatastore.addAccount(testVolunteer7);
        testVolunteer8 = new Volunteer("R053MErry@gmail.com", "2539693888", "Rosie Mary");
        testDatastore.addAccount(testVolunteer8);
        testVolunteer9 = new Volunteer("CapKirk@gmail.com", "5559693548", "Kirk Robinson");
        testDatastore.addAccount(testVolunteer9);
        testVolunteer10 = new Volunteer("RamoxxxXD@gmail.com", "5559693768", "Ramon Mendoza");
        testDatastore.addAccount(testVolunteer10);
        testVolunteer11 = new Volunteer("Sharpie23@gmail.com", "5559693867", "Leslie Sharp");
        testDatastore.addAccount(testVolunteer11);
        testVolunteer12 = new Volunteer("MonInc@gmail.com", "5559693222", "Mike Wazowski");
        testDatastore.addAccount(testVolunteer12);
        
	    testDatastore.addAccount(testPM1);
	    testPark1 = new Park(testPM1, "Franklin Park", "1201 S Puget Sound Ave", "Tacoma", "WA", "98408");
	    testDatastore.addPark(testPark1);
	    /*JOBS------------------------------------------------------------------------------------------------------*/
		testJob1 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Recycling", 1, 13, 2 ,2017);
	    testDatastore.addJob(testJob1);
	    testJob1.setMaxVolunteers(20);
	    testJob1.setVolunteers(testVolunteer1.getRealName());
	    testJob1.setVolunteers(testVolunteer2.getRealName());
	    testJob1.setVolunteers(testVolunteer3.getRealName());
	    testJob1.setVolunteers(testVolunteer4.getRealName());
	    testJob1.setVolunteers(testVolunteer5.getRealName());
	    testJob1.setVolunteers(testVolunteer6.getRealName());
	    testJob1.setVolunteers(testVolunteer7.getRealName());
	    testJob1.setVolunteers(testVolunteer8.getRealName());
	    testJob1.setVolunteers(testVolunteer9.getRealName());
	    testJob1.setVolunteers(testVolunteer10.getRealName());
	    testJob1.setVolunteers(testVolunteer11.getRealName());
	    testJob1.setVolunteers(testVolunteer12.getRealName());
	    
	    
	    testJob2 = new Job(testPark1, "09:00", "Paint over graphiti.","Painting", 1, 14, 2 , 2017);
	    testDatastore.addJob(testJob2);
	    testJob2.setMaxVolunteers(20);
	    testJob2.setVolunteers(testVolunteer1.getRealName());
        testJob2.setVolunteers(testVolunteer2.getRealName());
        testJob2.setVolunteers(testVolunteer3.getRealName());
        testJob2.setVolunteers(testVolunteer4.getRealName());
        testJob2.setVolunteers(testVolunteer5.getRealName());
        testJob2.setVolunteers(testVolunteer6.getRealName());
        testJob2.setVolunteers(testVolunteer7.getRealName());
        testJob2.setVolunteers(testVolunteer8.getRealName());
        testJob2.setVolunteers(testVolunteer9.getRealName());
        testJob2.setVolunteers(testVolunteer10.getRealName());
        testJob2.setVolunteers(testVolunteer11.getRealName());
        testJob2.setVolunteers(testVolunteer12.getRealName());
        
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
//        testDatastore.addJob(testJob20);
//        testJob21 = new Job(testPark1, "10:00", "Cutting the Lawns.","Mowing", 1, 5, 3 ,2017);
//        testDatastore.addJob(testJob21);
//        testJob22 = new Job(testPark1, "10:00", "People to hand out water bottles.","FunRun", 1, 6, 3 ,2017);
//        testDatastore.addJob(testJob22);
//        testJob23 = new Job(testPark1, "10:00", "Need help digging holes and moving trees.","Planting Trees", 1, 7, 3 ,2017);
//        testDatastore.addJob(testJob23);
//        testJob24 = new Job(testPark1, "10:00", "Volunteers to not only taste wine but pour it too.","Wine tasting", 1, 8, 3 ,2017);
//        testDatastore.addJob(testJob24);
//        testJob25 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Recycling", 1, 9, 3 ,2017);
//        testDatastore.addJob(testJob25);
//        testJob26 = new Job(testPark1, "10:00", "Repainting the Gazebo.","Painting", 1, 10, 3 ,2017);
//        testDatastore.addJob(testJob26);
//        testJob27 = new Job(testPark1, "10:00", "People to bring their dogs and go for a walk!","Dog Walk", 1, 11, 3 ,2017);
//        testDatastore.addJob(testJob27);
//        testJob28 = new Job(testPark1, "10:00", "Cleaning Leaves.","Raking", 1, 12, 3 ,2017);
//        testDatastore.addJob(testJob28);
//        testJob29 = new Job(testPark1, "10:00", "Cutting the Lawn.","Mowing", 1, 13, 3 ,2017);
//        testDatastore.addJob(testJob29);
        
        
        testPastJob1 = new Job(testPark1, "10:00", "Volunteers to not only taste wine but pour it too.","Wine tasting", 1, 8, 1 ,2017);
        testDatastore.addJob(testPastJob1);
        testDatastore.removeJob(testJob1);
        testDatastore.removeJob(testPastJob1);
        testPastJob2 = new Job(testPark1, "10:00", "Empty all trash and recycling bins.","Recycling", 1, 12, 1 ,2017);
        testDatastore.addJob(testPastJob2);
        testDatastore.removeJob(testPastJob2);
        testPastJob3 = new Job(testPark1, "10:00", "Repainting the Gazebo.","Painting", 1, 11, 3 ,2017);
        testDatastore.addJob(testPastJob3);
        testDatastore.removeJob(testPastJob3);
        testPastJob4 = new Job(testPark1, "10:00", "People to bring their dogs and go for a walk!","Dog Walk", 1, 10, 1 ,2017);
        testDatastore.addJob(testPastJob4);
        testDatastore.removeJob(testPastJob4);
        testPastJob5 = new Job(testPark1, "10:00", "Cleaning Leaves.","Raking", 1, 9, 1 ,2017);
        testDatastore.addJob(testPastJob5);
        testDatastore.removeJob(testPastJob5);
        
        testJob30 = new Job(testPark1, "10:00", "wiping down the swiming pool.","Cleaning Swming pool", 1, 14, 3 ,2017);
        testDatastore.addJob(testJob30);
        testJob31 = new Job(testPark1, "10:00", "sanitizing the bathrooms.","Cleaning Bathrooms", 1, 15, 3 ,2017);
        testDatastore.addJob(testJob31);
        
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
	// TODO Test for 31 Jobs
	//TODO test 3 Jobs 1 Day Conflict
	//TODO Max 30 VOlunteers pear Job
	//TODO test Job more than 1 month in future 
	//TODO VIEW Volunteers for past and present Jobs.
	//TODO no Job Longer Than 2 Days.

}
