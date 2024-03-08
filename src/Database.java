/*
 * This is the class where we create the connection to our MySQL Database
 *
 *  -----> Assign your own credentials in lines 18 - 20 <-----
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    Connection link;

    public Database() {}

    public Connection getConnection() {
        String user = "root"; // MySQL username
        String password = "Password"; // MySQL password
        String databaseName = "bnbshop"; // which database to be used, refer 'sql/runmefirst.sql' file

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register the driver
            link = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, user, password); // create connection
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other Exception");
            e.printStackTrace();
        }
        return link;
    }
}
