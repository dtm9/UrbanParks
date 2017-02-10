package view;

import backend.Account;
import backend.OfficeStaff;
import backend.ParkManager;
import backend.Volunteer;

//This is not required for check in 2
public class Main {

  public static void main(final String[] args) {
    init();
  }

  private static void init() {
    Account theUser;
    View theView;
    //TODO print a login screen and listen for input
    //note: eclipse will stop complaining once the code for these TODO statements is done. Complainin of no initialization - Dylan

    //TODO capture user's input and match account name

    if (theUser instanceof Volunteer) {
      theView = new VolunteerView();
    } else if (theUser instanceof ParkManager) {
      theView = new ParkManagerView();
    } else if (theUser instanceof OfficeStaff) {
      theView = new OfficeStaffView();
    } else {
      //TODO throw some kind of exception. We should never each this code block.
    }
    
    theView.launchGUI();
  }
}
