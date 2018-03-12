package Reservation.domain;

import Reservation.service.HoldExpiredException;
import Reservation.service.TicketServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceApp {
    public static void main(String[] args) throws Exception {

        Venue venue = new Venue(initVenue(5, 6, 100.00));

        System.out.println("Initial seats availability");
        for (Seat seat: venue){
            System.out.println(seat.toString());
        }

        TicketServiceImpl service = new TicketServiceImpl(venue);

        // put tickets on hold1
        SeatHold hold1 = service.findAndHoldSeats(5, "customer1@test.com");

        // put tickets on hold2
        SeatHold hold2 = service.findAndHoldSeats(8, "customer2@test.com");

        // print
        System.out.println("Step1: Seats availability");
        for (Seat seat: venue){
            System.out.println(seat.toString());
        }

        // reserve ticket from hold1
        try {
            service.reserveSeats(hold1.getHoldId(),"customer1@test.com" );
        } catch (HoldExpiredException e) {
            System.out.println("Hold Expired");
        }

        // print
        System.out.println("Step2: Seats availability");
        for (Seat seat: venue){
            System.out.println(seat.toString());
        }

        // delay

        Thread.sleep(30000);


        //try to reserve from hold2, should get expire message
        try {
            service.reserveSeats(hold2.getHoldId(),"customer1@test.com" );
        } catch (HoldExpiredException e) {
            System.out.println("Hold Expired");
        }

        // print
        System.out.println("Step3: Seats availability");
        for (Seat seat: venue){
            System.out.println(seat.toString());
        }

        // put on hold again
        SeatHold hold3 = service.findAndHoldSeats(8, "customer2@test.com");

        // successfully reserve
        try {
            service.reserveSeats(hold3.getHoldId(),"customer1@test.com" );
        } catch (HoldExpiredException e) {
            System.out.println("Hold Expired");
        }

        // print
        System.out.println("Step4: Seats availability");
        for (Seat seat: venue){
            System.out.println(seat.toString());
        }
    }

    private static List<Seat> initVenue(int rows, int seatsPerRow, double ticketPrice) {
        List<Seat> seats=new ArrayList<Seat>();
        for(int i = 1; i <= rows; i++) 	{
            for(int j = 1; j <= seatsPerRow; j++)	{
                Seat seat = new Seat(i, j, ticketPrice, SeatStatus.AVAILABLE);
                seats.add(seat);
            }
        }
        return seats;
    }
}
