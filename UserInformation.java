  
package airplaneBookingSystem;

public class UserInformation {
    private String name;
    private int age;

    public UserInformation(String name, int age){
        this.name = name;
        this.age = age;
    }

    
    public String getName() {
    	return name;
    }
    
    public int getAge() {
    	return age;
    }
    
    @Override
    public String toString() {
    	String str = "";
    		str += "Passenger : " + name + ", " + age + " years old.\n";
    	return str;
    }
    
}