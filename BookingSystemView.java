package airplaneBookingSystem;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

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
	private static String[] minutes = { "0", "15", "30", "45" };
	private static String[] hour = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23" };
	public int row;
	public int col;

	int screenNumber;
	BookingSystem model;
	JPanel panel;

	private JFrame bookingFrame;
	private BlockingQueue<Message> queue;
	private List<Valve> valves = new LinkedList<Valve>();
	private BookingSystemView view;

	public static BookingSystemView init(BookingSystem bs, BlockingQueue<Message> queue) {
		return new BookingSystemView(bs, queue);
	}

	public BookingSystemView(BookingSystem bs, BlockingQueue<Message> queue) {
		model = bs;
		screenNumber = 0;
		panel = new JPanel();
		this.add(panel);

		this.queue = queue;

		setTitle("BookingSystem GUI");
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// showNextSlide();

	}

	public interface Valve {
		/**
		 * Performs certain action in response to message
		 */
		public ValveResponse execute(Message message);
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
		c.insets = new Insets(0, 0, 0, 0); // external padding (in pixels) top, left, bottom, right
		c.ipadx = 5; // internal padding (in pixels)
		c.ipady = 5;

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

		confirm.addActionListener(event -> {
			try {
				String departureLocation = (String) departures.getSelectedItem();
				String arrivalLocation = (String) destinations.getSelectedItem();
				String departMonth = (String) departureMonth.getSelectedItem();
				String departDay = (String) departureDay.getSelectedItem();
				String departHour = (String) departureHour.getSelectedItem();
				String departMinute = (String) departureMinute.getSelectedItem();
				boolean date = true;
				model.updateFlightInformation(departureLocation, arrivalLocation, departMonth, departDay, departHour,
						departMinute);
				if ((departMonth == "2" && (departDay == "29" || departDay == "30" || departDay == "31"))) {
					date = false; // skip feb 29th, 30th, 31st
				} else if (departDay == "31"
						&& (departMonth == "4" || departMonth == "6" || departMonth == "9" || departMonth == "11")) {
					date = false; // skip months without 31st
				}
				if (departureLocation != arrivalLocation && date == true) {
					queue.put(new NewBookingMessage());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	public void flightSelectScreen() {
		ArrayList<Airplane> arr = model.getFilteredAirplanes();
		Airplane[] airplanes = new Airplane[arr.size()];
		for (int i = 0; i < airplanes.length; i++) {
			airplanes[i] = model.getFilteredAirplanes().get(i);
		}
		JLabel flightSelect = new JLabel("Select a flight:");
		JComboBox<Airplane> listOfFlights = new JComboBox<>(airplanes);
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Airplane flight = (Airplane) listOfFlights.getSelectedItem();
				model.updateChosenFlight(flight);
				showNextSlide();
			}
		});

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0); // external padding (in pixels) top, left, bottom, right
		c.ipadx = 5; // internal padding (in pixels)
		c.ipady = 5;

		c.gridx = 0;
		c.gridy = 0;
		panel.add(flightSelect, c);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(listOfFlights, c);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(confirm, c);

	}

	public void userInfoSelectScreen() {
		JLabel fullName = new JLabel("Full name: ");
		JLabel age = new JLabel("Age: ");

		JTextField nameBox = new JTextField(25);
		JTextField ageBox = new JTextField(25);

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(event -> {
			try {
				String name = nameBox.getText();
				if (name.length() == 0) {
					throw new Exception();
				}
				int newAge = Integer.parseInt(ageBox.getText());
				model.updateUserInformation(name, newAge);
				queue.put(new NewBookingMessage());
			} catch (Exception e) {
				JFrame errorFrame = new JFrame("Error Message");
				errorFrame.setSize(400, 400);
				JLabel errorText = new JLabel("Error. Please fill out the required fields.");
				errorFrame.add(errorText);
				errorFrame.pack();
				errorFrame.setVisible(true);
			}
		});

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0); // external padding (in pixels) top, left, bottom, right
		c.ipadx = 5; // internal padding (in pixels)
		c.ipady = 5;

		c.gridx = 0;
		c.gridy = 0;
		panel.add(fullName, c);

		c.gridx = 1;
		c.gridy = 0;
		panel.add(nameBox, c);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(age, c);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(ageBox, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		panel.add(confirm, c);

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
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel fullName = new JLabel("Passenger: ");
		JLabel flightSelect = new JLabel("Selected flight: ");
		JLabel row = new JLabel("Row: ");
		JLabel col = new JLabel("Col: ");

		JTextField nameBox = new JTextField(model.userInfo.toString());
		JTextField flight = new JTextField(model.chosenAirplane.toString());
		JTextField selectedRow = new JTextField(model.chosenAirplane.row);
		JTextField selectCol = new JTextField(model.chosenAirplane.col);

		c.gridx = 0;
		c.gridy = 0;
		panel.add(fullName);
		panel.add(nameBox);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(flightSelect);
		panel.add(flight);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(row);
		panel.add(selectedRow);

		c.gridx = 0;
		c.gridy = 3;
		panel.add(col);
		panel.add(selectCol);
	}

	public void printTicketScreen() {
		// "your ticket has been booked"
		// click print button to output to System.out.println
		// click close to close

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10); // top left bottom right
		
		JLabel prompt = new JLabel("Your flight has been booked. Thank you for using our Flight Booking System.");

		JButton bookButton = new JButton("Book");
		bookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Passenger: " + model.userInfo);
				System.out.println("Airplane: " + model.chosenAirplane);
				System.out.println("Seat: " + model.chosenAirplane.row + ", " + model.chosenAirplane.col);
				
				JButton backButton = new JButton("Back");
				c.gridx = 2;
				c.gridy = 1;
				panel.add(backButton, c);
				backButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//GO BACK TO FIRST SCREEN
					}
				});

			}
		});

		JButton closeButton = new JButton("Close");
		panel.add(closeButton);
		panel.setLayout(new FlowLayout());
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		panel.add(prompt, c);
		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = 1;
		panel.add(bookButton, c);

		c.gridx = 1;
		c.gridy = 1;
		panel.add(closeButton, c);
	}
}