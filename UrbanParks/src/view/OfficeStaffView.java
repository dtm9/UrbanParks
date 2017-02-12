/**
 * The user interface for an office staff user. This object is not
 * able to validate the type of user interacting with the system
 * so be mindful that the user's type is validated before
 * running this code.
 */
package view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import backend.Account;
import backend.Datastore;
import backend.Job;
import backend.ParkManager;
import backend.OfficeStaff;

/**
 * @author Dylan Miller
 *
 */
public class OfficeStaffView extends View {

  /**OS-independent string to add a line break in string builder.*/
  private static final String SB_LINE_BREAK = System.getProperty("line.separator");

  private OfficeStaff myOfficeStaff;
  private Datastore myDataStore;
  private Scanner myScanner = new Scanner(System.in); 
  private StringBuilder mySB;

  
	public OfficeStaffView(Account theAccount, Datastore theDatastore) {
		super();
		myOfficeStaff = (OfficeStaff) theAccount;
		myDataStore = theDatastore;
		mySB = new StringBuilder();
	}
  /**
   * Public method to launch the GUI for Office Staff accounts.
   * This is the only public method and it drives all other code in
   * this module.
   * @author Dylan Miller
   */
  @Override
  public void launchGUI() {
    //TODO all necessary initialization, then call another method to print first menu. That method should call more methods not return until the end.
    //TODO some kind of exit/cleanup method after the call stack pops back here at the end.
	  mainMenu();
  }
  
  private void mainMenu() {
	  //TODO print somethign with SB and listen for input option on console	  
	  Datastore theDatastore = myDataStore;
	  int choice;
	  int newChoice;
	  choice = 0; //temp to clear annoying error for temp code

		// TODO Auto-generated method stub
		mySB.append("\nWelcome to Urban Parks Office Staff: ");
		mySB.append(myOfficeStaff.getRealName());
		mySB.append("\n----------------------------------------------------------\n\n");
		mySB.append("What would you like to do?\n");
		mySB.append("1. View calendar of upcoming jobs\n");
		mySB.append("2. Administrative functions\n");
		mySB.append("3. Exit eUrban Parks\n");
		System.out.print(mySB.toString());
		mySB.delete(0, mySB.capacity());
		int theChoice = myScanner.nextInt();
		 
		switch (theChoice) {
		case 1:
		    System.out.println(printCalendar(theDatastore));
		    System.out.println();
		    System.out.println("What would you like to do?");
		    System.out.println("1. Go back to prior menu");
		    System.out.println("2. Exit eUrbanParks");
			newChoice = myScanner.nextInt();
			if (newChoice == 1) {
				mainMenu();
			}
			break;
		case 2:
		    System.out.println("What would you like to do?");
		    System.out.println("1. Change max pending jobs");
		    System.out.println("2. Go back to main menu");
		    System.out.println("3. Exit eUrbanParks");
			newChoice = myScanner.nextInt();
			if (newChoice == 1) {
				System.out.println("Please enter the new maximum number of pending jobs");
				newChoice = myScanner.nextInt();
				myDataStore.setMaxPendingJobs(newChoice);
				System.out.println("The new maximum number of pending jobs is " + newChoice);
				mainMenu();
			} else if (newChoice == 2) {
				mainMenu();
			} else {
				break;
			}
		case 3: // exit Urban Parks
			break;
		}
  }
  
	private String printCalendar(Datastore theDatastore) {
		ZoneId z = ZoneId.of( "America/Los_Angeles" );
		LocalDate today = LocalDate.now(z);		
		StringBuilder calendarString = new StringBuilder();
		int startDay = 0;
		int monthCheck = today.getDayOfMonth(); 
		int jobsToday = 0;
		List<Job> allJobs = theDatastore.getPendingJobs();
		
		// Still have to add total number of jobs to the first line
		calendarString.append(System.getProperty("line.separator"));
		calendarString.append(today.getMonth().getDisplayName(TextStyle.FULL , Locale.US));
		calendarString.append(" " + today.getDayOfMonth() + ", ");
		calendarString.append(today.getYear() + ". ");
		calendarString.append(theDatastore.getNumberOfJobs() + " upcoming jobs out of ");
		calendarString.append(theDatastore.getMaxPendingJobs() + " maximum");
		calendarString.append(SB_LINE_BREAK);
		calendarString.append(SB_LINE_BREAK);
		calendarString.append("   Su  ");
		calendarString.append("    M  ");
		calendarString.append("    T  ");
		calendarString.append("    W  ");
		calendarString.append("    T  ");
		calendarString.append("    F  ");
		calendarString.append("    S  ");
		calendarString.append(SB_LINE_BREAK);
		calendarString.append("               [");
		calendarString.append(today.getMonth().getDisplayName(TextStyle.FULL , Locale.US) + "]");
		calendarString.append(SB_LINE_BREAK);
		
		switch(today.getDayOfWeek()) {
			case SUNDAY: startDay = 0; break;
			case MONDAY: startDay = 1; break;
			case TUESDAY: startDay = 2; break;
			case WEDNESDAY: startDay = 3; break;
			case THURSDAY: startDay = 4; break;
			case FRIDAY: startDay = 5; break;
			case SATURDAY: startDay = 6; break;
		}
		
		calendarString.append("|");
		for (int i = 0; i < startDay; i++) {
			calendarString.append("      |");
		}
		
		//Starts the calendar from here with date and job
		YearMonth ym = YearMonth.from(today);
		while(ym.equals(YearMonth.from(today))) {
			jobsToday = 0;
			if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
				calendarString.append(SB_LINE_BREAK);
				calendarString.append("|");
			}
			if (today.getDayOfMonth() < 10) {
				calendarString.append(" ");
			}
			calendarString.append(" " + today.getDayOfMonth() + ":");
			for (int i = 0; i < allJobs.size(); i++) {
				if (allJobs.get(i).getMonth() == today.getMonthValue()) {
					if (allJobs.get(i).getDay() == today.getDayOfMonth()) {
						jobsToday++;
					}
				}
			}
			calendarString.append(jobsToday + " |");
			today = today.plusDays(1); 
		}
		
		LocalDate currentDay = today; 
		while(currentDay.getDayOfWeek() != (DayOfWeek.SUNDAY)) {
			calendarString.append("      |");
			currentDay = currentDay.plusDays(1);
		}
		
		// This is where the next month's calendar starts
		calendarString.append(SB_LINE_BREAK);
		calendarString.append("               [");
		calendarString.append(today.getMonth().getDisplayName(TextStyle.FULL , Locale.US) + "]");
		calendarString.append(SB_LINE_BREAK);

		switch(today.getDayOfWeek()) {
			case SUNDAY: startDay = 0; break;
			case MONDAY: startDay = 1; break;
			case TUESDAY: startDay = 2; break;
			case WEDNESDAY: startDay = 3; break;
			case THURSDAY: startDay = 4; break;
			case FRIDAY: startDay = 5; break;
			case SATURDAY: startDay = 6; break;
		}
		
		calendarString.append("|");
		for (int i = 0; i < startDay; i++) {
			calendarString.append("      |");
		}
		
		while(today.getDayOfMonth() != monthCheck) {
			jobsToday = 0;
			if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
				calendarString.append(SB_LINE_BREAK);
				calendarString.append("|");
			}
			if (today.getDayOfMonth() < 10) {
				calendarString.append(" ");
			}
			calendarString.append(" " + today.getDayOfMonth() + ":");
			for (int i = 0; i < allJobs.size(); i++) {
				if (allJobs.get(i).getMonth() == today.getMonthValue()) {
					if (allJobs.get(i).getDay() == today.getDayOfMonth()) {
						jobsToday++;
					}
				}
			}
			calendarString.append(jobsToday + " |");
			today = today.plusDays(1); 
		}
		
		currentDay = today; 
		while(currentDay.getDayOfWeek() != (DayOfWeek.SUNDAY)) {
			calendarString.append("      |");
			currentDay = currentDay.plusDays(1);
		}
		return calendarString.toString();
	}
}