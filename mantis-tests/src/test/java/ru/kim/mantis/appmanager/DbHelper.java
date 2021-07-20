package ru.kim.mantis.appmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
    }

    public List<String> getUser() {
        List<String> userData = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                    "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT USERNAME, EMAIL " +
                    "FROM mantis_user_table " +
                    "WHERE USERNAME != 'administrator'" +
                    "LIMIT 1");
            rs.next();
            userData.add(rs.getString("USERNAME"));
            userData.add(rs.getString("EMAIL"));
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return userData;
    }
}
