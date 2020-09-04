package group1.BL;

import java.sql.SQLException;

import group1.DAL.PaymentDAL;
import group1.Persistence.Payment;

public class PaymentBL {
    PaymentDAL payDal = new  PaymentDAL();
    public Payment payment(Payment pay) throws SQLException {

        return payDal.payment(pay); 
    }
}