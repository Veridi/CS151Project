package airplaneBookingSystem;

import java.util.Random;

public class Airplane implements Comparable<Airplane> {

	private String from;
	private String to;
	private Date date;
	private boolean[][] seats;
	
	public Airplane() {
		
	}

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
		return 0;
	}

	public void randomizeSeats() {
		Random r = new Random();
		for (int row = 0; row < seats.length; row++) {
			for (int col = 0; col < seats[row].length; col++) {
				seats[row][col] = r.nextBoolean();
			}
		}
	}

	public void printSeats() {
		// seats[row][col] == true means taken;
		// seats[row][col] == false means available;
		// 00 01 02 03 04 05 06 07 08 09 10 ...
		// 00 X O ...
		// 01 O X ...
		// 02 O O ...
		// 03 X O ...
		// 04 X X ...
		String initialRow = "    ";
		for (int i = 0; i < seats[0].length; i++) {
			if (i < 10) {
				initialRow += "0";
			}
			initialRow += i + "  ";
			
		}
		System.out.println(initialRow);
		for (int row = 0; row < seats.length; row++) {
			String out = "";
			if (row < 10) {
				out += "0";
			}
			out += row;
			for (int col = 0; col < seats[row].length; col++) {
				if (seats[row][col]) {
					out += "   " + "X";
				} else {
					out += "   " + "O";
				}
			}
			System.out.println(out);
		}

	}

	public void takeSeat(int chosenRow, int chosenCol) {
		seats[chosenRow][chosenCol] = true;
		
	}
}
