package parking;

import auto.Car;

import java.util.HashMap;
import java.util.Map;

class ParkingManager {

    private int parkID;
    private Map<Integer, Ticket> ticketMap = new HashMap<>();

    ParkingManager(int parkID) {
        // ParkingManager must contains the id of the parking
        this.parkID = parkID;
    }

    synchronized ParkingSpot acquireParkingSpot(ParkingSpot[] parkingSpots) throws ParkingFullException {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isFree()) {
                parkingSpot.occupy();
                return parkingSpot;
            }
        }
        throw new ParkingFullException("Parking is full");
    }

    int factoryTicket(ParkingSpot targetSlot, Car car) {
        // Generate the ticket
        Ticket generatedTicket = new Ticket(this.parkID, targetSlot, car);
        this.ticketMap.put(generatedTicket.hashCode(), generatedTicket);
        return generatedTicket.hashCode();
    }

}
