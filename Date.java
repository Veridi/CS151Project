package airplaneBookingSystem;

public class Date {
	
	private int month;
	private int day;
	private int hour;
	private int minute;
	public Date(int month, int day, int hour, int minute) {
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += month;
		hash += day;
		hash += hour;
		hash += minute;
		return hash;
	}
}
