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
    public void testMoveElevatorToFloor() throws Exception {
        int destination1 = 2;
        int destination2 = 5;
        int destination3 = 1;
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        new Thread(classUnderTest).start();
        assertEquals("current floor should be 1", 1, classUnderTest.getCurrentFloor());
        classUnderTest.addToQueue(destination1);
        Thread.sleep(3000);   
        assertEquals("current floor should match destination", destination1, classUnderTest.getCurrentFloor());
        classUnderTest.addToQueue(destination2);
        Thread.sleep(7000);  
        assertEquals("current floor should match destination", destination2, classUnderTest.getCurrentFloor());
        classUnderTest.addToQueue(destination3);
        Thread.sleep(8000);  
        assertEquals("current floor should match destination", destination3, classUnderTest.getCurrentFloor());
    }   

    @Test
    public void testAddWhileMoveElevatorToFloor() throws Exception {
        int destination1 = 7;
        int destination2 = 5;
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        new Thread(classUnderTest).start();
        assertEquals("current floor should be 1", 1, classUnderTest.getCurrentFloor());
        classUnderTest.addToQueue(destination1);
        Thread.sleep(2000);  
        classUnderTest.addToQueue(destination2);
        Thread.sleep(5500);   
        assertEquals("current floor should match destination", destination2, classUnderTest.getCurrentFloor());
        Thread.sleep(4000);  
        assertEquals("current floor should match destination", destination1, classUnderTest.getCurrentFloor());
    }   

    @Test
    public void testAccurateFloorsTraveledLog() throws Exception {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        new Thread(classUnderTest).start();
        assertEquals("floors traveled log updates correctly", 0, classUnderTest.getFloorsTraveled());
        classUnderTest.addToQueue(10);
        Thread.sleep(11000); 
        assertEquals("floors traveled log updates correctly", 9, classUnderTest.getFloorsTraveled());
    }   

    @Test
    public void testAccurateTripsMadeLog() throws Exception {
        Elevator classUnderTest = new Elevator(TEST_FLOORS);
        new Thread(classUnderTest).start();
        assertEquals("floors traveled log updates correctly", 0, classUnderTest.getTripsMade());
        classUnderTest.moveToFloor(10);
        Thread.sleep(11000); 
        assertEquals("floors traveled log updates correctly", 1, classUnderTest.getFloorsTraveled());
    }   
}