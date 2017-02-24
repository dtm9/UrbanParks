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
//TODO make displayHeader work in a centralized and abstract way. COmmented out for now.  Display Header needs to be implemented in an interface if we are doing it that way.
  private AbstractAccount myUser;
  
  public AbstractView() {
  }
  
  /**All view objects must use this signature to run the GUI.*/
  public abstract Datastore launchGUI(Datastore theDatastore);
  
  // TODO Gardner fix this shit
  public void displayHeader() { 
  }
}
