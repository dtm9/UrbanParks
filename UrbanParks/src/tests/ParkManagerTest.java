package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ParkManager;

/**
 * Basic unit tests for the ParkManager class.
 *
 * @author Dylan Miller
 * @author Walter Weeks
 */
public class ParkManagerTest {

    //***** Constant(s) ************************************************************************************************

	/** Address that passes the email REGEX validation. */
	String GOOD_EMAIL = "john@tacomaparks.com";

	/** 10 digit phone number that meets our phone REGEX validation. */
	String GOOD_PHONE = "2535551234";

	/** There is no REGEX for real names, it just needs to not be blank. */
	String GOOD_NAME = "John Doe";

	//***** Test fixture(s), setUp(), etc. *****************************************************************************

	/** Park manager account test fixture. */
    private ParkManager validParkManagerAccount;

    /**
     * Sets up the test fixture.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        validParkManagerAccount = new ParkManager(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
    }

    //***** Unit test(s) ***********************************************************************************************

    /**
     * Tests for equality comparing two separate objects with the same data.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void equals_CreateComparedObjectFromSameSources_ShouldReturnTrue() {
        ParkManager otherManager = new ParkManager(GOOD_EMAIL, GOOD_PHONE, GOOD_NAME);
        assertTrue(otherManager.equals(validParkManagerAccount));
    }

    /**
     * Tests for inequality comparing two separate objects with only the username field different.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void equals_CreateComparedObjectFromDifferentSources_ShouldReturnFalse() {
        ParkManager otherManager = new ParkManager("jimmy@tacomaparks.com", "2535551234", "John Doe");
        assertFalse(otherManager.equals(validParkManagerAccount));
    }

}
