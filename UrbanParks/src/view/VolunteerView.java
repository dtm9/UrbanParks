/**
 * 
 */
package view;

import backend.Datastore;
import backend.OfficeStaff;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import backend.Account;
import backend.Job;
import backend.Datastore;
import backend.Volunteer;
import backend.Park;

/**
 * @author dylan
 *
 */
public class VolunteerView extends View {

    private Volunteer myVolunteer;
    private final Datastore myDatastore;
    private Scanner myScanner = new Scanner(System.in);
    private StringBuilder mySB;
    
    public VolunteerView(Account theAccount, Datastore theDatastore){
    	super();
    	myVolunteer = (Volunteer) theAccount;
    	myDatastore = theDatastore;
    	mySB = new StringBuilder();
    	
    	
    }
	@Override
	public void launchGUI() {
		// TODO Auto-generated method stub
		mainMenu();
	}
	
	private void mainMenu(){
		 	mySB.append("\nWelcome to Urban Parks\nVolunteer: ");
	        mySB.append(myVolunteer.getRealName());
	        mySB.append("\n----------------------------------------------------------\n\n");
	        mySB.append("1. Volunteer for jobs\n");
	        mySB.append("2. View My Jobs\n");
	        mySB.append("3. Exit\n");
	        mySB.append("\n\nPlease Select a number followed by the enter key: ");
	        System.out.print(mySB.toString());
	        mySB.delete(0, mySB.capacity());
	        int theChoice = myScanner.nextInt();
	        
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
	
	private void signUpForJob(){
		if (myVolunteer.isBlackballed()){
			mySB.append("You can not Sign up for a job.\nYou have been Blackballed.\nPlease contact an Urban Parks Staff Member.\n" );
			mainMenu();
		}
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
	    	Park selectedPark = parkList.get(theChoice);
	    	List<Job> parkJobList = myDatastore.getJobsByPark(selectedPark);
	    	listJobs(selectedPark, parkJobList);
	    }
	    
	    
	}
	
	private void listJobs(Park selectedPark, List<Job> parkJobList){
		
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
				mySB.append("\n");
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
				    mySB.append("You have signed up for this job: \n");
				    printJob.setVolunteers(myVolunteer.getUsername());
				    mySB.append(printJob.getName());
				    mySB.append("\n");
				    mySB.append(printJob.getDescription());
				    mySB.append("\nDate: ");
				    mySB.append(printJob.getMonth());
				    mySB.append("/");
				    mySB.append(printJob.getDay());
				    mySB.append("/");
				    mySB.append(printJob.getYear());

				}else {
					mySB.append("You can not sign up for this job: \n");
					mySB.append("You have already signed up for a job on this day.\n");
				}
				
			    mySB.append("\nEnter 1 to retutrn to main menu\n");
			    System.out.print(mySB.toString());
			    mySB.delete(0, mySB.capacity());
			    int throwAwayInpt = myScanner.nextInt();
			    mainMenu();
			 }
			
		}
		
	}
	
	private void viewJobs(){
		mySB.append("\nView Jobs for: ");
		mySB.append(myVolunteer.getRealName());
		mySB.append("\n----------------------------------------------------------\n\n");
		List<Job> volunteerJobs = myDatastore.getJobsByVolunteer(myVolunteer);
		Iterator<Job> itr = volunteerJobs.iterator();
		int count =0;
		if (volunteerJobs==null) mySB.append("You have not signed up for any jobs");
		while( itr.hasNext()){
			 Job currentJob = itr.next();
			 count++;
			 mySB.append(count);
			 mySB.append(". ");
			 mySB.append(currentJob.getName());
			 mySB.append("\n");
			 
		}	
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
