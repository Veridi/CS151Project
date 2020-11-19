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
	
	private void removeAirplane(int hash) {
		for(Airplane a : airplanes) {
			if(a.hashCode() == hash) {
				airplanes.remove(a);
			}
		}
	}

	public void editAirplane(int hash, Airplane a){
		removeAirplane(hash);
		addAirplane(a);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
