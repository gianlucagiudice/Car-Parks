package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public abstract class Logger {
    public enum Operation {delivering, delivered, sleeping, pickingUp, pickedUp}
    private Operation currentState;

    private String toStringLogger() {
        String currentTime = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
        return "[" + currentTime + "] " + this.getClass().getSimpleName() + ": " +
                Thread.currentThread().getName() + " " + toStringOperation() + ".\n\t" +
                this.toString();
    }

    protected void setOperation(Operation operation) {
        this.currentState = operation;
        System.out.println(toStringLogger());
    }

    public abstract String toString();

    private String toStringOperation() {
        switch (this.currentState) {
            case delivering:
                return "is going to deliver the car";
            case delivered:
                return "has delivered the car";
            case sleeping:
                return "is going to sleep before pickup";
            case pickingUp:
                return "is going to pickup the car";
            case pickedUp:
                return "has picked up the car";
        }
        return "";
    }
}
