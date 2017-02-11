/**
 * The user interface for an office staff user. This object is not
 * able to validate the type of user interacting with the system
 * so be mindful that the user's type is validated before
 * running this code.
 */
package view;

import java.util.Calendar;

/**
 * @author Dylan Miller
 *
 */
public class OfficeStaffView extends View {

  /**OS-independent string to add a line break in string builder.*/
  private static final String SB_LINE_BREAK = System.getProperty("line.separator");

  private StringBuilder sb;
  
  public OfficeStaffView() { //TODO consider having a OfficeStaff object be a param for the constructor..
	  super();
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
  
  public String viewCalendar() { // Need to get data from Datastore for jobs
	  StringBuilder calendarString = new StringBuilder();
	  Calendar calendar = Calendar.getInstance();
	  String month;
	  int theMonth = Calendar.MONTH;
	  
	  switch(theMonth) {
	  	case 1: month = "January"; break;
	  	case 2: month = "February"; break;
	  	case 3: month = "March"; break;
	  	case 4: month = "April"; break;
	  	case 5: month = "May"; break;
	  	case 6: month = "June"; break;
	  	case 7: month = "July"; break;
	  	case 8: month = "August"; break;
	  	case 9: month = "September"; break;
	  	case 10: month = "October"; break;
	  	case 11: month = "November"; break;
	  	default: month = "December"; break;
	  }
	  calendarString.append(month + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", ");
	  calendarString.append(calendar.get(Calendar.YEAR) + "."); // First line ends here (Add Jobs this month)
	  calendarString.append(System.getProperty("line.separator"));
	  calendarString.append(System.getProperty("line.separator"));
	  calendarString.append("   Su  ");
	  calendarString.append("    M  ");
	  calendarString.append("    T  ");
	  calendarString.append("    E  ");
	  calendarString.append("    T  ");
	  calendarString.append("    F  ");
	  calendarString.append("    S  ");
	  calendarString.append(System.getProperty("line.separator"));
	  calendarString.append("               [" + month + "]");
	  calendarString.append(System.getProperty("line.separator"));
	  

	  
	  return calendarString.toString();
  }
}
