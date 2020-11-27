  
package airplaneBookingSystem;

public class UserInformation {
    private String[] name;
    private int[] age;
    private int passengers;

    public UserInformation(int numPassengers, String[] name, int[] age){
    	passengers = numPassengers;
        this.name = name;
        this.age = age;
    }

    public int getNumberOfPassengers() {
    	return passengers;
    }
    
    public String getName(int passengerNumber) {
    	return name[passengerNumber];
    }
    
    public int getAge(int passengerNumber) {
    	return age[passengerNumber];
    }
    
    @Override
    public String toString() {
    	String str = "";
    	for(int i = 0; i < passengers; i++) {
    		str += "Passenger #" + (i+1) + ": " + name[i] + ", " + age[i] + " years old.\n";
    	}
    	return str;
    }
    
}