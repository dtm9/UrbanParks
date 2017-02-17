/**
 * The User Interface for volunteers
 * This object is not able to validate the type of user interacting with the system
 * so be mindful that the user's type is validated before
 * running this code.
 */
package view;

import backend.Datastore;
import backend.OfficeStaff;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;

import backend.AbstractAccount;
import backend.Job;
import backend.Datastore;
import backend.Volunteer;
import backend.Park;

/**
 * @author Dylan
 *@author Ethan
 */
public class VolunteerView extends View {

    private Volunteer myVolunteer;
    private final Datastore myDatastore;
    private Scanner myScanner = new Scanner(System.in);
    private StringBuilder mySB;
    private static LocalDate myDay;
    private static ZoneId myZone;
    
    /**
     * Constructor That instantiates the Volunteer view
     * @param theAccount
     * @param theDatastore
     */
    public VolunteerView(AbstractAccount theAccount, Datastore theDatastore){
    	super();
    	myVolunteer = (Volunteer) theAccount;
    	myDatastore = theDatastore;
    	mySB= new StringBuilder();
        myZone = ZoneId.of("America/Los_Angeles");
        myDay = LocalDate.now(myZone);

    	
    	
    }
	/**
	 * Launches the GUI
	 * 
	 */
	@Override
	public void launchGUI() {
		// TODO Auto-generated method stub
		mainMenu();
	}
	/**
	 * Creates the main menu for the Volunteer
	 */
	private void mainMenu(){
			header();
			int theChoice;
			if (myVolunteer.isBlackballed()){
				mySB.append("You can not Sign up for a job.\nYou have been Blackballed.\nPlease contact an Urban Parks Staff Member.\n" );
				mySB.append("1. Exit\n");
				mySB.append("Please make a selection: ");
				System.out.print(mySB.toString());
				 mySB.delete(0, mySB.capacity());
				 theChoice=myScanner.nextInt();
				 theChoice=3;
			} else{
		        mySB.append("1. Volunteer for jobs\n");
		        mySB.append("2. View My Jobs\n");
		        mySB.append("3. Exit\n");
		        mySB.append("\n\nPlease Select a number followed by the enter key: ");
		        System.out.print(mySB.toString());
		        mySB.delete(0, mySB.capacity());
		        theChoice = myScanner.nextInt();
			}
	        
	        switch (theChoice) {
	        case 1:
	            signUpForJob();
	            break;
	        case 2:
	            viewJobs();
	            break;
	        case 3:
	            break;
	        }
	}
	
	/**
	 * Lists the parks in anticipation of the volunteer selecting jobs from that park to sign up for
	 */
	private void signUpForJob(){
		header();
		
		mySB.append("Which park do you want to volunteer for?");
	    mySB.append("\n----------------------------------------------------------\n\n");
	    List<Park> parkList = myDatastore.getAllParks();
	    Iterator<Park> itr = parkList.iterator();
	    int count = 0;
	    if(parkList==null) mySB.append("There are no jobs to sign up for\n");
	    while(itr.hasNext()){
	    	Park currentPark =itr.next();
	    	count++;
	    	mySB.append(count);
	    	mySB.append(". ");
	    	mySB.append(currentPark.getName());
	    	mySB.append(" in ");
	    	mySB.append(currentPark.getCity());
	    	mySB.append("\n");
	    }
	    count ++;
	    mySB.append(count);
	    mySB.append(". Back to main menu\n");
	    System.out.print(mySB.toString());
	    mySB.delete(0, mySB.capacity());
	    int theChoice = myScanner.nextInt();
	    if (theChoice==count){
	    	mainMenu();
	    } else{
	    	theChoice--;
	    	Park selectedPark = parkList.get(theChoice);
	    	List<Job> parkJobList = myDatastore.getJobsByPark(selectedPark);
	    	listJobs(selectedPark, parkJobList);
	    }
	    
	    
	}
	
	/**
	 * Lists the jobs from the parkJobList parameter that the user can sign up for
	 * @param selectedPark
	 * @param parkJobList
	 */
	private void listJobs(Park selectedPark, List<Job> parkJobList){
		header();
		mySB.append("Which job do you want to sign up for?\n");
		mySB.append("\n----------------------------------------------------------\n\n");
		int count = 0;
        ZoneId z = ZoneId.of("America/Los_Angeles");
        LocalDate today = LocalDate.now(z);
        int futureDayofYear = today.getDayOfYear()+3;
        LocalDate futureLimit = LocalDate.ofYearDay(today.getYear(), futureDayofYear);
		Iterator<Job> itr=parkJobList.iterator();
		if(parkJobList==null) mySB.append("There are no jobs to sign up for\n");
		List<Job> legitJobs = new ArrayList<Job>();
		//placeholder in array
		Job notARealJob = new Job();
		legitJobs.add(notARealJob);
		while(itr.hasNext()){
			Job currentJob=itr.next();
			LocalDate jobDate = LocalDate.of(currentJob.getYear(), currentJob.getMonth(), currentJob.getDay());
			//logic error...This does not cover the case of signing up for a January job during December
			if (jobDate.getDayOfYear()>futureLimit.getDayOfYear()){
				count++;
				legitJobs.add(currentJob);
				mySB.append(count);
				mySB.append(". ");
				mySB.append(currentJob.getName());
				mySB.append("\n      ");
				mySB.append(currentJob.getDescription());
				mySB.append("\n     ");
				mySB.append("Date: ");
				mySB.append(currentJob.getDay());
				mySB.append("/");
				mySB.append(currentJob.getMonth());
				mySB.append("/");
				mySB.append(currentJob.getYear());
				mySB.append("\n");
			}
		}
			 count ++;
			 mySB.append(count);
			 mySB.append(". Back to main menu\n");
			 System.out.print(mySB.toString());
			 mySB.delete(0, mySB.capacity());
			 int theChoice = myScanner.nextInt();
			 boolean sameDayFlag = false;
			 if (theChoice==count){
			    	mainMenu();
			 } else{
				 Job printJob = legitJobs.get(theChoice);
				 List<Job> volunteerJobs = myDatastore.getJobsByVolunteer(myVolunteer);
				 for(int i=0;i<volunteerJobs.size();i++){
					 Job jobIterator = volunteerJobs.get(i);
					 if(jobIterator.getDay()==printJob.getDay() && jobIterator.getMonth()==printJob.getMonth()){
						 sameDayFlag=true;
					 }
				 }
				if(!sameDayFlag){
					header();
				    mySB.append("You are signing up for this job: \n");
				    mySB.append(printJob.getName());
				    mySB.append("\n");
				    mySB.append(printJob.getDescription());
				    mySB.append("\nDate: ");
				    mySB.append(printJob.getMonth());
				    mySB.append("/");
				    mySB.append(printJob.getDay());
				    mySB.append("/");
				    mySB.append(printJob.getYear());
				    mySB.append("\nAre you sure you want to volunteer for this job? (Y/N)");

				}else {
					header();
					mySB.append("You can not sign up for this job: \n");
					mySB.append("You have already signed up for a job on this day.\n");
					mySB.append("\nEnter 1 to retutrn to main menu\n");
				}
				
			    
			    System.out.print(mySB.toString());
			    mySB.delete(0, mySB.capacity());
			    String confirmChoice = myScanner.next();
			    if(confirmChoice.equalsIgnoreCase("y")&&!sameDayFlag){
			    	printJob.setVolunteers(myVolunteer.getUsername());
			    }
			    mainMenu();
			 }
			
		
		
	}
	
	/**
	 * Generates the view that lists all jobs the volunteer has signed up for
	 */
	private void viewJobs(){
		header();
		mySB.append("\nJobs currently volunteering for: ");
		mySB.append("\nDate         Park               Job Description");
		mySB.append("\n----------------------------------------------------------\n\n");
		List<Job> volunteerJobs = myDatastore.getJobsByVolunteer(myVolunteer);
		Iterator<Job> itr = volunteerJobs.iterator();
		int count =0;
		if (volunteerJobs==null) mySB.append("You have not signed up for any jobs");
		while( itr.hasNext()){
			 Job currentJob = itr.next();
			 count++;
			 mySB.append(currentJob.getDay());
			 mySB.append("/");
			 mySB.append(currentJob.getMonth());
			 mySB.append("/");
			 mySB.append(currentJob.getYear());
			 mySB.append("   ");
			 mySB.append(currentJob.getPark().getName());
			 mySB.append("     ");
			 mySB.append(currentJob.getDescription());
			 mySB.append("\n");
			 
		}	
		mySB.append("What would you like to do?");
		mySB.append("\n1. Back");
		mySB.append("\n2. Exit\n");
		System.out.print(mySB.toString());
		mySB.delete(0, mySB.capacity());
		int theChoice = myScanner.nextInt();
		
		switch (theChoice) {
        case 1:
            mainMenu();
            break;
        case 2:
            break;
        
        }
		
	}
	
	/**
	 * Prints the header to the console
	 */
    void header() {
        mySB.append("\neUrbanParks: the Volunteer organizer for Park Districts nationwide\n");
        mySB.append(myVolunteer.getRealName());
        mySB.append(" logged in as a Volunteer\n");
        mySB.append(myDay.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
        mySB.append(" ");
        mySB.append(myDay.getDayOfMonth());
        mySB.append(", ");
        mySB.append(myDay.getYear());
        mySB.append(".\n-----------------------------------------------------------------\n");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
    }

}
