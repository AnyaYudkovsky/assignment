package Reservation;

import Reservation.domain.Seat;
import Reservation.domain.SeatHold;
import Reservation.domain.SeatStatus;
import Reservation.domain.Venue;
import Reservation.service.HoldExpiredException;
import Reservation.service.TicketServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceTests {
    private TicketServiceImpl service;

    @Before
    public void initService (){
        Venue venue = new Venue(initVenue(5, 5, 150.00));
        this.service = new TicketServiceImpl(venue);
    }

    @Test
    public void shouldReturnNumberOfAvailableSeats() {
        long numberOfSeats = this.service.numSeatsAvailable();
        Assert.assertEquals(numberOfSeats, 25);

    }

    @Test
    public void shouldFindAndHoldSeats() {
        SeatHold hold1 = service.findAndHoldSeats(5, "customer1@test.com");
        Assert.assertEquals(service.getHolds().size(), 1);
        Assert.assertEquals(service.getVenue().seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.ONHOLD)
                .collect(Collectors.toList()).size(), 5);
    }

    @Test
    public void shouldReserveSeats() throws HoldExpiredException {
        SeatHold hold1 = service.findAndHoldSeats(5, "customer1@test.com");
        service.reserveSeats(hold1.getHoldId(),"customer1@test.com" );
        Assert.assertEquals(service.getVenue().seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.RESERVED)
                .collect(Collectors.toList()).size(), 5);

    }

    @Test(expected = HoldExpiredException.class)
    public void shouldThrowHoldExpired() throws InterruptedException, HoldExpiredException {
        service.setHoldTime(5);
        SeatHold hold1 = service.findAndHoldSeats(5, "customer1@test.com");
        Thread.sleep(6000);
        service.reserveSeats(hold1.getHoldId(),"customer1@test.com" );
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
