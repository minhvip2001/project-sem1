package group1.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import group1.Persistence.Rating;

public class RatingDAL {
    private String query;
    private ResultSet rs;
    String s;
    public Rating ratingDriver(int cus_id, int dri_id, int rating) throws SQLException {

        query = "INSERT INTO Ratings(customer_id , driver_id, star_rating) Values('" + cus_id + "', '" + dri_id + "', '" + rating + "');";

        //Mở kết nối đến database
        DbHelper.getConnection();
        DbHelper.executeQuery("SET foreign_key_checks = 0;");
        //Thực hiện truy vấn
        s = DbHelper.executeUpdate(query);
        DbHelper.executeQuery("SET foreign_key_checks = 1;");

        //Lấy thông tin Employee từ ResultSet
        Rating rate = null;


        //Đóng kết nối
        DbHelper.closeConnection();

        return rate;

    }
    public Rating getRating(int cus_id) throws SQLException {

        query = "SELECT * FROM Ratings WHERE customer_id  = " + cus_id + ";";

        //Mở kết nối đến database
        DbHelper.getConnection();
        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        Rating rate = null;
        while (rs.next()) {
            rate = getRating(rs);          
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return rate;

    }
    private Rating getRating(ResultSet rs) throws SQLException {
        Rating rate = new Rating();
        rate.setCustomerID(rs.getInt("customer_id"));
        rate.setStarRating(rs.getInt("star_rating"));
        return rate;
    }

}