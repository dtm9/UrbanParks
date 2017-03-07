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
 * Object that runs the user interface for a Volunteer user. 
 * Launched with the public launchGUI() method.
 * @author Dylan Miller
 * @author Ethan Young
 * @author Walter Weeks
 */
public class VolunteerView extends AbstractView {

    //***** Constant(s) ************************************************************************************************
    
    public static final String DASHED_LINE_FOR_JOB_LISTING = 
            "-------------------------------------------------------------------------------";
    
    static final int MIN_DATE_AWAY_MINUS_ONE = 1;
    
    //***** Field(s) ***************************************************************************************************
    
    private Volunteer myVolunteer;
    private static Datastore myDatastore;
    private Scanner myScanner = new Scanner(System.in);
    private StringBuilder mySB;
    private static LocalDate myDay;
    private static ZoneId myZone;
    
    //**** Constructor(s) **********************************************************************************************
    
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
    
    //**** Accessor/Mutator Method(s) **********************************************************************************
    
    /**
     * Launches the GUI
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
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("[1] Volunteer for jobs");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("[2] View My Jobs");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("[3] Exit");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("Enter a command: ");
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
            case 3: // exit
                System.out.print("Logged out..." + Main.SB_LINE_BREAK);
                break;
        }
    }

    /**
     * Lists the parks in anticipation of the volunteer selecting jobs from that park to sign up for
     */
    private void signUpForJobByPark(){
        super.displayHeader(myVolunteer, myDay);

        mySB.append("Which park do you want to volunteer for?");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(DASHED_LINE);
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(Main.SB_LINE_BREAK);
        //using main datastore instead of local copy
        List<Park> parkList = myDatastore.getAllParks();
        Iterator<Park> itr = parkList.iterator();
        int count = 0;
        while(itr.hasNext()) {
            Park currentPark =itr.next();
            count++;
            mySB.append('[');
            mySB.append(count);
            mySB.append("] ");
            mySB.append(currentPark.getName());
            mySB.append(" in ");
            mySB.append(currentPark.getCity());
            mySB.append(Main.SB_LINE_BREAK);
        }
        count ++;
        mySB.append('[');
        mySB.append(count);
        mySB.append("] Back to main menu");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("Enter a command: ");
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
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("Which job do you want to sign up for?");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(DASHED_LINE_FOR_JOB_LISTING);
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("[x] Date\tPark\t\tTime\tDays\tJob Description");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(DASHED_LINE_FOR_JOB_LISTING);
        mySB.append(Main.SB_LINE_BREAK);
        int count = 0;
        Iterator<Job> itr=parkJobList.iterator();
        if(parkJobList.size() == 0) mySB.append("There are no jobs to sign up for"); // Changed because parkJobList can never be NULL
        mySB.append(Main.SB_LINE_BREAK);
        List<Job> legitJobs = new ArrayList<Job>();
        //placeholder in array
        Job notARealJob = new Job();
        legitJobs.add(notARealJob);
        while(itr.hasNext()){
            Job currentJob=itr.next();
            if (minDaysAway(currentJob)) { // This if-statement calls the helper method directly
                count++;
                legitJobs.add(currentJob);
                mySB.append('[');
                mySB.append(count);
                mySB.append("] ");
                mySB.append(currentJob.getMonth()+"/"+currentJob.getDay()+"/"+currentJob.getYear());
                //mySB.append("    ");
                mySB.append("\t");
                mySB.append(currentJob.getPark().getName());
                //mySB.append("     ");
                mySB.append("\t");
                mySB.append(currentJob.getTime());
                //mySB.append("         ");
                mySB.append("\t");
                mySB.append(currentJob.getDuration());
                //mySB.append("         ");
                mySB.append("\t");
                mySB.append(currentJob.getDescription());
                mySB.append(Main.SB_LINE_BREAK);
            }
        }
        count ++;
        mySB.append('[');
        mySB.append(count);
        mySB.append("] Back to main menu");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("Enter a command: ");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();

        if (theChoice==count) {
            mainMenu();
        } else {
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

    private void confirmScreen(int theChoice, List<Job> legitJobs) {
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

        if (!sameDayFlag) { 
            super.displayHeader(myVolunteer, myDay);
            mySB.append("You are signing up for this job: ");
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append(printJob.getName());
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append(printJob.getDescription());
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append("Date: ");
            mySB.append(printJob.getMonth());
            mySB.append("/");
            mySB.append(printJob.getDay());
            mySB.append("/");
            mySB.append(printJob.getYear());
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append("Are you sure you want to volunteer for this job? (Y/n) ");
        } else {
            super.displayHeader(myVolunteer, myDay);
            mySB.append("You can not sign up for this job: ");
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append("You have already signed up for a job on this day.");
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append("Enter 1 to return to main menu");
            mySB.append(Main.SB_LINE_BREAK);
        }
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        String confirmChoice = myScanner.next();
        if (confirmChoice.equals("Y") && !sameDayFlag) {
            printJob.setVolunteers(myVolunteer.getUsername());
            System.out.print(Main.SB_LINE_BREAK);
            System.out.print("Success: You have signed up for this Job!");
            System.out.print(Main.SB_LINE_BREAK);
            System.out.print("A confirmation email will be sent to: ");
            System.out.print(myVolunteer.getUsername());
            System.out.print(Main.SB_LINE_BREAK);
        } else {
            System.out.print(Main.SB_LINE_BREAK);
            System.out.print("You did not sign up for this Job. Returning to main menu...");
            System.out.print(Main.SB_LINE_BREAK);
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
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("Jobs currently volunteering for: ");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("Date\t\tPark\t\tTime\tDays\tJob Description");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append(DASHED_LINE_FOR_JOB_LISTING);
        mySB.append(Main.SB_LINE_BREAK);
        List<Job> volunteerJobs = myVolunteer.getJobsByVolunteer(myDatastore);
        Iterator<Job> itr = volunteerJobs.iterator();
        while( itr.hasNext()){
            Job currentJob = itr.next();
            mySB.append(currentJob.getDay());
            mySB.append("/");
            mySB.append(currentJob.getMonth());
            mySB.append("/");
            mySB.append(currentJob.getYear());
            mySB.append('\t');
            mySB.append(currentJob.getPark().getName());
            mySB.append('\t');
            mySB.append(currentJob.getTime());
            mySB.append('\t');
            mySB.append(currentJob.getDuration());
            mySB.append('\t');
            mySB.append(currentJob.getDescription());
            mySB.append('\t');
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append(Main.SB_LINE_BREAK);
            mySB.append(Main.SB_LINE_BREAK);
        }	
        mySB.append("[1] Back");
        mySB.append(Main.SB_LINE_BREAK);
        mySB.append("[2] Exit");
        mySB.append(Main.SB_LINE_BREAK);
        System.out.print(mySB.toString());
        
        System.out.print(Main.SB_LINE_BREAK); 
        System.out.print("Enter a command: ");
        int theChoice = myScanner.nextInt();

        switch (theChoice) {
            case 1:
                mainMenu();
                break;
            case 2:
                System.out.print("Logged out...");
                System.out.print(Main.SB_LINE_BREAK);
                break;
        }
    }
}
