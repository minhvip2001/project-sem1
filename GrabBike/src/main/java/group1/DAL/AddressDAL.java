package group1.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;

import group1.Persistence.Address;

public class AddressDAL {
    private String query;
    private ResultSet rs;
    public Address getAddress(int id) throws SQLException {

        query = "SELECT * FROM address WHERE customer_id  = " + id + ";";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        Address address = null;
        while (rs.next()) {
            address = getAddressInfo(rs);          
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return address;

    }
    private Address getAddressInfo(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setAddressID(rs.getInt("address_id"));
        address.setCustomerID(rs.getInt("customer_id"));
        address.setValue(rs.getString("value"));
        return address;
    }
}