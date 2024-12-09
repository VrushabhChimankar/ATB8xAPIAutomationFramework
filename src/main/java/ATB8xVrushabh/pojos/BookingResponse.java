
package ATB8xVrushabh.pojos;

import ATB8xVrushabh.pojos.Booking;

public class BookingResponse {

    private Integer bookingid;
    private Booking booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public ATB8xVrushabh.pojos.Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
