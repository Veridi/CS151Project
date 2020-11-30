package airplaneBookingSystem;

import java.util.Random;

public class Airplane implements Comparable<Airplane> {

	private String from;
	private String to;
	private Date date;
	private boolean[][] seats;
	public String row;
	public String col;


	public Airplane(String from, String to, Date date) {
		this.from = from;
		this.to = to;
		this.date = date;
		seats = new boolean[6][30];
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += from.hashCode();
		hash += to.hashCode();
		hash += date.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		return "from " + from + " to " + to + " on " + date;
	}

	public Date getDate() {
		return date;
	}

	public boolean[][] getSeats(){
		return seats;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	// should compare by destinations first, then date (can use compareTo in Date
	// class)
	@Override
	public int compareTo(Airplane o) {
		int thisInt = from.hashCode();
		thisInt += to.hashCode();
		int thatInt = o.getFrom().hashCode();
		thatInt += o.getTo().hashCode();
		
		if(thisInt < thatInt) {
			return -1;
		}else if(thisInt > thatInt) {
			return 1;
		}else {
			return this.getDate().compareTo(o.getDate());
		}
	}

	public void randomizeSeats() {
		Random r = new Random();
		for (int row = 0; row < seats.length; row++) {
			for (int col = 0; col < seats[row].length; col++) {
				seats[row][col] = r.nextBoolean();
			}
		}
	}

	public void takeSeat(int chosenRow, int chosenCol) {
		row = Integer.toString(chosenRow);
		col = Integer.toString(chosenCol);
		seats[chosenRow][chosenCol] = true;
	}
}
