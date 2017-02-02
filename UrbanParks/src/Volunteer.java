/**
 * Volunteer User. Other classes will check instancof account 
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 */
public class Volunteer extends Account {

  private boolean blackballed;
  private String myWorkGrade;
  private int myAbsenceCount;
  private int myBadCancellationCount;
  private String myAccountNotes;

  /**
   * Creates an Volunteer user. minimal permissions
   * @param theUsername email address of the user that is used to log into the system.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public Volunteer(String theUsername, String thePhone, String theRealName) {
    super(theUsername, thePhone, theRealName);
  }
  
  /**
   * Creates a volunteer user. minimal permissions.
   * Volunteers do not need to provide all of the account info at first
   * so this constructor takes the username (required) and legal name (optional).
   * @param theUsername email address of the user that is used to log into the system.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public Volunteer(String theUsername, String theRealName) {
    this(theUsername, null, theRealName);
  }
  
  /**
   * Creates a volunteer user. minimal permissions.
   * Volunteers do not need to provide all of the account info at first
   * so this constructor takes the username only.
   * @param theUsername email address of the user that is used to log into the system.
   * @author Dylan Miller
   */
  public Volunteer(String theUsername) {
    this(theUsername, null, null);
  }

  /**
  * Checks if the user is blacklisted by Urban Parks.
  * @return the myIsBlackballed boolean value for user's blacklisted status.
  */
  public boolean isBlackballed() {
    return blackballed;
  }

  /**
  * Blacklists the volunteer user.
  * @param theBlackballStatus boolean value for user's blacklisted status to set.
  */
  public void setBlackballed(boolean theBlackballStatus) {
    this.blackballed = theBlackballStatus;
  }

  /**
  * @return the myWorkGrade 
  */
  public String getWorkGrade() { //TODO consider using an enum for this
    return myWorkGrade;
  }

  /**
  * @param theWorkGrade the myWorkGrade to set //TODO modify this after deciding on enum or not
  */
  public void setWorkGrade(String theWorkGrade) {
    this.myWorkGrade = theWorkGrade;
  }

  /**
  * Returns the number of times this volunteer has not shown up to a job they signed up for.
  * @return the myAbsenceCount number of times abandoning jobs.
  */
  public int getAbsenceCount() {
    return myAbsenceCount;
  }

  /**
  * Updates the absent count for this volunteer.
  * @param theAbsenceCount number of times abandoning jobs to set.
  */
  public void setAbsenceCount(int theAbsenceCount) {
    this.myAbsenceCount = theAbsenceCount;
  }

  /**
  * Returns the number of times this volunteer cancelled in the violation period.
  * @return the myBadCancellationCount the number of times cancelled too close to the job date.
  */
  public int getBadCancellationCount() {
    return myBadCancellationCount;
  }

  /**
  * Updates the bad cancellation count for this volunteer.
  * @param theBadCancellationCount number of of times cancelled too close to the job date to set.
  */
  public void setBadCancellationCount(int theBadCancellationCount) {
    this.myBadCancellationCount = theBadCancellationCount;
  }

  /**
  * @return the myAccountNotes
  */
  public String addAccountNote(String theNewNote) {
    return myAccountNotes;
  }

  /**
  * @param theAccountNotes the myAccountNotes to set.
  */
  public void editAccountNote(String theAccountNotes) {
    this.myAccountNotes = theAccountNotes;
    //TODO is this a collection of notes? Or a single text field?
  }

}
