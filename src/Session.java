import java.sql.SQLException;

public class Session {

    // the one and only
    protected Customer currentCustomer;
    protected boolean signedIn;

    public Session(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public String getCurrentCustomerUsername() {
        return this.currentCustomer.username;
    }

    public String getCurrentCustomerPassword() {
        return this.currentCustomer.password;
    }

    public void registerCurrentCustomer() throws SQLException {
        this.currentCustomer.register();
    }

    public void signCurrentCustomerIn() throws SQLException {
        this.signedIn = true;
        this.currentCustomer.signIn();
    }

    public void signCurrentCustomerOut() {
        this.signedIn = false;
        this.currentCustomer.signOut();
    }

    public boolean getSignedIn() {
        return this.signedIn;
    }
}
