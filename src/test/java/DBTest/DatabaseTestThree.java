package DBTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTestThree {

    public static void main(String[] args) {

        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created for batch 15");
            Statement statement = conn.createStatement();
            String query = "select * from person";
            ResultSet rset = statement.executeQuery(query);
            //ResultSetMetaData - object that contains information about the result
            //information such as in  the table how many columns are there,
            //name of the columns, rows and number of the rows
            ResultSetMetaData rsmetaData = rset.getMetaData();
            //extract data from resultset and store it in java data structure
            List<Map<String, String>> listFromRset = new ArrayList<>();
            //iterate through the rows
            while (rset.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                // iterate over columns
                for (int i = 1; i <= rsmetaData.getColumnCount(); i++) {
                    String key = rsmetaData.getColumnName(i);
                    String value = rset.getString(key);
                    map.put(key, value);
                }
                System.out.println(map);
                listFromRset.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
