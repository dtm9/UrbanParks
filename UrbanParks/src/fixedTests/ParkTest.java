package fixedTests;

import static org.junit.Assert.*;

import backend.Park;
import backend.ParkManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic test cases for the Park class.
 *
 * @author Walter Weeks (ww3@uw.edu)
 */
public class ParkTest {

	// ***** Test fixture(s) and setUp() *******************************************************************************

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
		myManager = new ParkManager("johnjones@tacomaparks.com", "2535551111", "John Jones");
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
                "Tacoma", "WA", "98405");
	}

	//***** Unit test(s) ***********************************************************************************************

	/**
	 * Tests two separate Park objects for equality; should be true.
	 *
	 * @author Peter Park
	 */
	@Test
	public void equals_UnchangedTestFixture_True() {
		Park otherPark = new Park(myPark.getManager(), myPark.getName(), myPark.getStreet(),
				myPark.getCity(), myPark.getState().toString(), myPark.getZipcode());
		assertEquals(otherPark, myPark);
	}

	/**
	 * Tests two separate Park objects for equality; should be false.
	 *
	 * @author Peter Park
	 */
	@Test
	public void equals_UnchangedTestFixture_False() {
		Park otherPark = new Park(myManager, "Wapato Park", "6500 S Sheridan Ave",
				"Tacoma", "WA", "98408");
		assertFalse(otherPark.equals(myPark));
	}

	/**
	 * Tests to see if the park manager is as expected from the getter, Park#getManager().
	 *
	 * @author Peter Park
	 */
	@Test
	public void getManager_UnchangedTestFixture_True() {
		assertEquals("johnjones@tacomaparks.com", myPark.getManager().getUsername());
	}

	/**
	 * Tests to see if a park name is as expected from the getter, Park#getName().
	 *
	 * @author Peter Park
	 */
	@Test
	public void getName_UnchangedTestFixture_True() {
		assertEquals("Tacoma Nature Center", myPark.getName());
	}

	/**
	 * Tests to see if the getter works properly, Park#getCity().
	 *
	 * @author Peter Park
	 */
	@Test
	public void getCity_UnchangedTestFixture_True() {
		assertEquals("Tacoma", myPark.getCity());
	}

	/**
	 * Tests to see if the getter works properly, Park#getState().
	 *
	 * @author Peter Park
	 */
	@Test
	public void getState_UnchangedTestFixture_True() {
		assertEquals("WA", myPark.getState().toString());
	}

	/**
	 * Tests to see if the getter works properly, Park#getZipcode().
	 *
	 * @author Peter Park
	 */
	@Test
	public void getZipcode_UnchangedTestFixture_True() {
		assertEquals("98405", myPark.getZipcode());
	}

    /**
     * Tests to see if the getter works properly, Park#getStreet().
     *
     * @author Walter Weeks
     */
	@Test
	public void getStreet_UnchangedTestFixture_True() {
		assertEquals("1919 S Tyler St", myPark.getStreet());
	}

	//***** Unit test(s) looking or thrown exceptions for improper data *****************************************************

	/**
	 * Tests for NullPointerException when passed bad state data, i.e., null.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = NullPointerException.class)
	public void Park_NewInstantiationWithNullState_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", null, "98405");
	}

	/**
	 * Tests for IllegalArgumentException when passed bad state data, i.e., the empty string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWithEmptyState_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", "", "98405");
	}

	/**
	 * Tests for NullPointerException when passed bad ZIP Code data, i.e., null.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = NullPointerException.class)
	public void Park_NewInstantiationWithNullZipcode_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", "WA", null);
	}

	/**
	 * Tests for IllegalArgumentException when passed bad ZIP Code data, i.e., the empty string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWithEmptyZipcode_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", "WA", "");
	}

	/**
	 * Tests for NullPointerException when passed bad city data, i.e., null.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = NullPointerException.class)
	public void Park_NewInstantiationWithNullCity_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				null, "WA", "98405");
	}

	/**
	 * Tests for IllegalArgumentException when passed bad city data, i.e., the empty string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWithEmptyCity_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"", "WA", "98405");
	}

	/**
	 * Tests for NullPointerException when passed bad street data, i.e., null.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = NullPointerException.class)
	public void Park_NewInstantiationWithNullStreet_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", null,
				"Tacoma", "WA", "98405");
	}

	/**
	 * Tests for IllegalArgumentException when passed bad street data, i.e., the empty string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWithEmptyStreet_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "",
				"Tacoma", "WA", "98405");
	}

	/**
	 * Tests for NullPointerException when passed bad park name data, i.e., null.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = NullPointerException.class)
	public void Park_NewInstantiationWithNullName_ExceptionThrown() {
		myPark = new Park(myManager, null, "1919 S Tyler St",
				"Tacoma", "WA", "98405");
	}

	/**
	 * Tests for IllegalArgumentException when passed bad park name data, i.e., the empty string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWithEmptyName_ExceptionThrown() {
		myPark = new Park(myManager, "", "1919 S Tyler St",
				"Tacoma", "WA", "98405");
	}

	/**
	 * Tests for NullPointerException when passed bad park manager data, i.e., null.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = NullPointerException.class)
	public void Park_NewInstantiationWithNullParkManager_ExceptionThrown() {
		myPark = new Park(null, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", "WA", "98405");
	}

	/**
	 * Tests for IllegalArgumentException when passed bad state data, i.e., 1 character string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWith1CharacterState_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", "W", "98405");
	}

	/**
	 * Tests for IllegalArgumentException when passed bad state data, i.e., 3 character string.
	 *
	 * @author Walter Weeks
	 */
	@Test (expected = IllegalArgumentException.class)
	public void Park_NewInstantiationWith3CharacterState_ExceptionThrown() {
		myPark = new Park(myManager, "Tacoma Nature Center", "1919 S Tyler St",
				"Tacoma", "Was", "98405");
	}
}
