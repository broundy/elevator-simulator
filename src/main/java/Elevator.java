import java.util.*;

public class Elevator implements Runnable{
    private static int DOOR_OPEN_CLOSE_DELAY = 3000;
    private static int PER_FLOOR_MOVE_DELAY = 1000;
    private UUID id;
    private int floors;
    private int currentFloor;
    private List<Integer> jobQueue = new ArrayList<Integer>();
    public enum State {
        WORKING,
        IDLE,
        OUT_OF_SERVICE;
    }
    private State currentState;

    public enum Direction {
        UP,
        DOWN,
        NONE;
    }
    private Direction currentDirection;

    private int floorsTraveled;
    private int tripsMade;

    public Elevator(){
        this(2);
    }

    public Elevator(int f){
        floors = f;
        currentFloor = 1;
        id = UUID.randomUUID();
        floorsTraveled = 0;
        tripsMade = 0;
        currentState = Elevator.State.IDLE;
        currentDirection = Elevator.Direction.NONE;
    }

    public UUID getId() {
        return id;
    } 

    public int getFloors() {
        return floors;
    } 

    public int getCurrentFloor() {
        return currentFloor;
    } 

    public int getTripsMade() {
        return tripsMade;
    } 

    public int getFloorsTraveled() {
        return floorsTraveled;
    } 

    public void addToQueue(int floor) throws Exception{
        jobQueue.add(floor);
    }

    public void clearQueue() throws Exception{
        // Do we need to go up or down.
        for(Integer floor : jobQueue){
            if(floor < currentFloor){
                currentDirection = Elevator.Direction.DOWN;
            }else{
                currentDirection = Elevator.Direction.UP;
            }
        }
        
        // Now that we know which direction we are going clear the queue
        if(currentDirection == Elevator.Direction.UP){
            while(currentFloor != getHighestFloorInQueue()){
                int newFloor = currentFloor + 1;
                moveToFloor(newFloor);
                if(jobQueue.contains(newFloor)){
                    loadPassangers(newFloor);
                }
            }
        }

        if(currentDirection == Elevator.Direction.DOWN){
            while(currentFloor != getLowestFloorInQueue()){
                int newFloor = currentFloor - 1;
                moveToFloor(newFloor);
                if(jobQueue.contains(newFloor)){
                    loadPassangers(newFloor);
                }
            }
        }

        currentDirection = Elevator.Direction.NONE;
        currentState = Elevator.State.IDLE;
    }

    public int getTravelDistance(int floor){
        return Math.abs(currentFloor - floor);
    }

    private int getLowestFloorInQueue(){
        int lowestFloor = this.getCurrentFloor();
        for(Integer floor : jobQueue){
            if(floor < lowestFloor){
                lowestFloor = floor;
            }
        }
        return lowestFloor;
    }

    private int getHighestFloorInQueue(){
        int highestFloor = this.getCurrentFloor();
        for(Integer floor : jobQueue){
            if(floor > highestFloor){
                highestFloor = floor;
            }
        }
        return highestFloor;
    }

    public void moveToFloor(int floor) throws Exception{
        System.out.println("Elevator " + id + " is moving to floor " + floor);
        // Add in a delay
        Thread.sleep(PER_FLOOR_MOVE_DELAY);
        currentFloor = floor;
        floorsTraveled += 1;
    }

    public void loadPassangers(int floor) throws Exception{
        System.out.println("Passangers are loading elevator " + id + " on floor " + currentFloor);
        jobQueue.remove(Integer.valueOf(floor));
        // Add in a delay
        Thread.sleep(DOOR_OPEN_CLOSE_DELAY);
        tripsMade += 1;
    }
    
    public State getCurrentState(){
        return currentState;
    }

    public boolean canService(int floor){
        if(currentState != Elevator.State.OUT_OF_SERVICE){
            if(currentState == Elevator.State.IDLE){
                return true;
            }

            if(currentState == Elevator.State.WORKING){
                if(floor < currentFloor && currentDirection == Elevator.Direction.DOWN){
                    return true;
                }
                if(floor > currentFloor && currentDirection == Elevator.Direction.UP){
                    return true;
                }       
            }
        }

        return false;
    }

    public void run(){
        try{
            while(true){
                if(!jobQueue.isEmpty()){
                    System.out.println("Jobs in queue for elevator " + id + ": " + jobQueue.size());
                    currentState = Elevator.State.WORKING;
                    clearQueue();
                }else{
                    Thread.sleep(1000);   
                }         
            }
        }catch(Exception e){
            System.out.println("Elevator error " + e.getMessage());
        }
    }
}