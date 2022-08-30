package RestAssuredDataDrivenTesting;

import java.sql.*;

public class DataBaseConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String dburl="jdbc:oracle:thin:@it163aia.it.sedc.internal.vodafone.com:1521:BSCSPRE";
        String username="oca_t";
        String password="ocapwd";
        String query="select * from MPSCFTAB where CFCODE in('6210')";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection(dburl,username,password);

        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery(query);

        while (rs.next()){
            String cfcode=rs.getString(1);
            String cfvalue=rs.getString(2);
            String recver=rs.getString(3);
            String des=rs.getString(4);
            System.out.println(cfcode+" "+cfvalue+" "+recver+" "+des);
        }
        con.close();

    }
}
