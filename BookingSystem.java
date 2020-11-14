package airplaneBookingSystem;

public class BookingSystem {
	
	private static final String[] DESTINATIONS = new String[] {"OAKLAND", "SAN_JOSE", "SACRAMENTO"};
	
	public static void main(String[] args) {
		Airline a1 = new Airline("United");
		Airline a2 = new Airline("Delta");
		
		Airplane airplane1 = new Airplane(DESTINATIONS[0], DESTINATIONS[1], new Date(11, 14, 9, 0));
		
		a1.addAirplane(airplane1);
		
//		System.out.println(airplane1.hashCode());
		//ask for user info
		//Scanner sc to scan input from user
	}
}
