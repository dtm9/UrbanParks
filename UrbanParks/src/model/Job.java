package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * The volunteer Job object for Urban Parks.
 *
 * @author Gardner Gomes
 * @author Walter Weeks
 * @version 2017 Feb 12
 */
public class Job implements Serializable {

    //***** Constant(s)
//TODO review these constants for the minimums and MONTH_MAX_DAY -- do we need those?
    /** Default serialVersionUID for serialization. */
    public static final long serialVersionUID = 1L;
    public static final int MIN_YEAR = 1900;
    public static final int MAX_DURRATION = 3;
    public static final int MIN_DURRATION = 1;
    public static final int MONTH_MIN_DAY = 1;
    public static final int MONTH_MAX_DAY = 31;
    public static final int MAX_Volunteers = 10;

    //***** Field(s) ***************************************************************************************************

    /** Day for a Job. */
    private int myDay;

    /** Month for a Job. */
    private int myMonth;

    /** Year for a Job. */
    private int myYear;

    /** Time for a Job. */
    private String myTime;

    /** Name of Job. */
    private String myName;

    /** The duration of a Job. */
    private int myDuration;

    /** Park the Job is at. */
    private Park myPark;

    /** Description of the Job. */
    private String myDescription;

    /** Max Volunteers for the Job.
     * @deprecated */
    private int myMaxVolunteers;

    /** List of Volunteers signed up for the Job. */
    private List<String> myVolunteers;

    /** Notes about the Job from the Manager or Office. */
    private String myNotes;

    //**** Constructor(s) **********************************************************************************************

    /**
     * No-argument constructor for a Job object.
     */
    public Job() {
        setName("");
        setDay(1);
        setMonth(1);
        setTime("");
        myPark = null;
        setDescription("");
        setMaxVolunteers(0);
        setNotes("");
        myVolunteers = new ArrayList<String>();
    }

    /**
     * A four-argument constructor that takes the park name, time, date and description of a job.
     *
     * @param thePark The park.
     * @param theTime The time.
     * @param theDescription The description.
     * @param theName The name.
     * @param theDuration The duration in hours.
     * @param theDay The day.
     * @param theMonth The month.
     * @param theYear The Year.
     * @throws NullPointerException if any value is null.
     */
    public Job(final Park thePark, final String theTime, final String theDescription,
               final String theName, final int theDuration, final int theDay, final int theMonth,
               final int theYear) {
        this(); // call no-arg constructor to init missing arguments

        if (thePark == null || theDay <= 0 || theTime == null || theDescription == null || theName == null) {
            throw new NullPointerException("No values passed to constructor can be null.");
        }

        setPark(thePark);
        setDay(theDay);
        setMonth(theMonth);
        setDuration(theDuration);
        setYear(theYear);
        setTime(theTime);
        setDescription(theDescription);
        setName(theName);
    }

    //**** Accessor/Mutator Method(s) **********************************************************************************

    /**
     * Getter for the day of this Job.
     *
     * @return The day of the Job.
     * @author Gardner Gomes
     */
    public int getDay() {
        return myDay;
    }

    /**
     * Setter for day of this job.
     *
     * @param theDay The day of the Job.
     * @throws IllegalArgumentException Parameter not of type String
     * @author Gardner Gomes
     */
    public void setDay(final int theDay) {
        if (theDay >= MONTH_MIN_DAY && theDay <= MONTH_MAX_DAY) {
            this.myDay = theDay;
        } else {
            throw new IllegalArgumentException("Parameter is out of bounds for Day\n");
        }
    }

    /**
     * Getter for the month of this Job.
     *
     * @return The Month of the Job.
     * @author Gardner Gomes
     */
    public int getMonth() {
        return myMonth;
    }

    /**
     * Setter for month of this job.
     *
     * @param theMonth The month of the Job.
     * @throws IllegalArgumentException Parameter not of type String
     * @author Gardner Gomes
     */
    public void setMonth(final int theMonth) {
        if (theMonth > 0 && theMonth <= 12) {
            this.myMonth = theMonth;
        } else {
            throw new IllegalArgumentException("Parameter is out of Bounds for Month\n");
        }
    }

    /**
     * Getter for the year of this Job.
     *
     * @author Walter Weeks
     * @return The year of the Job.
     */
    public int getYear() {
        return myYear;
    }

    /**
     * Setter for the year of this Job.
     *
     * @author Walter Weeks
     * @param theYear The year of the Job.
     * @throw IllegalArgumentException if theYear is invalid.
     */
    public void setYear(final int theYear) {
        if (theYear < MIN_YEAR || theYear >  Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException(theYear +" is not valid.\n");
        }
        myYear = theYear;
    }

    /**
     * Getter for the duration of this Job.
     *
     * @return The duration of the Job.
     * @author Walter Weeks
     */
    public int getDuration() {
        return myDuration;
    }

    /**
     * Setter for the year of this Job.
     *
     * @author Walter Weeks
     * @param theDuration The duration of the Job.
     * @throw IllegalArgumentException if theDuration is less than 1.
     */
    public void setDuration(final int theDuration) {
        if (theDuration > MAX_DURRATION || theDuration < MIN_DURRATION) {
            throw new IllegalArgumentException(theDuration + " is not a valid duration.\n");
        }
        myDuration = theDuration;
    }

    /**
     * Getter for the time of this Job.
     *
     * @return The time of Job.
     * @author Gardner Gomes
     */
    public String getTime() {
        return myTime;
    }

    /**
     * Setter for time of this Job.
     *
     * @param theTime The time of the Job.
     * @throws IllegalArgumentException Incompatible information for myTime
     * @author Gardner Gomes
     */
    public void setTime(final String theTime) {
        if (theTime instanceof String) {
            this.myTime = theTime;
        } else {
            throw new IllegalArgumentException("Parameter is not of type String\n");
        }
    }

    /**
     * Getter for park location of this Job.
     *
     * @return The Park location.
     * @author Gardner Gomes
     */
    public Park getPark() {
        return myPark;
    }

    /**
     * Setter for park of this Job.
     *
     * @param thePark The park.
     * @throws IllegalArgumentException Parameter if not of type Park.
     * @author Gardner Gomes
     */
    public void setPark(Park thePark) {
        if (thePark instanceof Park) {
            this.myPark = thePark;
        } else {
            throw new IllegalArgumentException("Parameter is not of type Park\n");
        }
    	/*TODO J-UnitTest*/

    }

    /**
     * Getter for the description of this Job.
     *
     * @return The description of the Job.
     * @author Gardner Gomes
     */
    public String getDescription() {
        return myDescription;
    }

    /**
     * Setter for myDescription.
     *
     * @param theDescription The description of the Job.
     * @author Gardner Gomes
     */
    public void setDescription(final String theDescription) {
        if (theDescription instanceof String) {
            this.myDescription = theDescription;
        } else {
            throw new IllegalArgumentException("Parameter is not of type String\n");
        }

    }

    /**
     * Getter for the maximum required Volunteers for this Job.
     *
     * @return The maximum required Volunteers for the Job.
     * @author Gardner Gomes
     * @deprecated
     */
    public int getMaxVolunteers() {
        return myMaxVolunteers;
    }

    /**
     * Setter for the maximum required Volunteers for this Job.
     *
     * @param theMaxVolunteers The maximum Volunteers required for this Job.
     * @author Gardner Gomes
     * @deprecated
     */
    public void setMaxVolunteers(final int theMaxVolunteers) {
        if (theMaxVolunteers >= 0) {
            this.myMaxVolunteers = theMaxVolunteers;
        } else {
            throw new IllegalArgumentException("Parameter is not of type String.\n ");
        }
    }

    /**
     * Getter for the list of Volunteers signed up for this Job..
     *
     * @return The list of Volunteers signed up for this Job.
     * @author Gardner Gomes
     */
    public List<String> getVolunteers() {
        return myVolunteers;
    }

    /**
     * Add a Volunteer for this Job.
     *
     * @param theVolunteerUsername The unique username of the Volunteer.
     * @author Gardner Gomes
     */
    public void setVolunteers(final String theVolunteerUsername) {
        if (theVolunteerUsername instanceof String) {
            this.myVolunteers.add(theVolunteerUsername);
        } else {
            throw new IllegalArgumentException("Parameter is not of type String\n");
        }
    }

    /**
     * Getter for the notes about this Job..
     *
     * @return The notes about this Job.
     * @author Gardner Gomes
     */
    public String getNotes() {
        return myNotes;
    }

    /**
     * Setter for the field notes for this Job.
     *
     * @param theNotes The field notes for this Job.
     * @author Gardner Gomes
     */
    public void setNotes(final String theNotes) {
        if (theNotes instanceof String) {
            this.myNotes = theNotes;
        } else {
            throw new IllegalArgumentException("Parameter is not of type String\n");
        }
    }

    /**
     * Getter for myName.
     * @author VG Gnome
     * @return the Name of the Job.
     */
    public String getName() {
        return myName;
    }

    /**
     * Setter for the name of the Job.
     * @author VG Gnome
     * @param theName
     */
    public void setName(String theName) {
        if (theName instanceof String) {
            this.myName = theName;
        } else {
            throw new IllegalArgumentException("Parameter is not of type String\n");
        }
    }
    public boolean isMaxVolunteers() {
        boolean ans = true;
        if (this.myVolunteers.size() < MAX_Volunteers) {
            ans = false;
        }
        return ans;
    }
    
    /**
     * Getter for max duration constant.
     * @return int representing how many days a job can last.
     * @author Dylan Miller
     */
    public static int getMaxDuration() {
      return MAX_DURRATION;
    }

    //***** Overridden method(s) ***************************************************************************************

    /**
     * Computes the hash code for this method. Remember, this method is required to maintain Java's equals
     * "contract."
     *
     * @author Walter Weeks
     * @return The hash code representing this object.
     */
    public int hashCode() {
        return Objects.hash(myPark, myMonth, myDay, myMonth, myTime);
    }

    /**
     * Equals operation for comparing Job objects for equality. We consider Job objects to be equal
     * if both Jobs have the same park name, day, month, and time fields.
     *
     * TODO: This might have to be changed if we decide to use the Calendar class for date & time representation.
     *
     * @author Walter Weeks
     * @param theObj the other object we are comparing to.
     * @return true if the Job objects are considered the same, i.e., park name, day, month, and time.
     */
    @Override
    public boolean equals(Object theObj) {
        if (theObj == null) {
            return false;
        }
        if (theObj == this) { // the identity
            return true;
        }
        if (!(theObj instanceof Job)) { // not the same object types
            return false;
        }

        Job otherJob = (Job) theObj;
        return myPark.equals(otherJob.myPark) && myDay == otherJob.myDay &&
                myMonth == otherJob.myMonth && myTime.equals(otherJob.myTime);
    }
}