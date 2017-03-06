import java.util.*;

public class ElevatorSimulator {
    private static ElevatorManager manager;

    public static void main(String[] args) throws Exception {
        if(args.length == 2){
            String floors = args[0]; 
            String elevators = args[1];
            manager = new ElevatorManager(Integer.valueOf(floors), Integer.valueOf(elevators));
            System.out.println("Starting up simulator with " + elevators + " elevators.");
        }else{
            manager = new ElevatorManager();
        }
        new Thread(manager).start();
        for(int i=1; i<100; i++){
            Thread.sleep((long)(Math.random() * 2000));
            manager.addToQueue(1 + (int)(Math.random() * manager.getFloors()));
        }
        System.out.println("Simulation complete!");
        System.exit(0);
        return;
    }
}
