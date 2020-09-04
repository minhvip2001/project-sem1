package group1.PL_Console;

import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import group1.BL.AddressBL;
import group1.BL.BookingBL;
import group1.BL.CustomerBL;
import group1.BL.DriverBL;
import group1.BL.PaymentBL;
import group1.BL.RatingBL;
import group1.Persistence.Address;
import group1.Persistence.Booking;
import group1.Persistence.Customer;
import group1.Persistence.Driver;
import group1.Persistence.Payment;
import group1.Persistence.Rating;

public class Menu {
    BookingBL bookingBL = new BookingBL();
    DriverBL driverBL = new DriverBL();
    Booking boo = new Booking();
    Rating rate = new Rating();
    RatingBL rateBL = new RatingBL();
    AddressBL addressBL = new AddressBL();
    CustomerBL cusBL = new CustomerBL();
    Scanner sc = new Scanner(System.in);
    Utility util = new Utility();
    DecimalFormat format = new DecimalFormat("####,###,###");
    DecimalFormatSymbols f = new DecimalFormatSymbols();
    DecimalFormat formatPhone = new DecimalFormat("###,###,###");

    public void menu() throws SQLException {
        util.clrscr();
        int count = 0;
        line(65, "-");
        System.out.printf("| %-61s |\n", "Grab");
        line(65, "-");
        System.out.printf("| %-61s |\n", "1. Login.");
        line(65, "-");
        System.out.printf("| %-61s |\n", "2. Register.");
        line(65, "-");
        System.out.printf("| %-61s |\n", "0. Exit.");
        line(65, "-");
        System.out.print(" Your choice: ");
        while (true) {
            if (count != 0) {
                System.out.print(" Only enter[0-2]: ");
            }
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "0":
                    System.out.println(" Good bye");
                    System.exit(0);
                    break;
            }
            count++;
            continue;
        }
    }

    public void login() throws SQLException {
        util.clrscr();
        int count = 0;
        String phone;
        while (true) {
            while (true) {
                line(65, "-");
                System.out.printf("| %-61s |\n", "Login");
                line(65, "-");
                System.out.print(" Phone: ");
                phone = sc.nextLine();
                Pattern p = Pattern.compile("^[0]{1}[3,7,8,9,5]{1}[0-9]{8}+$");
                if (p.matcher(phone).find()) {
                    break;
                } else {
                    util.time(" Invalid phone number", 1);
                    util.clrscr();
                    continue;
                }
            }

            line(65, "-");
            System.out.print(" Password: ");
            Console con = System.console();
            char[] pass = con.readPassword();
            String password = String.valueOf(pass);
            password = MD5(password);
            line(65, "-");
            Boolean check = cusBL.Login(phone, password);

            count++;
            if (!check) {

                util.time(" Wrong phone or password", 1);

                util.clrscr();
                if (count == 3) {
                    while (true) {
                        String YN = util.onlyYN(" Do you want to create an account or not ?(Y/N): ");
                        switch (YN) {
                            case "Y":
                                register();
                                break;

                            case "N":
                                menu();
                                break;
                        }
                        break;
                    }

                }
                continue;
            } else {
                Customer cus = new Customer();
                cus.setPhone(phone);
                cus.setPassword(password);
                util.time(" Login successful", 1);
                main();
            }
            break;
        }
    }

    public void register() throws SQLException {
        util.clrscr();
        String phone;
        line(65, "-");
        System.out.printf("| %-61s |\n", "Register account");
        line(65, "-");
        System.out.print(" Input your name: ");
        String name = sc.nextLine();
        line(65, "-");
        System.out.print(" Input your email: ");
        String email = sc.nextLine();
        line(65, "-");
        while (true) {
            System.out.print(" Input your phone: ");
            phone = sc.nextLine();
            Pattern p = Pattern.compile("^[0]{1}[3,7,8,9,5]{1}[0-9]{8}+$");
            if (p.matcher(phone).find()) {
                break;
            } else {
                util.time(" Invalid phone number", 1);
                continue;
            }
        }
        line(65, "-");
        Console con = System.console();
        String password;
        while (true) {
            System.out.print(" Input your password: ");
            char[] pass = con.readPassword();
            password = String.valueOf(pass);
            if (password.length() < 8) {

                System.out.println(" Password must be greater than 8 and less than 25 characters ");
                continue;
            } else if (password.length() > 25) {
                System.out.println(" Password must be greater than 8 and less than 25 characters ");
                continue;
            }

            break;
        }

        password = MD5(password);
        line(65, "-");
        Boolean check = cusBL.verifyRegister(phone);
        if (!check) {
            System.out.println(" Phone number already exists.");
            while (true) {
                String choice = util.onlyYN(" Do you want to continue(Y/N): ");
                if (choice.equals("Y")) {
                    register();
                } else {
                    menu();
                }
            }
        } else {
            Customer cus = new Customer();
            cus.setName(name);
            cus.setEmail(email);
            cus.setPhone(phone);
            cus.setPassword(password);
            cusBL.Register(cus);
            util.time(" Register success", 1);
            util.clrscr();
            menu();
        }

    }

    public void main() throws SQLException {
        util.clrscr();
        Customer cus = cusBL.getCustomer();
        ArrayList<Booking> bookings = bookingBL.listBooking(cus.getID());
        line(65, "-");
        System.out.printf("| %-61s |\n", "Grab");
        line(65, "-");
        System.out.printf("| %-61s |\n", "1. Booking");
        line(65, "-");
        System.out.printf("| %-61s |\n", "2. View User's information");
        line(65, "-");
        System.out.printf("| %-61s |\n", "3. View Journeys History");
        line(65, "-");
        String choice1 = util.Choice(" Your choice[1-3]: ");
        switch (choice1) {
            case "1":
                booking();
                break;
            case "2":
                userInfo();
                break;
            case "3":
                int count = 0;
                journeyInfoHistory(0, 3);
                System.out.println("\n\t\t\t\t\t\t\t[1]-2-3-4-5");
                while (true) {
                    if (count == 0) {
                        System.out.print(" [1..5:Next page][E. Exit]: ");
                    } else {
                        System.out.print(" Only enter[1-5] or [E]: ");
                    }
                    String next = sc.nextLine().toUpperCase();
                    switch (next) {
                        case "1":
                            count = 0;
                            journeyInfoHistory(0, 3);
                            System.out.println("\n\t\t\t\t\t\t\t[1]-2-3-4-5");
                            continue;
                        case "2":
                            count = 0;
                            journeyInfoHistory(3, 6);
                            if (bookings.size() < 3) {
                                util.clrscr();
                                System.out.println(" The trip list is empty");
                            }
                            System.out.println("\n\t\t\t\t\t\t\t1-[2]-3-4-5");
                            continue;
                        case "3":
                            count = 0;
                            journeyInfoHistory(6, 9);
                            if (bookings.size() < 6) {
                                util.clrscr();
                                System.out.println(" The trip list is empty");
                            }
                            System.out.println("\n\t\t\t\t\t\t\t1-2-[3]-4-5");
                            continue;
                        case "4":
                            count = 0;
                            journeyInfoHistory(9, 12);
                            if (bookings.size() < 9) {
                                util.clrscr();
                                System.out.println(" The trip list is empty");
                            }
                            System.out.println("\n\t\t\t\t\t\t\t1-2-3-[4]-5");
                            continue;
                        case "5":
                            count = 0;
                            journeyInfoHistory(12, 15);
                            if (bookings.size() < 12) {
                                util.clrscr();
                                System.out.println(" The trip list is empty");
                            }
                            System.out.println("\n\t\t\t\t\t\t\t1-2-3-4-[5]");
                            continue;
                        case "E":
                            main();
                            break;
                        default:
                            count = 1;
                            continue;
                    }
                    break;
                }
                break;
        }
    }

    public void booking() throws SQLException {
        util.clrscr();
        line(65, "-");
        System.out.printf("| %-61s |\n", "Find driver");
        line(65, "-");
        System.out.print(" Start: ");
        String start = sc.nextLine();
        line(65, "-");
        System.out.print(" End: ");
        String end = sc.nextLine();
        line(65, "-");
        Random rd = new Random();
        float price = (rd.nextInt(30) + 5) * 5000;
        System.out.println(" Price: " + format.format(price) + " VND");
        line(65, "-");
        boo.setStart(start);
        boo.setEnd(end);
        boo.setPrice(price);
        int choice = util.Choose(" [1. Booking] - [2. Back]: ");
        switch (choice) {
            case 1:
                searchDriver();
                break;
            case 2:
                main();
                break;
        }

    }

    public void searchDriver() throws SQLException {
        Random rd = new Random();
        String[] words = boo.getStart().split("\\s");
        String str = words[1].concat(" ").concat(words[2]);
        ArrayList<Driver> drivers = driverBL.getListDriver(str);
        util.time(" Searching for the driver ....", 2);
        if (drivers.size() == 0) {
            System.out.println(" The driver is busy, please try again !");
            String c = util.onlyC(" Press C to Continue:");
            if (c.equals("C")) {
                main();
            }
        }
        int i;
        util.clrscr();
        line(133, "-");
        System.out.printf("| %-129s |\n", "The drivers are near you");
        line(133, "-");
        System.out.printf("| %-4s | %-18s | %-12s | %-20s | %-35s | %-25s |\n", "ID", "Driver_name", "Phone",
                "License_plates", "VehicleInfo", "Location");
        for (i = 0; i < drivers.size(); i++) {
            line(133, "-");
            System.out.printf("| %-4d | %-18s | %-12s | %-20s | %-35s | %-25s |\n", drivers.get(i).getID(),
                    drivers.get(i).getName(), "0" + formatPhone.format(Long.valueOf(drivers.get(i).getPhone())),
                    drivers.get(i).getLicensePlates(), drivers.get(i).getVehicleInfo(), drivers.get(i).getLocation());
        }
        line(133, "-");
        int id = rd.nextInt(i);
        Driver driver = driverBL.getDriverInfo(drivers.get(id).getID());
        boo.setDriverID(driver.getID());
        driverAccept();

    }

    public void driverAccept() throws SQLException {
        Driver driver = driverBL.getDriver();
        String choice = util.onlyYN(" [Y. Booking][N. Back]: ");
        switch (choice) {
            case "Y":
                util.clrscr();
                Random rd = new Random();
                String[] s = { "4 *", "5 *" };
                int star = 0;
                for (int i = 0; i < s.length; i++) {
                    star = rd.nextInt(2);
                }
                line(156, "-");
                System.out.printf("| %-152s |\n", "Yeah you have found a driver ");
                line(156, "-");
                System.out.printf("| %-3s | %-18s | %-4s | %-12s | %-18s | %-18s | %-14s | %-25s | %-16s |\n", "ID",
                        "Driver_name", "Star", "Phone", "Start", "End", "license_plates", "vehicleInfo", "location");
                line(156, "-");
                System.out.printf("| %-3d | %-18s | %-4s | %-12s | %-18s | %-18s | %-14s | %-25s | %-16s |\n",
                        driver.getID(), driver.getName(), s[star],
                        "0" + formatPhone.format(Long.valueOf(driver.getPhone())), boo.getStart(), boo.getEnd(),
                        driver.getLicensePlates(), driver.getVehicleInfo(), driver.getLocation());
                line(156, "-");
                String choose = util.onlyYN(" [Y. Keep trip][N. Cancel trip]: ");
                if (choose.equals("N")) {
                    main();
                }
                util.time(" Driver is coming ....", 2);
                Driver status1 = driverBL.updateStatus(1, driver.getID());
                boo = bookingBL.creatJourney(boo);
                // util.clrscr();
                System.out.println(" The trip begin ....");
                while (true) {
                    int select = util.Choose(" [1].End|[2]. View Journey's info: ");
                    switch (select) {
                        case 1:
                            payment();
                            util.clrscr();
                            int count = 0;
                            Customer cus = cusBL.getCustomer();
                            Driver status2 = driverBL.updateStatus(0, driver.getID());
                            Driver location = driverBL.updateLocation(boo, driver.getID());
                            line(47, "-");
                            System.out.printf("| %-43s |\n", " Rating the Journey");
                            line(47, "-");
                            System.out.printf("| %-43s |\n", "--- [1]. * (Very satisfied)");
                            line(47, "-");
                            System.out.printf("| %-43s |\n", "--- [2]. * * (Satisfied)");
                            line(47, "-");
                            System.out.printf("| %-43s |\n", "--- [3]. * * * (Normal)");
                            line(47, "-");
                            System.out.printf("| %-43s |\n", "--- [4]. * * * * (Not satisfied)");
                            line(47, "-");
                            System.out.printf("| %-43s |\n", "--- [5]. * * * * * (Disappointed)");
                            line(47, "-");
                            System.out.print(" Rating driver[1->5]: ");
                            while (true) {
                                if (count == 1) {
                                    System.out.print(" Only Enter[1-5]: ");
                                }
                                int rateStar = Integer.parseInt(sc.nextLine());
                                if (rateStar != 1 && rateStar != 2 && rateStar != 3 && rateStar != 4 && rateStar != 5) {
                                    count = 1;
                                    continue;
                                } else {
                                    RatingBL rateBL = new RatingBL();
                                    Rating rate = new Rating();
                                    rate.setStarRating(rateStar);
                                    rate = rateBL.ratingDriver(cus.getID(), driver.getID(), rate.getStarRating());
                                    while (true) {
                                        String pick = util.onlyYN(" [Y]. Continue to book - [N]. Exit: ");
                                        switch (pick) {
                                            case "Y":
                                                main();
                                                break;
                                            case "N":
                                                System.out.println(" Good bye");
                                                System.exit(0);
                                                break;
                                        }
                                        break;
                                    }

                                }

                                break;
                            }
                            break;
                        case 2:
                            util.clrscr();
                            boo = bookingBL.viewJourney();
                            System.out.println(boo.getDate());
                            line(148, "-");
                            System.out.printf("| %-144s |\n", "Journey details information");
                            line(148, "-");
                            System.out.printf("| %-3s | %-20s | %-12s | %-20s | %-20s | %-14s | %-25s | %-9s |\n", "ID",
                                    "Driver_name", "Phone", "Start", "End", "License_plates", "VehicleInfo", "Price",
                                    "Date of booking");
                            line(148, "-");
                            System.out.printf("| %-3d | %-20s | %-12s | %-20s | %-20s | %-14s | %-25s | %-9s |\n",
                                    driver.getID(), driver.getName(),
                                    "0" + formatPhone.format(Long.valueOf(driver.getPhone())), boo.getStart(),
                                    boo.getEnd(), driver.getLicensePlates(), driver.getVehicleInfo(),
                                    format.format(boo.getPrice()));
                            line(148, "-");
                            continue;
                    }
                    break;
                }
                break;
            case "N":
                main();
                break;
        }

    }

    public void userInfo() throws SQLException {
        util.clrscr();
        Customer cus = cusBL.getCustomer();
        Address address = addressBL.getAddress(cus.getID());
        line(134, "-");
        System.out.printf("| %-130s |\n", "Personal information");
        line(134, "-");
        System.out.printf("| %-4s | %-20s | %-30s | %-12s | %-52s |\n", "ID", "Fullname", "Email", "Phone", "Address");
        line(134, "-");
        System.out.printf("| %-4s | %-20s | %-30s | %-12s | %-52s |\n", cus.getID(), cus.getName(), cus.getEmail(),
                "0" + formatPhone.format(Long.valueOf(cus.getPhone())), address.getValue());
        line(134, "-");
        String select = util.onlyYN(" [Y. Continue]-[N. Log out]: ");
        switch (select) {
            case "Y":
                main();
                break;
            case "N":
                menu();
                break;
        }
    }

    public void journeyInfoHistory(int from, int to) throws SQLException {
        util.clrscr();
        int count = 0;
        Customer cus = cusBL.getCustomer();
        ArrayList<Booking> bookings = bookingBL.listBooking(cus.getID());
        if (bookings.size() == 0) {
            System.out.println(" You have not had a trip yet");
            String choice = util.onlyC(" Press C to Continue:");
            if (choice.equals("C")) {
                main();
            }
        } else {
            if (bookings.size() == 1) {
                System.out.println(" Hello " + cus.getName() + ", you have a total of " + bookings.size() + " trip\n");
            } else {
                System.out.println(" Hello " + cus.getName() + ", you have a total of " + bookings.size() + " trips\n");
            }
            if (from < bookings.size()) {
                line(131, "-");
                System.out.printf("| %-127s |\n", "Journey History");
                line(131, "-");
                System.out.printf("| %-2s | %-20s | %-12s | %-20s | %-20s | %-10s | %-25s |\n", "ID", "Driver_name",
                        "Driver_phone", "Start", "End", "Price", "Date of booking");
                line(131, "-");
            }
            for (int j = from; j < bookings.size(); j++) {
                if (j < to) {
                    Driver driver = driverBL.getDriverId(bookings.get(j).getDriverID());
                    System.out.printf("| %-2d | %-20s | %-12s | %-20s | %-20s | %-10s | %-25s |\n", j + 1,
                            driver.getName(), "0" + formatPhone.format(Long.valueOf(driver.getPhone())),
                            bookings.get(j).getStart(), bookings.get(j).getEnd(),
                            format.format(bookings.get(j).getPrice()), bookings.get(j).getDate());
                    line(131, "-");
                    // count++;
                    // from++;
                }

                // continue;
            }
            // String choice = util.onlyC(" Press C to Continue:");
            // if (choice.equals("C")) {
            // main();
            // }

        }
    }

    public void payment() throws SQLException {
        util.clrscr();
        int count = 0;
        Payment pay = new Payment();
        PaymentBL payBl = new PaymentBL();
        Customer cus = cusBL.getCustomer();
        line(65, "-");
        System.out.printf("| %-61s |\n", "Payment methods");
        line(65, "-");
        System.out.printf("| %-61s |\n", "1. Payment by card");
        line(65, "-");
        System.out.printf("| %-61s |\n", "2. Payment in cash");
        line(65, "-");
        System.out.print(" Your choice: ");
        while (true) {
            String choose;
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    util.clrscr();
                    System.out.println(" The price of your trip is: " + format.format(boo.getPrice()) + " VND");
                    choose = util.onlyYN(" [Y]. confirm payment [N]. Back: ");
                    if (choose.equals("N")) {
                        util.clrscr();
                        payment();
                    }
                    System.out.println(" Payment success");
                    pay.setMethod("Chuyển khoản");
                    break;
                case "2":
                    util.clrscr();
                    float money;
                    while (true) {
                        if (count == 1) {
                            util.clrscr();
                            System.out.println(" The amount you entered is not valid");
                        }
                        System.out.println(" The price of your trip is: " + format.format(boo.getPrice()) + " VND");
                        System.out.print(" Input the amount to pay: ");
                        try {
                            money = Float.parseFloat(sc.nextLine());
                        } catch (Exception e) {
                            count = 1;
                            continue;
                        }

                        if (money <= 0) {
                            util.clrscr();
                            System.out.println(" The amount you input must be greater than 0: ");
                            continue;
                        }
                        choose = util.onlyYN(" [Y]. Confirm payment [N]. Back: ");
                        if (choose.equals("N")) {
                            util.clrscr();
                            payment();
                        }
                        float residual = money - boo.getPrice();
                        if (money == boo.getPrice()) {
                            System.out.println(" Your balance is: " + format.format(residual) + " VND");
                            break;
                        } else if (money < boo.getPrice()) {
                            util.clrscr();
                            System.out.println(
                                    " The amount paid is greater than " + format.format(boo.getPrice()) + " VND");
                            continue;
                        } else {
                            System.out.println(" Your balance is: " + format.format(residual) + " VND");
                            break;
                        }

                    }

                    pay.setMethod("Tiền mặt");
                    break;
                default:
                    System.out.print(" Only enter[1-2]: ");
                    continue;

            }
            break;
        }

        pay.setCusID(cus.getID());

        pay = payBl.payment(pay);
        String c = util.onlyC(" Press c to continue: ");
        if (c.equals("C")) {

        }
    }

    private static String MD5(String text) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(text.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void line(int lenght, String symbol) {
        for (int i = 0; i < lenght; i++) {
            if (i == 0 || i == lenght - 1) {
                System.out.printf("+");
            } else {
                System.out.printf(symbol);
            }
        }
        System.out.print("\n");
    }

}