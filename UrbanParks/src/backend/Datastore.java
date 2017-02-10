package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The datastore for Urban Parks. This class contains data used by the organization such as Parks, Jobs, etc.
 *
 * @author Walter Weeks (ww3@uw.edu)
 * @version 1.0 (2017 Feb 3)
 */
public final class Datastore implements Serializable {

    //***** Constant(s) ************************************************************************************************

    /** The maximum number of pending jobs default value. */
    public static final int MAX_PENDING_JOBS_DEFAULT = 30;

    //***** Field(s) ***************************************************************************************************

    /** The user accounts on the system, i.e, Volunteer, OfficeStaff, and ParkManager. */
    private List<Account> myAccounts;

    /** The parks list. */
    private List<Park> myParks;

    /** The current pending jobs list. */
    private List<Job> myPendingJobs;

    /**
     * The previous jobs list. Note that these are any Job that is not supposed to be in the pending Jobs list such as
     * complete, cancelled, etc.
     */
    private List<Job> myPreviousJobs;

    /** The maxixum number of pending jobs. */
    private int myMaxPendingJobs;

    //**** Constructor(s) **********************************************************************************************

    /**
     * The no-argument constructor that creates an empty backend.Datastore.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    public Datastore() {
        myAccounts = new ArrayList<>();
        myParks = new ArrayList<>();
        myPendingJobs = new ArrayList<>();
        myPreviousJobs = new ArrayList<>();
        myMaxPendingJobs = MAX_PENDING_JOBS_DEFAULT;
    }

    //**** Accessor/Mutator Method(s) **********************************************************************************

    /**
     * Gets the list of pending jobs at a given park.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param thePark the park we want the jobs from.
     * @throws NullPointerException if thePark is null.
     * @return the list of jobs given a Park.
     */
    public final List<Job> getJobsByPark(final Park thePark) {
        if (thePark == null) {
            throw new NullPointerException("thePark cannot be null.");
        }

        List<Job> result = new ArrayList<>();

        // Iterate over the entire pending jobs list to compile the list of job @ a given park
        Iterator<Job> itr = myPendingJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (thePark.equals(currentJob.getPark())) {
                result.add(currentJob);
            }
        }

        return result;
    }

    /**
     * Gets a list of jobs of a given volunteer.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param theVolunteer The volunteer.
     * @return The list of jobs of a given volunteer.
     * @throws NullPointerException if theVolunteer is null.
     */
    public final List<Job> getJobsByVolunteer(final Volunteer theVolunteer) {
        if (theVolunteer == null) {
            throw new NullPointerException("theVolunteer cannot be null.");
        }

        List<Job> result = new ArrayList<>();

        // Iterate over the entire pending jobs list to compile the list of job @ a given park
        Iterator<Job> itr = myPendingJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (currentJob.getVolunteers().contains(theVolunteer.getUsername())) {
                result.add(currentJob);
            }
        }

        return result;
    }

    /**
     * Gets a list of pending jobs given a particular park manager.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param theParkManager The park manager.
     * @return The list of pending jobs that the park manager has.
     */
    public final List<Job> getJobsByParkManager(final ParkManager theParkManager) {
        if (theParkManager == null) {
            throw new NullPointerException("theParkManager cannot be null.");
        }

        // Compile the list of Parks within a given city and ZIP Code
        List<Park> managedParks = new ArrayList<>();
        for (int i = 0; i < myParks.size(); i++) {
            if (myParks.get(i).getManager().equals(theParkManager)) {
                managedParks.add(myParks.get(i));
                //System.out.println("Added Park: " + myParks.get(i).getName());
            }
        }

        List<Job> result = new ArrayList<>();

        // Iterate over the entire pending jobs list to compile the list of job @ a given city
        Iterator<Job> itr = myPendingJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (managedParks.contains(currentJob.getPark())) {
                result.add(currentJob);
                //System.out.println("Added Job: " + currentJob.getMyDescription());
            }
        }

        return result;
    }

    /**
     * Gets the list of pending jobs within a given city and state.
     *
     * Note that the ZIP Code is useful city name conflict resolution, i.e., cities
     * with the same name but are in different places.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param theCity city string.
     * @param theState state string.
     * @throws NullPointerException if theCity is null.
     * @throws NullPointerException if theState is null.
     * @throws IllegalArgumentException if theCity is less than 1 character.
     * @throws IllegalArgumentException if theState is less than 2 characters.
     * @return the list of jobs given a city.
     */
    public final List<Job> getJobsByCity(final String theCity, final String theState) {
        if (theCity == null) {
            throw new NullPointerException("theCity cannot be null.");
        }
        if (theCity.length() < 1) {
            throw new IllegalArgumentException("theCity must be at least one character.");
        }
        if (theState == null) {
            throw new NullPointerException("theState cannot be null.");
        }
        if (theState.length() < 2) {
            throw new IllegalArgumentException("The state must be at least 2 characters");
        }

        // Compile the list of Parks within a given city and ZIP Code
        List<Park> parks = new ArrayList<>();
        for (int i = 0; i < myParks.size(); i++) {
            if (myParks.get(i).getCity().equals(theCity) && myParks.get(i).getState().equals(theState)) {
                parks.add(myParks.get(i));
                //System.out.println("Added Park: " + myParks.get(i).getName());
            }
        }

        List<Job> result = new ArrayList<>();

        // Iterate over the entire pending jobs list to compile the list of job @ a given city
        Iterator<Job> itr = myPendingJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (parks.contains(currentJob.getPark())) {
                result.add(currentJob);
                //System.out.println("Added Job: " + currentJob.getMyDescription());
            }
        }

        return result;
    }


    /**
     * Adds a new pending job to the list, if it does not exceed the maximum allowed or if it is not already in
     * the list.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param theJob the job to add to the pending jobs list.
     */
    public void addJob(final Job theJob) {
        if (theJob == null) {
            throw new NullPointerException("theJob cannot be null.");
        }
        if (myPendingJobs.size() < myMaxPendingJobs && !myPendingJobs.contains(theJob)) {
            myPendingJobs.add(theJob);
        }
    }

    /**
     * Moves a currently pending job to the previous jobs list, if it exists. Otherwise this
     * method does nothing.
     *
     * @author Walter Weeks
     * @param theJob the job to move to previous jobs list.
     */
    public void removeJob(final Job theJob) {
        if (theJob == null) {
            throw new NullPointerException("theJob cannot be null.");
        }

        // Iterate over the entire pending jobs list in search of the job in question: O(n) runtime.
        Iterator<Job> itr = myPendingJobs.iterator();
        boolean found = false;
        while (itr.hasNext() && !found) {
            Job currentJob = itr.next();
            if (theJob.equals(currentJob)) {
                myPreviousJobs.add(currentJob);
                itr.remove();
                found = true;
            }
        }
    }

    /**
     * Adds a park to the list of parks.
     *
     * @author Walter Weeks
     * @param thePark The park to add to the list.
     * @throws NullPointerException if thePark is null.
     */
    public void addPark(final Park thePark) {
        if (thePark == null) {
            throw new NullPointerException("thePark cannot be null.");
        }
        if (!myParks.contains(thePark)) {
            myParks.add(thePark);
        }
    }

    /**
     * Getter for the current number of pending jobs in the list.
     *
     * @author Walter Weeks
     * @return The number of pending jobs currently in the system.
     */
    public int getNumberOfJobs() {
        return myPendingJobs.size();
    }

    /**
     * Getter for the current number of previous jobs.
     *
     * @author Walter Weeks
     * @return The number of previous jobs in the system.
     */
    public int getNumberOfPreviousJobs() {
        return myPreviousJobs.size();
    }

    /**
     * Adds an account to the list of accounts.
     *
     * @author Walter Weeks
     * @param theAccount The account.
     */
    public void addAccount(final Account theAccount) {
        if (theAccount == null) {
            throw new NullPointerException("theAccount cannot be null.");
        }
        if (!myAccounts.contains(theAccount)) {
            myAccounts.add(theAccount);
        }
    }

    /**
     * Get the number of accounts in the Urban Parks system.
     *
     * @author Walter Weeks
     * @return The number of accounts.
     */
    public int getNumberOfAccounts() {
        return myAccounts.size();
    }

    /**
     * Get the number of parks in the Urban Parks system.
     *
     * @author Walter Weeks
     * @return The number of parks.
     */
    public int getNumberOfParks() {
        return myParks.size();
    }

    /**
     * Get the pending jobs list.
     *
     * @author Walter Weeks
     * @return The pending job list.
     */
    public List<Job> getPendingJobs() {
        return myPendingJobs;
    }

    /**
     * Set the maximum number of pending jobs.
     *
     * @author Walter Weeks
     * @param theMaxPendingJobs The new maximum number of pending jobs.
     * @throws IllegalArgumentException if theMaxPendingJobs is less than 1.
     */
    public void setMaxPendingJobs(final int theMaxPendingJobs) {
        if (theMaxPendingJobs < 1) {
            throw new IllegalArgumentException("theMaxPendingJobs value must be at least 1.");
        }

        myMaxPendingJobs = theMaxPendingJobs;
    }
}
