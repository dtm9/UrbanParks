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
import backend.OfficeStaff;
import backend.ParkManager;
import backend.Volunteer;

/**
 * Class to launch the application. Stores the mainline.
 *
 * @author Dylan Miller
 */
public class Main {

    //***** Field(s) ***************************************************************************************************

    /**
     * Static reference to the datastore in memory.
     */
    static Datastore datastore;

    /**
     * String builder for printing any text needed to console.
     */
    private static StringBuilder mySB = new StringBuilder();

    /**
     * OS-independent string to add a line break in string builder.
     */
    private static final String SB_LINE_BREAK = System.getProperty("line.separator");

    /**
     * Scanner object for any user input that may be required for log in.
     */
    private static Scanner myScanner = new Scanner(System.in);

    //***** Constructor(s) *********************************************************************************************

    /**
     * Empty package-level constructor for testing purposes only.
     *
     * @author Dylan Miller
     */
    Main() {
    }

    //***** Static method(s) *******************************************************************************************

    public static void main(final String[] args) {

        boolean done = false;
        load();
        while (!done) {
            done = init();
            mySB.delete(0, mySB.capacity());
            save();
        }
        mySB.append(SB_LINE_BREAK);
        System.out.print(mySB.toString());
    }

    /**
     * Helper method to log in and start the GUI.
     *
     * @author Dylan Miller
     */
    private static boolean init() {
        boolean done = false;
        printHeader();

        int theChoice = myScanner.nextInt();

        switch (theChoice) {
            case 1: //log in
                //get the username
                View theView = null;
                Account userAccount = null;
                String username = scanUsername();

                //get the account
                userAccount = seekAccount(username);

                //launch the view
                try {
                    theView = generateView(userAccount, theView);
                    theView.launchGUI();
                } catch (NullPointerException e) {
                    mySB.delete(0, mySB.capacity());
                    mySB.append(SB_LINE_BREAK);
                    mySB.append(e.getMessage());
                    mySB.append(SB_LINE_BREAK);
                    mySB.append(SB_LINE_BREAK);
                    System.out.print(mySB.toString());
                    mySB.delete(0, mySB.capacity());
                }

                done = false;
                break;
            case 2: //exit
                done = true;
                break;
            default: //wrong input
                done = false; //will repeat the menu again
                break;
        }
        return done;
    }

    /**
     * Helper method to get the username from the command line.
     *
     * @return user's GUID
     * @author Dylan Miller
     */
    private static String scanUsername() {
        myScanner.nextLine();
        //get the username
        mySB.append("Email: ");
        System.out.print(mySB.toString());
        String username = myScanner.nextLine();
        return username;
    }

    /**
     * Matches passed in username for account in the datastore.
     *
     * @param username provided by user.
     * @return account of user or null if not found.
     * @author Dylan Miller
     */
    static Account seekAccount(String username) {
        List<Account> users = datastore.getAllAccounts();
        boolean found = false;
        Account userAccount = null;

        //seek the list for the entered username
        for (Account user : users) {

            if (!found && user.getUsername().equals(username)) {
                userAccount = user;
                found = true;
            }
        }
        return userAccount;
    }

    /**
     * Helper method to test the log-in screen display.
     *
     * @author Dylan Miller
     */
    private static void printHeader() {
        mySB.append(SB_LINE_BREAK);
        mySB.append(SB_LINE_BREAK);
        mySB.append("----------------------------------------------------------");
        mySB.append(SB_LINE_BREAK);
        mySB.append("Welcome to Urban Parks ");
        mySB.append(SB_LINE_BREAK);
        mySB.append("----------------------------------------------------------");
        mySB.append(SB_LINE_BREAK);
        mySB.append(SB_LINE_BREAK);

        mySB.append("Make a selection:");
        mySB.append(SB_LINE_BREAK);
        mySB.append("1. Log in");
        mySB.append(SB_LINE_BREAK);
        mySB.append("2. Exit");
        mySB.append(SB_LINE_BREAK);

        System.out.print(mySB.toString());
        System.out.print("Selection: ");
        mySB.delete(0, mySB.capacity());
    }

    /**
     * Helper method to create the proper view for the user.
     *
     * @param userAccount
     * @param theView
     * @return instantiated view object.
     * @throws NullPointerException if the userAccount was null (not found in previous method)
     * @author Dylan Miller
     */
    static View generateView(Account userAccount, View theView) {
        if (userAccount instanceof Volunteer) {
            theView = new VolunteerView(userAccount, datastore);
        } else if (userAccount instanceof ParkManager) {
            theView = new ParkManagerView(userAccount, datastore);
        } else if (userAccount instanceof OfficeStaff) {
            theView = new OfficeStaffView(userAccount, datastore);
        } else {
            throw new NullPointerException("Account not found.");
        }
        return theView;
    }

    /**
     * Saves the datastore object to file.
     *
     * @author Dylan Miller
     */
    public static void save() { //consider private or package
        try {
            FileOutputStream outfile = new FileOutputStream("datastore.bin");
            ObjectOutputStream out = new ObjectOutputStream(outfile);
            out.writeObject(datastore);
            out.close();
            outfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the datastore from file.
     *
     * @author Dylan Miller
     */
    public static void load() {
        try {
            FileInputStream infile = new FileInputStream("datastore.bin");
            ObjectInputStream in = new ObjectInputStream(infile);
            datastore = (Datastore) in.readObject();
            in.close();
            infile.close();
        } catch (IOException i) {
            System.out.println("something has gone wrong with IO\n");
        } catch (ClassNotFoundException c) {
            System.out.println("Datastore not found\n");
        }
    }

    //***** Misc method(s) *********************************************************************************************

    /**
     * (package level) for testing only.
     */
    void setDatastore(Datastore theDatastore) {
        Main.datastore = theDatastore;
    }

    /**
     * (package level) for testing only.
     */
    Datastore getDatastore() {
        return Main.datastore;
    }
}
