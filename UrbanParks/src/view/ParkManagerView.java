package view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.AbstractAccount;
import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;

/**
 * Object that runs the user interface for a ParkManager user. 
 * Launched with the public launchGUI() method.
 * @author Dylan Miller
 * @author Gardner Gomes
 * @author Walter Weeks
 */
public class ParkManagerView extends AbstractView {
    
    //***** Constant(s) ************************************************************************************************
    
    public static final int TEMP_MAX_JOBS = 20;
    
    /**@deprecated*/
    public static final int MONTH_BUFFER = 1;
    
    //***** Field(s) ***************************************************************************************************
    
    private StringBuilder mySB = new StringBuilder();
    private final ParkManager myManager;
    private Park myPark;
    private Scanner myScanner = new Scanner(System.in);
    private static LocalDate myDay;
    private static ZoneId myZone;
    private static Datastore myDatastore;
    
    //**** Constructor(s) **********************************************************************************************
    
    /**
     * Constructor for the ParkManagerView.
     * @author Gardner Gomes
     * @param theAccount
     */
    public ParkManagerView(AbstractAccount theAccount) {
        super();
        myZone = ZoneId.of("America/Los_Angeles");
        myDay = LocalDate.now(myZone);
        myDatastore = null;
        myPark = null;
        myManager = (ParkManager) theAccount;
    }

    //**** Accessor/Mutator Method(s) **********************************************************************************
    
    /**
     * Launches the user interface for the park manager.
     * 
     * @author Gardner Gomes
     * @param theDatastore
     */
    @Override
    public Datastore launchGUI(Datastore theDatastore) {
        myDatastore = theDatastore;
        super.displayHeader(myManager, myDay);
        userChoice();
        return myDatastore;
    }
    
    /**
     * Presents the user with a choice of what to do next.
     * @author Gardner Gomes
     */
    private void userChoice() {
        mySB.append(Main.LINE_BREAK);
        mySB.append("[1] Submit Job");
        mySB.append(Main.LINE_BREAK);
        mySB.append("[2] Exit Urban Parks");
        mySB.append(Main.LINE_BREAK);
        mySB.append(Main.LINE_BREAK);
        mySB.append("Enter a command: ");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();
        switch (theChoice) {
        case 1: // submit a job
            checkMaxJobs(myDatastore);
            submitJob();
            break;
        case 2: // log out
            System.out.print("Logged out..." + Main.LINE_BREAK);
            break;
        case 3:
            ViewJobs();
            break;
        }
        
    }

    /**
     * Helper Method to check if max Jobs are already created.
     * @author Gardner Gomes
     */
    private void checkMaxJobs(Datastore theDatastore) {
        if (theDatastore.getNumberOfJobs() == TEMP_MAX_JOBS) {
            System.out.println("Max Number of Jobs reached. Please choose a different selection.");
            this.userChoice();
        }
    }
    
    
    /**
     * Submit a Job element for the UI.
     * @author Gardner Gomes
     */
    private void submitJob() {
        Job myJob = new Job();
        myJob = addPark(myJob);
        super.displayHeader(myManager, myDay);
        System.out.print("Submit a Job for " + myManager.getRealName());
        System.out.print(" at " + myPark.getName() + " in " + myPark.getCity());
        System.out.print(Main.LINE_BREAK);
        System.out.print(DASHED_LINE);
        System.out.print(Main.LINE_BREAK);
        System.out.print(Main.LINE_BREAK);
        myJob = addName(myJob);
        myJob = addDescription(myJob);
        myJob = addMonth(myJob);
        myJob = addDay(myJob);
        myJob = addYear(myJob);
        myJob = addTime(myJob);
        myJob = addDuration(myJob);
        myJob = addNotes(myJob);
        try {
            myDatastore.addJob(myJob);
        } catch (Exception e){
            System.out.print(Main.LINE_BREAK);
            System.out.print("ERROR: Adding the Job failed, please try again.");
            System.out.print(Main.LINE_BREAK);
            System.out.print("Check the values you entered. Remember, the maximum number of Jobs ");
            System.out.print(Main.LINE_BREAK);
            System.out.print("per day for the volunteer system is " + Datastore.MAX_PENDING_JOBS_PER_DAY_DEFAULT);
            System.out.print(" (with a total of " + Datastore.MAX_PENDING_JOBS_DEFAULT + ")");
            System.out.print(Main.LINE_BREAK);
            System.out.print(" and a maximum of " + Datastore.MAX_FUTURE_JOB_START_DATE + " days "
                    + "in the future.");
            System.out.print(Main.LINE_BREAK);
            submitJob();
        }
        System.out.print(Main.LINE_BREAK);
        System.out.print("Job successfully submitted for " + myPark.getName() + "!");
        System.out.print(Main.LINE_BREAK);
        userChoice();
    }

    /**
     * Park manager sets the duration of the job in days.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller
     */
    Job addDuration(Job theJob) {
        System.out.print("Please set the Duration for this job (in days): ");
        try {
            theJob.setDuration(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addDuration(theJob);
        }
        return theJob;
    }
    
    /**
     * Park manager sets additional notes for the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addNotes(Job theJob) {
        System.out.print("Please add any additional notes here: ");
        try {
            theJob.setNotes(myScanner.nextLine());
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addNotes(theJob);
        }
        return theJob;
    }
    
    /**
     * Park manager sets the maximum number of volunteers for a job.
     * @author Gardner Gomes
     * @param theJob
     * @return
     * @deprecated
     */
    Job addVolunteerMax(Job theJob) {
        System.out.print("Please set the maximum number of Volunteers: ");
        try {
            theJob.setMaxVolunteers(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addVolunteerMax(theJob);
        }
        return theJob;
    }
    
    /**
     * Park manager sets the time for the job.
     * @author Garder Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addTime(Job theJob) {
        System.out.print("Please set the Time of this Job in 24-hour format (e.g., 13:30): ");
        try {
            theJob.setTime(myScanner.nextLine());
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addTime(theJob);
        }
        return theJob;
    }

    /**
     * Park manager sets the year of the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addYear(Job theJob) {
        System.out.print("Please set the Year of this Job (e.g., 2017): ");
        try {
            theJob.setYear(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addYear(theJob);
        }
        return theJob;

    }

    /**
     * Park manager sets the day of the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addDay(Job theJob) {
        System.out.print("Please set the Day of this Job (1-31): ");
        try {
            theJob.setDay(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addDay(theJob);
        }
        return theJob;
    }

    /**
     * Park manager sets the month of the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addMonth(Job theJob) {
        System.out.print("Please set the Month of this Job (1-12): ");
        int monthChoice = Integer.parseInt(myScanner.nextLine());
        try {

            theJob.setMonth(monthChoice);
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addMonth(theJob);
        }
        return theJob;
    }

    /**
     * Park manager sets a description of the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addDescription(Job theJob) {
        System.out.print("Please type out a Description of the Job: ");
        try {
            theJob.setDescription(myScanner.nextLine());
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addDescription(theJob);
        }
        return theJob;
    }

    /**
     * Park manager sets a name for the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addName(Job theJob) {
        System.out.print("Please set the Name of the Job: ");
        try {
            myScanner.nextLine();
            theJob.setName(myScanner.nextLine());
        } catch (Exception e) {
            addName(theJob);
        }
        return theJob;
    }

    /**
     * Park manager sets the park for the job.
     * @author Gardner Gomes
     * @param theJob
     * @return theJob back to caller.
     */
    Job addPark(Job theJob) {
        List<Park> theParks = myDatastore.getAllParks();
        Iterator<Park> itr = theParks.iterator();
        while (theJob.getPark() == null) {
            Park tempPark = (Park) itr.next();
            if (tempPark.getManager().equals(myManager)) {
                theJob.setPark(tempPark);
                myPark = theJob.getPark();
            }
        }
        return theJob;
    }

    /**
     * View Jobs elemt for UI
     * @author Gardner Gomes
     * @deprecated
     */
    private void ViewJobs() { 
        super.displayHeader(myManager, myDay);
        int count = 1;
        for (int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
            if (myDatastore.getPendingJobs().get(i).getPark().getManager().equals(myManager)) {
                mySB.append(count++);
                mySB.append(". ");
                mySB.append(myDatastore.getPendingJobs().get(i).getName());
                mySB.append(", ");
                mySB.append(myDatastore.getPendingJobs().get(i).getDay());
                mySB.append("/");
                mySB.append(myDatastore.getPendingJobs().get(i).getMonth());
                mySB.append("\n");
            }
        }
        mySB.append("\nPast Jobs Below this Point\n");
        for (int i = 0; i < myDatastore.getPreviousJobs().size(); i++) {
            if (myDatastore.getPreviousJobs().get(i).getPark().getManager().equals(myManager)) {
                mySB.append(count++);
                mySB.append(". ");
                mySB.append(myDatastore.getPreviousJobs().get(i).getName());
                mySB.append(", ");
                mySB.append(myDatastore.getPreviousJobs().get(i).getDay());
                mySB.append("/");
                mySB.append(myDatastore.getPreviousJobs().get(i).getMonth());
                mySB.append("\n");
            }
        }
        mySB.append(Main.LINE_BREAK);
        mySB.append(Main.LINE_BREAK);
        mySB.append("Please choose a job you want to view: "); 
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();
        count = 0;
        boolean flag = false;
        for (int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
            if (myDatastore.getPendingJobs().get(i).getPark().getManager().equals(myManager)) {
                count++;
                if (count == theChoice) {
                    showAJob(myDatastore.getPendingJobs().get(i));
                    break;
                }
            }
            if (i == myDatastore.getNumberOfJobs() - 1) {
                flag = true;
                System.out.println("got here");
                theChoice = theChoice - count;
                count = 0;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < myDatastore.getPreviousJobs().size(); i++) {
                if (myDatastore.getPreviousJobs().get(i).getPark().getManager().equals(myManager)) {
                    count++;
                    if (count == theChoice) {
                        showAJob(myDatastore.getPreviousJobs().get(i));
                    }
                }
            }
        }
    }

    /**
     * @author Gardner Gomes
     * @param theJob
     * @deprecated
     */
    private void showAJob(Job theJob) {
        super.displayHeader(myManager, myDay);
        showJobInformation(theJob);
        showVolunteers(theJob);
        userChoice();
    }

    /**
     * @author Gardner Gomes
     * @param theJob
     * @deprecated
     */
    private void showJobInformation(Job theJob) {
        mySB.append("Name: ");
        mySB.append(theJob.getName());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Description: ");
        mySB.append(theJob.getDescription());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Date(Day/Month/year): ");
        mySB.append(theJob.getDay());
        mySB.append("/");
        mySB.append(theJob.getMonth());
        mySB.append("/");
        mySB.append(theJob.getYear());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Time: ");
        mySB.append(theJob.getTime());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Durration for the Job: ");
        mySB.append(theJob.getDuration());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Minumum Light Grade Volunteers(0 or More): ");
        mySB.append(Main.LINE_BREAK);
        mySB.append("Minumum Medium Grade Volunteers(0 or More): ");
        mySB.append(Main.LINE_BREAK);
        mySB.append("Minumum Heavy Grade Volunteers(0 or More): ");
//        mySB.append("\nMaximum Volunteers(0 or More): "); 
//        mySB.append(theJob.getMaxVolunteers());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Additional Notes: ");
        mySB.append(theJob.getNotes());
        mySB.append(Main.LINE_BREAK);
        System.out.println(mySB.toString());
        mySB.delete(0, mySB.capacity());
    }

    /**
     * @author Gardner Gomes
     * @deprecated
     * @param theJob
     */
    private void showVolunteers(Job theJob) {
        if (!theJob.getVolunteers().isEmpty()) {
            System.out.print(Main.LINE_BREAK);
            System.out.print("Volunteers for this Job.");
            System.out.print(Main.LINE_BREAK);
            List<String> myVolunteers = theJob.getVolunteers();
            Iterator<String> itr = myVolunteers.iterator();
            int count = 1;
            while (itr.hasNext()) {
                mySB.append(count++);
                mySB.append(".");
                mySB.append(itr.next());
                System.out.println(mySB.toString());
                mySB.delete(0, mySB.capacity());
            }
        } else {
            System.out.print("There are no Volunteers signed up.");
            System.out.print(Main.LINE_BREAK);
            System.out.print(Main.LINE_BREAK);
        }
    }

}
