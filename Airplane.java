package airplaneBookingSystem;

public class Airplane implements Comparable<Airplane>{
	
	private String from;
	private String to;
	private Date date;
	private boolean[][] seats;
	
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

	//should compare by destinations first, then date (can use compareTo in Date class)
	@Override
	public int compareTo(Airplane o) {
		return 0;
	}
	
	//methods for seats
	public void randomizeSeats() {
		// 
	}
}
