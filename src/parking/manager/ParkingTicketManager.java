package parking.manager;

import auto.Car;
import parking.ParkingSpot;
import parking.exceptions.FullParkingException;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
class ParkingTicketManager {
    private Map<Integer, Ticket> ticketMap = new HashMap<>();

    synchronized ParkingSpot acquireParkingSpot(ParkingSpot[] parkingSpots) throws FullParkingException {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isFree()) {
                parkingSpot.occupy();
                return parkingSpot;
            }
        }
        throw new FullParkingException("Parking is full");
    }


    int factoryTicket(ParkingSpot targetSpot, Car car) {
        // Generate the ticket
        Ticket generatedTicket = new Ticket(targetSpot, car);
        this.ticketMap.put(generatedTicket.hashCode(), generatedTicket);
        return generatedTicket.hashCode();
    }

    void destroyTicket(int ticketId) {
        ticketMap.remove(ticketId);
    }

    Ticket getTicketFromId(int id) {
        return ticketMap.get(id);
    }

    boolean containsTicket(int ticketId) {
        return ticketMap.containsKey(ticketId);
    }
}
