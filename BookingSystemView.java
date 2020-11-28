package airplaneBookingSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookingSystemView extends JFrame {
	
	private static String[] months = { "1", "2", "3", "4", "5", "6", "7", "8", "10", "11", "12" };
	private static String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private static String[] minutes = { "15", "30", "45", "0" };
	private static String[] hour = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "0" };
	
	int screenNumber;
	BookingSystem model;
	JPanel panel;

	public BookingSystemView(BookingSystem bs) {
		model = bs;
		screenNumber = 0;
		panel = new JPanel();
		this.add(panel);

		setTitle("BookingSystem GUI");
		setSize(500, 500);
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
		JLabel fromMonth = new JLabel("Departure Month: ");
		JLabel fromDay = new JLabel("Departure Day: ");
		JLabel cityOfDeparture = new JLabel("City of Departure: ");
		JLabel destination = new JLabel("Destination: ");
		JLabel fromHour = new JLabel("Hour: ");
		JLabel fromMinute = new JLabel("Minute: ");

		JComboBox<String> departures = new JComboBox<String>(BookingSystem.DESTINATIONS);
		JComboBox<String> destinations = new JComboBox<String>(BookingSystem.DESTINATIONS);
		JComboBox<String> departureMonth = new JComboBox<String>(months);
		JComboBox<String> departureDay = new JComboBox<String>(days);
		JComboBox<String> departureHour = new JComboBox<String>(hour);
		JComboBox<String> departureMinute = new JComboBox<String>(minutes);

		JButton confirm = new JButton("Confirm");

		panel.add(cityOfDeparture);
		panel.add(departures);

		panel.add(destination);
		panel.add(destinations);

		panel.add(fromMonth);
		panel.add(departureMonth);

		panel.add(fromDay);
		panel.add(departureDay);

		panel.add(fromHour);
		panel.add(departureHour);

		panel.add(fromMinute);
		panel.add(departureMinute);

		panel.add(confirm);
		panel.setLayout(new FlowLayout());
		System.out.println("confirm button added");
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
		ArrayList<Airplane> arr = model.getFilteredAirplanes();
        Airplane[] airplanes = new Airplane[arr.size()];
        for(int i = 0; i < airplanes.length; i++) {
            airplanes[i] = model.getFilteredAirplanes().get(i);
        }
        JLabel flightSelect = new JLabel("Select a flight ");

        JComboBox<Airplane> listOfFlights = new JComboBox<>(airplanes);

        JButton confirm =  new JButton("Confirm");

        panel.add(flightSelect);
        panel.add(listOfFlights);
        panel.add(confirm);
        panel.setLayout(new FlowLayout());
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

		System.out.println("slide 2 ran");
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
		System.out.println("slide 3 ran");
		JButton confirm = new JButton("ConfirmSeatSelectScreen");
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