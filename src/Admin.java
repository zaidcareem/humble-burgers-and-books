import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {


    public Admin() {

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

        if (resultSet.getInt(1) == 1) {
            String deleteQuery = "DELETE FROM customers WHERE username = ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(deleteQuery);
            preparedStatement1.setString(1, customer.username);

            // run the delete query
            preparedStatement1.executeUpdate();
        } else {
            System.out.println("Customer is not in the database");
        }
    }
}
