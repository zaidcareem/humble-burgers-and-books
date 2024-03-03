import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Humble Burgers and Books\n\n");
        boolean exit = false;

        while (!exit) {

            Scanner in = new Scanner(System.in);
            System.out.println("Continue as...");
            System.out.println("Admin (Enter 1) || Customer (Enter 2)");
            System.out.println("EXIT  (Enter 0)");
            System.out.println("Enter choice: ");

            int choice;

            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer\n");
                continue;
            }

            in.nextLine();

            if (choice == 1) {

                // admin section
                System.out.println("Enter admin username: ");
                String adminUsername = in.nextLine();
                System.out.println("Enter admin password: ");
                String adminPassword = in.nextLine();
                Admin adminUser = new Admin(adminUsername, adminPassword);
                boolean signedIn = adminUser.signIn();

                if (signedIn) {

                    System.out.println("Admin can only remove customers");

                    int choice1 = 1;

                    while (choice1 == 1) {

                        System.out.println("Sign Out (Enter 0) || Remove naughty customer (Enter 1): ");
                        choice1 = in.nextInt();
                        in.nextLine();

                        if (choice1 == 1) {

                            System.out.println("Enter naughty customer's username: ");
                            String naughtyCustomerUsername = in.nextLine();
                            Customer naughtyCustomer = new Customer(naughtyCustomerUsername);
                            adminUser.removeCustomer(naughtyCustomer);

                        } else if (choice1 == 0) {

                            adminUser.signOut();
                            break;

                        } else {

                            System.out.println("Please enter 1 or 0.\n");

                        }
                        System.out.println("\n");
                    }
                }

            } else if (choice == 2) {
                // customer section
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