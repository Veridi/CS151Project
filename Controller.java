package airplaneBookingSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Controller {
	private BlockingQueue<Message> queue;
	private BookingSystemView view;
	
	private List<Valve> valves = new LinkedList<Valve>();
	
	public Controller(BookingSystemView view, BlockingQueue<Message> queue) {
		this.view = view;
		this.queue = queue;
		valves.add(new NewBooking());
		valves.add(new QuitBooking());
		//valves.add(new Confirm());
		try {
			queue.put(new ConfirmMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mainLoop() {
		ValveResponse response = ValveResponse.START;
		Message message = null;
		while(response != ValveResponse.QUIT) {
			try {
				message = queue.take();
				view.showNextSlide();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (Valve valve : valves) {
				response = valve.execute(message);
				if (response == ValveResponse.FINISH) {
					response = ValveResponse.START;
					view.setScreenNumber(0);
					view.showNextSlide();
				} else if (response == ValveResponse.QUIT) {
					System.exit(0);
				}
			}
		}
	}

	public interface Valve {
		/**
		 * Performs certain action in response to message
		 */
		public ValveResponse execute(Message message);
	}
	
	private class NewBooking implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message instanceof NewBookingMessage) {
				return ValveResponse.FINISH;
			}
			return ValveResponse.CONFIRM;
		}
	}
	
	private class QuitBooking implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message instanceof QuitMessage) {
				return ValveResponse.QUIT;
			}
			return ValveResponse.CONFIRM;
		}
	}
}
