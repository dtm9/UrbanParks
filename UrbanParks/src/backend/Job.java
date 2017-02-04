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
    private String myLocation;
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
    private ArrayList<String/*Volunteer*/> myVolunteers;
    /**
     * Notes about the Job from the Manager or Office.
     */
    private String myNotes;

    /**
     * Constructor for a Job object.
     */
    public Job(Park thePark) {
        setMyDate("");
        setMyTime("");
        setMyLocation("");
        myLocation = thePark.getName();
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
     * @author Gardner Gomes
     */
    public void setMyDate(final String theDate) {
        this.myDate = theDate;
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
     * @author Gardner Gomes
     */
    public void setMyTime(final String theTime) {
        this.myTime = theTime;
    }
	/**
     * Getter for Location.
     * @return myLocation
     * @author Gardner Gomes
     */
    public String getMyLocation() {
        return myLocation;
    }
    /**
     * Setter for myLocation.
     * @param myLocation
     * @author Gardner Gomes
     */
    public void setMyLocation(final String theLocation/*Park thePark */) {
        this.myLocation = theLocation;
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
        this.myDescription = theDescription;
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
        this.myMaxVolunteers = theMaxVolunteers;
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
        this.myMinLight = theMinLight;
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
        this.myMinMedium = theMinMedium;
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
        this.myMinHeavy = theMinHeavy;
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
        this.myVolunteers.add(theVolunteer);
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
        this.myNotes = theNotes;
    }

}
