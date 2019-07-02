package parking.manager;

import auto.Car;
import parking.ParkingSpot;
import parking.exceptions.FullParkingException;
import parking.valet.DeliveryStrategy;
import parking.valet.PickupStrategy;
import parking.valet.TaskStrategy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ParkingManager {
    private ParkingTicketManager parkingTicketManager;
    private Queue<Integer> deliveries;
    private Queue<Integer> pickups;
    private HashMap<Integer, Car> givesBack;

    public ParkingManager() {
        // Initialize the parkingTicketmanager
        parkingTicketManager = new ParkingTicketManager();
        // Initialize the empty queue of deliveries
        deliveries = new LinkedList<>();
        // Initialize the empty queue of pickups
        pickups = new LinkedList<>();
        // Initialize the gives back hashmap
        givesBack = new HashMap<>();
    }

    public int delivery(Car car, ParkingSpot[] parkingSpots) throws FullParkingException {
        ParkingSpot targetSpot;
        int ticket;
        // Acquire a free parking spot
        targetSpot = parkingTicketManager.acquireParkingSpot(parkingSpots);
        // Factory ticket
        ticket = parkingTicketManager.factoryTicket(targetSpot, car);
        deliveries.add(ticket);
        return ticket;
    }

    public synchronized Car pickup(Integer ticketId) {
        return givesBack.get(ticketId);
    }

    public synchronized TaskStrategy accomplishTask(){
        TaskStrategy taskStrategy;
        if (deliveries.size() == 0 && pickups.size() == 0) {
            // Nothing to do
            taskStrategy = null;
        } else if (deliveries.size() >= pickups.size()) {
            // Do a delivery
            Ticket ticket = getFirstDelivery();
            taskStrategy = new DeliveryStrategy(ticket.getParkedCarSpot(), ticket.getParkedCar());
        } else {
            // Do a pickup
            Ticket ticket = getFirstPickup();
            taskStrategy = new PickupStrategy(ticket.getParkedCarSpot(), givesBack, ticket.hashCode());
        }
        return taskStrategy;
    }

    private Ticket getFirstDelivery() {
        int ticketId = deliveries.remove();
        return parkingTicketManager.getTicketFromId(ticketId);
    }

    private synchronized Ticket getFirstPickup() {
        return parkingTicketManager.getTicketFromId(pickups.remove());
    }

    public void prepareParking(Integer ticketId) {
        pickups.add(ticketId);
    }

    public synchronized void pickupCompleted(int ticketId) {
        givesBack.remove(ticketId);
        parkingTicketManager.destroyTicket(ticketId);
    }

    public boolean containsTicket(int ticketId) {
        return parkingTicketManager.containsTicket(ticketId);
    }
}
