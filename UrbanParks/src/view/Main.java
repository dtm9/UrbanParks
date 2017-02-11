package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import backend.Account;
import backend.Datastore;
import backend.Job;
import backend.OfficeStaff;
import backend.Park;
import backend.ParkManager;
import backend.Volunteer;

public class Main {

  static Datastore datastore;
  private static StringBuilder mySB = new StringBuilder();
  private static Scanner myScanner = new Scanner(System.in);
  
  /**
   * Empty package-level constructor for testing purposes only.
   * @author Dylan Miller
   */
  Main() {}
  
  public static void main(final String[] args) {
    
    load();
    //debug_init();
    init();
    save();
  }
  
  /**
   * Helper method to log in and start the GUI.
   * @author Dylan Miller 
   */
  private static void init() {

    //get the username
    mySB.append("Username: ");
    System.out.print(mySB.toString());
    String username = myScanner.nextLine();
    
    //get the list of accounts
    List<Account> users = datastore.getAllAccounts();
    boolean found = false;
    Account userAccount = null;
    View theView = null;
    
    //seek the list for the entered username
    for (Account user : users) {
      if (!found && user.getUsername().equals(username)) {
        userAccount = user;
        found = true;
      }
    }
    
  theView = generateView(userAccount, theView);
  theView.launchGUI();
  }

/**
 * @param userAccount
 * @param theView
 * @return
 */
private static View generateView(Account userAccount, View theView) {
	if (userAccount instanceof Volunteer) {
      theView = new VolunteerView();
    } else if (userAccount instanceof ParkManager) {
      theView = new ParkManagerView(userAccount, datastore);
    } else if (userAccount instanceof OfficeStaff) {
      theView = new OfficeStaffView(userAccount, datastore);
    } else {
      throw new NullPointerException("Account not found.");
    }
	return theView;
}
  private static void debug_init() {

    System.out.print(mySB.append("please enter your email(anything works this is a test): "));
	String myUsername = myScanner.nextLine();//scanner to get the username for checking acounts, not used yet.
	List<Account> myAccounts = datastore.getAllAccounts();

    //Just using the IOtest and Hard coding to load a View
//	View theView = new ParkManagerView(myAccounts.get(0),datastore);
    View theView = new OfficeStaffView(myAccounts.get(4),datastore);
//    View theView = new VolunteerView(myAccounts.get(4),datastore);
	theView.launchGUI();

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
