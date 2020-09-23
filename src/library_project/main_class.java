package library_project;

import java.sql.*;

public class main_class {

	public static void main(String[] args) {
		selectionPage selectPage = new selectionPage();
		selectPage.createPage();
		
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	   */
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		  final String DB_URL = "jdbc:mysql://localhost/";

		   //  Database credentials
		  final String USER = "root";
		   final String PASS = "hkm0101";
		   
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE DATABASE library";
		      stmt.executeUpdate(sql);
		      new CreateTable("library");
		   }catch(SQLException se){
		      //Handle errors for JDBC
			   System.out.println("sql query exception");
		      //new CreateTable("library");
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      System.out.println("Other error");
		   }
		   
		   
		
	}

}
