public class User {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void signOut() {
        // System.exit(1);
        System.out.println("Signed out, session over.");
    }
}
