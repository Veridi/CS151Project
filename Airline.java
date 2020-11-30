package airplaneBookingSystem;

import java.util.ArrayList;

public class Airline {
	private String name;
	private ArrayList<Airplane> airplanes;

	public Airline(String name) {
		this.name = name;
		airplanes = new ArrayList<Airplane>();

	}

	public void addAirplane(Airplane a) {
		airplanes.add(a);
	}

	public ArrayList<Airplane> getAirplanes() {
		return airplanes;
	}

	public static ArrayList<Airplane> filterDestination(ArrayList<Airplane> listOfAirplanes, String from, String to) {
		ArrayList<Airplane> filteredAirplanes = new ArrayList<Airplane>();
		for (Airplane airplane : listOfAirplanes) {
			if (airplane.getFrom().equalsIgnoreCase(from) && airplane.getTo().equalsIgnoreCase(to)) {
				filteredAirplanes.add(airplane);
			}
		}
		return filteredAirplanes;
	}

	public static ArrayList<Airplane> filterDate(ArrayList<Airplane> listOfAirplanes, Date date) {
		ArrayList<Airplane> filteredAirplanes = new ArrayList<Airplane>();
		for (Airplane airplane : listOfAirplanes) {
			if (airplane.getDate().equalsDay(date)) {
				filteredAirplanes.add(airplane);
			}
		}
		return filteredAirplanes;
	}

	@Override
	public String toString() {
		return name + ", " + airplanes.size() + " flights this year.";
	}
}
