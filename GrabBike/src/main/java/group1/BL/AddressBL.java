package group1.BL;

import java.sql.SQLException;

import group1.DAL.AddressDAL;
import group1.Persistence.Address;

public class AddressBL {
    AddressDAL driDal = new AddressDAL();
    public Address getAddress(int id) throws SQLException {
        Address address = driDal.getAddress(id);
        return address;
    }
}