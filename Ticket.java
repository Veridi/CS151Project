package airplaneBookingSystem;

public class Ticket {

	private Airplane a;
	private UserInformation info;
	private int seatRow;
	private int seatCol;

	public Ticket(Airplane a, UserInformation uinfo, int row, int col) {
		this.a = a;
		info = uinfo;
		seatRow = row;
		seatCol = col;
	}

	@Override
	public String toString() {
		String str = "";
		str += "Passenger: " + info.toString() + "\nAirplane: " + a.toString() + "\nRow: " + seatRow + "\nCol: "
				+ seatCol;
		return str;
	}

}