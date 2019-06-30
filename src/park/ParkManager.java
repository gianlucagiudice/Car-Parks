package park;

import auto.Car;
import park.ticketing.Ticket;

import java.util.HashMap;
import java.util.Map;

class ParkManager {

    private int parkID;
    private Map<Integer, Ticket> ticketMap = new HashMap<>();

    ParkManager(int parkID) {
        // ParkManager must contains the id of the park
        this.parkID = parkID;
    }

    synchronized ParkSlot acquireParkingSlot(ParkSlot[] parkSlots) throws ParkFullException {
        for (ParkSlot parkSlot : parkSlots) {
            if (parkSlot.isFree()) {
                parkSlot.occupy();
                return parkSlot;
            }
        }
        throw new ParkFullException("Park is full");
    }

    int factoryTicket(ParkSlot targetSlot, Car car) {
        // Generate the ticket
        Ticket generatedTicket = new Ticket(this.parkID, targetSlot, car);
        this.ticketMap.put(generatedTicket.hashCode(), generatedTicket);
        return generatedTicket.hashCode();
    }

}
