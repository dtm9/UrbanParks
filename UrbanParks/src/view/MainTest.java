package view;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.AbstractAccount;
import model.ParkManager;
import model.Volunteer;

/**
 * Unit tests for the Main driver class.
 * @author Dylan Miller
 */
public class MainTest {

    //***** Test fixture(s), setUp(), etc. *****************************************************************************

    /** The Account test fixture. */
    private AbstractAccount myGoodAccount;

    /** The username test fixture. */
    private String myGoodUsername;

    /** The ParkManager test fixture. */
    private ParkManager myGoodPM;

    /** The Volunteer test fixture. */
    private Volunteer myGoodVolunteer;

    /** The View test fixture. */
    private AbstractView myTestView;

    /** The expected exception rule. */
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    /**
     * Sets up the relevant test fixtures.
     */
    @Before
    public void setUp() throws Exception {
        Main.load();
        Main testMainObj = new Main();
        List<AbstractAccount> accounts = testMainObj.getAllAccounts();

        myGoodAccount = accounts.get(0);
        myGoodUsername = myGoodAccount.getUsername();

        myGoodPM = new ParkManager("test@test.com", "5551112222", "Testy McTesterson");
        myGoodVolunteer = new Volunteer("test2@test.com", "5552223333", "Testy McTesterson Jr.");
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Tests Main#seekAccount(String) using a good Account from the datastore.
     * @author Dylan Miller
     */
    @Test
    public final void seekAccount_goodUsername_NoExceptionsExpected() {
        AbstractAccount testUser = Main.seekAccount(myGoodUsername);
        if (!testUser.equals(myGoodAccount)) {
            System.err.println("testUser name: " + testUser.getUsername() + "\ngoodAccount name: " + myGoodAccount.getUsername());
            fail("Could not seek account!");
        }
    }

    /**
     * Tests Main#seekAccount(String) using an Account not contained in the datastore.
     * @author Dylan Miller
     */
    @Test
    public final void seekAccount_UserNotInDatastore_ShouldReturnNull() {
        String badUsername = "notInTheDatastore@gmail.com";
        AbstractAccount testUser = Main.seekAccount(badUsername);
        if (testUser != null) fail("account was fetched with bad username input.");
    }

    /**
     * Tests Main#generateView(Account, View) with each type of user Account, i.e., ParkManager,
     * Volunteer, and OfficeStaff.
     * @author Dylan Miller
     */
    @Test
    public final void generateView_EachAccountIsValid_NoExceptionsExcpected() {
        try {
            Main.generateView(myGoodPM, myTestView);
        } catch (NullPointerException e) {
            fail("Failing to launch park manager gui!");
        }

        try {
            Main.generateView(myGoodVolunteer, myTestView);
        } catch (NullPointerException e) {
            fail("failing to launch volunteer gui!");
        }
    }

    /**
     * Tests Main#generateView(Account, View) with a null Account abstract object; expected to throw a
     * NullPointerException.
     * @author Dylan Miller
     */
    @Test
    public final void generateView_AbstractAccount_NullPointerExceptionExpected() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Account not found.");

        AbstractAccount badAccount = null;
        Main.generateView(badAccount, myTestView);
    }

    /**
     * Tests Main#generateView(Account, View) with a null Volunteer object; expected to throw a
     * NullPointerException.
     * @author Dylan Miller
     */
    @Test
    public final void generateView_NullObjectWithVolunteerCast_NullPointerExceptionExpected() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Account not found.");

        Main.generateView((Volunteer)null, myTestView);
    }

    /**
     * Tests Main#generateView(Account, View) with a null ParkManager object; expected to throw a
     * NullPointerException.
     * @author Dylan Miller
     */
    @Test
    public final void generateView_NullObjectWithParkManagerCast_NullPointerExceptionExpected() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Account not found.");

        Main.generateView((ParkManager)null, myTestView);
    }
}
