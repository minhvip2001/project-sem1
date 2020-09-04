package group1.DAL;

import java.sql.SQLException;

import group1.Persistence.Payment;

public class PaymentDAL {
    private String query;
    String s;
    public Payment payment(Payment pay) throws SQLException {

        query = "INSERT INTO Payments(customer_id, method) Values('" + pay.getCusID() + "', '" + pay.getMethod()
                + "');";

        // Mở kết nối đến database
        DbHelper.getConnection();
        // Thực hiện truy vấn
        DbHelper.executeQuery("SET foreign_key_checks = 0;");
        s = DbHelper.executeUpdate(query);
        DbHelper.executeQuery("SET foreign_key_checks = 1;");

        // Đóng kết nối
        DbHelper.closeConnection();

        return pay;

    }
}