package airplaneBookingSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {
	private LinkedBlockingQueue<Message> queue;
	private BookingSystemView view;
	private Airplane model;
	
	private List<Valve> valves = new LinkedList<Valve>();
	
	public Controller(BookingSystemView view, Airplane model, LinkedBlockingQueue<Message> queue) {
		this.view = view;
		this.model = model;
		this.queue = queue;
		valves.add(new Confirm());
	}
	
	public void mainLoop() {
		ValveResponse response = ValveResponse.START;
		Message message = null;
		while(response != ValveResponse.CONFIRM) {
			try {
				message = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (Valve valve : valves) {
				response = valve.execute(message);
				if (response != ValveResponse.CONFIRM) {
					break;
				}
			}
		}
	}

	private interface Valve {
		/**
		 * Performs certain action in response to message
		 */
		public ValveResponse execute(Message message);
	}
	
	private class Confirm implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewBookingMessage.class) {
				return ValveResponse.CONFIRM;
			}
			return ValveResponse.FINISH;
		}
	}
}
