/**
 * 
 */
package view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.AbstractAccount;
import model.Datastore;
import model.Job;
import model.Park;
import model.ParkManager;

/**
 * @author dylan
 * @author VG Gnome
 */
public class ParkManagerView extends AbstractView {
    private static final int TEMP_MAX_JOBS = 30;
    private static final int MONTH_BUFFER = 1;
    private StringBuilder mySB = new StringBuilder();
    private final ParkManager myManager;
    private Scanner myScanner = new Scanner(System.in);
    private static LocalDate myDay;
    private static ZoneId myZone;
    private static Datastore myDatastore;
    
    
    public ParkManagerView(AbstractAccount theAccount) {
        super();
        myZone = ZoneId.of("America/Los_Angeles");
        myDay = LocalDate.now(myZone);
        myDatastore = null;
        myManager = (ParkManager) theAccount;
    }

    /*-----------------------------------------------------------------------------------------------------------------------------*/
    @Override
    public Datastore launchGUI(Datastore theDatastore) {
        myDatastore = theDatastore;
        displayHeader();
        userChoice();
        return myDatastore;
    }
    /**
     * presents the user with a choice of what to do next.
     */
    private void userChoice() {
        mySB.append("\n1. Submit Job\n");
        mySB.append("2. View Jobs\n");
        mySB.append("3. Exit eUrbanParks\n");
        mySB.append("\nWhat would you like to do?: ");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();
        switch (theChoice) {
        case 1:
            checkMaxJobs();
            submitJob();
            break;
        case 2:
            ViewJobs();
            break;
        case 3:
            break;
        }
        
    }

    /**
     * Helper Method to check if max Jobs are already created.
     */
    private void checkMaxJobs() {
        if (myDatastore.getNumberOfJobs() == TEMP_MAX_JOBS) {
            System.out.println("Max Number of Jobs reached. Please choose a different selection.");
            this.userChoice();
        }
    }
    /*-----------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Submit a Job element for the UI.
     */
    private void submitJob() {
        Job myJob = new Job();
        displayHeader();
        System.out.println("Submit a Job for " + myManager.getRealName());
        System.out.print("----------------------------------------------------------\n\n");
        myJob = addPark(myJob);
        myJob = addName(myJob);
        myJob = addDescription(myJob);
        myJob = addMonth(myJob);
        myJob = addDay(myJob);
        myJob = addYear(myJob);
        myJob = addTime(myJob);
        myJob = addDuration(myJob);
        myJob = addVolunteerMax(myJob);
        myJob = addNotes(myJob);
        try {
            myDatastore.addJob(myJob);
        } catch (Exception e){
            System.out.println("Adding the Job Failed Unexpectedly, try again\n");
            submitJob();
        }
        Main.save();
        System.out.println("Job submitted. You can view it in your Jobs.");
        userChoice();

    }

    Job addDuration(Job theJob) {
        System.out.print("Please set the Duration for this job(1 or 2 days): ");
        try {
            theJob.setDuration(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addDuration(theJob);
        }
        return theJob;
    }

    Job addNotes(Job theJob) {
        System.out.print("Please add any aditional notes here: ");
        try {
            theJob.setNotes(myScanner.nextLine());
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addNotes(theJob);
        }
        return theJob;
    }

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

    Job addTime(Job theJob) {
        System.out.print("Please set the Time of this Job(ie. 12:30): ");
        try {
            theJob.setTime(myScanner.nextLine());
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addTime(theJob);
        }
        return theJob;
    }

    Job addYear(Job theJob) {
        System.out.print("Please set the Year of this Job(ie.2017): ");
        try {
            theJob.setYear(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addYear(theJob);
        }
        return theJob;

    }

    Job addDay(Job theJob) {
        System.out.print("Please set the Day of this Job(1-31): ");
        try {
            theJob.setDay(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addDay(theJob);
        }
        return theJob;
    }

    Job addMonth(Job theJob) {
        System.out.print("Please set the Month of this Job(1-12, Within 1 month from now): ");
        
        int monthChoice = Integer.parseInt(myScanner.nextLine());
        if(monthChoice <= myDay.getMonth().getValue() + MONTH_BUFFER && monthChoice >= myDay.getMonth().getValue()) {
            try {
                
                    theJob.setMonth(monthChoice);
            } catch (Exception e) {
                System.out.println("Value not Accepted.");
                addMonth(theJob);
            }
        } else {
            addMonth(theJob);
        }
        return theJob;
    }

    Job addDescription(Job theJob) {
        System.out.print("Please type out a description of the Job: ");
        try {
            theJob.setDescription(myScanner.nextLine());
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addDescription(theJob);
        }
        return theJob;
    }

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

    Job addPark(Job theJob) {
        List<Park> theParks = myDatastore.getAllParks();
        Iterator<Park> itr = theParks.iterator();
        while (theJob.getPark() == null) {
            Park tempPark = (Park) itr.next();
            if (tempPark.getManager().equals(myManager)) {
                theJob.setPark(tempPark);
            }
        }
        return theJob;
    }

    /**
     * View Jobs elemt for UI
     */
    private void ViewJobs() {
        displayHeader();
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
        mySB.append("\n\nPlease choose a job you want to view: ");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();
        count = 0;
        boolean flag = false;
        for (int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
            if (myDatastore.getPendingJobs().get(i).getPark().getManager().equals(myManager)) {
                count++;
                if (count == theChoice) {
                    showJob(myDatastore.getPendingJobs().get(i));
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
                        showJob(myDatastore.getPreviousJobs().get(i));
                    }
                }
            }
        }

    }

    /**
     * 
     * @param theJob
     */
    private void showJob(Job theJob) {
        displayHeader();
        showJobInformation(theJob);
        showVolunteers(theJob);
        userChoice();
    }

    /**
     * 
     * @param theJob
     */
    private void showJobInformation(Job theJob) {
        mySB.append("Name: ");
        mySB.append(theJob.getName());
        mySB.append("\nDescription: ");
        mySB.append(theJob.getDescription());
        mySB.append("\nDate(Day/Month/year): ");
        mySB.append(theJob.getDay());
        mySB.append("/");
        mySB.append(theJob.getMonth());
        mySB.append("/");
        mySB.append(theJob.getYear());
        mySB.append("\nTime: ");
        mySB.append(theJob.getTime());
        mySB.append("\nDurration for the Job: ");
        mySB.append(theJob.getDuration());
        mySB.append("\nMinumum Light Grade Volunteers(0 or More): ");
        mySB.append("\nMinumum Medium Grade Volunteers(0 or More): ");
        mySB.append("\nMinumum Heavy Grade Volunteers(0 or More): ");
        mySB.append("\nMaximum Volunteers(0 or More): ");
        mySB.append(theJob.getMaxVolunteers());
        mySB.append("\nAdditional Notes: ");
        mySB.append(theJob.getNotes());
        mySB.append("\n");
        System.out.println(mySB.toString());
        mySB.delete(0, mySB.capacity());
    }

    /**
     * 
     * @param theJob
     */
    private void showVolunteers(Job theJob) {
        if (!theJob.getVolunteers().isEmpty()) {
            System.out.print("\nVolunteers for this Job.\n");
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
            System.out.println("There are no Volunteers signed up.\n");
        }
    }
    
    void displayHeader() {
      mySB.append("\neUrbanParks: the Volunteer organizer for Park Districts nationwide\n");
      mySB.append(myManager.getRealName());
      mySB.append(" logged in as Urban Parks Manager\n");
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
