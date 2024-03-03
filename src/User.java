public class User {

    protected String username;
    protected String password;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void signOut() {
        // return to home screen / welcome screen
        System.out.println(this.username + " signed out, session over.");
    }
}
