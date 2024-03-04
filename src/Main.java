import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("------------ Welcome to Humble Burgers and Books ------------\n\n");
        boolean exit = false;

        while (!exit) {

            Scanner in = new Scanner(System.in);
            System.out.println("This is the HOME page................");
            System.out.println("Continue as..........................");
            System.out.println("Admin (Enter 1) || Customer (Enter 2)");
            System.out.println("EXIT  (Enter 0)");
            System.out.println("Enter choice: ");

            int choice;

            // handle input type mismatch errors
            try {
                choice = in.nextInt();
                in.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("* Please enter an integer *\n");
                in.nextLine();
                continue;
            }

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

                        System.out.println("Sign Out (Enter 0) || Remove naughty customer (Enter 1) :");
                        try {
                            choice1 = in.nextInt();
                            in.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer");
                            in.nextLine();
                            continue;
                        }

                        if (choice1 == 1) {

                            System.out.println("Enter naughty customer's username: ");
                            String naughtyCustomerUsername = in.nextLine();
                            Customer naughtyCustomer = new Customer(naughtyCustomerUsername);
                            adminUser.removeCustomer(naughtyCustomer);

                        } else if (choice1 == 0) {

                            adminUser.signOut();
                            break;

                        } else {

                            System.out.println("** Please enter 1 or 0 **\n");

                        }
                        System.out.println("\n");
                    }
                }

            } else if (choice == 2) {
                // customer section

                boolean goToHome = false;

                while (!goToHome) {

                    System.out.println("Sign In (Enter 1) || Register (Enter 2)");
                    System.out.println("Back to Home page (Enter 0)");
                    System.out.println("Enter choice: ");
                    int choice2;

                    try {
                        choice2 = in.nextInt();
                        in.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an integer");
                        in.nextLine();
                        continue;
                    }

                    if (choice2 == 1) {

                        System.out.println("Enter customer username: ");
                        String customerUsername = in.nextLine();
                        System.out.println("Enter customer password");
                        String customerPassword = in.nextLine();
                        Customer customerUser = new Customer(customerUsername, customerPassword);

                        boolean signedIn = customerUser.signIn();

                        if (signedIn) {

                            while (signedIn) {

                                System.out.println();
                                Product.displayAvailableProducts();
                                System.out.println();
                                System.out.println("Buy book (Enter 1) || Buy burger (Enter 2)");
                                System.out.println("Check your cumulative expense    (Enter 3)");
                                System.out.println("Sign Out (Enter 0)");
                                System.out.println("Enter choice: ");
                                int choice2Sub;
                                try {
                                    choice2Sub = in.nextInt();
                                    in.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please enter an integer");
                                    in.nextLine();
                                    continue;
                                }

                                if (choice2Sub == 0) {
                                    customerUser.signOut();
                                    signedIn = false;
                                } else if (choice2Sub == 1) {

                                    System.out.println("Enter book title: ");
                                    String bookTitle = in.nextLine();
                                    Book book = new Book(bookTitle);

                                    Database db = new Database();
                                    Connection conn = db.getConnection();
                                    String query = "SELECT Price FROM books WHERE Title = ?";
                                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                                    preparedStatement.setString(1, book.getTitle());

                                    ResultSet resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        book.price = resultSet.getFloat(1);
                                        customerUser.buyBook(book);
                                    } else {
                                        System.out.println("Book " + book.getTitle() + " is not in stock");
                                    }

                                    conn.close();
                                    preparedStatement.close();
                                    resultSet.close();

                                } else if (choice2Sub == 2) {

                                    System.out.println("Enter burger type: ");
                                    String burgerType = in.nextLine();
                                    Burger burger = new Burger(burgerType);

                                    Database db = new Database();
                                    Connection conn = db.getConnection();
                                    String query = "SELECT Price FROM burgers WHERE Type = ?";
                                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                                    preparedStatement.setString(1, burger.getType());

                                    ResultSet resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        burger.price = resultSet.getFloat(1);
                                        customerUser.buyBurger(burger);
                                    } else {
                                        System.out.println(burger.getType() + " Burger " + " is not in stock");
                                    }

                                    conn.close();
                                    preparedStatement.close();
                                    resultSet.close();

                                } else if (choice2Sub == 3) {
                                    float expense = customerUser.getCumulativeExpense();
                                    System.out.println("Customer " + customerUser.username + "'s cumulative expense is " + expense);
                                } else {
                                    System.out.println("Please enter 1 OR 2 OR 3 OR 0");
                                }
                            }
                        }
                    } else if (choice2 == 0) {
                        goToHome = true;

                    } else if (choice2 == 2) {

                        System.out.println("Enter username: ");
                        String customerUsername = in.nextLine();
                        System.out.println("Enter password");
                        String customerPassword = in.nextLine();
                        Customer customerUser = new Customer(customerUsername, customerPassword);

                        customerUser.register();
                    }
                }
            } else if (choice == 0) {
                System.out.println("Application Closed.");
                exit = true;
            }
            else {
                System.out.println("** Please enter either 1 or 2 or 0 ***");
            }
        }

    }
}