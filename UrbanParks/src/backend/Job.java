package backend;

import java.util.ArrayList;
/**
 *
 * @author Gardner Gomes
 * @version 1.0
 * Date 2/1/2017
 * Job Class for Urban Parks
 */
public class Job {
	/**
	 * Zero the Minimum for several fields
	 */
	private static final int ZERO = 0;
    /**
     * Date for a Job.
     */
    private String myDate;
    /**
     * Time for a Job.
     */
    private String myTime;
    /**
     * Park the Job is at.
     */
    private String myParkName;
    /**
     * Description of the Job.
     */
    private String myDescription;
    /**
     * Max Volunteers for the Job.
     */
    private int myMaxVolunteers;
    /**
     * Minimum number of volunteers for light work grade.
     */
    private int myMinLight;
    /**
     * Minimum number of volunteers for medium work grade.
     */
    private int myMinMedium;
    /**
     * Minimum number of volunteers for heavy work grade.
     */
    private int myMinHeavy;
    /**
     * List of Volunteers signed up for the Job.
     */
    private ArrayList<String> myVolunteers;
    /**
     * Notes about the Job from the Manager or Office.
     */
    private String myNotes;

    /**
     * Constructor for a Job object.
     */
    public Job() {
        setMyDate("");
        setMyTime("");
        setMyDescription("");
        setMyMaxVolunteers(0);
        setMyMinLight(0);
        setMyMinMedium(0);
        setMyMinHeavy(0);
        setMyNotes("");
        myVolunteers = new ArrayList<String>();
    }
    /**
     * Getter for myDate.
     * @return myDate
     * @author Gardner Gomes
     */
    public String getMyDate() {
        return myDate;
    }
    /**
     * Setter for myDate.
     * @param theDate
     * @throws IllegalArgumentException Paramater not of type String
     * @author Gardner Gomes
     */
    public void setMyDate(final String theDate) {
        if(theDate instanceof String) {
            this.myDate = theDate;
        } else {
        	throw new IllegalArgumentException("Parameter is not of type String");
        }
    }
    /**
     * Getter for myTime.
     * @return myTime
     * @author Gardner Gomes
     */
    public String getMyTime() {
        return myTime;
    }
    /**
     * Setter for myTime.
     * @param theTime
     * @throws IllegalArgumentException Incompatible information for myTime
     * @author Gardner Gomes
     */
    public void setMyTime(final String theTime) {
    	if (theTime instanceof String) {
            this.myTime = theTime;
    	} else {
    		throw new IllegalArgumentException("Parameter is not of type String");
    	}
    }
	/**
     * Getter for Location.
     * @return myLocation
     * @author Gardner Gomes
     */
    public String getMyLocation() {
        return myParkName;
    }
    /**
     * Setter for myParkName.
     * @param myLocation
     * @throws IllegalArgumentException Parameter if not of type Park.
     * @author Gardner Gomes
     */
    public void setMyParkName(Park thePark ) {
    	if(thePark instanceof Park) {
    		this.myParkName = thePark.getName();
    	} else {
    		throw new IllegalArgumentException("Paramater is not of type Park");
    	}
    	/*TODO J-UnitTest*/
    	
    }
    /**
     * Getter for myDescription.
     * @return myDescription
     * @author Gardner Gomes
     */
    public String getMyDescription() {
        return myDescription;
    }
    /**
     * Setter for myDescription.
     * @param myDescription
     * @author Gardner Gomes
	 */
    public void setMyDescription(final String theDescription) {
    	if(theDescription instanceof String) {
    		this.myDescription = theDescription;
    	} else {
    		throw new IllegalArgumentException("Paramater is not of type String");
    	}
        
    }
    /**
	 * Getter for myMaxVolunteers.
	 * @return myMaxVolunteers
	 * @author Gardner Gomes
	 */
    public int getMyMaxVolunteers() {
        return myMaxVolunteers;
    }
    /**
	 * Setter for myMaxVolunteers.
	 * @param myMaxVolunteers
	 * @author Gardner Gomes
	 */
    public void setMyMaxVolunteers(final int theMaxVolunteers) {
    	if (theMaxVolunteers >= ZERO) {
    		this.myMaxVolunteers = theMaxVolunteers;
    	} else {
    		throw new IllegalArgumentException("Size too ");
    	}
        
    }
    /**
	 * Getter for myMinLight.
	 * @return myMinLight
	 * @author Gardner Gomes
	 */
    public int getMyMinLight() {
        return myMinLight;
    }
    /**
     * Setter for myMinLight.
	 * @param myMinLight
	 * @author Gardner Gomes
	 */
    public void setMyMinLight(final int theMinLight) {
    	if (theMinLight >= ZERO) {
            this.myMinLight = theMinLight;
    	} else {
    		throw new IllegalArgumentException("Paramater is not of type Integer");
    	}
    }
    /**
     * Getter for myMinMedium.
     * @return myMinMedium
     * @author Gardner Gomes
     */
    public int getMyMinMedium() {
        return myMinMedium;
    }
    /**
     * Setter for myMinMedium.
     * @param myMinMedium
     * @author Gardner Gomes
     */
    public void setMyMinMedium(final int theMinMedium) {
        if (theMinMedium >= ZERO) {
        	this.myMinMedium = theMinMedium;
    	} else {
    		throw new IllegalArgumentException("Paramater is not of type Integer");
    	}
    }
    /**
     * Getter for myMinHeavy.
     * @return myMinHeavy
     * @author Gardner Gomes
     */
    public int getMyMinHeavy() {
        return myMinHeavy;
    }
    /**
     * Setter for MyMinHeavy.
     * @param myMinHeavy
     * @author Gardner Gomes
     */
    public void setMyMinHeavy(final int theMinHeavy) {
        if (theMinHeavy >= ZERO) {
        	this.myMinHeavy = theMinHeavy;
        } else {
    		throw new IllegalArgumentException("Paramater is not of type Integer");
    	}
    }
    /**
     * Getter for myVolunteers.
     * @return myVolunteers
     * @author Gardner Gomes
     */
    public ArrayList<String> getMyVolunteers() {
        return myVolunteers;
    }
    /**
     * Add a Volunteer.
     * @param myVolunteers
     * @author Gardner Gomes
     */
    public void setMyVolunteers(final String theVolunteer) {
        if(theVolunteer instanceof String) {
        	this.myVolunteers.add(theVolunteer);
    	} else {
    		throw new IllegalArgumentException("Paramater is not of type String");
    	}
    }
    /**
     * Getter for myNotes.
     * @return myNotes
     * @author Gardner Gomes
     */
    public String getMyNotes() {
        return myNotes;
    }
    /**
     * Setter for myNotes.
     * @param theNotes
     * @author Gardner Gomes
     */
    public void setMyNotes(final String theNotes) {
        if(theNotes instanceof String) {
        	this.myNotes = theNotes;
    	} else {
    		throw new IllegalArgumentException("Paramater is not of type String");
    	}
    }

}
