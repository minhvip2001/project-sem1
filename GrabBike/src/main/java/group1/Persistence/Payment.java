package group1.Persistence;

public class Payment {
    int payID;
    int cusID;
    String method;

    public int getPayID() {
        return this.payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public int getCusID() {
        return this.cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}