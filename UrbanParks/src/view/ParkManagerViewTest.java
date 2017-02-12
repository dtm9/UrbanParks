/**
 * 
 */
package view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.Datastore;
import backend.ParkManager;

/**
 * @author dylan
 *
 */
public class ParkManagerViewTest {
	private ParkManager testParkManager;
	private ParkManagerView testParkManagerView;
	private Datastore testDatastore;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testParkManager = new ParkManager("John", "5555555555", "Josh");
		testDatastore = new Datastore();
		testParkManagerView = new ParkManagerView(testParkManager, testDatastore);
		
	} 

	@Test
	public final void launchGUI_goodCases_runsParkManagerView() {
		
	}

}
