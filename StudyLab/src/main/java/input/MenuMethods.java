package input;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.Member;


public class MenuMethods {
	private static String dburl = "jdbc:mysql://localhost:9999/userdb";
	private static String dbUname = "root";
	private static String dbPassword = "mysql";
	private static String dbDriver = "com.mysql.jdbc.Driver";
	
	
	public static Connection getConnection() {

		loadDriver(dbDriver);
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbUname, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String userInput(Member member, String title, String body) {
		
		Connection con = getConnection();
		String result = "1";
		String sql = "insert into userdb." + member.getUserName() + " values(?,?)";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, member.getUserName());
			ps.setString(1, title);
			ps.setString(2, body);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = "0";
		}
		
		return result;
		
	}
	
	public static String editBody(Member member, String title) {
		return title;
		
		
		
	}
	
	public static String textBody(Member member, String title) {
		
		String temptitle = "initial";
		try (Connection con = getConnection()) {
			String sql = "SELECT * FROM " + member.getUserName() + " WHERE title = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				temptitle = rs.getString(2);
			}
			
			return temptitle;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temptitle;
		
		
	}
	
	

}