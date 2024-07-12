import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class OnlineReservationSystem {
    private static Map<String, String> reservations = new HashMap<>();
    private static Random random = new Random();

    public static void register() {
        Scanner sc = new Scanner(System.in);

        // Register
        System.out.println("------Register------");

        System.out.print("First name: ");
        String firstName = sc.nextLine();
        System.out.print("Last name: ");
        String lastName = sc.nextLine();

        System.out.println("Enter your birthday and gender");
        System.out.print("Date of birth: ");
        String dob = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();

        System.out.print("Create Password: ");
        String createPassword = sc.nextLine();

        login(sc, firstName, createPassword);
    }

    public static void login(Scanner sc, String firstName, String createPassword) {
        // Login
        System.out.println("------Login------");
        System.out.print("Enter username: ");
        String userName = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (firstName.equals(userName) && createPassword.equals(password)) {
            System.out.println("Login Successful!");
            fill_details(sc);
        } else {
            System.out.println("Login failed. Incorrect username or password");
            System.out.println();
            System.out.println("------Enter correct details------");
            System.out.println();
            login(sc, firstName, createPassword); // Recursive call for retrying login
        }
    }

    public static void fill_details(Scanner sc) {
        System.out.println();
        System.out.println("------Reservation Form------");

        System.out.print("Enter your fullname: ");
        String fullName = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();

        String trainName = getTrainName(trainNumber);
        System.out.println("Train name: " + trainName);

        System.out.print("Enter class type (e.g., Sleeper, AC): ");
        String classType = sc.nextLine();

        System.out.print("Enter date of journey (yyyy-mm-dd): ");
        String dateOfJourney = sc.nextLine();

        System.out.print("Enter departure place: ");
        String departure = sc.nextLine();

        System.out.print("Enter destination place: ");
        String destination = sc.nextLine();

        System.out.println();
        System.out.println();
        System.out.println("------Summary------");
        System.out.println("Name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("Departure: " + departure);
        System.out.println("Destination: " + destination);

        System.out.print("Press 'insert' to confirm the reservation: ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("insert")) {
            String pnr = generatePNR();
            // reservations.put(pnr, fullName);
            System.out.println();
            System.out.println("Reservation confirmed! Your PNR is: " + pnr);
            showMenu(sc);
        } else {
            System.out.println();
            System.out.println("Reservation not confirmed. Please try again.");
            fill_details(sc); // Retry if reservation not confirmed
        }
    }

    public static void showMenu(Scanner sc) {
        while (true) {
            System.out.println("------Menu------");
            System.out.println("1. Confirm Tickets");
            System.out.println("2. Cancel Tickets");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Booking already confirmed.");
                    return;
                case 2:
                    cancelTickets(sc);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void cancelTickets(Scanner sc) {
        System.out.println("------Cancellation Form------");
        System.out.print("Enter PNR number: ");
        String pnr = sc.nextLine();
    
        if (pnr.matches("PNR\\d+")) { 
            System.out.println("Reservation found for PNR: " + pnr);
            System.out.print("Do you want to cancel this reservation? (yes/no): ");
            String confirm = sc.nextLine();
    
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println("Reservation cancelled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR format. Please enter a valid PNR.");
        }
    }
    

    public static String getTrainName(String trainNumber) {
        switch (trainNumber) {
            case "12798":
                return "Venkatadri Super Fast Express";
            case "17230":
                return "Sabari Express";
            case "12704":
                return "FALAKNUMA EXP";
            case "12713":
                return "SATAVAHANA EXP";
            case "12705":
                return "GNT SC EXP";
            case "12794":
                return "RAYALASEEMA EXP";
            default:
                return "Unknown Train";
        }
    }

    public static String generatePNR() {
        return "PNR" + random.nextInt(100000);
    }

    public static void main(String[] args) {
        register();
    }
}