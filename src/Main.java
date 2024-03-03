import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Humble Burgers and Books\n\n");
        boolean exit = false;

        while (!exit) {

            Scanner in = new Scanner(System.in);
            System.out.println("\nContinue as...");
            System.out.println("Admin (Enter 1) || Customer (Enter 2)");
            System.out.println("EXIT  (Enter 0)");
            System.out.print("\nEnter choice: ");
            int choice = in.nextInt();
            if (choice == 1) {
                System.out.print("\nEnter admin username: ");
                String adminUsername = in.nextLine();
                System.out.print("\nEnter admin password: ");
                String adminPassword = in.nextLine();
                Admin adminUser = new Admin(adminUsername, adminPassword);
                boolean signedIn = adminUser.signIn();
                if (signedIn) {
                    System.out.println("\nAdmin can only remove customers\n");
                    int choice1 = 1;
                    while (choice1 == 1) {
                        System.out.print("\nSign Out (Enter 0) || Remove naughty customer (Enter 1): ");
                        choice1 = in.nextInt();
                        if (choice1 == 1) {
                            System.out.print("\nEnter naughty customer's username: ");
                            String naughtyCustomerUsername = in.nextLine();
                            Customer naughtyCustomer = new Customer(naughtyCustomerUsername);
                            adminUser.removeCustomer(naughtyCustomer);
                        } else if (choice1 == 0) {
                            adminUser.signOut();
                            break;
                        } else {
                            System.out.println("\nPlease enter 1 or 0.");
                        }
                    }
                }

            } else if (choice == 2) {
                
            } else if (choice == 0) {
                System.out.println("Application Closed.");
                exit = true;
            }
            else {
                System.out.println("Please enter either 1 or 2 or 0");
            }
        }

    }
}