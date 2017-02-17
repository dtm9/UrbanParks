package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import model.AbstractAccount;

/**
 * @author Dylan Miller
 *
 */
public abstract class AbstractView {
//TODO make displayHeader work in a centralized and abstract way. COmmented out for now.  
  private AbstractAccount myUser;
//  private static LocalDate myDay;
//  private static ZoneId myZone;
//  private StringBuilder mySB = new StringBuilder();
  
  public AbstractView() {
//      myZone = ZoneId.of("America/Los_Angeles");
//      myDay = LocalDate.now(myZone);
      
  }
  
  /**All view objects must use this signature to run the GUI.*/
  public abstract void launchGUI();
  
//  void displayHeader() {
//      mySB.append("\neUrbanParks: the Volunteer organizer for Park Districts nationwide\n");
//      mySB.append(myUser.getRealName());
//      mySB.append(" logged in as Urban Parks Manager\n");
//      mySB.append(myDay.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
//      mySB.append(" ");
//      mySB.append(myDay.getDayOfMonth());
//      mySB.append(", ");
//      mySB.append(myDay.getYear());
//      mySB.append(".\n-----------------------------------------------------------------\n");
//      System.out.print(mySB.toString());
//      mySB.delete(0, mySB.capacity());
//  }
}
