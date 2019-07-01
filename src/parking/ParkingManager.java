package parking;

import auto.Car;

import java.util.HashMap;
import java.util.Map;

class ParkingManager {
    /**
     * The parking id (ParkingManager must contain it)
     */
	private int parkID;
    
	/**
	 * The HashMap of the current tickets released by the parking
	 */
	private Map<Integer, Ticket> ticketMap = new HashMap<>();

	/**
	 * Acquires the first free parking spot and occupies it.
	 * @param parkingSpots The array of parking spots of the parking
	 * @return the first free parking spot
	 * @throws ParkingFullException If the park is full
	 */
    synchronized ParkingSpot acquireParkingSpot(ParkingSpot[] parkingSpots)
    		throws ParkingFullException {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isFree()) {
                parkingSpot.occupy();
                return parkingSpot;
            }
        }
        throw new ParkingFullException("Parking is full");
    }

    /**
     * Generates a ticket from a target spot and a car
     * and puts it into the HashMap of the current tickets released by the parking.
     * @param targetSpot The parking spot where to park the car
     * @param car The car to park
     * @return hash code of the generated ticket
     */
    int factoryTicket(ParkingSpot targetSpot, Car car) {
        // Generate the ticket
        Ticket generatedTicket = new Ticket(targetSpot, car);
        this.ticketMap.put(generatedTicket.hashCode(), generatedTicket);
        return generatedTicket.hashCode();
    }

    /**
     * Deletes a ticket.
     * @param ticketId The id of the ticket to delete
     */
    void deleteTicket(int ticketId){
        ticketMap.remove(ticketId);
    }

    /**
     * Gets a ticket from its id.
     * @param id The id of the ticket to get
     * @return the ticket corresponding to the id
     */
    Ticket getTicketFromId(int id){
        return ticketMap.get(id);
    }

}
