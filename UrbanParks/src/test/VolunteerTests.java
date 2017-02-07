package test;

import backend.Volunteer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Simple unit tests for the Volunteer class.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class VolunteerTests {
    /** Volunteer manager account test fixture. */
    private Volunteer myVolunteer;

    /**
     * Sets up the test fixture.
     *
     * @author Walter Weeks
     */
    @Before
    public void setUp() {
        myVolunteer = new Volunteer("joe@yahoo.com", "2535551212", "Joe Smith", Volunteer.WorkGrade.LIGHT);
    }

    /**
     * Tests to see if the IllegalArgumentException is thrown for negative values of absence count.
     *
     * @author Walter Weeks
     */
    @Test (expected =  IllegalArgumentException.class)
    public void testSetAbsentCountNegative() {
        myVolunteer.setAbsenceCount(-1);
    }

    /**
     * Tests to see if the IllegalArgumentException is thrown for negative values of cancellation count.
     *
     * @author Walter Weeks
     */
    @Test (expected =  IllegalArgumentException.class)
    public void testSetCancellationCountNegative() {
        myVolunteer.setBadCancellationCount(-1);
    }

    /**
     * Tests to see if the isBlackballed default value of false is set.
     *
     * @author Walter Weeks
     */
    @Test
    public void testIsBlackballedDefault() {
        assertFalse(myVolunteer.isBlackballed());
    }

    /**
     * Tests the setBlackballed method.
     *
     * @author Walter Weeks
     */
    @Test
    public void testSetIsBlackballed() {
        myVolunteer.setBlackballed(true);
        assertTrue(myVolunteer.isBlackballed());
    }

    /**
     * Tests the expected work grade value of the test fixture.
     *
     * @author Walter Weeks
     */
    @Test
    public void testGetWorkGrade() {
        assertEquals(Volunteer.WorkGrade.LIGHT, myVolunteer.getWorkGrade());
    }

    /**
     * Tests the setAbsenceCount method.
     *
     * @author Walter Weeks
     */
    @Test
    public void testSetAbsenceCount() {
        myVolunteer.setAbsenceCount(20);
        assertEquals(20, myVolunteer.getAbsenceCount());
    }

    /**
     * Tests the setBadCancellationCount method.
     *
     * @author Walter Weeks
     */
    @Test
    public void testSetBadCancellationCount() {
        myVolunteer.setBadCancellationCount(42);
        assertEquals(42, myVolunteer.getBadCancellationCount());
    }

    /**
     * Tests to see if an IllegalArgumentException is thrown when passing null as an account note.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddAccountNoteNull() {
        myVolunteer.addAccountNote(null);
    }

    /**
     * Tests to see if an IllegalArgumentException is thrown when passing an empty string as an account note.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddAccountNoteEmptyString() {
        myVolunteer.addAccountNote("");
    }
}
