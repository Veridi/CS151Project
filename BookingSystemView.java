package airplaneBookingSystem;

import java.awt.FlowLayout;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookingSystemView extends JFrame {

	private JFrame bookingFrame;
	private static LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(1);
	
	public static BookingSystemView init(LinkedBlockingQueue<Message> queue) {
		return new BookingSystemView(queue);
	}
	
	BookingSystemView(LinkedBlockingQueue<Message> queue) {
		BookingSystemView.queue = queue;
		
		JButton newBooking = new JButton("Book a Flight");
		JButton confirm = new JButton("Confirm");
		
		newBooking.addActionListener(event -> {  // Start booking a flight
			try {
				BookingSystemView.queue.add(new NewBookingMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		confirm.addActionListener(event -> { // confirm
			try {
				BookingSystemView.queue.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		bookingFrame.add(newBooking);
		bookingFrame.add(confirm);
		bookingFrame.pack();
		bookingFrame.setLayout(new FlowLayout());
		bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bookingFrame.setVisible(true);
	}
}
