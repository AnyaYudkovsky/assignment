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

    @Test
    public void shouldGetRowNumber() {
        Seat seat = new Seat(2, 3, 100.00, SeatStatus.AVAILABLE);
        Assert.assertEquals(seat.getRowNumber(), 2);
    }

    @Test
    public void shouldGetSeatNumberInRow() {
        Seat seat = new Seat(2, 3, 100.00, SeatStatus.AVAILABLE);
        Assert.assertEquals(seat.getNumberInRow(), 3);
    }

    @Test
    public void shouldGetConfirmationId(){
        Seat seat = new Seat(2, 3, 100.00, SeatStatus.AVAILABLE);
        seat.setConfirmationId("test1");
        Assert.assertEquals(seat.getConfirmationId(), "test1");
    }

    @Test
    public void shouldGetCustomerEmail(){
        Seat seat = new Seat(2, 3, 100.00, SeatStatus.AVAILABLE);
        seat.setCustomerEmail("test@test.com");
        Assert.assertEquals(seat.getCustomerEmail(), "test@test.com");
    }

}
