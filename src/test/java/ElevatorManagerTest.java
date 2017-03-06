import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorManagerTest {

    @Test(expected = IllegalArgumentException.class) 
    public void testDestinationGreaterThanFloors() throws Exception {
        ElevatorManager classUnderTest = new ElevatorManager();
        classUnderTest.addToQueue(ElevatorManager.DEFAULT_FLOORS + 1);
    }  

    @Test(expected = IllegalArgumentException.class) 
    public void testDestinationFloorZero() throws Exception {
        ElevatorManager classUnderTest = new ElevatorManager();
        classUnderTest.addToQueue(0);
    }  

    @Test(expected = IllegalArgumentException.class) 
    public void testDestinationFloorNegative() throws Exception {
        ElevatorManager classUnderTest = new ElevatorManager();
        classUnderTest.addToQueue(-20);
    }  

    @Test
    public void testGetNearestElevator() throws Exception{
        int floors = 10;
        int elevators = 4;
        ElevatorManager classUnderTest = new ElevatorManager(floors, elevators);
        new Thread(classUnderTest).start();
        Elevator[] elevatorArray = new Elevator[classUnderTest.getElevators().size()];
        elevatorArray = classUnderTest.getElevators().toArray(elevatorArray);
        elevatorArray[0].addToQueue(2);
        elevatorArray[1].addToQueue(6);
        elevatorArray[2].addToQueue(3);
        elevatorArray[3].addToQueue(7);
        UUID one = elevatorArray[0].getId();
        UUID two = elevatorArray[1].getId();
        UUID three = elevatorArray[2].getId();
        UUID four = elevatorArray[3].getId();
        // wait for elevators to get into place
        Thread.sleep(10000); 
        assertEquals("simulator should have sent the correct elevator", one, classUnderTest.getNearestAvailableElevator(1).getId());
        assertEquals("simulator should have sent the correct elevator", two, classUnderTest.getNearestAvailableElevator(5).getId());
        assertEquals("simulator should have sent the correct elevator", three, classUnderTest.getNearestAvailableElevator(4).getId());
        assertEquals("simulator should have sent the correct elevator", four, classUnderTest.getNearestAvailableElevator(8).getId());
    }
}
