package airplaneBookingSystem;

public class Date implements Comparable<Date>{
	
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
	
	
	//str should be in this format: MM, DD, Hour(0-24), Minute
	public Date(String str) {
		String[] strArr = str.split(", ");
		this.month = Integer.parseInt(strArr[0]);
		this.day = Integer.parseInt(strArr[1]);
		this.hour = Integer.parseInt(strArr[2]);
		this.minute = Integer.parseInt(strArr[3]);
		System.out.println("selected date: " + month + " " + day + " " + hour + " " + minute);
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
	
	@Override
	public String toString() {
		String str = month + "/" + day + " ";
		if(hour < 10) {
			str += "0";
		}
		str += hour + ":";
		if(minute < 10) {
			str += "0";
		}
		str += minute;
		return str;
	}


	public boolean equalsDay(Date other) {
		if(month == other.month && day == other.day) {
			return true;
		}
		return false;
	}

	//should compare month > day > hour > minute
	@Override
	public int compareTo(Date o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

