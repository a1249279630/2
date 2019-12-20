package day08.Utils;
import java.sql.Connection;
import java.sql.DriverManager;


public class dbUtil {

    private String dbUrl = "jdbc:mysql://101.132.33.149/TBY";
    private String dbPassword = "root";
    private String dbUsername = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";

    public Connection con() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbPassword, dbUsername);
        return con;
    }

    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
