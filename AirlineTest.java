package airplaneBookingSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class AirlineTest {

	@Test
	public void testAddAirplane() {
		Airline a = new Airline("testAirline");
		assertEquals("Should return 0 since no airplanes were added", 0, a.getAirplanes().size());
	}

	@Test
	public void testGetAirplanes() {
		Airline a = new Airline("testAirline");
		a.addAirplane(new Airplane("SAN_JOSE", "OAKLAND", new Date(12, 23, 9, 15)));
		assertNotNull("Should return a not null airplane", a.getAirplanes());
	}

	@Test
	public void testToString() {
		Airline a = new Airline("testAirline");
		assertEquals("Should return airplane name with number of flights", "testAirline, 0 flights this year.",
				a.toString());
	}
}
