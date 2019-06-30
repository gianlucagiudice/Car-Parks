package parking;

public class ParkFullException extends Exception {
    ParkFullException(String message) {
        super(message);
    }
}
