package backend;
import java.util.LinkedList;

/**
 * Volunteer User. Other classes will check instance of account 
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 */
public class Volunteer extends Account {
  /**
   * Level at which a volunteer can perform a job.
   * @author Dylan Miller
   */
  public enum WorkGrade {
    LIGHT, MEDIUM, HEAVY
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
   * @param theUsername email address of the user that is used to log into the system.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public Volunteer(String theUsername, String thePhone, 
                   String theRealName, WorkGrade theWorkGrade) {
    super(theUsername, thePhone, theRealName);
    myAccountNotes = new LinkedList<String>();
    this.myWorkGrade = theWorkGrade;
  }
  
  /**
   * Creates a volunteer user. minimal permissions.
   * Volunteers do not need to provide all of the account info at first
   * so this constructor takes the username (required) and legal name (optional).
   * @param theUsername email address of the user that is used to log into the system.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public Volunteer(String theUsername, String theRealName, WorkGrade theWorkGrade) {
    this(theUsername, null, theRealName, theWorkGrade);
  }
  
  /**
   * Creates a volunteer user. minimal permissions.
   * Volunteers do not need to provide all of the account info at first
   * so this constructor takes the username only.
   * @param theUsername email address of the user that is used to log into the system.
   * @author Dylan Miller
   */
  public Volunteer(String theUsername, WorkGrade theWorkGrade) {
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
  * @param theBlackballStatus boolean value for user's blacklisted status to set.
  * @author Dylan Miller
  */
  public void setBlackballed(boolean theBlackballStatus) {
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
  public void setWorkGrade(WorkGrade theWorkGrade) {
    this.myWorkGrade = theWorkGrade;
  }

  /**
  * Returns the number of times this volunteer has not shown up to a job they signed up for.
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
  */
  public void setAbsenceCount(int theAbsenceCount) {
    this.myAbsenceCount = theAbsenceCount;
  }

  /**
  * Returns the number of times this volunteer cancelled in the violation period.
  * @return the myBadCancellationCount the number of times cancelled too close to the job date.
  * @author Dylan Miller
  */
  public int getBadCancellationCount() {
    return myBadCancellationCount;
  }

  /**
  * Updates the bad cancellation count for this volunteer.
  * @param theBadCancellationCount number of of times cancelled too close to the job date to set.
  * @author Dylan Miller
  */
  public void setBadCancellationCount(int theBadCancellationCount) {
    this.myBadCancellationCount = theBadCancellationCount;
  }

  /**
  * Adds a new note onto the volunteer.
  * @param theNewNote any string to be added to the account notes.
  * @author Dylan Miller
  */
  public void addAccountNote(String theNewNote) {
    this.myAccountNotes.add(theNewNote);
  }
}
