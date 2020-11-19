package airplaneBookingSystem;

public class Ticket {

    private Airplane a;

    public Ticket(Airplane a){
        this.a = a;
    }

    public void updateTicket(Airplane a){
        this.a = a;
    }

    public void assignTicket(UserInformation user){

    }
}
