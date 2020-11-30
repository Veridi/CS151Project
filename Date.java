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
		if(month < o.month) {
			return -1;
		}else if(month > o.month) {
			return 1;
		}else {
			if(day < o.day) {
				return -1;
			}else if(day > o.day) {
				return 1;
			}else {
				if(hour < o.hour) {
					return -1;
				}else if(hour > o.hour) {
					return 1;
				}else {
					if(minute < o.minute) {
						return -1;
					}else if(minute > o.minute) {
						return 1;
					}else {
						return 0;
					}
				}
			}
		}
	}
}

