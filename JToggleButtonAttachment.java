package airplaneBookingSystem;

import javax.swing.JToggleButton;

public class JToggleButtonAttachment{
	JToggleButton button;
	int x;
	int y;
	public JToggleButtonAttachment(JToggleButton button, int x, int y) {
		this.button = button;
		this.x = x;
		this.y = y;
	}
	
	public JToggleButton getButton() {
		return button;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
