import java.util.Scanner;

public class Uiux {
    public Uiux() {
        // welcome
        System.out.println("Welcome to Humble Burgers And Books\n\n");
    }
    public static void registerOrSignIn(int x) {
        Scanner in = new Scanner(System.in);
        System.out.print("Register (enter 1) OR Sign In (enter 2): ");
        int choice = in.nextInt();
    }
}
