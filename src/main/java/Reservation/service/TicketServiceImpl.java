package Reservation.service;

import Reservation.domain.Seat;
import Reservation.domain.SeatHold;
import Reservation.domain.SeatStatus;
import Reservation.domain.Venue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService{

    private Venue venue;
    private List<SeatHold> holds;
    private long holdTime;

    public TicketServiceImpl (Venue venue){
        this.venue = venue;
        this.holds = new ArrayList<SeatHold>();
        this.holdTime = 30;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<SeatHold> getHolds() {
        return holds;
    }

    public void setHolds(List<SeatHold> holds) {
        this.holds = holds;
    }

    public long getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(long holdTime) {
        this.holdTime = holdTime;
    }

    @Override
    public long numSeatsAvailable() {
        purgeExpiredHolds();
        return this.venue.seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.AVAILABLE).count();
    }

    @Override
    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        List<Seat> seatsOnHold = new ArrayList<Seat>();

        Iterator<Seat> ticketsIterator = this.venue.seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.AVAILABLE)
                .collect
                (Collectors.toList()).iterator();

        int i = 1;
        while (ticketsIterator.hasNext()){
            if (i <= numSeats) {
                Seat seat = ticketsIterator.next();
                seat.setSeatStatus(SeatStatus.ONHOLD);
                seatsOnHold.add(seat);
                i++;
            } else {
                break;
            }
        }

        UUID uuid = UUID.randomUUID();
        String holdId = uuid.toString();

        SeatHold seatHold = new SeatHold(seatsOnHold, holdId, customerEmail, System.currentTimeMillis());

        holds.add(seatHold);

        return seatHold;
    }

    @Override
    public void reserveSeats(String seatHoldId, String customerEmail) throws HoldExpiredException {
        purgeExpiredHolds();
        SeatHold seatHold = this.holds.stream().filter(s -> s.getHoldId() == seatHoldId)
                .findFirst().orElse(null);

        if (!(seatHold == null)) {
            Iterator<Seat> reserveIterator = seatHold.getSeatsOnHold().iterator();
            UUID uuid = UUID.randomUUID();
            String confirmationId = uuid.toString();
            while (reserveIterator.hasNext()) {
                Seat seat = this.venue.findSeat(reserveIterator.next());
                if (!seat.equals(null)) {
                    seat.setSeatStatus(SeatStatus.RESERVED);
                    seat.setCustomerEmail(customerEmail);
                    seat.setConfirmationId(confirmationId);
                }
            }
            holds.remove(seatHold);
            return;
        } else {
            throw new HoldExpiredException("Your hold expired!");
        }
    }

    private void purgeExpiredHolds(){
        if (!holds.isEmpty()){
            Iterator<SeatHold> holdIterator = holds.iterator();
            while (holdIterator.hasNext()) {
               SeatHold seatHold= holdIterator.next();
               if (getElapsedTime(seatHold.getCreatedHold()) > this.holdTime){
                   Iterator<Seat> seatIterator = seatHold.getSeatsOnHold().iterator();
                   while (seatIterator.hasNext()) {
                       Seat seat = this.venue.findSeat(seatIterator.next());
                       if (!seat.equals(null)) {
                           seat.setSeatStatus(SeatStatus.AVAILABLE);
                           seat.setCustomerEmail("");
                           seat.setConfirmationId("");
                       }
                   }
                   //removing
                   holdIterator.remove();
               }
            }
            return;
        }
        return;
    }

    private double getElapsedTime(long startTime){
        long now = System.currentTimeMillis();
        long delta = now - startTime;
        return delta / 1000.0;
    }
}
