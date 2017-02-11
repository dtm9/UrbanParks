package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import backend.Account;
import backend.Datastore;
import backend.Job;
import backend.OfficeStaff;
import backend.Park;
import backend.ParkManager;
import backend.Volunteer;

public class Main {

  private Account user;
  private View view;
  static Datastore datastore;
  
  /**
   * Empty package-level constructor for testing purposes only.
   * @author Dylan Miller
   */
  Main() {}
  
  public static void main(final String[] args) {
    //init();
    load();
    
    save();
  }

  private static void init() {
    
    //TODO print a login screen and listen for input
    //note: eclipse will stop complaining once the code for these TODO statements is done. Complaining of no initialization - Dylan

    //TODO capture user's input and match account name

//    if (theUser instanceof Volunteer) {
//      theView = new VolunteerView();
//    } else if (theUser instanceof ParkManager) {
//      theView = new ParkManagerView();
//    } else if (theUser instanceof OfficeStaff) {
//      theView = new OfficeStaffView();
//    } else {
//      //TODO throw some kind of exception. We should never each this code block.
//    }
//    
//    theView.launchGUI();
  }
  
  public static void save() { //consider private or package
      try {
    	  FileOutputStream outfile = new FileOutputStream("datastore.bin");
    	  ObjectOutputStream out = new ObjectOutputStream(outfile);
    	  out.writeObject(datastore);
    	  out.close();
    	  outfile.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
  public static void load() {
    try {
      FileInputStream infile = new FileInputStream("datastore.bin");
      ObjectInputStream in = new ObjectInputStream(infile);
      datastore = (Datastore) in.readObject();
      in.close();
      infile.close();
    } catch(IOException i) {
      System.out.println("something has gone wrong with IO\n");
    } catch (ClassNotFoundException c) {
      System.out.println("Datastore not found\n");
    }
  }
  
  //(package level) for testing only
  void setDatastore(Datastore theDatastore) {
    Main.datastore = theDatastore;
  }
  
  //(package level) for testing only
  Datastore getDatastore() {
    return Main.datastore;
  }
}