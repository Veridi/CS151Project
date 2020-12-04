package airplaneBookingSystem;

import org.junit.*;
import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void testHashCode(){
        Date day = new Date(1,2,3,15);
        assertEquals("Hash should be 21", 21, day.hashCode());
    }

    @Test
    public void testToString(){
        Date day = new Date(4,3,2,15);
        assertEquals("Date should be 4/3 at 02:15", "4/3 02:15", day.toString());
    }

    @Test
    public void testEqualsDay(){
        Date day = new Date(1,2,3,15);
        Date day2 = new Date(1,2,3,15);
        Date day3 = new Date(3,4,1,30);

        assertTrue("They should return true since they are equal", day.equalsDay(day2));
        assertFalse("They should return false since they are not equal", day.equalsDay(day3));
    }
    
    @Test
    public void testCompareTo() {
    	Date day1 = new Date(1, 2, 3, 15);
    	Date day2 = new Date(1, 2, 3, 15);
    	Date day3 = new Date(0, 4, 4, 30);
    	Date day4 = new Date(2, 0, 0, 0);
    	
    	assertEquals("Should return 0", 0, day1.compareTo(day2));
    	assertEquals("Should return 1", 1, day1.compareTo(day3));
    	assertEquals("Should return -1", -1, day1.compareTo(day4));
    }
}