/**
 * 
 */
package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Dylan Miller
 *
 */
public abstract class View {
  /**
   * All view objects must use this signature to run the GUI.
   */
  public abstract void launchGUI();
}
