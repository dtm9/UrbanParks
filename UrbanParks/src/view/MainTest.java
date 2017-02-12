/**
 * 
 */
package view;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.Account;
import backend.OfficeStaff;
import backend.ParkManager;
import backend.Volunteer;
import backend.Volunteer.WorkGrade;

/**
 * @author Dylan Miller
 *
 */
public class MainTest {
  
  private Account goodAccount;
  private String goodUsername;
  private ParkManager goodPM;
  private Volunteer goodVolunteer;
  private OfficeStaff goodOfficeStaff;
  private View testView;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
      Main.load();
      List<Account> accounts = Main.datastore.getAllAccounts();
      
      goodAccount = accounts.get(0);
      goodUsername = goodAccount.getUsername();
      
      goodPM = new ParkManager("test@test.com", "5551112222", "Testy McTesterson");
      goodVolunteer = new Volunteer("test2@test.com", "5552223333", "Testy McTesterson Jr.", WorkGrade.LIGHT);
      goodOfficeStaff = new OfficeStaff("test3@test.com", "5553334444", "Testy McTesterson III");
    }
    
    @Test
    public final void seekAccount_goodUsername_NoExceptionsExpected() {
       Account testUser = Main.seekAccount(goodUsername);
       if (!testUser.equals(goodAccount)) {
         System.err.println("testUser name: " + testUser.getUsername() + "\ngoodAccount name: " + goodAccount.getUsername());
         fail("Could not seek account!");
       } 
    }
    
    @Test
    public final void seekAccount_UserNotInDatastore_ShouldReturnNull() {
      String badUsername = "notInTheDatastore@gmail.com";
      Account testUser = Main.seekAccount(badUsername);
      if (testUser != null) fail("account was fetched with bad username input.");
    }
    
    @Test
    public final void generateView_EachAccountIsValid_NoExceptionsExcpected() {
      try {
        Main.generateView(goodPM, testView);
      } catch (NullPointerException e) {
        fail("Failing to launch park manager gui!");
      }
      
      try {
        Main.generateView(goodVolunteer, testView);
      } catch (NullPointerException e) {
        fail("failing to launch volunteer gui!");  
      }
      
      try {
        Main.generateView(goodOfficeStaff, testView);
      } catch (NullPointerException e) {
        fail("failing to launch office staff gui!");
      }
    }
    
    @Test
    public final void generateView_AbstractAccount_NullPointerExceptionExpected() {
      exception.expect(NullPointerException.class);
      exception.expectMessage("Account not found.");
      
      Account badAccount = null;
      Main.generateView(badAccount, testView);
    }
    
    @Test
    public final void generateView_NullObjectWithVolunteerCast_NullPointerExceptionExpected() {
      exception.expect(NullPointerException.class);
      exception.expectMessage("Account not found.");
      
      Main.generateView((Volunteer)null, testView);
    }
    
    @Test
    public final void generateView_NullObjectWithParkManagerCast_NullPointerExceptionExpected() {
      exception.expect(NullPointerException.class);
      exception.expectMessage("Account not found.");
      
      Main.generateView((ParkManager)null, testView);
    }
    
    @Test
    public final void generateView_NullObjectWithOfficeStaffCast_NullPointerExceptionExpected() {
      exception.expect(NullPointerException.class);
      exception.expectMessage("Account not found.");
      
      Main.generateView((OfficeStaff)null, testView);
    }
}
