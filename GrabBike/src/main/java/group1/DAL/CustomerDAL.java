package group1.DAL;

import group1.Persistence.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAL {
    private String query;
    private ResultSet rs;
    String s;
    public Customer getCustomer(String phone, String password) throws SQLException {

        query = "SELECT * FROM customers WHERE phone  = '" + phone + "' And password = '" + password +"';";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        Customer cus = null;
        while (rs.next()) {
            cus = getCustomerInfo(rs);          
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return cus;

    }
    private Customer getCustomerInfo(ResultSet rs) throws SQLException {
        Customer cus = new Customer();
        cus.setID(rs.getInt("customer_id"));
        cus.setName(rs.getString("customer_name"));
        cus.setEmail(rs.getString("email"));
        cus.setPhone(rs.getString("phone"));
        cus.setPassword(rs.getString("password"));
        return cus;
    }


    public Customer Register(Customer cus) throws SQLException {

        query = "INSERT INTO customers(customer_name, email, phone, password) Values('" + cus.getName() + "', '" + cus.getEmail() + "', '" + cus.getPhone() + "', '" + cus.getPassword() + "');";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        s = DbHelper.executeUpdate(query);

        //Lấy thông tin Employee từ ResultSet

        //Đóng kết nối
        DbHelper.closeConnection();

        return cus;

    }

    public Customer verifyRegister(String phone) throws SQLException {

        query = "SELECT * FROM customers WHERE phone  = '" + phone + "';";

        //Mở kết nối đến database
        DbHelper.getConnection();

        //Thực hiện truy vấn
        rs = DbHelper.executeQuery(query);

        //Lấy thông tin Employee từ ResultSet
        Customer cus = null;
        while (rs.next()) {
            cus = checkRegister(rs);
        }

        //Đóng kết nối
        DbHelper.closeConnection();

        return cus;

    }
    private Customer checkRegister(ResultSet rs) throws SQLException {
        Customer cus = new Customer();
        cus.setPhone(rs.getString("phone"));

        return cus;
    }
    
}
