package airplaneBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class BookingSystem {

	private static final String[] DESTINATIONS = new String[] { "OAKLAND", "SAN_JOSE", "SACRAMENTO" };

	static ArrayList<Airline> airlines;
	UserInformation userInfo;
	Airplane chosenAirplane;
	static Scanner sc;
	
	private static LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>(1);
    private static BookingSystemView view;
    private static Airplane airplane;
    

	public static void main(String[] args) {
		
		BookingSystemView bsv = new BookingSystemView(queue);
		view = BookingSystemView.init(queue);
		airplane = new Airplane();
		Controller controller = new Controller(view, airplane, queue);

        controller.mainLoop();
        view.dispose();
        queue.clear();
		/*
		airlines = new ArrayList<>();
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
									Airplane ap = new Airplane(from, to, date);
									ap.randomizeSeats();
									airline.addAirplane(ap);
									// airplane.randomizeSeats();
								}
							}
						}
					}
				}
			}
		}

		BookingSystem bs = new BookingSystem();
		sc = new Scanner(System.in);
		bs.selectUserFlight();
		bs.getUserInformation();
		bs.selectSeats();
		// confirm / printout ticket
		sc.close();
		*/
	}
	
	
	public void selectSeats() {
		
		
		for(int i = 0; i < userInfo.getNumberOfPassengers(); i++) {
			System.out.println("Choose a seat for passenger #" + (i+1));
			//print seats
			System.out.println("Available seats are marked with an 'O', 'X' are unavailable");
			chosenAirplane.printSeats();
			System.out.print("Enter chosen row: ");
			int chosenRow = sc.nextInt();
			System.out.print("Enter chosen column: ");
			int chosenCol = sc.nextInt();
			//check if selected seat is actually available
			//update the selected seat to unavailable
		}
		
		
		sc.close();
	}

	public void getUserInformation() {
		System.out.println("Please input the number of passengers.");
		int numberOfPassengers = sc.nextInt();
		String[] passengerNames = new String[numberOfPassengers];
		int[] passengerAge = new int[numberOfPassengers];
		for(int i = 0; i < numberOfPassengers; i++) {
			System.out.println("Enter passenger #" + (i + 1) + "'s first and last name.");
			//String name = sc.nextLine();
			String firstName = sc.next();
			String lastName = sc.next();
			passengerNames[i] = firstName + " " + lastName;
			System.out.println(passengerNames[i]);
			System.out.println("Enter passenger #" + (i + 1) + "'s age");
			int age = sc.nextInt();
			passengerAge[i] = age;
		}
		userInfo = new UserInformation(numberOfPassengers, passengerNames, passengerAge);
	}

	public void selectUserFlight() {

		// think about getting more user information such as number of passengers,
		// preferred seating(?)

		System.out.println("Choose a city to fly from: " + listOfCities());
		String from = sc.nextLine();
		System.out.println("Choose a city to fly to: " + listOfCities());
		String to = sc.nextLine();
		System.out.println("Pick a date in this format: MM, DD, Hour(0-24), Minute");
		String d = sc.nextLine();
		System.out.println("Searching for flight within parameters...");

		Date date = new Date(d);

		// looks into database of airlines and grab all flights on the same day
		ArrayList<Airplane> allAirplanes = new ArrayList<>();
		for (Airline airline : airlines) {
			allAirplanes.addAll(airline.getAirplanes());
		}
		ArrayList<Airplane> filteredByDestination = Airline.filterDestination(allAirplanes, from, to);
		ArrayList<Airplane> filteredAirplanes = Airline.filterDate(filteredByDestination, date);
		Collections.sort(filteredAirplanes); // implement compareTo for Airplane and Date

		System.out.println("Here are the airplanes on the same day. Please type in the number of the desired flight.");
		for (int i = 1; i <= filteredAirplanes.size(); i++) {
			System.out.println(i + ": " + filteredAirplanes.get(i - 1));
		}
		int chosenNumber = sc.nextInt();
		chosenAirplane = filteredAirplanes.get(chosenNumber - 1);
		System.out.println("You have chosen airplane #" + chosenNumber + ": " + chosenAirplane);
		
		//chosenAirplane.printSeats();
		
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
