package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import backend.Park;
import backend.ParkManager;

public class ParkRainyDay {
	
    /**
     * This rule allows us to test exception handling.
     * @author Peter Park
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();
	
	private ParkManager myManager;
	private Park myPark;

	@Before
	public void setUp() {
        myManager = new ParkManager("Someone@gmail.com", "1112223333", "Someone");
	}
	
//	@Test
//	public void testPark() {
//	    exception.expect(IllegalArgumentException.class);
//	    exception.expectMessage("Parameters hold an incorrect class");
//        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
//	}
	
	@Test
	public void testGetManager() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		assertEquals("Someone@gmail.com", myPark.getManager().getUsername());
	}

	@Test
	public void testSetManager() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		ParkManager newManager = new ParkManager("NewGuy@gmail.com", "2223334444", "NewGuy");
		myPark.setManager(newManager);
		assertEquals(myPark.getManager(), newManager);
	}
	
	@Test
	public void testGetName() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		assertEquals(myPark.getName(), "University Park");
	}

	@Test
	public void testSetName() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		myPark.setName("NewName Park");
		assertEquals(myPark.getName(), "NewName Park");
	}
	
	@Test
	public void testGetCity() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		System.out.println(myPark.getCity());
	}

	@Test
	public void testSetCity() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		myPark.setCity("Seattle");
		assertEquals(myPark.getCity(), "Seattle");	}
	
	@Test
	public void testGetZipcode() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		assertEquals(myPark.getZipcode(), "12345");
	}

	@Test
	public void testSetZipcode() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		myPark.setZipcode("55555");
		assertEquals(myPark.getZipcode(), "55555");	}

	@Test
	public void testEqualsPark() {
        myPark = new Park(myManager, "University Park", "Tacoma", "12345");
		Park samePark = new Park(myPark.getManager(), myPark.getName(), myPark.getCity(), myPark.getZipcode());
        assertEquals(samePark.equals(myPark), true);
	}

}
