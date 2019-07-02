package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Logger {
    private Operation currentState;

    private String toStringLogger() {
        String currentTime = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
        return "[" + currentTime + "] " + this.getClass().getSimpleName() + ": " +
                Thread.currentThread().getName() + " " + toStringOperation() + "\n\t" +
                this.toString();
    }

    protected void setOperation(Operation operation) {
        this.currentState = operation;
        System.out.println(toStringLogger());
    }
    
    @Override
    public abstract String toString();

    private String toStringOperation() {
        switch (this.currentState) {
            case delivering:
                return "is going to deliver the car.";
            case delivered:
                return "delivered the car.";
            case sleeping:
                return "is going to sleep before pickup.";
            case pickingUp:
                return "is going to pickup the car.";
            case pickedUp:
                return "picked up the car.";
            case waiting:
                return "is waiting for something to do.";
        }
        return "";
    }

    public enum Operation {delivering, delivered, sleeping, pickingUp, pickedUp, waiting}
}
