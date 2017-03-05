import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorTest{

    @Test
    public void testElevatorHasCorrectNumberOfFloors() {
        int floors = 20;
        Elevator classUnderTest = new Elevator(floors);
        assertEquals("elevator needs to have correct number of floors", floors, classUnderTest.getFloors());
    }   
}