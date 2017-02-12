/**
 * 
 */
package view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import backend.Account;
import backend.Datastore;
import backend.Job;
import backend.Park;
import backend.ParkManager;

/**
 * @author dylan
 * @author VG Gnome
 */
public class ParkManagerView extends View {
    private static final int TEMP_MAX_JOBS = 30;
    private Scanner myScanner = new Scanner(System.in);
    private StringBuilder mySB = new StringBuilder();
    private final ParkManager myManager;
    private final Datastore myDatastore;

    public ParkManagerView(Account theAccount, Datastore theDatastore) {
        super();
        myManager = (ParkManager) theAccount;
        myDatastore = theDatastore;
    }

    /*-----------------------------------------------------------------------------------------------------------------------------*/
    @Override
    public void launchGUI() {
        mySB.append("\nWelcome to Urban Parks\nPark Manager: ");
        mySB.append(myManager.getRealName());
        mySB.append("\n----------------------------------------------------------\n\n");
        mySB.append("1. Submit Job\n");
        mySB.append("2. View Jobs\n");
        mySB.append("3. Exit\n");
        mySB.append("\n\nPlease Select a number followed by the enter key: ");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();

        switch (theChoice) {
        case 1:
            checkMaxJobs();
            SubmitJob();
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
            System.out.println("Max Number of Jobs reached. Please choose a different selection.\n\n");
            this.launchGUI();
        }
    }
    /*-----------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Submit a Job element for the UI.
     */
    private void SubmitJob() {
        Job myJob = new Job();
        System.out.println("\nSubmit a Job for " + myManager.getRealName());
        System.out.print("----------------------------------------------------------\n\n");
        myJob = addPark(myJob);
        myJob = addName(myJob);
        myJob = addDescription(myJob);
        myJob = addDay(myJob);
        myJob = addMonth(myJob);
        myJob = addYear(myJob);
        myJob = addTime(myJob);
        myJob = addDuration(myJob);
        myJob = addLightGrade(myJob);
        myJob = addMediumGrade(myJob);
        myJob = addHeavyGrade(myJob);
        myJob = addVolunteerMax(myJob);
        myJob = addNotes(myJob);
        try {
            myDatastore.addJob(myJob);
        } catch (Exception e){
            System.out.println("Adding the Job Failed Unexpectedly, try again\n");
            SubmitJob();
        }
        Main.save();
        showJob(myJob);

    }

    private Job addDuration(Job theJob) {
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

    Job addHeavyGrade(Job theJob) {
        System.out.print("Please set the minumum Heavy Grade Workers: ");
        try {
            theJob.setMinHeavy(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addHeavyGrade(theJob);
        }
        return theJob;
    }

    Job addMediumGrade(Job theJob) {
        System.out.print("Please set the minumum Medium Grade Workers: ");
        try {
            theJob.setMinMedium(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
            addMediumGrade(theJob);
        }
        return theJob;
    }

    Job addLightGrade(Job theJob) {
        System.out.print("Please set the minumum Light Grade Workers: ");
        try {
            theJob.setMinLight(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.print(e.getMessage());
            addLightGrade(theJob);
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
        System.out.print("Please set the Month of this Job(1-12): ");
        try {
            theJob.setMonth(Integer.parseInt(myScanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Value not Accepted.");
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

    /*
     * -------------------------------------------------------------------------
     * ----------------------------------------------------
     */
    /**
     * View Jobs elemt for UI
     */
    private void ViewJobs() {
        mySB.append("\nView Jobs for ");
        mySB.append(myManager.getRealName());
        mySB.append("\n----------------------------------------------------------\n\n");
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
        for (int i = myDatastore.getNumberOfJobs(); i < myDatastore.getNumberOfJobs()
                + myDatastore.getNumberOfPreviousJobs(); i++) {
            if (myDatastore.getPreviousJobs().get(i).getPark().getManager().equals(myManager)) {
                mySB.append(count++);
                mySB.append(". ");
                mySB.append(myDatastore.getPreviousJobs().get(i).getName());
                mySB.append(", ");
                mySB.append(myDatastore.getPendingJobs().get(i).getDay());
                mySB.append("/");
                mySB.append(myDatastore.getPendingJobs().get(i).getMonth());
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
                }
            }
            if (i == myDatastore.getNumberOfJobs()) {
                flag = true;
                theChoice = theChoice - count;
                count = 0;
            }
        }
        if (flag) {
            for (int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
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
        mySB.append("\n\nView of the Job.");
        mySB.append("\n----------------------------------------------------------\n\n");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        showJobInformation(theJob);
        showVolunteers(theJob);

        mySB.append("\nPlease choose what you would like to do now");
        mySB.append("\n----------------------------------------------------------\n\n");
        mySB.append("1. Submit Job\n");
        mySB.append("2. View Jobs\n");
        mySB.append("3. Exit\n");
        System.out.print(mySB.toString());
        mySB.delete(0, mySB.capacity());
        int theChoice = myScanner.nextInt();

        switch (theChoice) {
        case 1:
            checkMaxJobs();
            SubmitJob();
            break;
        case 2:
            ViewJobs();
            break;
        case 3:
            break;
        }
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
        mySB.append(theJob.getMinLight());
        mySB.append("\nMinumum Medium Grade Volunteers(0 or More): ");
        mySB.append(theJob.getMinMedium());
        mySB.append("\nMinumum Heavy Grade Volunteers(0 or More): ");
        mySB.append(theJob.getMinHeavy());
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
                mySB.append(count);
                mySB.append(".");
                mySB.append(itr.next());
                System.out.println(mySB.toString());
                mySB.delete(0, mySB.capacity());
            }
        } else {
            System.out.println("There are no Volunteers signed up.\n");
        }
    }

}
