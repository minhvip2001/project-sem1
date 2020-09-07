package group1.BL;

import group1.DAL.DriverDAL;
import group1.Persistence.Booking;
import group1.Persistence.Driver;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBL {
    DriverDAL driDal = new DriverDAL();
    Driver dri;

    public Driver getDriverInfo(int id) throws SQLException {
        dri = driDal.getDriver(id);

        return dri;
    }

    public Driver getDriver() throws SQLException {
        return dri;
    }

    public Driver getDriverId(int id) throws SQLException {
        return dri = driDal.getDriverID(id);
    }

    public Driver updateStatus(int status, int id) throws SQLException {
        dri = driDal.updateStatus(status, id);

        return dri;
    }

    public ArrayList<Driver> getListDriver(String location) throws SQLException {
        return driDal.getListDriver(location);
    }

    public Driver updateLocation(Booking boo, int id) throws SQLException{
        return driDal.updateLocation(boo, id);
    }
    public Driver getDriverDetail(int id, int cusID) throws SQLException {
        return dri = driDal.getDriverDetail(id, cusID);
    }

}
