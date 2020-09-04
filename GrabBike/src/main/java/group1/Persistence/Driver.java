package group1.Persistence;

public class Driver {
    int ID;
    String name;
    String email;
    String phone;
    String licensePlates;
    String vehicleInfo;
    String location;
    int status;

    public Driver() {
    }

    public Driver(int ID, String name, String email, String phone, int status) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
    public String getLicensePlates() {
        return this.licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getVehicleInfo() {
        return this.vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }


}
