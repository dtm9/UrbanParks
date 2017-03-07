package view;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import model.AbstractAccount;
import model.Datastore;

/**
 * @author Dylan Miller
 * @author Walter Weeks
 */
public abstract class AbstractView {
    
    //***** Constant(s) ************************************************************************************************
   
    public static final String DASHED_LINE = "-----------------------------------------------------------------";
    
    //***** Field(s) ***************************************************************************************************
    
    private StringBuilder mySB = new StringBuilder();
  
    //***** Constructor(s) *********************************************************************************************
    
    public AbstractView() { }
    
    //***** Method(s) **************************************************************************************************
    
    /** All view objects must use this signature to run the GUI. */
    public abstract Datastore launchGUI(Datastore theDatastore);
  
    /**
     * Displays the header for the views.
     * @param theAccount
     * @param theDay
     */
    public void displayHeader(AbstractAccount theAccount, LocalDate theDay) { 
        mySB.delete(0, mySB.length());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Urban Parks | ");
        mySB.append(theDay.getMonthValue());
        mySB.append("/");
        mySB.append(theDay.getDayOfMonth());
        mySB.append("/");
        mySB.append(theDay.getYear());
        mySB.append(Main.LINE_BREAK);
        mySB.append("Welcome ");
        mySB.append(theAccount.getRealName());
        mySB.append(" (");
        mySB.append(theAccount.AccountType());
        mySB.append(')');
        mySB.append(Main.LINE_BREAK);
        mySB.append("-----------------------------------------------------------------");
        mySB.append(Main.LINE_BREAK);
        System.out.print(mySB.toString());
    }
}
