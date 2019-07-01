package parking;

import auto.Car;

import java.util.HashMap;
import java.util.Map;

class ParkingManager {
    private int parkID;     // ParkingManager must contain the parking id
    private Map<Integer, Ticket> ticketMap = new HashMap<>();

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
        Ticket generatedTicket = new Ticket(targetSlot, car);
        this.ticketMap.put(generatedTicket.hashCode(), generatedTicket);
        return generatedTicket.hashCode();
    }

    void destroyTicket(int ticketId){
        ticketMap.remove(ticketId);
    }

    Ticket getTicketFromId(int id){
        return ticketMap.get(id);
    }

}
