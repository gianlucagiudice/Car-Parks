package parking;

import auto.Car;
import parking.valet.DeliveryStrategy;
import parking.valet.PickupStrategy;
import parking.valet.TaskStrategy;
import parking.valet.Valet;

import java.util.*;

public class Parking {
    private String id;
    private ParkingSpot[] parkingSpots;

    private List<Thread> valets;
    private int freeValets;

    private ParkingManager parkingManager;

    /**
     * Queue of tickets corresponding to the cars delivered by drivers to the valets
     */
    private Queue<Integer> deliveries;
    
    /**
     * Queue of tickets corresponding to the cars that drivers requested to pickup
     */
    private Queue<Integer> pickups;
    
    /**
     * Queue of tickets corresponding to the cars picked-up by valets to be given back to drivers
     */
    private Queue<Integer> giveBackCars;

    public Parking(String id, int parkingSpotsNumber, int valetsNumber) {
        this.id = id;
        // Factory all parking spots
        this.parkingSpots = factoryParkingSpots(parkingSpotsNumber);
        // Factory all valets
        this.valets = factoryValets(valetsNumber);
        // Start all valets
        runValets();
        this.freeValets = valetsNumber;
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
        // Initialize the empty queue of deliveries
        deliveries = new LinkedList<>();
        // Initialize the empty queue of pickups
        pickups = new LinkedList<>();
        // Initialize the empty queue of cars picked-up by valets to be given back to drivers
        giveBackCars = new LinkedList<>();
    }

    public int delivery(Car car) throws ParkingFullException {
        ParkingSpot targetSpot;
        int ticket;
        // Acquire a free parking spot
        targetSpot = parkingManager.acquireParkingSpot(this.parkingSpots);
        // Factory the ticket
        ticket = parkingManager.factoryTicket(targetSpot, car);
        deliveries.add(ticket);
        occupyValet();
        // The valet accomplishes the task (he deliveries the car)
        return ticket;
    }

    public Car pickup(int ticketId) {
        // Get the ticket from its ID
        pickups.add(ticketId);
        occupyValet();
        
        // The valet accomplishes the task (he pickups the car)
        parkingManager.deleteTicket(ticketId);
        return null;
    }

    public void giveBackCar(){
    	
    }


    public synchronized TaskStrategy accomplishTask() {
        TaskStrategy taskStrategy;
        if (deliveries.size() == 0 && pickups.size() == 0)
            taskStrategy = null;
        else if (deliveries.size() >= pickups.size()) {
            Ticket ticket = getFirstFromQueue(deliveries);
            taskStrategy = new DeliveryStrategy(ticket.getCarParkedSpot(), ticket.getParkedCar());
        } else {
            Ticket ticket = getFirstFromQueue(pickups);
            taskStrategy = new PickupStrategy(ticket.getCarParkedSpot());
        }
        return taskStrategy;
    }

    private Ticket getFirstFromQueue(Queue<Integer> queue) {
        int ticketId = queue.remove();
        return parkingManager.getTicketFromId(ticketId);
    }

    private List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            this.valets.add(new Thread(new Valet(this)));
        }
        return valets;
    }

    private void runValets() {
        for (Thread valet : valets) {
            valet.run();
        }
    }

    private synchronized void occupyValet() {
        this.freeValets--;
        // Notify all waiting valets for a new task
        notifyAll();
    }

    public synchronized void releaseValet() {
        this.freeValets++;
        // Notify all waiting drivers for a new free delivery/pickup valet
        notifyAll();
    }

    private ParkingSpot[] factoryParkingSpots(int parkingSpotsNumber) {
        parkingSpots = new ParkingSpot[parkingSpotsNumber];
        for (int i = 0; i < parkingSpots.length; i++) {
            parkingSpots[i] = new ParkingSpot();
        }
        return parkingSpots;
    }

    public synchronized int getFreeValets() {
        return freeValets;
    }

}
