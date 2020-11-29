package airplaneBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BookingSystem {

	public static final String[] DESTINATIONS = new String[] { "OAKLAND", "SAN_JOSE", "SACRAMENTO" };

	static ArrayList<Airline> airlines;
	ArrayList<Airplane> filteredAirplanes;
	UserInformation userInfo;
	Airplane chosenAirplane;
	String chosenFrom;
	String chosenTo;
	Date chosenDate;
	static Scanner sc;
	
	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>(1);
	private static BookingSystemView view;

	public static void main(String[] args) {

		BookingSystem bs = new BookingSystem();
		
		view = BookingSystemView.init(bs, queue);
		Controller controller = new Controller(view, queue);
		
		controller.mainLoop();
		view.dispose();
		queue.clear();
		//sc = new Scanner(System.in);
		//bs.selectUserFlight();
		//bs.getUserInformation();
		//bs.selectSeats();
		// confirm / printout ticket
		//sc.close();

	}

	public BookingSystem() {
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

	}

	public void updateSeats(int chosenRow, int chosenCol) {
		chosenAirplane.takeSeat(chosenRow, chosenCol);
	}

//	public void selectSeats() {
//		System.out.println("Choose a seat for passenger");
//		// print seats
//		System.out.println("Available seats are marked with an 'O', 'X' are unavailable");
//		chosenAirplane.printSeats();
//		System.out.print("Enter chosen row: ");
//		int chosenRow = sc.nextInt();
//		System.out.print("Enter chosen column: ");
//		int chosenCol = sc.nextInt();
//		// check if selected seat is actually available
//		// update the selected seat to unavailable
//	}

	public void updateUserInformation(String passengerName, int passengerAge) {
		userInfo = new UserInformation(passengerName, passengerAge);
	}

//	public void getUserInformation() {
//		String passengerNames;
//		int passengerAge;
//		System.out.println("Enter passenger's first and last name.");
//		// String name = sc.nextLine();
//		String firstName = sc.next();
//		String lastName = sc.next();
//		passengerNames = firstName + " " + lastName;
//		System.out.println(passengerNames);
//		System.out.println("Enter passenger's age");
//		int age = sc.nextInt();
//		passengerAge = age;
//		userInfo = new UserInformation(passengerNames, passengerAge);
//
//	}
	
	public void updateFlightInformation(String from, String to, String month, String day, String hour, String minute) {
		int chosenMonth = Integer.parseInt(month);
		int chosenDay = Integer.parseInt(day);
		int chosenHour = Integer.parseInt(hour);
		int chosenMinute = Integer.parseInt(minute);
		chosenFrom = from;
		chosenTo = to;
		chosenDate = new Date(chosenMonth, chosenDay, chosenHour, chosenMinute);
		findReasonableFlight(chosenDate, chosenFrom, chosenTo);
	}
	
	public void findReasonableFlight(Date date, String from, String to) {
		ArrayList<Airplane> allAirplanes = new ArrayList<>();
		for (Airline airline : airlines) {
			allAirplanes.addAll(airline.getAirplanes());
		}
		ArrayList<Airplane> filteredByDestination = Airline.filterDestination(allAirplanes, chosenFrom, chosenTo);
		
		Collections.sort(filteredByDestination); // implement compareTo for Airplane and Date
		filteredAirplanes = Airline.filterDate(filteredByDestination, chosenDate);
		
		
		//prints list of reasonable flights
//		System.out.println("Here are the airplanes on the same day. Please type in the number of the desired flight.");
//		for (int i = 1; i <= filteredAirplanes.size(); i++) {
//			System.out.println(i + ": " + filteredAirplanes.get(i - 1));
//		}
	}
	
	public void updateChosenFlight(Airplane airplane) {
		chosenAirplane = airplane;
	}
	
	public ArrayList<Airplane> getFilteredAirplanes(){
		return filteredAirplanes;
	}
	
	public Airplane getChosenAirplane() {
		return chosenAirplane;
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