import java.util.*;

public class ElevatorManager implements Runnable{
    public static int DEFAULT_FLOORS = 2;
    private List<Elevator> elevators = new ArrayList<Elevator>();
    private int floors;
    private List<Integer> requestQueue = new ArrayList<Integer>();

    public ElevatorManager(){
        this(DEFAULT_FLOORS,1);
    }

    public ElevatorManager(int f, int e){
        floors = f;
        for(int i=0; i<e; i++){
            this.addElevator(f);
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    } 

    public int getFloors() {
        return floors;
    } 

    public void addElevator(int floors){
        Elevator elevator =  new Elevator(floors);
        elevators.add(elevator);
        new Thread(elevator).start();
    } 

    public void addToQueue(int floor) throws Exception{
        if(floor > floors) {
            throw new IllegalArgumentException("Elevator does not support destination greater than " + floor);
        }

        if(floor < 1) {
            throw new IllegalArgumentException("Elevator does not support destination less than 1");
        }
        System.out.println("Request for floor " + floor);
        requestQueue.add(floor);
    }

    public List<Integer> getRequestQueue() {
        return requestQueue;
    } 

    public Elevator getNearestAvailableElevator(int floor){
        Elevator nearest = null;
        for(Elevator elevator : elevators){
            if(elevator.canService(floor)){
                if(nearest == null){
                    // Set to the first (maybe only) elevetor
                    nearest = elevator;
                }else{
                    if(nearest.getTravelDistance(floor) > elevator.getTravelDistance(floor)){
                        nearest = elevator;
                    }
                }
            }
        }
        return nearest;
    }

    public void run() {
        try{
            while(true){
                if(!requestQueue.isEmpty()){
                    int floor = requestQueue.iterator().next();
                    Elevator e = getNearestAvailableElevator(floor);
                    if(e != null){
                        e.addToQueue(floor);
                        requestQueue.remove(Integer.valueOf(floor));
                    }else{
                        Thread.sleep(1000); 
                    }
                }else{
                    Thread.sleep(1000);   
                }         
            }
        }catch(Exception e){
            System.out.println("Elevator Manager error " + e.getMessage());
        }
    }
}
