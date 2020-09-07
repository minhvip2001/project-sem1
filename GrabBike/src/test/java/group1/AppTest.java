package group1;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import group1.BL.CustomerBL;
import group1.Persistence.Customer;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    CustomerBL cusBL = new CustomerBL();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws SQLException
    {
        assertTrue(cusBL.Login("0395445571", "202cb962ac59075b964b07152d234b70"));
    }
}
