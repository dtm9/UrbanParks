/**
 * The user interface for an office staff user. This object is not
 * able to validate the type of user interacting with the system
 * so be mindful that the user's type is validated before
 * running this code.
 */
package view;

/**
 * @author Dylan Miller
 *
 */
public class OfficeStaffView extends View {

  /**OS-independent string to add a line break in string builder.*/
  private static final String SB_LINE_BREAK = System.getProperty("line.separator");

  private StringBuilder sb;
  
  public OfficeStaffView() { //TODO consider having a OfficeStaff object be a param for the constructor.
	  super();
	  sb = new StringBuilder();
  }
  /**
   * Public method to launch the GUI for Office Staff accounts.
   * This is the only public method and it drives all other code in
   * this module.
   * @author Dylan Miller
   */
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
}
