import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Uiux ui = new Uiux();
        ui.registerOrSignIn();
        String role = ui.adminOrCustomer();
        if (role.equals("admins")) {
            // do admin functions
        } else {
            // do customer
        }
    }
}