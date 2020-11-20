package airplaneBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class BookingSystem {

	private static final String[] DESTINATIONS = new String[] { "OAKLAND", "SAN_JOSE", "SACRAMENTO" };

	public static void main(String[] args) {

		ArrayList<Airline> airlines = new ArrayList<>();
		airlines.add(new Airline("United Airlines"));
		airlines.add(new Airline("Delta Airlines"));
		airlines.add(new Airline("American Airlines"));

		// adds an airplane for a combination for every from and to combination with 2 -
		// 5 flights every day
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
								int numberOfFlights = 2 + r.nextInt(3); // gives random 2 - 5 flights
								for (int i = 0; i < numberOfFlights; i++) {
									int hour = r.nextInt(24);
									int minute = (int) Math.round(r.nextDouble() * 3) * 15; // gives 0, 15, 30, or 45
									Date date = new Date(month, day, hour, minute);
									airline.addAirplane(new Airplane(from, to, date));
									//airplane.randomizeSeats();
								}
							}
						}
					}
				}
			}
		}

		BookingSystem bs = new BookingSystem();
		Scanner sc = new Scanner(System.in);
		
		//think about getting more user information such as number of passengers, preferred seating(?)

		System.out.println("Choose a city to fly from: " + bs.listOfCities());
		String from = sc.nextLine();
		System.out.println("Choose a city to fly to: " + bs.listOfCities());
		String to = sc.nextLine();
		System.out.println("Pick a date in this format: MM, DD, Hour(0-24), Minute");
		String d = sc.nextLine();
		System.out.println("Searching for flight within parameters...");

		Date date = new Date(d);
		
		// looks into database of airlines and grab all flights on the same day
		ArrayList<Airplane> allAirplanes = new ArrayList<>();
		for(Airline airline : airlines) {
			allAirplanes.addAll(airline.getAirplanes());
		}
		ArrayList<Airplane> filteredByDestination = Airline.filterDestination(allAirplanes, from, to);
		ArrayList<Airplane> filteredAirplanes = Airline.filterDate(filteredByDestination, date);
		Collections.sort(filteredAirplanes); // implement compareTo for Airplane and Date
		
		System.out.println("Here are the airplanes on the same day. Please type in the number of the desired flight.");
		for(int i = 1; i <= filteredAirplanes.size(); i++) {
			System.out.println(i + ": " + filteredAirplanes.get(i - 1));
		}
		int chosenNumber = sc.nextInt();
		Airplane chosenAirplane = filteredAirplanes.get(chosenNumber - 1);
		System.out.println("You have chosen airplane #" + chosenNumber + ": " + chosenAirplane);
		
		// get user's information: # of passengers, names, age 
		// select seats (based on # of passengers) in desired airplane
		// confirm / printout ticket
		
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
