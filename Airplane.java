package airplaneBookingSystem;

public class Airplane {
	
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
	
	//methods for seats
}
