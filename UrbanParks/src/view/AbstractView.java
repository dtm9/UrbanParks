package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import model.Datastore;
import model.AbstractAccount;

/**
 * @author Dylan Miller
 *
 */
public abstract class AbstractView {
  private AbstractAccount myUser;
  
  public AbstractView() {
  }
  
  /**All view objects must use this signature to run the GUI.*/
  public abstract Datastore launchGUI(Datastore theDatastore);
  
  /**
   * Displays the header for the views.
   * @param theAccount
   * @param theDay
   */
  public void displayHeader(AbstractAccount theAccount, LocalDate theDay) { 
      System.out.print("\neUrbanParks: the Volunteer organizer for Park Districts nationwide\n\n");
      System.out.print(theAccount.getRealName());
      System.out.print(" logged in as Urban Parks " + theAccount.AccountType() + "\n");
      System.out.print(theDay.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
      System.out.print(" ");
      System.out.print(theDay.getDayOfMonth());
      System.out.print(", ");
      System.out.print(theDay.getYear());
      System.out.print(".\n-----------------------------------------------------------------\n");
  }
}
