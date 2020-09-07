package group1.BL;

import group1.Persistence.Booking;
import group1.DAL.BookingDAL;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBL {
    ArrayList<Booking> bookings;

    BookingDAL booDal = new BookingDAL();

    public Booking creatJourney(Booking boo) throws SQLException {

        return booDal.creatJourney(boo);
    }

    public Booking viewJourney() throws SQLException {
        Booking boo = booDal.viewJourney();
        return boo;
    }

    public ArrayList<Booking> listBooking(int id) throws SQLException {
        bookings = booDal.listBooking(id);
        return bookings;
    }

    public ArrayList<Booking> getList() {
        return bookings;
    }

    public Booking insertRating(Booking boo) throws SQLException {
        return booDal.insertRating(boo);
    }
    
}
