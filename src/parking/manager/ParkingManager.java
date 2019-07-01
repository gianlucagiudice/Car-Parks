package parking.manager;

import auto.Car;
import parking.ParkingSpot;
import parking.exceptions.CarNotFoundException;
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
    private HashMap<Integer, Car> pickups;

    public ParkingManager() {
        // Initialize the parkingTicketmanager
        parkingTicketManager = new ParkingTicketManager();
        // Initialize the empty queue of deliveries
        deliveries = new LinkedList<>();
        // Initialize the gives back hashmap
        pickups = new HashMap<>();
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

    public synchronized Car pickup(Integer ticketId) throws CarNotFoundException {
        if (!parkingTicketManager.containsTicket(ticketId))
            throw new CarNotFoundException("Car not found in the parking");
        if (pickups.containsKey(ticketId))
            return pickups.get(ticketId);
        else
            // TODO: Prepara la conegna. RequestPickup
            return pickups.put(ticketId, null);
    }

    public TaskStrategy accomplishTask() throws InterruptedException {
        TaskStrategy taskStrategy = null;


        if (deliveries.size() == 0 && pickups.size() == 0) {

            //wait();
            return null;
        }


        if (deliveries.size() >= pickups.size()) {

            Ticket ticket = getFirstDelivery();
            taskStrategy = new DeliveryStrategy(ticket.getCarParkedSpot(), ticket.getParkedCar());
        } else {
            Ticket ticket = getFirstPickup();
            //ParkingSpot parkingSpotTarget = ticket.getCarParkedSpot()
            taskStrategy = new PickupStrategy(ticket.getCarParkedSpot(), pickups, ticket.hashCode());
        }
        return taskStrategy;
    }

    private synchronized Ticket getFirstPickup() {
        int ticketId = pickups.keySet().iterator().next();
        return parkingTicketManager.getTicketFromId(ticketId);
    }

    private Ticket getFirstDelivery() {
        int ticketId = deliveries.remove();
        return parkingTicketManager.getTicketFromId(ticketId);
    }

    public synchronized void pickupCompleted(int ticketId) {
        pickups.remove(ticketId);
        parkingTicketManager.destroyTicket(ticketId);
    }


}
