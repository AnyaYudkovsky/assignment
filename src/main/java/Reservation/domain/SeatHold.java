package Reservation.domain;

import java.util.List;

public class SeatHold {
    private List<Seat> seatsOnHold;
    private String holdId;
    private String customerEmail;

    private long createdHold;

    public SeatHold(List<Seat> seats, String holdId, String customerEmail, long createdHold){
        this.seatsOnHold = seats;
        this.holdId = holdId;
        this.customerEmail = customerEmail;
        this.createdHold = createdHold;

    }

    public List<Seat> getSeatsOnHold() {
        return seatsOnHold;
    }

    public void setSeatsOnHold(List<Seat> seatsOnHold) {
        this.seatsOnHold = seatsOnHold;
    }

    public String getHoldId() {
        return holdId;
    }

    public void setHoldId(String holdId) {
        this.holdId = holdId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getCreatedHold() {
        return createdHold;
    }

    public void setCreatedHold(long createdHold) {
        this.createdHold = createdHold;
    }

}
