/**
 * Basic sunny-day test cases for the Park class.
 *
 * @author Peter Park
 */

package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.Park;
import backend.ParkManager;

public class ParkSunnyDay {

	/** The park manager test fixture. */
	private ParkManager myManager;

	/** The park test fixture. */
	private Park myPark;

	/**
	 * Sets up the test fixture.
	 *
	 * @author Peter Park
	 */
	@Before
	public void setUp() {
		myManager = new ParkManager("Someone@gmail.com", "1112223333", "Someone");
		myPark = new Park(myManager, "University Park", "Tacoma", "WA", "12345");
	}

	/**
	 * Tests two separate Park objects for equality; should be true.
	 *
	 * @author Peter Park
	 */
	@Test
	public void testParkEqualityTrue() {
		Park otherPark = new Park(myPark.getManager(), myPark.getName(), myPark.getCity(), myPark.getState(), myPark.getZipcode());
		assertEquals(otherPark, myPark);
	}

	/**
	 * Tests two separate Park objects for equality; should be false.
	 *
	 * @author Peter Park
	 */
	@Test
	public void testParkEqualityFalse() {
		Park otherPark = new Park(myManager, "University Park", "Seattle", "WA", "12345");
		assertFalse(otherPark.equals(myPark));
	}

	/**
	 * Tests to see if the park manager is as expected from the getter, Park#getManager().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testGetManager() {
		assertEquals("Someone@gmail.com", myPark.getManager().getUsername());
	}

	/**
	 * Tests to see if a new park manager has been set properly, Park#setManager(String).
	 *
	 * @author Peter Park
	 */
	@Test
	public void testSetManager() {
		ParkManager newManager = new ParkManager("NewGuy@gmail.com", "2223334444", "NewGuy");
		myPark.setManager(newManager);
		assertEquals(myPark.getManager(), newManager);
	}

	/**
	 * Tests to see if a park name is as expected from the getter, Park#getName().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testGetName() {
		assertEquals(myPark.getName(), "University Park");
	}

	/**
	 * Tests to see if the setter works properly, Park#setName(String).
	 *
	 * @author Peter Park
	 */
	@Test
	public void testSetName() {
		myPark.setName("NewName Park");
		assertEquals(myPark.getName(), "NewName Park");
	}

	/**
	 * Tests to see if the getter works properly, Park#getCity().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testGetCity() {
        assertEquals(myPark.getCity(), "Tacoma");
	}

	/**
	 * Tests to see if the setter works properly, Park#setCity().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testSetCity() {
		myPark.setCity("Seattle");
		assertEquals(myPark.getCity(), "Seattle");
	}

	/**
	 * Tests to see if the getter works properly, Park#getState().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testGetState() {
		assertEquals(myPark.getState(), "WA");
	}

	/**
	 * Tests to see if the setter works properly, Park#setState().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testSetState() {
		myPark.setState("OR");
		assertEquals(myPark.getState(), "OR");
	}


	/**
	 * Tests to see if the getter works properly, Park#getZipcode().
	 *
	 * @author Peter Park
	 */
	@Test
	public void testGetZipcode() {
		assertEquals(myPark.getZipcode(), "12345");
	}

	/**
	 * Tests to see if the setter works properly, Park#setZipcode(String).
	 *
	 * @author Peter Park
	 */
	@Test
	public void testSetZipcode() {
		myPark.setZipcode("55555");
		assertEquals(myPark.getZipcode(), "55555");
	}
}
