package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Park Manager User. Other classes will check instance of account
 * and grant this user appropriate view and permissions in the application.
 * @author Ethan Young
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class ParkManager extends AbstractAccount implements Serializable {

    /** Default serialVersionUID for serialization. */
    private static final long serialVersionUID = 1L;
    private static final String ACCOUNT_TYPE = "Park Manager";

    /**
     * Creates an Park Manager user. Moderate permissions
     * @param theUsername email address used for login.
     * @param thePhone phone number of the user.
     * @param theRealName legal name of the user.
     * @author Dylan Miller
     */
    public ParkManager(final String theUsername, final String thePhone,
            final String theRealName) {
        super(theUsername, thePhone, theRealName);
    }

  //***** Overridden method(s) *****************************************************************************************

    /**
     * Computes the hash code for this method. Remember, this method is required to maintain Java's equals
     * "contract."
     *
     * @author Walter Weeks
     * @return  The hash code representing this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    /**
     * Equals operation comparing two ParkManagers together. Note that user names on the Urban Parks
     * system must be unique.
     *
     * @author Walter Weeks
     * @param theObj The other object we are comparing.
     * @return True if the ParkManager objects are equal, i.e., unique username, false otherwise.
     */
    @Override
    public boolean equals(Object theObj) {
        if (theObj == null) {
            return false;
        }
        if (theObj == this) { // the identity
            return true;
        }
        if (!(theObj instanceof ParkManager)) { // not the same object types
            return false;
        }
        ParkManager otherManager = (ParkManager) theObj;

        return otherManager.getUsername().equals(getUsername());
    }
  
    /**
     * Gets a list of pending jobs for this park manager.
     * @param readOnlyDatastore - the datastore from the caller, does not modify it.
     * @author Walter Weeks (ww3@uw.edu)
     * @return The list of pending jobs that the park manager has.
     */
    public final List<Job> getJobsByParkManager(Datastore readOnlyDatastore) {
        List<Park> allParks = readOnlyDatastore.getAllParks();
        List<Job> allJobs = readOnlyDatastore.getPendingJobs();
        // Compile the list of Parks within a given city and ZIP Code
        List<Park> managedParks = new ArrayList<>();
        for (int i = 0; i < allParks.size(); i++) {
            if (allParks.get(i).getManager().equals(this)) {
                managedParks.add(allParks.get(i));
            }
        }
        
        List<Job> result = new ArrayList<>();
        // Iterate over the entire pending jobs list to compile the list of job @ a given city
        Iterator<Job> itr = allJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (managedParks.contains(currentJob.getPark())) {
                result.add(currentJob);
            }
        }
        return result;
    }
    /**
     * returns a string of what kind of account this is.
     */
    @Override
    public String AccountType() {
        return ACCOUNT_TYPE;
    }
}
