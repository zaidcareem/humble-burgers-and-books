import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public User() {

    }
    public void signIn(String username, String password) throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "SELECT username, password from admins WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Admin " + username + " is logged in.");
        } else {
            System.out.println("Username or Password incorrect");
        }

        conn.close();
        ps.close();
        rs.close();
    }
    public void signOut() {
        System.exit(1);
        System.out.println("Signed out, session over.");
    }
}
