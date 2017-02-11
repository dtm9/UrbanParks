/**
 * 
 */
package view;

import java.util.Scanner;

import backend.Account;
import backend.Datastore;
import backend.Job;
import backend.ParkManager;

/**
 * @author dylan
 * @author VG Gnome
 */
public class ParkManagerView extends View {
	private static final int TEMP_MAX_JOBS = 30;
	private Scanner myScanner = new Scanner(System.in);
	private StringBuilder mySB = new StringBuilder();
	private final ParkManager myManager;
	private final Datastore myDatastore;
	
	public ParkManagerView(Account theAccount, Datastore theDatastore) {
		super();
		myManager = (ParkManager) theAccount;
		myDatastore = theDatastore;
	}
	@Override
	public void launchGUI() {
		// TODO Auto-generated method stub
		mySB.append("Welcome to Urban Parks\n\n");
		mySB.append("1.Submit Job\n");
		mySB.append("2.View Jobs\n");
		mySB.append("\n\nPlease Select a number followed by the enter key: ");
		System.out.print(mySB.toString());
		mySB.delete(0, mySB.capacity());
		int theChoice = myScanner.nextInt();
		 
		switch (theChoice) {
		case 1:
			checkMaxJobs();
			SubmitJob();
			break;
		case 2:
			ViewJobs();
			break;
		}
		
	}
	/**
	 * Helper Method to check if max Jobs are already created.
	 */
	private void checkMaxJobs() {
		if (myDatastore.getNumberOfJobs() == TEMP_MAX_JOBS) {
			System.out.println("Max Number of Jobs reached. Please choose a different selection.\n\n");
			this.launchGUI();
		}		
	}
/* -----------------------------------------------------------------------------------------------------------------------------*/

	/**
	 * Submit a Job element for the UI.
	 */
	private void SubmitJob() {
		Job myJob = new Job();
		// TODO Auto-generated method stub
		
	}
/* -----------------------------------------------------------------------------------------------------------------------------*/
	/**
	 * View Jobs elemt for UI
	 */
	private void ViewJobs() {
		mySB.append("View Jobs\n\n");
		int count = 1;
		for(int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
			if (myDatastore.getPendingJobs().get(i).getPark().getManager().equals(myManager)) {
				mySB.append(count++);
				mySB.append(". ");
				mySB.append(myDatastore.getPendingJobs().get(i).getName());
				mySB.append(", ");
				mySB.append(myDatastore.getPendingJobs().get(i).getDate());
				mySB.append("\n");
			}
		}
		mySB.append("\nPast Jobs Below this Point\n");
		for(int i = myDatastore.getNumberOfJobs(); i < myDatastore.getNumberOfJobs() + myDatastore.getNumberOfPreviousJobs(); i++) {
			if (myDatastore.getPreviousJobs().get(i).getPark().getManager().equals(myManager)) {
				mySB.append(count++);
				mySB.append(". ");
				mySB.append(myDatastore.getPreviousJobs().get(i).getName());
				mySB.append(", ");
				mySB.append(myDatastore.getPreviousJobs().get(i).getDate());
				mySB.append("\n");
			}
		}
		mySB.append("\n\nPlease choose a job you want to view: ");
		System.out.print(mySB.toString());
		mySB.delete(0, mySB.capacity());
		int theChoice = myScanner.nextInt();
		// TODO get choice to show the job details
		count = 0;
		boolean flag = false;
		for(int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
			if (myDatastore.getPendingJobs().get(i).getPark().getManager().equals(myManager)) {
				count++;
				if (count == theChoice) {
					OpenJob(myDatastore.getPendingJobs().get(i));
				}
			}
			if (i == myDatastore.getNumberOfJobs()) {
				flag = true;
				theChoice = theChoice - count;
				count = 0;
			}
		}
		if (flag) {
			for(int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
				if (myDatastore.getPreviousJobs().get(i).getPark().getManager().equals(myManager)) {
					count++;
					if (count == theChoice) {
						OpenJob(myDatastore.getPreviousJobs().get(i));
					}
				}
			}
		}
		// TODO Auto-generated method stub
		
	}
	private void OpenJob(Job job) {
		System.out.println("It Worked");
		
		// TODO Auto-generated method stub
		
	}
	

}
