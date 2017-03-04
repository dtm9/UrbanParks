/**
 * The User Interface for volunteers
 * This object is not able to validate the type of user interacting with the system
 * so be mindful that the user's type is validated before
 * running this code.
 */
package view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.AbstractAccount;
import model.Datastore;
import model.Job;
import model.Park;
import model.Volunteer;

/**
 * @author Dylan Miller
 * @author Ethan Young
 */
public class VolunteerView extends AbstractView {

    static final int MIN_DATE_AWAY_MINUS_ONE = 1;
    private Volunteer myVolunteer;
	private static Datastore myDatastore;
	private Scanner myScanner = new Scanner(System.in);
	private StringBuilder mySB;
	private static LocalDate myDay;
	private static ZoneId myZone;

	/**
	 * Constructor That instantiates the Volunteer view
	 * @param theAccount
	 */
	public VolunteerView(AbstractAccount theAccount){
		super();
		myVolunteer = (Volunteer) theAccount;
		myDatastore = null;
		mySB= new StringBuilder();
		myZone = ZoneId.of("America/Los_Angeles");
		myDay = LocalDate.now(myZone);



	}
	/**
	 * Launches the GUI
	 * 
	 */
	@Override
	public Datastore launchGUI(Datastore theDatastore) {
		myDatastore = theDatastore;
		mainMenu();
		return myDatastore;
	}
	/**
	 * Creates the main menu for the Volunteer
	 */
	private void mainMenu(){
		super.displayHeader(myVolunteer, myDay);
		int theChoice;

		mySB.append("1. Volunteer for jobs\n");
		mySB.append("2. View My Jobs\n");
		mySB.append("3. Exit\n");
		mySB.append("\n\nPlease Select a number followed by the enter key: ");
		System.out.print(mySB.toString());
		mySB.delete(0, mySB.capacity());
		theChoice = myScanner.nextInt();

		switch (theChoice) {
		case 1:
			signUpForJobByPark();
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
	private void signUpForJobByPark(){
		super.displayHeader(myVolunteer, myDay);

		mySB.append("Which park do you want to volunteer for?");
		mySB.append("\n----------------------------------------------------------\n\n");
		//using main datastore instead of local copy
		List<Park> parkList = myDatastore.getAllParks();
		Iterator<Park> itr = parkList.iterator();
		int count = 0;
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
			// subtract because parkList starts at index 0
			theChoice--;
			Park selectedPark = parkList.get(theChoice);
			List<Job> parkJobList = selectedPark.getJobs(myDatastore);
			// List<Job> parkJobList = myDatastore.getJobsByPark(selectedPark);
			listJobsInPark(selectedPark, parkJobList);
		}


	}

	/**
	 * Lists the jobs from the parkJobList parameter that the user can sign up for
	 * @param selectedPark
	 * @param parkJobList
	 */
	private void listJobsInPark(Park selectedPark, List<Job> parkJobList){
		super.displayHeader(myVolunteer, myDay);
		mySB.append("Which job do you want to sign up for?\n");
		mySB.append("\n----------------------------------------------------------\n\n");
		mySB.append("\nDate          Park               Job Time    Duration     Job Description");
		mySB.append("\n-----------------------------------------------------------------------\n\n");
		int count = 0;
		Iterator<Job> itr=parkJobList.iterator();
		if(parkJobList.size() == 0) mySB.append("There are no jobs to sign up for\n"); // Changed because parkJobList can never be NULL
		List<Job> legitJobs = new ArrayList<Job>();
		//placeholder in array
		Job notARealJob = new Job();
		legitJobs.add(notARealJob);
		while(itr.hasNext()){
			Job currentJob=itr.next();
			if (minDaysAway(currentJob)) { // This if-statement calls the helper method directly
				count++;
				legitJobs.add(currentJob);
				mySB.append(count);
				mySB.append(". ");
				mySB.append(currentJob.getMonth()+"/"+currentJob.getDay()+"/"+currentJob.getYear());
				mySB.append("    ");
				mySB.append(currentJob.getPark().getName());
				mySB.append("     ");
				mySB.append(currentJob.getTime());
				mySB.append("         ");
				mySB.append(currentJob.getDuration());
				mySB.append("            ");
				mySB.append(currentJob.getDescription());
				mySB.append("\n");
				mySB.append("\n");
				mySB.append("\n");
			}
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
			confirmScreen(theChoice, legitJobs);
		}
	}

	/**
	 * @author Peter Park
	 * 
	 * This is business rule 1B for Deliverable 3
	 * @param currentJob
	 * @return boolean
	 */
	boolean minDaysAway(Job currentJob) {
		ZoneId z = ZoneId.of("America/Los_Angeles");
		LocalDate today = LocalDate.now(z);

		int futureDayofYear = today.getDayOfYear() + MIN_DATE_AWAY_MINUS_ONE; 
		LocalDate futureLimit = LocalDate.ofYearDay(today.getYear(), futureDayofYear);	
		LocalDate jobDate = LocalDate.of(currentJob.getYear(), currentJob.getMonth(), currentJob.getDay());

		boolean futureDatesSameYear = jobDate.getDayOfYear()>futureLimit.getDayOfYear();
		boolean futureDatesNextYear = jobDate.getYear()>=futureLimit.getYear()+1; 

		return futureDatesSameYear || futureDatesNextYear; 
	}

	private void confirmScreen(int theChoice, List<Job> legitJobs){
		boolean sameDayFlag = false;
		Job printJob = legitJobs.get(theChoice);
		List<Job> volunteerJobs = myVolunteer.getJobsByVolunteer(myDatastore);
		Calendar myCal = Calendar.getInstance();
		myCal.set(Calendar.DAY_OF_MONTH, printJob.getDay());
		myCal.set(Calendar.MONTH, printJob.getMonth());
		myCal.set(Calendar.YEAR, printJob.getYear());
		for (int i = 0; i < printJob.getDuration(); i++) {
		  myCal.add(Calendar.DATE, i);
		  if(isSameDayJob(myCal.get(Calendar.DAY_OF_MONTH), myCal.get(Calendar.MONTH), volunteerJobs, mySB)) {
		    sameDayFlag = true;
		  }
		}

		if(!sameDayFlag){ 
			super.displayHeader(myVolunteer, myDay);
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
			super.displayHeader(myVolunteer, myDay);
			mySB.append("You can not sign up for this job: \n");
			mySB.append("You have already signed up for a job on this day.\n");
			mySB.append("\nEnter 1 to return to main menu\n");
		}
		System.out.print(mySB.toString());
		mySB.delete(0, mySB.capacity());
		String confirmChoice = myScanner.next();
		if(confirmChoice.equalsIgnoreCase("y")&&!sameDayFlag){
			printJob.setVolunteers(myVolunteer.getUsername());
		}
		mainMenu();
	}
	
	/**
     * Tests if the day the selected job is on, is on the same day as another job the volunteer has signed up for
     * @param printJob
     * @param volunteerJobs
     * @param mySB
     * @return
     */
    boolean isSameDayJob(int jobDay, int jobMonth, List<Job> volunteerJobs, StringBuilder mySB ){
        boolean sameDayFlag = false;
        for(int i=0;i<volunteerJobs.size();i++){
            Job jobIterator = volunteerJobs.get(i);
            if(jobIterator.getDay()==jobDay && jobIterator.getMonth()==jobMonth){
                sameDayFlag=true;
            }
        }


        return sameDayFlag;

    }
	
	/**
	 * Generates the view that lists all jobs the volunteer has signed up for
	 */
	private void viewJobs(){
		super.displayHeader(myVolunteer, myDay);
		mySB.append("\nJobs currently volunteering for: ");
		mySB.append("\nDate         Park            Job Time    Duration     Job Description");
		mySB.append("\n-----------------------------------------------------------------------\n\n");
		List<Job> volunteerJobs = myVolunteer.getJobsByVolunteer(myDatastore);
		Iterator<Job> itr = volunteerJobs.iterator();
		while( itr.hasNext()){
			Job currentJob = itr.next();
			mySB.append(currentJob.getDay());
			mySB.append("/");
			mySB.append(currentJob.getMonth());
			mySB.append("/");
			mySB.append(currentJob.getYear());
			mySB.append("   ");
			mySB.append(currentJob.getPark().getName());
			mySB.append("       ");
			mySB.append(currentJob.getTime());
			mySB.append("         ");
			mySB.append(currentJob.getDuration());
			mySB.append("          ");
			mySB.append(currentJob.getDescription());
			mySB.append("     ");
			mySB.append("\n");
			mySB.append("\n");
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



}
