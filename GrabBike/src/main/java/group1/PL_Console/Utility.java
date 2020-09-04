package group1.PL_Console;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Utility {
    Scanner sc = new Scanner(System.in);

    public String Choice(String choose) {
        String choice;
        System.out.print(choose);
        choice = sc.nextLine();
        while (true) {
            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                System.out.print(" Only enter[1->3]: ");
                choice = sc.nextLine().toUpperCase();
                continue;

            }
            break;
        }

        return choice;
    }

    public String onlyYN(String choose) {
        String choice;
        System.out.print(choose);
        choice = sc.nextLine().toUpperCase();
        while (true) {
            if (!choice.equals("Y") && !choice.equals("N")) {
                System.out.print(" Only enter Y or N: ");
                choice = sc.nextLine().toUpperCase();
                continue;

            }
            break;
        }

        return choice;
    }

    public int Choose(String str) {
        int choice;
        System.out.print(str);
        choice = Integer.parseInt(sc.nextLine());
        while (true) {
            if (choice != 1 && choice != 2) {
                System.out.print(" Only enter[1-2]: ");
                choice = Integer.parseInt(sc.nextLine());
                continue;

            }
            break;
        }

        return choice;
    }

    public String onlyC(String choose) {
        String choice;
        System.out.print(choose);
        choice = sc.nextLine().toUpperCase();
        while (true) {
            if (!choice.equals("C")) {
                System.out.print(" Only enter C: ");
                choice = sc.nextLine().toUpperCase();
                continue;

            }
            break;
        }

        return choice;
    }

    public void time(String str, int time) {
        try {
            System.out.println(str);
            Thread.sleep(TimeUnit.SECONDS.toMillis(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clrscr() {
        // Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }
    
}
