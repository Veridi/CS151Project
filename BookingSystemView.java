package airplaneBookingSystem;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookingSystemView extends JFrame {

	int slideNumber;
	
	public BookingSystemView() {
		

        add(new JLabel("Red"));
        JTextField redNumber = new JTextField(10);
        redNumber.setText("a");
        add(redNumber);
        
        add(new JLabel("Green"));
        JTextField greenNumber = new JTextField(10);
        greenNumber.setText("b");
        add(greenNumber);
        
        add(new JLabel("Blue"));
        JTextField blueNumber = new JTextField(10);
        blueNumber.setText("c");
        add(blueNumber);

        JButton update = new JButton("Update");
        add(update);
        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		slideNumber = 0;
		showSlide(slideNumber);
	}
	
	public void showSlide(int slideNumber) {
		switch(slideNumber){
			case 0 : flightSelectScreen();
			
		};
	}
	
	public void flightSelectScreen() {
		
	}
	
	public void userInfoSelectScreen() {
		
	}
	
	public void seatSelectScreen() {
		
	}
}
