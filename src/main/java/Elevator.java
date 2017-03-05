public class Elevator{
    public int floors;
    public int currentFloor;
    public boolean doorsOpen;

    public Elevator(){
        this(2);
    }

    public Elevator(int f){
        floors = f;
        currentFloor = 1;
        doorsOpen = false;
    }

    public int getFloors() {
        return floors;
    } 

    public int getCurrentFloor() {
        return currentFloor;
    } 

    public boolean getDoorsOpen() {
        return doorsOpen;
    } 

    public void moveToFloor(int destination){
        if(destination > floors) {
            throw new IllegalArgumentException("Elevator does not support destination greater than " + floors);
        }

        if(destination < 1) {
            throw new IllegalArgumentException("Elevator does not support destination less than 1");
        }

        System.out.println("Moving to floor " + destination);
        currentFloor = destination;
    }

    public void openDoors(){
        doorsOpen = true;
        System.out.println("Opening doors on floor " + currentFloor);
    }

    public void closeDoors(){
        doorsOpen = false;
        System.out.println("Closing doors on floor " + currentFloor);
    }
}