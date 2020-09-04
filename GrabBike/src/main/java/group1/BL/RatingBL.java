package group1.BL;

 import java.sql.SQLException;

 import group1.DAL.RatingDAL;
 import group1.Persistence.Rating;

 public class RatingBL {
     RatingDAL rateDal = new RatingDAL();

     public Rating ratingDriver(int cus_id , int dri_id, int rating) throws SQLException {
       
         return rateDal.ratingDriver(cus_id, dri_id, rating);
     }
     public Rating getRating(int cus_id) throws SQLException {
       
        return rateDal.getRating(cus_id);
    }
 }
