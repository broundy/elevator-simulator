import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorTest{
    private static int TEST_FLOORS = 20;

    @Test
    public void testElevatorHasCorrectNumberOfFloors() {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        assertEquals("elevator needs to have correct number of floors", TEST_FLOORS, classUnderTest.getFloors());
    }   

    @Test
    public void testMoveElevatorToFloor() {
        int destination = 2;
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        assertEquals("current floor should be 1", 1, classUnderTest.getCurrentFloor());
        classUnderTest.moveToFloor(destination);
        assertEquals("current floor should match destination", destination, classUnderTest.getCurrentFloor());
    }   

    @Test
    public void testOpenAndCloseDoors() {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        assertEquals("doors should be closed", false, classUnderTest.getDoorsOpen());
        classUnderTest.openDoors();
        assertEquals("doors should be open", true, classUnderTest.getDoorsOpen());
        classUnderTest.closeDoors();
        assertEquals("doors should be closed", false, classUnderTest.getDoorsOpen());
    }   

    @Test(expected = IllegalArgumentException.class) 
    public void testDestinationGreaterThanFloors() {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        classUnderTest.moveToFloor(TEST_FLOORS + 1);
    }  

    @Test(expected = IllegalArgumentException.class) 
    public void testDestinationFloorZero() {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        classUnderTest.moveToFloor(0);
    }  

    @Test(expected = IllegalArgumentException.class) 
    public void testDestinationFloorNegative() {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        classUnderTest.moveToFloor(-20);
    }  
}