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
        String checkQuery = "SELECT * FROM customers WHERE username = ?";
        PreparedStatement ps1 = conn.prepareStatement(checkQuery);
        ps1.setString(1, this.username);
        ResultSet rs1 = ps1.executeQuery();

        // if username exists
        if (rs1.next()) {
            System.out.println("Username already exists, try another please.");

        // if username does not exist, that means customer can use that username
        } else {

            String sqlQuery = "INSERT INTO customers VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ps.setFloat(3, 0); // initial cumulative cost is always 0 (third column is cumulative cost)

            System.out.println("Customer " +  this.username + " SUCCESSFULLY registered.");

            ps.executeUpdate();
            ps.close();
        }

        conn.close();
        ps1.close();
        rs1.close();
    }

    // customer Sign In
    public boolean signIn() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "SELECT username, password from customers WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, this.username);
        ps.setString(2, this.password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Customer " + this.username + " is signed in.");
            conn.close();
            ps.close();
            rs.close();
            return true;
        } else {
            System.out.println("Username or Password incorrect");
            conn.close();
            ps.close();
            rs.close();
            return false;
        }
    }

    public float getCumulativeExpense() throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String sqlQuery = "SELECT CumulativeExpense from customers WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, this.username);

        ResultSet rs = ps.executeQuery();
        float cumulativeExpense = 0;

        if (rs.next()) {
            cumulativeExpense = rs.getFloat(1);
        }
        conn.close();
        ps.close();
        rs.close();

        return cumulativeExpense;
    }

    // buy books, updates cumulative expense of customers here also
    public void buyBook(Book book) throws SQLException {
        Database db = new Database();
        Connection conn = db.getConnection();

        String query = "SELECT * FROM books WHERE Title = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, book.getTitle());
        ResultSet resultSet = preparedStatement.executeQuery();

        // 1 is returned if the book exists, otherwise 0
        if (resultSet.next()) {
            String sqlQuery = "UPDATE books SET InStock = InStock - 1 WHERE Title = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, book.getTitle());

            // run the update query
            ps.executeUpdate();

            System.out.println("Customer " + this.username + " bought the book titled " + book.getTitle());


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

        String query = "SELECT * FROM burgers WHERE Type = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, burger.getType());
        ResultSet resultSet = preparedStatement.executeQuery();

        // true is returned if the burger exists, otherwise false
        if (resultSet.next()) {
            String sqlQuery = "UPDATE burgers SET InStock = InStock - 1 WHERE Type = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, burger.getType());

            // run the update query
            ps.executeUpdate();

            ps.close();

            System.out.println("Customer " + this.username + " bought a " + burger.getType() + " burger");

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
