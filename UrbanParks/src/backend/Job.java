package backend;

import java.util.ArrayList;
/**
 * 
 * @author VG Gnome
 * @version 1.0
 * Date 2/1/2017
 * Job Class for Urban Parks
 */
public class Job {
	/**
	 * Date for a Job
	 */
	private String myDate;
	/**
	 * Time for a Job
	 */
	private String myTime;
	/**
	 * Park the Job is at
	 */
	private String myLocation;
	/**
	 * Description of the Job
	 */
	private String myDescription;
	/**
	 * Max Volunteers for the Job
	 */
	private int myMaxVolunteers;
	/**
	 * M<inimum number of volunteers for light work grade
	 */
	private int myMinLight;
	/**
	 * M<inimum number of volunteers for medium work grade
	 */
	private int myMinMedium;
	/**
	 * M<inimum number of volunteers for heavy work grade
	 */
	private int myMinHeavy;
	/**
	 * List of Volunteers signed up for the Job
	 */
	private ArrayList<String/*Volunteer*/> myVolunteers;
	/**
	 * Notes about the Job from the Manager or Office
	 */
	private String myNotes;
	
	/**
	 * Constructor for a Job object
	 */
	public Job() {
		setMyDate("");
		setMyTime("");
		setMyLocation("");
		setMyDescription("");
		setMyMaxVolunteers(0);
		setMyMinLight(0);
		setMyMinMedium(0);
		setMyMinHeavy(0);
		setMyNotes("");
		myVolunteers = new ArrayList<String>();
		
	}
	/**
	 * Getter for myDate
	 * @return myDate
	 */
	public String getMyDate() {
		return myDate;
	}
	/**
	 * Setter for myDate
	 * @param myDate
	 */
	public void setMyDate(String theDate) {
		this.myDate = theDate;
	}
	/**
	 * Getter for myTime
	 * @return myTime
	 */
	public String getMyTime() {
		return myTime;
	}
	/**
	 * Setter for myTime
	 * @param myTime
	 */
	public void setMyTime(String theTime) {
		this.myTime = theTime;
	}
	/**
	 * Getter for Location.
	 * @return myLocation
	 */
	public String getMyLocation() {
		return myLocation;
	}
	/**
	 * Setter for myLocation
	 * @param myLocation
	 */
	public void setMyLocation(String theLocation/*Park thePark */) {
		this.myLocation = theLocation;
	}
	/**
	 * Getter for myDescription
	 * @return
	 */
	public String getMyDescription() {
		return myDescription;
	}
	/**
	 * Setter for myDescription
	 * @param myDescription
	 */
	public void setMyDescription(String theDescription) {
		this.myDescription = theDescription;
	}
	/**
	 * Getter for myMaxVolunteers
	 * @return myMaxVolunteers
	 */
	public int getMyMaxVolunteers() {
		return myMaxVolunteers;
	}
	/**
	 * Setter for myMaxVolunteers
	 * @param myMaxVolunteers
	 */
	public void setMyMaxVolunteers(int theMaxVolunteers) {
		this.myMaxVolunteers = theMaxVolunteers;
	}
	/**
	 * Getter for myMinLight
	 * @return myMinLight
	 */
	public int getMyMinLight() {
		return myMinLight;
	}
	/**
	 * Setter for myMinLight
	 * @param myMinLight
	 */
	public void setMyMinLight(int theMinLight) {
		this.myMinLight = theMinLight;
	}
	/**
	 * Getter for myMinMedium
	 * @return myMinMedium
	 */
	public int getMyMinMedium() {
		return myMinMedium;
	}
	/**
	 * Setter for myMinMedium
	 * @param myMinMedium
	 */
	public void setMyMinMedium(int theMinMedium) {
		this.myMinMedium = theMinMedium;
	}
	/**
	 * Getter for myMinHeavy
	 * @return myMinHeavy
	 */
	public int getMyMinHeavy() {
		return myMinHeavy;
	}
	/**
	 * Setter for MyMinHeavy
	 * @param myMinHeavy
	 */
	public void setMyMinHeavy(int theMinHeavy) {
		this.myMinHeavy = theMinHeavy;
	}
	/**
	 * Getter for myVolunteers
	 * @return myVolunteers
	 */
	public ArrayList<String> getMyVolunteers() {
		return myVolunteers;
	}
	/**
	 * Add a Volunteer
	 * @param myVolunteers
	 */
	public void setMyVolunteers(String theVolunteer) {
		this.myVolunteers.add(theVolunteer);
	}
	/**
	 * Getter for myNotes
	 * @return myNotes
	 */
	public String getMyNotes() {
		return myNotes;
	}
	/**
	 * Setter for myNotes
	 * @param myNotes
	 */
	public void setMyNotes(String theNotes) {
		this.myNotes = theNotes;
	}
	
}