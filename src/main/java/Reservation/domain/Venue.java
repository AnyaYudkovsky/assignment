package Reservation.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Venue implements Iterable<Seat>{

    public List<Seat> seats=new ArrayList<Seat>();

    public Venue(List<Seat> seats){
        this.seats = seats;

    }

    public List<Seat> getTicketsOnHold() {
        return this.seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.ONHOLD).collect(Collectors.toList());
    }

    public List<Seat> getReservedTickets() {
        return this.seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.RESERVED).collect(Collectors.toList());
    }

    public List<Seat> getAvailableTickets() {
        return this.seats.stream().filter(s -> s.getSeatStatus() == SeatStatus.AVAILABLE).collect(Collectors.toList());
    }

    public Seat findSeat(Seat seatToFind){
        Seat seat = this.seats.stream().filter(s -> (s.getRowNumber() == seatToFind.getRowNumber() && s.getNumberInRow() ==
                seatToFind.getNumberInRow() ))
                .findFirst().orElse(null);
        return seat;
    }

    @Override
    public Iterator<Seat> iterator() {
        return seats.iterator();
    }
}
