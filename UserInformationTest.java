package airplaneBookingSystem;

import org.junit.*;
import static org.junit.Assert.*;

public class UserInformationTest {
	
	@Test
	public void testGetName() {
		UserInformation userInformation = new UserInformation("Mike", 22);
		assertEquals(userInformation.getName(), "Mike");
	}
	
	@Test
	public void testGetAge() {
		UserInformation userInformation = new UserInformation("Mike", 22);
		assertEquals(userInformation.getAge(), 22);
	}
	
	@Test
	public void testToString() {
		UserInformation userInformation = new UserInformation("Mike", 22);
		assertEquals(userInformation.toString(), "Mike, 22 years old.");
	}
}
