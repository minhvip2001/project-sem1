package group1.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHelper {
    private static Connection conn = null;

    // Tạo kết nối đến database
    public static Connection getConnection() throws SQLException{
        if(conn == null){
            String connectionString = "jdbc:mysql://localhost:3306/GrabSystem";
            conn = DriverManager.getConnection(connectionString, "root", "minh2001");
        }
        return conn;
    }
    // Đóng kết nối
    public static void closeConnection() throws SQLException{
        if(conn != null)
        {
            conn.close();
            conn = null;
        }
    }

    // Thực hiện truy vấn
    public static ResultSet executeQuery(String query) throws SQLException{
        ResultSet rs = null;
        PreparedStatement connPreparedStatement = conn.prepareStatement(query);
        rs = connPreparedStatement.executeQuery();
        return rs;
    }
    public static String executeUpdate(String query) throws SQLException{

        PreparedStatement connPreparedStatement = conn.prepareStatement(query);
        connPreparedStatement.executeUpdate();
        return null;

    }
}