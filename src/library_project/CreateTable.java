package library_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTable {
	
	   //  Database credentials
	   
          CreateTable(String str){
        	  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        	  final String DB_URL = "jdbc:mysql://localhost/library";
        	  final String USER = "root";
        	  final String PASS = "hkm0101";
        	  
        	  Connection conn = null;
        	   Statement stmt = null;
        	   try{
        	      //STEP 2: Register JDBC driver
        	      Class.forName(JDBC_DRIVER);
        	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
        	      
        	      stmt = conn.createStatement();
        	      
        	      String sql = "create table admin(username varchar(255) primary key unique,password varchar(255))"; 
        	      stmt.executeUpdate(sql);
        	      System.out.println("admin table created");
        	      
        	      String sql2 = "insert into admin values('Admin','9580ab5d9db022c73d6678b07c86c9db')";
        	      stmt.executeUpdate(sql2);
        	      
        	      String sql3 = "create table librarian(Id varchar(255) primary key unique ,Name varchar(255),Address varchar(255),Contact_No bigint,Password varchar(255))";
        	      stmt.executeUpdate(sql3);
                  System.out.println("librarian table created");
        	      
        	      String sql4 = "create table student(RollNo bigint primary key unique,Name varchar(255),Contact_No bigint,Class varchar(255),Book_Issued varchar(255),Book_Author varchar(255),Issue_Date varchar(255),Renew_Date varchar(255))";
        	      stmt.executeUpdate(sql4);

        	      System.out.println("student table created");
        	      
        	      String sql5 = "create table book(Book_Name varchar(255),Author_Name varchar(255),Total bigint,Issued bigint,Remaining bigint)";
        	      stmt.executeUpdate(sql5);
                  System.out.println("book table created");
        	      
        	      String sql6 = "create table class(Name varchar(255) not null unique)";
        	      stmt.executeUpdate(sql6);
                  System.out.println("class table created");
        	   }catch(SQLException se){
        	     System.out.println("error in sql query");
        	   }catch(ClassNotFoundException e){
        	      //Handle errors for Class.forName
        	      System.out.println("Driver Exception");
        	   }catch(Exception e){
        		   System.out.println("other exception");
        	   }

          }
}
