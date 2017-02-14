package fixedTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatastoreTest.class, JobTest.class, ParkManagerTest.class, ParkTest.class, VolunteerTest.class })
public class AllTests {

}
