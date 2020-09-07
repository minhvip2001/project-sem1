package group1.DAL;

import group1.Persistence.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAL {
    private String query;
    private ResultSet rs;
    String s;

    public Booking creatJourney(Booking boo) throws SQLException {

        query = "INSERT INTO Bookings(customer_id, driver_id, start, end, price) Values('" + boo.getCustomerID()
                + "', '" + boo.getDriverID() + "', '" + boo.getStart() + "', '" + boo.getEnd() + "', '" + boo.getPrice()
                + "');";

        // Mở kết nối đến database
        DbHelper.getConnection();
        DbHelper.executeQuery("SET foreign_key_checks = 0;");
        // Thực hiện truy vấn

        s = DbHelper.executeUpdate(query);
        DbHelper.executeQuery("SET foreign_key_checks = 1;");
        // Lấy thông tin Employee từ ResultSet

        // Đóng kết nối
        DbHelper.closeConnection();

        return boo;

    }

    public Booking insertRating(Booking boo) throws SQLException {

        query = "Update Bookings set rating = " + boo.getRating() + " Where booking_id = " + boo.getBookingID() + ";";

        // Mở kết nối đến database
        DbHelper.getConnection();
        DbHelper.executeQuery("SET foreign_key_checks = 0;");
        // Thực hiện truy vấn
        
        s = DbHelper.executeUpdate(query);
        DbHelper.executeQuery("SET foreign_key_checks = 1;");
        // Lấy thông tin Employee từ ResultSet

        // Đóng kết nối
        DbHelper.closeConnection();

        return boo;

    }

    public Booking viewJourney() throws SQLException {

        query = "SELECT * FROM Bookings";
        // Mở kết nối đến database
        DbHelper.getConnection();

        // Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        // Lấy thông tin Employee từ ResultSet
        Booking boo = null;
        while (rs.next()) {
            boo = getJourneyInfor(rs);
        }

        // Đóng kết nối
        DbHelper.closeConnection();

        return boo;

    }

    private Booking getJourneyInfor(ResultSet rs) throws SQLException {
        Booking boo = new Booking();
        boo.setBookingID(rs.getInt("booking_id"));
        boo.setCustomerID(rs.getInt("customer_id"));
        boo.setDriverID(rs.getInt("driver_id"));
        boo.setStart(rs.getString("start"));
        boo.setEnd(rs.getString("end"));
        boo.setPrice(rs.getFloat("price"));
        boo.setDate(rs.getTimestamp("time"));
        boo.setRating(rs.getInt("rating"));
        return boo;
    }

    public ArrayList<Booking> listBooking(int id) throws SQLException {
        query = "select * from Bookings where customer_id = " + id + ";";
        // Mở kết nối đến database
        DbHelper.getConnection();

        // Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        // Lấy thông tin Employee từ ResultSet
        ArrayList<Booking> booking = new ArrayList<>();
        Booking boo = null;
        while (rs.next()) {
            boo = getJourneyInfor(rs);
            booking.add(boo);
        }

        // Đóng kết nối
        DbHelper.closeConnection();

        return booking;

    }

}
