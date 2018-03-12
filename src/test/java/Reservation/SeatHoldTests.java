package Reservation;

import Reservation.domain.Seat;
import Reservation.domain.SeatHold;
import Reservation.domain.SeatStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatHoldTests {

    private static SeatHold initHold() {
        List<Seat> seats=new ArrayList<Seat>();
        for(int i = 1; i <= 1; i++) 	{
            for(int j = 1; j <= 5; j++)	{
                Seat seat = new Seat(i, j, 100.00, SeatStatus.ONHOLD);
                seats.add(seat);
            }
        }
        SeatHold seatHold = new SeatHold(seats, "Hold1", "test@test.com", System.currentTimeMillis());
        return seatHold;
    }

    @Test
    public void shouldCreateSeatHold() {
        SeatHold seatHold = initHold();
        Assert.assertNotNull(seatHold);
    }

    @Test
    public void shouldGetSeatsOnHold() {
        SeatHold seatHold = initHold();
        long holdTickets = seatHold.getSeatsOnHold().stream().filter(s -> s.getSeatStatus() == SeatStatus.ONHOLD).collect(Collectors
                .toList()).size();
        Assert.assertEquals(holdTickets, 5);
    }

    @Test
    public void shouldGetHoldId() {
        SeatHold seatHold = initHold();
        Assert.assertEquals(seatHold.getHoldId(), "Hold1");
    }

    @Test
    public void shouldGetCustomerEmail() {
        SeatHold seatHold = initHold();
        Assert.assertEquals(seatHold.getCustomerEmail(), "test@test.com");
    }

}
