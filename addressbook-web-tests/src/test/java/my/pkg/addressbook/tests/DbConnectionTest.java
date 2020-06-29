package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {
    @Test
    public void  testDbConnection () {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/addressbook?serverTimezone=UTC&user=root&password=");
Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select * from group_list");
            Groups groups = new Groups();
            while (resultSet.next()){
               groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getNString("group_footer")));
            }
            resultSet.close();
            st.close();
            conn.close();

            System.out.println(groups);
            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
