package test;

import backend.ParkManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Simple unit tests for the ParkManager class.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class ParkManagerTests {

    /** Park manager account test fixture. */
    private ParkManager myParkManager;

    /**
     * Sets up the test fixture.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        myParkManager = new ParkManager("john@tacomaparks.com", "2535551234", "John Doe");
    }

    /**
     * Tests for equality comparing two separate objects with the same data.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testEquality() {
        ParkManager otherManager = new ParkManager("john@tacomaparks.com", "2535551234", "John Doe");
        assertTrue(otherManager.equals(myParkManager));
    }

    /**
     * Tests for inequality comparing two separate objects with only the username field different.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test
    public void testInequality() {
        ParkManager otherManager = new ParkManager("jimmy@tacomaparks.com", "2535551234", "John Doe");
        assertFalse(otherManager.equals(myParkManager));
    }
}
