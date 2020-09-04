package group1.Persistence;

import java.sql.Timestamp;

public class Booking {
    int journeyID;
    int driverID;
    String start;
    String end;
    Timestamp date;
    float price;

    public Booking() {
    }

    public Booking(int bookingID, int driverID, String start, String end, Timestamp date, int price) {
        this.journeyID = bookingID;
        this.driverID = driverID;
        this.start = start;
        this.end = end;
        this.date = date;
        this.price = price;
    }


    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(int journeyID) {
        this.journeyID = journeyID;
    }

}
