import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product {

    protected float price;

    public Product() {}

    public float getPrice() {
        return this.price;
    }

    public static void displayAvailableProducts() throws SQLException {
        ArrayList<String> books = new ArrayList<>();
        ArrayList<String> burgers = new ArrayList<>();

        Database db = new Database();
        Connection conn = db.getConnection();

        String getBooksQuery = "SELECT Title FROM books";
        PreparedStatement psBook = conn.prepareStatement(getBooksQuery);
        ResultSet rsBook = psBook.executeQuery();

        while (rsBook.next()) {
            books.add(rsBook.getString(1));
        }

        String getBurgersQuery = "SELECT Type FROM burgers";
        PreparedStatement psBurger = conn.prepareStatement(getBurgersQuery);
        ResultSet rsBurger = psBurger.executeQuery();

        while (rsBurger.next()) {
            burgers.add(rsBurger.getString(1));
        }

        System.out.println("Available books are:");

        for (String i : books) {
            System.out.print(i + " ");
            System.out.println();
        }

        System.out.println("Available burgers are:");

        for (String i : burgers) {
            System.out.print(i + " ");
            System.out.println();
        }
    }
}
