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

    /*
     * -------------------------------------------------------------------------
     * ----------------------------------------------------
     */
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
    /*
     * -------------------------------------------------------------------------
     * ----------------------------------------------------
     */

    /**
     * Submit a Job element for the UI.
     */
    private void SubmitJob() {
        Job myJob = new Job();
        System.out.println("\nSubmit a Job for " + myManager.getRealName());
        System.out.print("----------------------------------------------------------\n\n");
        addPark(myJob);
        addName(myJob);
        addDescription(myJob);
        addDay(myJob);
        addMonth(myJob);
        addYear(myJob);
        addTime(myJob);
        addLightGrade(myJob);
        addMediumGrade(myJob);
        addHeavyGrade(myJob);
        addVolunteerMax(myJob);
        addNotes(myJob);
        myDatastore.addJob(myJob); // TODO TryCatch
        showJob(myJob);

    }

    void addNotes(Job myJob) {
        System.out.print("Please add any aditional notes here: ");
        myJob.setNotes(myScanner.nextLine());
     // TODO TryCatch
    }

    void addVolunteerMax(Job myJob) {
        System.out.print("Please set the maximum number of Volunteers: ");
        myJob.setMaxVolunteers(Integer.parseInt(myScanner.nextLine()));
     // TODO TryCatch
    }

    void addHeavyGrade(Job myJob) {
        System.out.print("Please set the minumum Heavy Grade Workers: ");
        myJob.setMinHeavy(Integer.parseInt(myScanner.nextLine()));
     // TODO TryCatch
    }

    void addMediumGrade(Job myJob) {
        System.out.print("Please set the minumum Medium Grade Workers: ");
        myJob.setMinMedium(Integer.parseInt(myScanner.nextLine()));
     // TODO TryCatch
    }

    void addLightGrade(Job myJob) {
        System.out.print("Please set the minumum Light Grade Workers: ");
        myJob.setMinLight(Integer.parseInt(myScanner.nextLine()));
     // TODO TryCatch
    }

    void addTime(Job myJob) {
        System.out.print("Please set the Time of this Job(ie. 12:30): ");
        myJob.setTime(myScanner.nextLine());
     // TODO TryCatch
    }

    private void addYear(Job myJob) {
        // TODO Auto-generated method stub
        
    }

    void addDay(Job myJob) {
        System.out.print("Please set the Day of this Job(1-31): ");
        myJob.setDay(Integer.parseInt(myScanner.nextLine()));
     // TODO TryCatch
    }

    void addMonth(Job myJob) {
        System.out.print("Please set the Month of this Job(1-12): ");
        myJob.setMonth(Integer.parseInt(myScanner.nextLine()));
     // TODO TryCatch
    }

    void addDescription(Job myJob) {
        System.out.print("Please type out a description of the Job: ");
        myJob.setDescription(myScanner.nextLine());
     // TODO TryCatch
    }

    void addName(Job myJob) {
        System.out.print("Please set the Name of the Job: ");
        myScanner.nextLine();
        myJob.setName(myScanner.nextLine());
     // TODO TryCatch
    }

    void addPark(Job myJob) {
        List<Park> theParks = myDatastore.getAllParks();
        Iterator<Park> itr = theParks.iterator();
        while (myJob.getPark() == null) {
            Park tempPark = (Park) itr.next();
            if (tempPark.getManager().equals(myManager)) {
                myJob.setPark(tempPark);
            }
        }
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

        // TODO UI functionality

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
        mySB.append("\nDate(Day/Month): ");
        mySB.append(theJob.getDay());
        mySB.append("/");
        mySB.append(theJob.getMonth());
        mySB.append("\nTime: ");
        mySB.append(theJob.getTime());
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

    }

}
