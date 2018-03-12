package Reservation.service;

public class HoldExpiredException extends Exception {

    public HoldExpiredException(){
    }

    public HoldExpiredException(String message){
        super(message);
    }

    public HoldExpiredException(String message, Throwable cause){
        super(message, cause);
    }
}
