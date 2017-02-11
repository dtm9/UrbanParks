/**
 * 
 */
package view;

import java.util.Scanner;

import backend.Datastore;
import backend.ParkManager;

/**
 * @author dylan
 *
 */
public class ParkManagerView extends View {
	Scanner myScanner = new Scanner(System.in);
	StringBuilder mySB = new StringBuilder();
	private static ParkManager myManager;
	private static Datastore myDatastore;
	
	public ParkManagerView(ParkManager theManager, Datastore theDatastore) {
		super();
		myManager = theManager;
		myDatastore = theDatastore;
	}
	@Override
	public void launchGUI() {
		// TODO Auto-generated method stub
		mySB.append("Welcome to Urban Parks\n");
		mySB.append("1.Submit Job\n");
		mySB.append("2.View Jobs\n");
		mySB.append("Please Select a number followed by the enter key: ");
		mySB.toString();
		mySB.delete(0, mySB.capacity());
		int theChoice = myScanner.nextInt();
		 
		switch (theChoice) {
		case 1:
			SubmitJob();
			break;
		case 2:
			ViewJobs();
			break;
		}
		
	}
	private void SubmitJob() {
		// TODO Auto-generated method stub
		
	}
	private void ViewJobs() {
		mySB.append("View Jobs\n\n");
		for(int i = 0; i < myDatastore.getNumberOfJobs(); i++) {
			if (myDatastore.getPendingJobs().get(i).getPark().getManager().equals(myManager)) {
				mySB.append(i);
				mySB.append(". ");
				mySB.append(myDatastore.getPendingJobs().get(i).getMyName());
				mySB.append("\n");
			}
		}
		
		
		// TODO Auto-generated method stub
		
	}
	public void setMyManager(ParkManager theManager) {
		myManager = theManager;
	}

}
