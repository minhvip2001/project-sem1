package group1.BL;

import group1.DAL.CustomerDAL;
import group1.Persistence.Customer;

import java.sql.SQLException;

public class CustomerBL {
    CustomerDAL cusDal = new CustomerDAL();
    Customer cus;
    public Boolean Login(String phone, String password) throws SQLException {
       cus = cusDal.getCustomer(phone, password);
       try {
       
        if(phone.equalsIgnoreCase(cus.getPhone()) && password.equalsIgnoreCase(cus.getPassword()))
          return true;
       } catch (Exception e) {
          return false;
       }
   return false;

      
     
    }
    
    public Customer getCustomer(){
        return cus;
    }

    public Customer Register(Customer cus) throws SQLException {
        return cusDal.Register(cus);
    }

    public boolean verifyRegister(String phone) throws SQLException {
       Customer custom = cusDal.verifyRegister(phone);
        
       try {
        if(phone.equals(custom.getPhone())){
            return false;
        }
       } catch (Exception e) {
          return true;
       }
       return true;

    }
}
