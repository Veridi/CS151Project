package airplaneBookingSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import airplaneBookingSystem.BookingSystemView.Valve;

public class Controller {
	private BlockingQueue<Message> queue;
	private BookingSystemView view;
	
	private List<Valve> valves = new LinkedList<Valve>();
	
	
	public Controller(BookingSystemView view, BlockingQueue<Message> queue) {
		this.view = view;
		this.queue = queue;
		//valves.add(new Confirm());
		try {
			queue.put(new NewBookingMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mainLoop() {
		ValveResponse response = ValveResponse.START;
		Message message = null;
		while(response != ValveResponse.FINISH) {
			try {
				message = queue.take();
				view.showNextSlide();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
