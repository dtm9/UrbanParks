package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    /**Default serialVersionUID. This should not change.*/
	private static final long serialVersionUID = 1L;

	/**The maximum number of pending jobs default value.*/
    public static final int MAX_PENDING_JOBS_DEFAULT = 20;

    /**The maximum number of pending jobs on a calendar date default value.*/
    public static final int MAX_PENDING_JOBS_PER_DAY_DEFAULT = 4;
    
    /**The maximum number of days in the future a job can be created.*/
    public static final int MAX_FUTURE_JOB_START_DATE = 75;

    //***** Field(s) ***************************************************************************************************

    /** The user accounts on the system, i.e, Volunteer, OfficeStaff, and ParkManager. */
    private List<AbstractAccount> myAccounts;

    /** The parks list. */
    private List<Park> myParks;

    /** The current pending jobs list. */
    private List<Job> myPendingJobs;

    /**
     * The previous jobs list. Note that these are any Job that is not supposed to be in the pending Jobs list such as
     * complete, cancelled, etc.
     */
    private List<Job> myPreviousJobs;

    /** The maximum number of pending jobs. */
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
     * Adds a new pending job to the list, if it does not exceed the maximum allowed or if it is not already in
     * the list.
     *
     * @author Walter Weeks (ww3@uw.edu)
     * @param theJob the job to add to the pending jobs list.
     * @throw NullPointerException if theJob is null.
     * @throw InvalidJobDurationException if the number of jobs is already at maximum capacity.
     */
    public void addJob(final Job theJob) {
        if (theJob == null) { // Check for null
            throw new NullPointerException("theJob cannot be null.");
        }
        if (myPendingJobs.size() >= myMaxPendingJobs) { // Check for total system pending jobs
            throw new IllegalStateException("System cannot have more than " + myMaxPendingJobs + " jobs.");
        }
        
        if (!isJobWithinMaxFutureDate(theJob)) {
            throw new IllegalStateException("System will only accept jobs within " + MAX_FUTURE_JOB_START_DATE + " days from today.");
        }

        if (hasMaxJobsAlreadyOnJobDate(LocalDate.of(theJob.getYear(), theJob.getMonth(), theJob.getDay()), theJob.getDuration())) {
            throw new InvalidJobDurationException("System cannot have more than " +
                    MAX_PENDING_JOBS_PER_DAY_DEFAULT + " jobs per day."); 
        }

        if (!myPendingJobs.contains(theJob)) {
           myPendingJobs.add(theJob);
        }
    }
   
    /**
     * Determines whether or not a job date conflicts with the maximum number of pending jobs on a given 
     * day. This method checks each day in its day range, i.e., the job's "duration."
     * 
     * @param theStartDate The start dating of the job,
     * @param theDuration The number of days of the job.
     * @return True if the given start date to end date has the maximum number of pending jobs allowed;
     * false otherwise.
     */
    public boolean hasMaxJobsAlreadyOnJobDate(LocalDate theStartDate, int theDuration) {
    	boolean result = false;
    	
    	List<Job> jobsOnDateList;
    	LocalDate currentDate = theStartDate;
    	
    	for (int i = 0; i < theDuration; i++) {
    		jobsOnDateList = getJobsByDate(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
    		if (jobsOnDateList.size() >= MAX_PENDING_JOBS_PER_DAY_DEFAULT) {
                result = true;
                break;
            }
    		currentDate = currentDate.plusDays(1);
		}
    	
    	return result;
    }

    
    /**
     * Returns true if a job is within the allowed date range.
     * @param theJob job to be checked.
     * @return true if within the range, false if either before today's date or after max allowed date.
     * @precondition theJob must not be null.
     * @precondition theJob must be for the future.
     * @author Dylan Miller
     */
    public boolean isJobWithinMaxFutureDate(Job theJob) {
        Calendar jobCal = Calendar.getInstance();
        jobCal.set(Calendar.YEAR, theJob.getYear());
        jobCal.set(Calendar.MONTH, theJob.getMonth());
        jobCal.set(Calendar.DAY_OF_MONTH, theJob.getDay());
        
        Calendar maxCal = Calendar.getInstance();
        maxCal.setTime(new Date()); //today's date
        maxCal.add(Calendar.DATE, MAX_FUTURE_JOB_START_DATE);
        
        Date jobStartDate = jobCal.getTime();
        
        jobCal.add(Calendar.DATE, (theJob.getDuration() - 1));
        Date jobEndDate = jobCal.getTime();
        
        Date maxAllowedDate = maxCal.getTime();
        Date todaysDate = new Date();
        
        return jobStartDate.compareTo(todaysDate) >= 0 && jobStartDate.compareTo(maxAllowedDate) <= 0
               && jobEndDate.compareTo(todaysDate) >= 0 && jobEndDate.compareTo(maxAllowedDate) <= 0;
    }
    
    /**
     * Gets the list of jobs on a given calendar date, i.e. the day, the month, and the year.
     *
     * @author Walter Weeks
     * @param theDay The day.
     * @param theMonth The month.
     * @param theYear The year.
     * @return The list of jobs on a given calendar date.
     */
    public List<Job> getJobsByDate(final int theDay, final int theMonth, final int theYear) {
        List<Job> result = new ArrayList<>();

        Iterator<Job> itr = myPendingJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (currentJob.getDay() == theDay &&
                    currentJob.getMonth() == theMonth &&
                    currentJob.getYear() == theYear) {
                result.add(currentJob);
            }
        }

        return result;
    }

    /**
     * Moves a currently pending job to the previous jobs list, if it exists. Otherwise this
     * method does nothing.
     *
     * @author Walter Weeks
     * @param theJob the job to move to previous jobs list.
     * @deprecated
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
     * Gets the list of previous jobs.
     *
     * @author Walter Weeks
     * @return The previous jobs list.
     */
    public List<Job> getPreviousJobs() {
        return myPreviousJobs;
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
    public void addAccount(final AbstractAccount theAccount) {
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
     * Get the maximum number of pending jobs.
     *
     * @author Walter Weeks
     * @return The maximum number of pending jobs.
     */
    public int getMaxPendingJobs() {
        return myMaxPendingJobs;
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
    
    /**
     * Get a list of all Parks in the system.
     *a
     * @author Dylan Miller
     * @return parks list from fields.
     */
    public List<Park> getAllParks() {
      return this.myParks;
    }
    
    /**
     * Get a list of all accounts in the system.
     *
     * @author Dylan Miller
     * @return account list from fields.
     */
    public List<AbstractAccount> getAllAccounts() {
      return this.myAccounts;
    }
}
