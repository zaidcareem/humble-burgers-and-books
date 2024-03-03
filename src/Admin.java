import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {

    // no register function for admin because admin will be directly added to the database.
    // there will be only one admin
    // this will be done through sql constraints

    public Admin(String username, String password) throws SQLException {
        super(username, password);
    }

    public boolean signIn() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "SELECT username, password from admins WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, this.username);
        ps.setString(2, this.password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Admin " + this.username + " is logged in.\n");
            conn.close();
            ps.close();
            rs.close();
            return true;
        } else {
            System.out.println("Username or Password incorrect\n");
            conn.close();
            ps.close();
            rs.close();
            return false;
        }
    }

    // deletes a customer from the database
    public void removeCustomer(Customer customer) throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        // check whether the customer is actually in the database
        String query = "SELECT EXISTS(SELECT * FROM customers WHERE username = ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, customer.username);
        ResultSet resultSet = preparedStatement.executeQuery();

        // if customer exists
        if (resultSet.getInt(1) == 1) {

            String deleteQuery = "DELETE FROM customers WHERE username = ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(deleteQuery);
            preparedStatement1.setString(1, customer.username);

            // run the delete query
            preparedStatement1.executeUpdate();

            preparedStatement1.close();
        // if customer does not exist
        } else {
            System.out.println("Customer is not in the database");
        }

        conn.close();
        preparedStatement.close();
        resultSet.close();
    }
}
