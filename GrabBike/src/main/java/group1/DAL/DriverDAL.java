package group1.DAL;

import group1.Persistence.Booking;
import group1.Persistence.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DriverDAL {
    private String query;
    private ResultSet rs;
    String s;
    public Driver getDriver(int id) throws SQLException {

        query = "SELECT * FROM drivers WHERE driver_id  = " + id + " And status = 0;";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        Driver dri = null;
        while (rs.next()) {
            dri = getDriverInfo(rs);
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return dri;

    }
    public Driver getDriverID(int id) throws SQLException {

        query = "SELECT * FROM drivers WHERE driver_id  = " + id ;

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        Driver dri = null;
        while (rs.next()) {
            dri = getDriverInfo(rs);
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return dri;

    }
   
   
    public ArrayList<Driver> getListDriver(String location) throws SQLException {

        query = "SELECT * FROM drivers WHERE location like '%" + location  + "%' And status = 0 LIMIT 10;";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        ArrayList<Driver> drivers = new ArrayList<>();
        Driver dri = null;
        while (rs.next()) {
            dri = getDriverInfo(rs);
            drivers.add(dri);
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return drivers;

    }
    
    private Driver getDriverInfo(ResultSet rs) throws SQLException {
        Driver dri = new Driver();
        dri.setID(rs.getInt("driver_id"));
        dri.setName(rs.getString("driver_name"));
        dri.setEmail(rs.getString("email"));
        dri.setPhone(rs.getString("phone"));
        dri.setLicensePlates(rs.getString("license_plates"));
        dri.setVehicleInfo(rs.getString("VehicleInfo"));
        dri.setLocation(rs.getString("location"));
        dri.setStatus(rs.getInt("status"));
        return dri;
    }
    public Driver updateStatus(int status ,int id) throws SQLException {

        query = "UPDATE drivers SET status = '" + status + "' WHERE driver_id  = '" + id + "';";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        s = DbHelper.executeUpdate(query);

        //Lấy thông tin Employee từ ResultSet
        Driver dri = null;  

        //Đóng kết nối
        DbHelper.closeConnection();

        return dri;

    }

    public Driver updateLocation(Booking boo ,int id) throws SQLException {

        query = "UPDATE drivers SET location = '" + boo.getEnd() + "' WHERE driver_id  = '" + id + "';";

        //Mở kết nối đến database
        DbHelper.getConnection();
        
        //Thực hiện truy vấn
        s = DbHelper.executeUpdate(query);

        //Lấy thông tin Employee từ ResultSet
        Driver dri = null;  

        //Đóng kết nối
        DbHelper.closeConnection();

        return dri;

    }
    
}

