package Reservation;

import Reservation.domain.Seat;
import Reservation.domain.SeatStatus;
import org.junit.Assert;
import org.junit.Test;

public class SeatTests {

    @Test
    public void shouldCreateSeat() {
        Seat seat = new Seat(2, 3, 100.00, SeatStatus.AVAILABLE);
        Assert.assertNotNull(seat);
    }

    @Test
    public void shouldGetSeatStatus() {
        Seat seat = new Seat(2, 3, 100.00, SeatStatus.AVAILABLE);
        Assert.assertEquals(SeatStatus.AVAILABLE, seat.getSeatStatus());
    }

}
