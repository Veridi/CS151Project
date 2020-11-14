package airplaneBookingSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BookingSystem {

	private static final String[] DESTINATIONS = new String[] { "OAKLAND", "SAN_JOSE", "SACRAMENTO" };

	public static void main(String[] args) {

		ArrayList<Airline> airlines = new ArrayList<>();
		airlines.add(new Airline("United Airlines"));
		airlines.add(new Airline("Delta Airlines"));
		airlines.add(new Airline("American Airlines"));

		// adds an airplane for a combination for every from and to combination with 2 - 5 flights every day
		Random r = new Random();
		for (int month = 1; month <= 12; month++) {
			for (int day = 1; day <= 31; day++) {
				if ((month == 2 && (day == 29 || day == 30 || day == 31))) {
					continue; // skip feb 29th, 30th, 31st
				} else if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
					continue; // skip months without 31st
				} else {
					// all airlines will get a few (2 - 5 flights at a random time for every
					// combination of from and to destinations
					for (Airline airline : airlines) {
						for (String from : DESTINATIONS) {
							for (String to : DESTINATIONS) {
								int numberOfFlights = 2 + r.nextInt(3); //gives random 2 - 5 flights
								for (int i = 0; i < numberOfFlights; i++) {
									int hour = r.nextInt(24);
									int minute = (int) Math.round(r.nextDouble() * 3) * 15; // gives 0, 15, 30, or 45
									Date date = new Date(month, day, hour, minute);
									airline.addAirplane(new Airplane(from, to, date));
								}
							}
						}
					}
				}
			}
		}

		
		

		BookingSystem bs = new BookingSystem();
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Choose a city to fly from: " + bs.listOfCities());
		String from = sc.nextLine();
		System.out.println("Choose a city to fly to: " + bs.listOfCities());
		String to = sc.nextLine();
		System.out.println("Pick a date in this format: MM, DD, Hour(0-24), Minute");
		String date = sc.nextLine();
		System.out.println("Searching for flight within parameters...");

		
		sc.close();
	}

	public String listOfCities() {
		String str = "";
		for (int i = 0; i < DESTINATIONS.length; i++) {
			str += DESTINATIONS[i];
			if (i < DESTINATIONS.length - 1) {
				str += ", ";
			}
		}
		return str;
	}
}
