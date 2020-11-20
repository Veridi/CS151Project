package airplaneBookingSystem;

public class Ticket {

    private Airplane a;
    private UserInformation info;

    public Ticket(Airplane a, UserInformation uinfo){
        this.a = a;
        info = uinfo;
    }

    public void updateTicket(Airplane a){
        this.a = a;
    }

    @Override
    public String toString() {
    	String str = "";
    	str += a.toString() + info.toString();
    	return str;
    }
    
    
}