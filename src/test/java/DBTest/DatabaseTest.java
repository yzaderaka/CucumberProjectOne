package DBTest;

import java.sql.*;

public class DatabaseTest {

    public static void main(String[] args) {
        /*
        to build the connection with the database we need 3 things, url, username, password
         */
        String urldb = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String usernamedb = "syntax_hrm";
        String passworddb = "syntaxhrm123";

        try {
            // we need to establish the connection to the database
            Connection conn = DriverManager.getConnection(urldb, usernamedb, passworddb);
            System.out.println("Connection is created for batch 15");
            // create a statement to send sql queries
            Statement statement = conn.createStatement();
            /*
            when we send any query to the database then db returns
            result set (tables with rows and columns
             */
            ResultSet rSet= statement.executeQuery("select firstname, lastname from person");
            rSet.next();

            String fName=rSet.getString("FirstName");
            String lName=rSet.getString("LastName");
            System.out.println(fName+" "+lName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
