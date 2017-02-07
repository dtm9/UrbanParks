package test;

import org.junit.Before;
import org.junit.Test;

import backend.Park;
import backend.ParkManager;

/**
 * Basic rainy-day test cases for the Park class.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class ParkRainyDay {

    /** The park manager test fixture. */
    private ParkManager myManager;

    /** The park test fixture. */
    private Park myPark;

    /**
     * Sets up the test fixture.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        myManager = new ParkManager("Someone@gmail.com", "1112223333", "Someone");
        myPark = new Park(myManager, "University Park", "Tacoma", "WA", "12345");
    }


    /**
     * Tests for IllegalArgumentException when passed bad state data.
     *
     * @author Walter Weeks
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetStateNull() {
        myPark.setState(null);
    }

    /**
     * Tests for IllegalArgumentException when passed bad ZIP Code data.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetZipcodeNull() {
        myPark.setZipcode(null);
    }

    /**
     * Tests for IllegalArgumentException when passed bad city data.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetCityNull() {
        myPark.setCity(null);
    }

    /**
     * Tests for IllegalArgumentException when passed bad park name data.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetNameNull() {
        myPark.setName(null);
    }

    /**
     * Tests for IllegalArgumentException when passed bad park manager data.
     *
     * @author Walter Weeks (ww3@uw.edu)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetManagerNull() {
        myPark.setManager(null);
    }
}