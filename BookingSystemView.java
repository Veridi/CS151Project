package airplaneBookingSystem;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class BookingSystemView extends JFrame {

	private static String[] months = { "1", "2", "3", "4", "5", "6", "7", "8", "10", "11", "12" };
	private static String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private static String[] minutes = { "15", "30", "45", "0" };
	private static String[] hour = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "0" };
	public int row;
	public int col;

	int screenNumber;
	BookingSystem model;
	JPanel panel;

	public BookingSystemView(BookingSystem bs) {
		model = bs;
		screenNumber = 0;
		panel = new JPanel();
		this.add(panel);

		setTitle("BookingSystem GUI");
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		showNextSlide();

	}

	public void showNextSlide() {
		panel.removeAll();
		if (screenNumber == 0) {
			dateAndDestinationSelectScreen();
		} else if (screenNumber == 1) {
			flightSelectScreen();
		} else if (screenNumber == 2) {
			userInfoSelectScreen();
		} else if (screenNumber == 3) {
			seatSelectScreen();
		} else if (screenNumber == 4) {
			confirmScreen();
		} else if (screenNumber == 5) {
			printTicketScreen();
		}
		panel.revalidate();
		panel.repaint();
		screenNumber++;
	}

	public void dateAndDestinationSelectScreen() {
		JLabel prompt1 = new JLabel("Select a departure and destination city:");
		JLabel cityOfDeparture = new JLabel("Departure City:");
		JLabel destination = new JLabel("Destination:");
		JLabel prompt2 = new JLabel("Select a date and time for your flight:");
		JLabel spacer = new JLabel("to");
		JLabel fromMonth = new JLabel("Month:");
		JLabel fromDay = new JLabel("Day:");
		JLabel fromHour = new JLabel("Hour:");
		JLabel fromMinute = new JLabel("Minute:");

		JComboBox<String> departures = new JComboBox<String>(BookingSystem.DESTINATIONS);
		JComboBox<String> destinations = new JComboBox<String>(BookingSystem.DESTINATIONS);
		JComboBox<String> departureMonth = new JComboBox<String>(months);
		JComboBox<String> departureDay = new JComboBox<String>(days);
		JComboBox<String> departureHour = new JComboBox<String>(hour);
		JComboBox<String> departureMinute = new JComboBox<String>(minutes);

		JButton confirm = new JButton("Confirm");

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
//		c.insets = new Insets(10, 10, 10, 10);  //external padding (in pixels)
		c.ipadx = 10; // internal padding (in pixels)
		c.ipady = 10;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(prompt1, c);
		c.gridwidth = 1;

		c.gridx = 1;
		c.gridy = 1;
		panel.add(cityOfDeparture, c);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(departures, c);

		c.gridx = 2;
		c.gridy = 2;
		panel.add(spacer, c);

		c.gridx = 3;
		c.gridy = 1;
		panel.add(destination, c);
		c.gridx = 3;
		c.gridy = 2;
		panel.add(destinations, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 5;
		panel.add(prompt2, c);
		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = 4;
		panel.add(fromMonth, c);
		c.gridx = 0;
		c.gridy = 5;
		panel.add(departureMonth, c);

		c.gridx = 1;
		c.gridy = 4;
		panel.add(fromDay, c);
		c.gridx = 1;
		c.gridy = 5;
		panel.add(departureDay, c);

		c.gridx = 3;
		c.gridy = 4;
		panel.add(fromHour, c);
		c.gridx = 3;
		c.gridy = 5;
		panel.add(departureHour, c);

		c.gridx = 4;
		c.gridy = 4;
		panel.add(fromMinute, c);
		c.gridx = 4;
		c.gridy = 5;
		panel.add(departureMinute, c);

		c.gridx = 2;
		c.gridy = 6;
		panel.add(confirm, c);

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String departureLocation = (String) departures.getSelectedItem();
				String arrivalLocation = (String) destinations.getSelectedItem();
				String departMonth = (String) departureMonth.getSelectedItem();
				String departDay = (String) departureDay.getSelectedItem();
				String departHour = (String) departureHour.getSelectedItem();
				String departMinute = (String) departureMinute.getSelectedItem();
				model.updateFlightInformation(departureLocation, arrivalLocation, departMonth, departDay, departHour,
						departMinute);

				showNextSlide();
			}
		});
	}

	public void flightSelectScreen() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		ArrayList<Airplane> arr = model.getFilteredAirplanes();
		Airplane[] airplanes = new Airplane[arr.size()];
		for (int i = 0; i < airplanes.length; i++) {
			airplanes[i] = model.getFilteredAirplanes().get(i);
		}
		JLabel flightSelect = new JLabel("Select a flight:");

		JComboBox<Airplane> listOfFlights = new JComboBox<>(airplanes);

		JButton confirm = new JButton("Confirm");

		c.gridx = 0;
		c.gridy = 0;
		panel.add(flightSelect, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(listOfFlights, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(confirm);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Airplane flight = (Airplane) listOfFlights.getSelectedItem();
				model.updateChosenFlight(flight);
				showNextSlide();
			}
		});
	}

	public void userInfoSelectScreen() {
		JLabel fullName = new JLabel("Full name: ");
		JLabel age = new JLabel("Age: ");

		JTextField nameBox = new JTextField(25);
		JTextField ageBox = new JTextField(25);

		JButton confirm = new JButton("Confirm");

		panel.add(fullName);
		panel.add(age);
		panel.add(nameBox);
		panel.add(ageBox);
		panel.add(confirm);

		panel.setLayout(new FlowLayout());

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameBox.getText();
				int age = Integer.parseInt(ageBox.getText());

				model.updateUserInformation(name, age);
				showNextSlide();
			}
		});
	}

	public void seatSelectScreen() {
		panel.setLayout(new GridBagLayout());

		JPanel seatsGridBag = new JPanel(new GridBagLayout());

		GridBagConstraints seatsConstraint = new GridBagConstraints();

		JLabel seatSelect = new JLabel("Please select a seat. X's means it is filled, O's are open.");

		Airplane chosenAirplane = model.getChosenAirplane();
		boolean[][] seats = chosenAirplane.getSeats();

		seatsConstraint.gridx = 0;
		seatsConstraint.gridy = 0;
		for (row = 0; row < seats.length; row++) {
			for (col = 0; col < seats[row].length; col++) {
				JToggleButton seatButton;
				if (seats[row][col] == true) { // seat unavailable
					seatButton = new JToggleButton("X");
					seatButton.setEnabled(false);
				} else { // seat available
					seatButton = new JToggleButton("O");
					seatButton.setEnabled(true);
					JToggleButtonAttachment attachment = new JToggleButtonAttachment(seatButton, row, col);
					seatButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (seatButton.isSelected()) {
								model.updateSeats(attachment.getX(), attachment.getY());
//								System.out.println("selected button: " + attachment.getX() + ", " + attachment.getY());
							}
						}
					});
				}
				seatsConstraint.gridx++;
				seatsGridBag.add(seatButton, seatsConstraint);
			}
			seatsConstraint.gridx = 0;
			seatsConstraint.gridy++;
		}

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNextSlide();
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 0, 10, 0); // top left bottom right
		c.gridx = 0;
		c.gridy = 0;
		panel.add(seatSelect, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(seatsGridBag, c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(confirm, c);
	}

	public void confirmScreen() {
		System.out.println("slide 4 ran");
		JButton confirm = new JButton("ConfirmConfirmScreen");
		panel.add(confirm);
		panel.setLayout(new FlowLayout());
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("confirm pressed");
				showNextSlide();
			}
		});
	}

	public void printTicketScreen() {
		System.out.println("slide 5 ran");
		JButton confirm = new JButton("Close");
		panel.add(confirm);
		panel.setLayout(new FlowLayout());
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("confirm pressed");
				setVisible(false);

			}
		});
	}
}