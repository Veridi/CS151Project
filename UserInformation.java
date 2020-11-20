  
package airplaneBookingSystem;

public class UserInformation {
    private String name;
    private int age;

    public UserInformation(String name, int age){
        this.name = name;
        this.age = age;
    }

    
    @Override
    public String toString() {
    	String str = name + " " + age;;
    	return str;
    }
    
}