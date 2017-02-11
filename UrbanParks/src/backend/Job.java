package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The volunteer Job object for Urban Parks.
 *
 * @author Gardner Gomes
 * @author Walter Weeks
 * @version 2017 Feb 10
 */
public class Job implements Serializable{

    //***** Field(s) ***************************************************************************************************

    /** Default serialVersionUID for serialization. */
	private static final long serialVersionUID = 1L;

	/** Date for a Job. */
    private String myDate;

    /** Time for a Job. */
    private String myTime;
    
    /** Name of Job. */
    private String myName;

    /** Park the Job is at. */
    private Park myPark;

    /** Description of the Job. */
    private String myDescription;

    /** Max Volunteers for the Job. */
    private int myMaxVolunteers;

    /** Minimum number of volunteers for light work grade. */
    private int myMinLight;

    /** Minimum number of volunteers for medium work grade. */
    private int myMinMedium;

    /** Minimum number of volunteers for heavy work grade. */
    private int myMinHeavy;

    /** List of Volunteers signed up for the Job. */
    private List<String> myVolunteers;

    /** Notes about the Job from the Manager or Office. */
    private String myNotes;

    //**** Constructor(s) **********************************************************************************************

    /**
     * No-argument constructor for a Job object.
     */
    public Job() {
        setDate("");
        setTime("");
        myPark = null;
        setDescription("");
        setMaxVolunteers(0);
        setMinLight(0);
        setMinMedium(0);
        setMinHeavy(0);
        setNotes("");
        myVolunteers = new ArrayList<String>();
    }

    /**
     * A four-argument constructor that takes the park name, time, date and description of a job.
     *
     * @param thePark The park.
     * @param theDate The date.
     * @param theTime The time.
     * @param theDescription The description.
     * @throws NullPointerException if any value is null.
     */
    public Job(final Park thePark, final String theDate, final String theTime, final String theDescription) {
        this(); // call no-arg constructor to init missing arguments

        if (thePark == null || theDate == null || theTime == null || theDescription == null) {
            throw new NullPointerException("No values passed to constructor can be null.");
        }

        myPark = thePark;
        setDate(theDate);
        setTime(theTime);
        setDescription(theDescription);
    }

    //**** Accessor/Mutator Method(s) **********************************************************************************

    /**
     * Getter for the date of this Job.
     *
     * @return The date of the Job.
     * @author Gardner Gomes
     */
    public String getDate() {
        return myDate;
    }

    /**
     * Setter for date of this job.
     *
     * @param theDate The date of the Job.
     * @throws IllegalArgumentException Parameter not of type String
     * @author Gardner Gomes
     */
    public void setDate(final String theDate) {
        if (theDate instanceof String) {
            this.myDate = theDate;
        } else {
            throw new IllegalArgumentException("Parameter is not of type String");
        }
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
            throw new IllegalArgumentException("Parameter is not of type String");
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
            throw new IllegalArgumentException("Parameter is not of type Park");
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
            throw new IllegalArgumentException("Parameter is not of type String");
        }

    }

    /**
     * Getter for the maximum required Volunteers for this Job.
     *
     * @return The maximum required Volunteers for the Job.
     * @author Gardner Gomes
     */
    public int getMaxVolunteers() {
        return myMaxVolunteers;
    }

    /**
     * Setter for the maximum required Volunteers for this Job.
     *
     * @param theMaxVolunteers The maximum Volunteers required for this Job.
     * @author Gardner Gomes
     */
    public void setMaxVolunteers(final int theMaxVolunteers) {
        if (theMaxVolunteers >= 0) {
            this.myMaxVolunteers = theMaxVolunteers;
        } else {
            throw new IllegalArgumentException("Size too ");
        }

    }

    /**
     * Getter for the minimum light-grade Volunteers required for this Job..
     *
     * @return The minimum light-grade Volunteers required for this Job.
     * @author Gardner Gomes
     */
    public int getMinLight() {
        return myMinLight;
    }

    /**
     * Setter for the minimum light-grade Volunteers required for this Job..
     *
     * @param theMinLight The minimum light-grade Volunteers required for this Job.
     * @author Gardner Gomes
     */
    public void setMinLight(final int theMinLight) {
        if (theMinLight >= 0) {
            this.myMinLight = theMinLight;
        } else {
            throw new IllegalArgumentException("Parameter is not of type Integer");
        }
    }

    /**
     * Getter for myMinMedium.
     *
     * @return The minimum medium-grade Volunteers for this Job.
     * @author Gardner Gomes
     */
    public int getMinMedium() {
        return myMinMedium;
    }

    /**
     * Setter for myMinMedium.
     *
     * @param theMinMedium The minimum medium-grade Volunteers required for this Job.
     * @author Gardner Gomes
     */
    public void setMinMedium(final int theMinMedium) {
        if (theMinMedium >= 0) {
            this.myMinMedium = theMinMedium;
        } else {
            throw new IllegalArgumentException("Parameter is not of type Integer");
        }
    }

    /**
     * Getter for the minimum heavy-grade Volunteers required for this Job..
     *
     * @return myMinHeavy The minimum heavy-grade Volunteers required for this Job.
     * @author Gardner Gomes
     */
    public int getMinHeavy() {
        return myMinHeavy;
    }

    /**
     * Setter for the minimum heavy-grade Volunteers required for this Job..
     *
     * @param theMinHeavy The minimum heavy-grade Volunteers required for this Job.
     * @author Gardner Gomes
     */
    public void setMinHeavy(final int theMinHeavy) {
        if (theMinHeavy >= 0) {
            this.myMinHeavy = theMinHeavy;
        } else {
            throw new IllegalArgumentException("Paramater is not of type Integer");
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
            throw new IllegalArgumentException("Parameter is not of type String");
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
            throw new IllegalArgumentException("Parameter is not of type String");
        }
    }
    /**
     * Getter for myName.
     * @author VG Gnome
     * @return the Name of the Job.
     */
    public String getMyName() {
		return myName;
	}
    /**
     * Setter for the name of the Job.
     * @author VG Gnome
     * @param theName
     */
	public void setMyName(String theName) {
		if (theName instanceof String) {
			this.myName = theName;
		} else {
            throw new IllegalArgumentException("Parameter is not of type String");
        }
			
	}

    //***** Overridden method(s) ***************************************************************************************

    /**
     * Computes the hash code for this method. Remember, this method is required to maintain Java's equals
     * "contract."
     *
     * @author Walter Weeks
     * @return  The hash code representing this object.
     */
    public int hashCode() {
        return Objects.hash(myPark, myDate, myTime);
    }

    /**
     * Equals operation for comparing Job objects for equality. We consider Job objects to be equal
     * if both Jobs have the same park name, date, and time fields.
     *
     * TODO: This might have to be changed if we decide to use the Calendar class for date & time representation.
     *
     * @author Walter Weeks
     * @param theObj the other object we are comparing to.
     * @return true if the Job objects are considered the same, i.e., park name, date, and time.
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
        return myPark.equals(otherJob.myPark) && myDate.equals(otherJob.myDate) &&
                myTime.equals(otherJob.myTime);
    }

}
