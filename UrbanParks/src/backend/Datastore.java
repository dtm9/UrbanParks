package backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The datastore for Urban Parks. This class contains data used by the organization such as Parks, Jobs, etc.
 *
 * @author Walter Weeks (ww3@uw.edu)
 * @version 1.0
 * date 2017 Feb 3
 */
public final class Datastore {


    //***** Field(s) **************************************************************************************************/

    /** The user accounts, i.e., OfficeStaff, Volunteer, ParkManager. */
    private List<Account> myAccounts;

    /** The parks list. */
    private List<Park> myParks;

    /** The current pending jobs list. */
    private List<Job> myPendingJobs;

    /**
     * The previous jobs list. Note that these are any Job that is not supposed to be in the pending Jobs list such as
     * complete, cancelled, etc.
     */
    private List<Job> myPreviousJobs;

    //**** Constructor(s) *********************************************************************************************/

    /**
     * The no-argument constructor that creates an empty backend.Datastore.
     */
    public Datastore() {
        myAccounts = new ArrayList<>();
        myParks = new ArrayList<>();
        myPendingJobs = new ArrayList<>();
        myPreviousJobs = new ArrayList<>();
    }

    //**** Accessor/Mutator Method(s) *********************************************************************************/

    /**
     * Accessor method that gets the pending jobs at a given park.
     *
     * @param thePark the park we want the jobs from.
     * @throws NullPointerException if thePark is null.
     * @return the list of jobs given a Park.
     */
    public final List<Job> getJobsByPark(final Park thePark) {
        if (thePark == null) {
            throw new NullPointerException();
        }

        List<Job> result = new ArrayList<>();

        // Iterator over the entire pending jobs list to compile the list of job @ a given park
        Iterator<Job> itr = myPendingJobs.iterator();
        while (itr.hasNext()) {
            Job currentJob = itr.next();
            if (thePark.equals(currentJob.getMyPark())) {
                result.add(currentJob);
            }
        }

        return result;
    }