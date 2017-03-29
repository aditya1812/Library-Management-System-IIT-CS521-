


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static  String url;
    public  static String user;
    public static String password;
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    public DbConnection(String u,String username,String pass) {
    	this.url=u;
    	this.user=username;
    	this.password=pass;
    }
    
/*    public static Connection getConnection(String databaseName) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
			System.out.println("\n Driver is connected Successfully");

			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return null;
		} catch (Exception ex) {
			// handle the error
		ex.printStackTrace();
			return null;
		}
	}*/
}