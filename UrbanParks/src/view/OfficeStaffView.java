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
import java.util.Locale;

import backend.Account;
import backend.Datastore;
import backend.ParkManager;
import backend.OfficeStaff;

/**
 * @author Dylan Miller
 *
 */
public class OfficeStaffView extends View {

  /**OS-independent string to add a line break in string builder.*/
  private static final String SB_LINE_BREAK = System.getProperty("line.separator");

  private StringBuilder sb;
  private OfficeStaff myOfficeStaff;
  private Datastore myDataStore;
  
	public OfficeStaffView(Account theAccount, Datastore theDatastore) {
		super();
		myOfficeStaff = (OfficeStaff) theAccount;
		myDataStore = theDatastore;
		sb = new StringBuilder();
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
  System.out.println(printCalendar());
  }
  
  private void mainMenu() {
	  //TODO print somethign with SB and listen for input option on console
	  
	  //TODO catch console input
	  int choice;
	  choice = 0; //temp to clear annoying error for temp code
	  
      switch (choice) {
        case 0:
        	//return (back to the main menu)
        	break;
        case 1:
        	//some other method that will run a public method from model code, print that, and then show a menu
        	break;
      }
  }
  
	private String printCalendar() {
		ZoneId z = ZoneId.of( "America/Los_Angeles" );
		LocalDate today = LocalDate.now(z);		
		StringBuilder calendarString = new StringBuilder();
		int startDay = 0;
		int monthCheck = today.getDayOfMonth();
		
		// Still have to add total number of jobs to the first line
		calendarString.append(today.getMonth().getDisplayName(TextStyle.FULL , Locale.US));
		calendarString.append(" " + today.getDayOfMonth() + ", ");
		calendarString.append(today.getYear());
		calendarString.append(System.getProperty("line.separator"));
		calendarString.append(System.getProperty("line.separator"));
		calendarString.append("   Su  ");
		calendarString.append("    M  ");
		calendarString.append("    T  ");
		calendarString.append("    W  ");
		calendarString.append("    T  ");
		calendarString.append("    F  ");
		calendarString.append("    S  ");
		calendarString.append(System.getProperty("line.separator"));
		calendarString.append("               [");
		calendarString.append(today.getMonth().getDisplayName(TextStyle.FULL , Locale.US) + "]");
		calendarString.append(System.getProperty("line.separator"));
		
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
			if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
				calendarString.append(System.getProperty("line.separator"));
				calendarString.append("|");
			}
			if (today.getDayOfMonth() < 10) {
				calendarString.append(" ");
			}
			calendarString.append(" " + today.getDayOfMonth() + ":" + "0" + " |");
			today = today.plusDays(1); 
		}
		
		LocalDate currentDay = today; 
		while(currentDay.getDayOfWeek() != (DayOfWeek.SUNDAY)) {
			calendarString.append("      |");
			currentDay = currentDay.plusDays(1);
		}
		
		// This is where the next month's calendar starts
		calendarString.append(System.getProperty("line.separator"));
		calendarString.append("               [");
		calendarString.append(today.getMonth().getDisplayName(TextStyle.FULL , Locale.US) + "]");
		calendarString.append(System.getProperty("line.separator"));

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
			if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
				calendarString.append(System.getProperty("line.separator"));
				calendarString.append("|");
			}
			if (today.getDayOfMonth() < 10) {
				calendarString.append(" ");
			}
			calendarString.append(" " + today.getDayOfMonth() + ":" + "0" + " |");
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