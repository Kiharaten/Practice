import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbName = "practice09";
    private String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?characterEncoding=utf8";
    private String dbUser = "root";
    private String dbPass = "root";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbUrl,dbUser,dbPass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}