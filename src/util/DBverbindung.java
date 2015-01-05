package util;

import java.sql.*;

/**
 *
 * @author mihaisava
 */
public class DBverbindung {
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String dbName = "arc";
	private static String driver = "com.mysql.jdbc.Driver"; 
	private static String userName = "root"; 
	private static String password = "";//"12345";
	private static Connection conn;
	
	
	// static Connection conn = null;
	public DBverbindung(Connection conn) {
		super();
		this.conn = conn;
	}
	
	
	public static Connection getConn() {
		return conn;
	}


	public static void setConn(Connection conn) {
		DBverbindung.conn = conn;
	}


	public static void dbconnect(){
		try { 
			Class.forName(driver).newInstance(); 
			 conn = DriverManager.getConnection(url+dbName,userName,password); 
			System.out.println("connected");
			
			
//			Statement st = conn.createStatement(); 
//			ResultSet res = st.executeQuery("SELECT * FROM kunde"); 
//			while (res.next()) { 
//				int id = res.getInt("ID"); 
//				String msg = res.getString("Name");
//				String msg2 = res.getString("Valabilitatectr");
//				System.out.println(id + "\t" + msg + "\t" + msg2); 
//			}


		} catch (Exception e) { e.printStackTrace(); } 	
	}
	
	

	public static void dbdisconect(){
		try { 
			conn.close();	
			System.out.println("disconnected");
		} catch (Exception e) { e.printStackTrace(); } 	
	}

}
