package CLI.DatabaseHandling;

import CLI.Customer;
import CLI.Vendor;
import CLI.TicketPool;

import java.sql.*;

public class VendorDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ticketingsystem"; // DB details
    private static final String USER = "root"; // DB username
    private static final String PASSWORD = "1553"; // DB password

    // Method to insert vendor data into the database
    public void saveVendor(Vendor vendor) {
        String query = "INSERT INTO vendor (vendor_name, vendor_email, vendor_password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vendor.getVendorName());
            preparedStatement.setString(2, vendor.getVendorEmail());
            preparedStatement.setString(3, vendor.getVendorPassword());
            preparedStatement.executeUpdate();

            System.out.println("Vendor " + vendor.getVendorName() + " added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method to save customer



    // method to load vendor
    public Vendor getVendorByEmail(String email, String password) {
        String query = "SELECT * FROM vendor WHERE vendor_email = ?";
        Vendor vendor = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getString("vendor_password").equals(password)) {
                vendor = new Vendor();
                if(password.equals(resultSet.getString("vendor_password"))) {
                    vendor.setVendorName(resultSet.getString("vendor_name"));
                    vendor.setVendorEmail(resultSet.getString("vendor_email"));
                    vendor.setVendorPassword(resultSet.getString("vendor_password"));

                    System.out.println("Login Successfully!, Welcome " + vendor.getVendorName());
                }else {
                    System.out.println("Incorrect Password...");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendor; // Returns the Vendor object or null if no record was found
    }

    // method to get the vendor ID
    public Vendor getVendorIdByEmail(String email, TicketPool ticketPool) {
        String query = "SELECT * FROM vendor WHERE vendor_email = ?";
        Vendor vendor = null; //default value

        try(Connection connect = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connect.prepareStatement(query)){

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int vendorId = resultSet.getInt("vendor_id");
                String name = resultSet.getString("vendor_name");

                vendor = new Vendor(vendorId,name,0,0,ticketPool);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return vendor;
    }
}


