import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends User {

    public Customer(String username) {
        super(username);
    }

    public Customer(String username, String password) {
        super(username, password);
    }

    // customer Register
    public void register() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        // check whether username already exists
        String checkQuery = "SELECT EXISTS(SELECT * FROM customers WHERE username = ?)";
        PreparedStatement ps1 = conn.prepareStatement(checkQuery);
        ps1.setString(1, this.username);
        ResultSet rs1 = ps1.executeQuery();

        // if username exists
        if (rs1.getInt(1) == 1) {
            System.out.println("Username already exists, try another please.");
            ps1.close();
            rs1.close();
        // if username does not exist
        } else {
            String sqlQuery = "INSERT INTO customers VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ps.setFloat(3, 0);

            System.out.println("Added customer " +  this.username + " to database.");

            ps.executeUpdate();
            ps.close();
        }

        conn.close();
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

        conn.close();
        ps.close();
        rs.close();

        return rs.getFloat(1);
    }

    // buy books, updates cumulative expense of customers here also
    public void buyBook(Book book) throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String query = "SELECT EXISTS(SELECT * FROM books WHERE Title = ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, book.getTitle());
        ResultSet resultSet = preparedStatement.executeQuery();

        // 1 is returned if the book exists, otherwise 0
        if (resultSet.getInt(1) == 1) {
            String sqlQuery = "UPDATE books SET InStock = InStock - 1 WHERE Title = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, book.getTitle());

            // run the update query
            ps.executeUpdate();

            // update the CumulativeExpense on the customers table
            String expenseUpdateQuery = "Update customers SET CumulativeExpense = CumulativeExpense + ? WHERE username = ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(expenseUpdateQuery);
            preparedStatement1.setFloat(1, book.getPrice());
            preparedStatement1.setString(2, this.username);

            preparedStatement1.executeUpdate();

            ps.close();
            preparedStatement1.close();
        } else {
            System.out.println("Book is not on database.");
        }

        conn.close();
        preparedStatement.close();
        resultSet.close();
    }

    // buy burgers, update cumulative expense of customer also
    public void buyBurger(Burger burger) throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String query = "SELECT EXISTS(SELECT * FROM burgers WHERE Type = ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, burger.getType());
        ResultSet resultSet = preparedStatement.executeQuery();

        // 1 is returned if the burger exists, otherwise 0
        if (resultSet.getInt(1) == 1) {
            String sqlQuery = "UPDATE burgers SET InStock = InStock - 1 WHERE Type = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, burger.getType());

            // run the update query
            ps.executeUpdate();

            ps.close();

            // update the CumulativeExpense on the customers table
            String expenseUpdateQuery = "Update customers SET CumulativeExpense = CumulativeExpense + ? WHERE username = ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(expenseUpdateQuery);
            preparedStatement1.setFloat(1, burger.getPrice());
            preparedStatement1.setString(2, this.username);

            preparedStatement1.executeUpdate();
            preparedStatement1.close();
        } else {
            System.out.println("Burger is not on database.");
        }

        conn.close();
        preparedStatement.close();
    }
}
