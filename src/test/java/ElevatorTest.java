import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorTest{


    @Test
    public void testElevatorHasCorrectNumberOfFloors() {
        int floors = 20;
        Elevator classUnderTest = new Elevator(floors);
        assertEquals("elevator needs to have correct number of floors", floors, classUnderTest.getFloors());
    }   

    @Test
    public void testMoveElevatorToFloor() {
        int destination = 2;
        int floors = 20;
        Elevator classUnderTest = new Elevator(floors);
        assertEquals("current floor should be 1", 1, classUnderTest.getCurrentFloor());
        classUnderTest.moveToFloor(destination);
        assertEquals("current floor should match destination", destination, classUnderTest.getCurrentFloor());
    }   

    @Test
    public void testOpenAndCloseDoors() {
        int floors = 20;
        Elevator classUnderTest = new Elevator(floors);
        assertEquals("doors should be closed", false, classUnderTest.getDoorsOpen());
        classUnderTest.openDoors();
        assertEquals("doors should be open", true, classUnderTest.getDoorsOpen());
        classUnderTest.closeDoors();
        assertEquals("doors should be closed", false, classUnderTest.getDoorsOpen());
    }   
}