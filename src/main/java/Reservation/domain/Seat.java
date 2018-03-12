package Reservation.domain;

public class Seat {

    private int numberInRow;

    private int rowNumber;

    private double price;

    private SeatStatus seatStatus;

    private String customerEmail;

    private String confirmationId;

    public Seat(int rowNumber, int numberInRow, double price, SeatStatus seatStatus){
        this.rowNumber = rowNumber;
        this.numberInRow = numberInRow;
        this.price = price;
        this.seatStatus = seatStatus;
    }

    public int getNumberInRow() {
        return numberInRow; //numberInRow+1;
    }

    public void setNumberInRow(int numberInRow) {
        this.numberInRow = numberInRow;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + rowNumber +
                ", number=" + numberInRow +
                ", status=" + seatStatus +
                ", price=" + price +
                '}';
    }
}
