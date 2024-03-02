import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends User {

    protected String username;
    protected String password;

    public Customer(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // customer Register
    public void register() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "INSERT INTO customers VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, this.username);
        ps.setString(2, this.password);
        ps.setFloat(3, 0);

        ps.executeUpdate();
    }

    // customer Sign In
    public void signIn() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "SELECT username, password from customers WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, this.username);
        ps.setString(2, this.password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Customer " + this.username + " is logged in.");
        } else {
            System.out.println("Username or Password incorrect");
        }

        conn.close();
        ps.close();
        rs.close();
    }

    public float getCumulativeExpense() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "SELECT CumulativeExpense from customers WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, this.username);

        ResultSet rs = ps.executeQuery();

        return rs.getFloat(1);
    }

    // buy books, updates cumulative expense of customers here also
    public void buyBook(Book book) throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String query = "SELECT EXISTS(SELECT * FROM books WHERE Title = ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, book.title);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.getInt(1) == 1) {
            String sqlQuery = "UPDATE books SET InStock = InStock - 1 WHERE Title = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, book.title);

            ps.executeUpdate();

            // update the CumulativeExpense on the customers table
            String expenseUpdateQuery = "Update customers SET CumulativeExpense = CumulativeExpense + ? WHERE username = ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(expenseUpdateQuery);
            preparedStatement1.setFloat(1, book.getPrice());
            preparedStatement1.setString(2, this.username);

            preparedStatement1.executeUpdate();

        } else {
            System.out.println("Book is not on database.");
        }
    }

    public void buyBurgers(Burger burger) {
        
    }
}
