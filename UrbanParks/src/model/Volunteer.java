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
   * Creates an Volunteer user. minimal permissions
   * @param theUsername email address of the user that is used
   * to log into the system.
   * @param thePhone phone number of the user.
   * @param theRealName legal name of the user.
   * @author Dylan Miller
   */
  public Volunteer(final String theUsername, final String thePhone, final String theRealName) {
    super(theUsername, thePhone, theRealName);
  }
  //TODO can volunteer just have a 3 arg constructor?
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
  public Volunteer(final String theUsername, final String theRealName) {
    this(theUsername, null, theRealName);
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
  public Volunteer(final String theUsername) {
    this(theUsername, null, null);
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
