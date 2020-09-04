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

        query = "INSERT INTO Journeys(driver_id ,start, end, price) Values('" + boo.getDriverID() + "', '"
                + boo.getStart() + "', '" + boo.getEnd() + "', '" + boo.getPrice() + "');";

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

        query = "SELECT * FROM journeys";
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
        boo.setJourneyID(rs.getInt("journey_id"));
        boo.setDriverID(rs.getInt("driver_id"));
        boo.setStart(rs.getString("start"));
        boo.setEnd(rs.getString("end"));
        boo.setPrice(rs.getFloat("price"));
        boo.setDate(rs.getTimestamp("DateTime"));
        return boo;
    }

    public ArrayList<Booking> listBooking(int id) throws SQLException {
       query = "select * from Journeys j inner join Drivers d on j.driver_id = d.driver_id inner join Ratings r on d.driver_id = r.driver_id where r.customer_id = " + id + ";";
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
