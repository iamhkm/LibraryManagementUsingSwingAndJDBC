package library_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminLogin {
	JFrame frame2;
	private JLabel username,password;
	JTextField adminname;
	JPasswordField adminpass;
	private JButton login,goBack;
	private JLabel warning;
     public void draw(){
    	 frame2 = new JFrame("Admin Login");
    	 frame2.getContentPane().setLayout(null);
    	 
    	 Font font = new Font("hkm",Font.BOLD,18);
    	 
    	 warning = new JLabel("Error!! Username or password is incorrect");
    	 warning.setFont(new Font("hkm",Font.BOLD+Font.ITALIC,14));
    	 warning.setForeground(Color.RED);
    	 warning.setBounds(10, 170, 400, 30);
    	 warning.setVisible(false);
    	 frame2.add(warning);
    	 
    	 username = new JLabel("Username");
    	 username.setFont(font);
    	 username.setBounds(10,10,100,40);
    	 frame2.add(username);
    	 
    	 adminname = new JTextField();
    	 adminname.setFont(font);
    	 adminname.setBounds(120,20,200, 30);
    	 frame2.add(adminname);
    	 
    	 password = new JLabel("Password");
    	 password.setFont(font);
    	 password.setBounds(10,60,100,40);
    	 frame2.add(password);
    	 
    	 adminpass = new JPasswordField();
    	 adminpass.setFont(font);
    	 adminpass.setBounds(120,70,200, 30);
    	 frame2.add(adminpass);
    	 
    	 login = new JButton("Log In");
    	 login.setBounds(10,125,150,30);
    	 login.addActionListener(new ActionListener(){
    		 @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
    			 
    			  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
            	  final String DB_URL = "jdbc:mysql://localhost/library";
            	  final String USER = "root";
            	  final String PASS = "hkm0101";
            	  String s="";
            	  String y="";
            	  
            	  String a = adminname.getText().trim().toString();
            	  String b = adminpass.getText().toString();
            	  
            	  Connection conn = null;
            	   Statement stmt = null;
            	   try{
            	      Class.forName(JDBC_DRIVER);
            	      
            	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
            	      
            	      stmt = conn.createStatement();
            	      
            	      String sql = "select *from admin"; 
            	      ResultSet rs = stmt.executeQuery(sql);
            	      
            	      while(rs.next()){
            	      s= rs.getString(1);
            	      y = rs.getString(2);
            	      }
            	      
            		  
            		  StringBuffer sb=new StringBuffer();
            	        MessageDigest md;
            			try {
            				md = MessageDigest.getInstance("MD5");
            				md.update(b.getBytes());
            		        
            		        byte byteData[] = md.digest();
            		        for (int i = 0; i < byteData.length; i++) {
            		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            		        }
            			} catch (NoSuchAlgorithmException e2) {
            				// TODO Auto-generated catch block
            				e2.printStackTrace();
            			}
            			
            			if(s.equals(a)&& sb.toString().equals(y)){
            				frame2.dispose();
                		    warning.setVisible(false);
                			AdminControl ctrl = new AdminControl();
                			ctrl.showOptions();
                			 }
                			 else{
                				warning.setVisible(true); 
                			 }
            			}
            	      
            	   catch(SQLException se){
            	     System.out.println("query exception");
            	   }catch(ClassNotFoundException e1){
            	      //Handle errors for Class.forName
            	      System.out.println("Driver Exception");
            	   }catch(Exception e2){
            		   System.out.println("other exception");
            	   }
    		 }
    	 });
    	 frame2.add(login);
    	 
    	 goBack = new JButton("Back");
    	 goBack.setBounds(235,125,150,30);
    	 goBack.addActionListener(new ActionListener(){
    		 public void actionPerformed(ActionEvent e){
    			 frame2.dispose();
    			 new selectionPage().createPage();
    		 }
    	 });
    	 frame2.add(goBack);
    	 frame2.setSize(400, 250);
    	 frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame2.setLocationRelativeTo(null);
    	 frame2.setVisible(true);
    	 frame2.setResizable(false);
     }
}
