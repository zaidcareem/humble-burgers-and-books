import java.util.Scanner;

public class Uiux {
    public Uiux() {
        // welcome
        System.out.println("Welcome to Humble Burgers And Books\n\n");
    }
    public void registerOrSignIn() {
        Scanner in = new Scanner(System.in);
        System.out.print("Register (enter 1) OR Sign In (enter 2): ");
        int choice = in.nextInt();
        if (choice == 1) {
            // register user
            System.out.println("Register");
        } else if (choice == 2){
            // sign in user
            System.out.println("Sign In");
        } else {
            System.out.println("Please enter either 1 or 2.");
            registerOrSignIn();
        }
    }
    public String adminOrCustomer() {
        Scanner in = new Scanner(System.in);
        String role = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Continue as Admin (enter 1) or Customer (enter 2): ");
            int choice = in.nextInt();
            if (choice == 1) {
                valid = true;
                role = "admins";
            } else if (choice == 2) {
                valid = true;
                role = "customers";
            } else {
                System.out.println("Please enter either 1 or 2.");
            }
        }
        return role;
    }
}
