package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import view.Main;

/**
 * Volunteer User. Other classes will check instance of account
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 */
public class Volunteer extends AbstractAccount implements Serializable {

  /** Default serialVersionUID for serialization. */
  private static final long serialVersionUID = 1L;

/**
   * Level at which a volunteer can perform a job.
   * @author Dylan Miller
   */
  public enum WorkGrade {
    /**Lowest skill level.*/
    LIGHT,
    /**Middle skill level.*/
    MEDIUM,
    /**Highest skill level.*/
    HEAVY
  }

  /**Blacklisted status of the Volunteer.*/
  private boolean blackballed;

  /**Skill level of the Volunteer.*/
  private WorkGrade myWorkGrade;

  /**Number of times volunteer did not show up for a job.*/
  private int myAbsenceCount;

  /**Number of times volunteer cancelled too close to the job date.*/
  private int myBadCancellationCount;

  /**Linked List of notes added to volunteer's record by the staff.*/
  private LinkedList<String> myAccountNotes;

  /**
   * Creates an Volunteer user. minimal permissions
   * @param theUsername email address of the user that is used
   * to log into the system.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @param theWorkGrade the skill level of the user.
   * @author Dylan Miller
   */
  public Volunteer(final String theUsername, final String thePhone,
                   final String theRealName, final WorkGrade theWorkGrade) {
    super(theUsername, thePhone, theRealName);
    myAccountNotes = new LinkedList<String>();
    this.myWorkGrade = theWorkGrade;
  }

  /**
   * Creates a volunteer user. minimal permissions.
   * Volunteers do not need to provide all of the account info at first
   * so this takes the username (required) and legal name (optional).
   * @param theUsername email address of the user that
   * is used to log into the system.
   * @param theRealName legal name of the user.
   * @param theWorkGrade skill level of the user.
   * @author Dylan Miller
   */
  public Volunteer(final String theUsername, final String theRealName,
  final WorkGrade theWorkGrade) {
    this(theUsername, null, theRealName, theWorkGrade);
  }

  /**
   * Creates a volunteer user. minimal permissions.
   * Volunteers do not need to provide all of the account info at first
   * so this constructor takes the username only.
   * @param theUsername email address of the user that
   * is used to log into the system.
   * @param theWorkGrade skill level of the volunteer.
   * @author Dylan Miller
   */
  public Volunteer(final String theUsername, final WorkGrade theWorkGrade) {
    this(theUsername, null, null, theWorkGrade);
  }

  /**
  * Checks if the user is blacklisted by Urban Parks.
  * @return the myIsBlackballed boolean value for user's blacklisted status.
  * @author Dylan Miller
  */
  public boolean isBlackballed() {
    return blackballed;
  }

  /**
  * Blacklists the volunteer user.
  * @param theBlackballStatus boolean value for user's
  * blacklisted status to set.
  * @author Dylan Miller
  */
  public void setBlackballed(final boolean theBlackballStatus) {
    this.blackballed = theBlackballStatus;
  }

  /**
  * Returns the work grade currently set for the volunteer.
  * @return the myWorkGrade enum representing the grade.
  * @author Dylan Miller
  */
  public WorkGrade getWorkGrade() { //TODO consider using an enum for this
    return myWorkGrade;
  }

  /**
  * Updates the volutneer's work grade to the value provided.
  * @param theWorkGrade enum representing the grade.
  * @author Dylan Miller
  */
  public void setWorkGrade(final WorkGrade theWorkGrade) {
    this.myWorkGrade = theWorkGrade;
  }

  /**
  * Returns the number of times this volunteer has not
  * shown up to a job they signed up for.
  * @return the myAbsenceCount number of times abandoning jobs.
  * @author Dylan Miller
  */
  public int getAbsenceCount() {
    return myAbsenceCount;
  }

  /**
  * Updates the absent count for this volunteer.
  * @param theAbsenceCount number of times abandoning jobs to set.
  * @author Dylan Miller
  * @author Ethan Young
  */
  public void setAbsenceCount(final int theAbsenceCount) {
	  if (theAbsenceCount<0){
		  throw new IllegalArgumentException("Parameter can't be less than 0");
	  } else {
		  this.myAbsenceCount = theAbsenceCount;
	  }
  }

  /**
  * Returns the number of times this volunteer
  * cancelled in the violation period.
  * @return the myBadCancellationCount the number of times cancelled
  * too close to the job date.
  * @author Dylan Miller
  */
  public int getBadCancellationCount() {
    return myBadCancellationCount;
  }

  /**
  * Updates the bad cancellation count for this volunteer.
  * @param theBadCancellationCount number of of times cancelled
  * too close to the job date to set.
  * @author Dylan Miller
  * @author Ethan Young
  */
  public void setBadCancellationCount(final int theBadCancellationCount) {
	  if (theBadCancellationCount<0){
		  throw new IllegalArgumentException("Parameter can't be less than 0");
	  } else {
		  this.myBadCancellationCount = theBadCancellationCount;
	  }
  }

  /**
   * Adds a new note onto the volunteer.
   *
   * @param theNewNote any string to be added to the account notes.
   * @author Dylan Miller
   * @author Ethan Young
   * @author Walter Weeks
   */
  public void addAccountNote(final String theNewNote) {
      if (theNewNote == null ) {
        throw new NullPointerException("theNewNote cannot be null.");
      }
      if (theNewNote.equals("")) {
        throw new IllegalArgumentException("theNewNote cannot be empty.");
      }

      this.myAccountNotes.add(theNewNote);
  }
  
  //TODO new method. Test this and remove the old one in datastore.java
  /**
   * Gets a list of jobs for this Volunteer.
   *
   * @author Walter Weeks (ww3@uw.edu)
   * @return The list of jobs of a given volunteer.
   */
  public final List<Job> getJobsByVolunteer() {

      List<Job> result = new ArrayList<>();

      // Iterate over the entire pending jobs list to compile the list of job @ a given park
      Iterator<Job> itr = Main.datastore.getPendingJobs().iterator();

      while (itr.hasNext()) {
          Job currentJob = itr.next();
          if (currentJob.getVolunteers().contains(this.getUsername())) {
              result.add(currentJob);
          }
      }

      return result;
  }
}