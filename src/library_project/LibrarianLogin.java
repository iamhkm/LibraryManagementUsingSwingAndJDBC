package library_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LibrarianLogin {
	private JFrame frame3;
	private JLabel username,password;
	private JTextField librarianname;
	private JPasswordField librarianpass;
	private JButton login,goBack;
	private JLabel warning;
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  final String DB_URL = "jdbc:mysql://localhost/library";
	  final String USER = "root";
	  final String PASS = "hkm0101";
        public void draw2(){
        	frame3 = new JFrame("Librarian Login");
       	    frame3.getContentPane().setLayout(null);
       	 
       	 Font font = new Font("hkm",Font.BOLD,18);
       	 
       	warning = new JLabel("Error!! Username or password is incorrect");
   	 warning.setFont(new Font("hkm",Font.BOLD+Font.ITALIC,14));
   	 warning.setForeground(Color.RED);
   	 warning.setBounds(10, 170, 400, 30);
   	 warning.setVisible(false);
   	 frame3.add(warning);
       	 
       	 username = new JLabel("I'd");
       	 username.setFont(font);
       	 username.setBounds(10,10,100,40);
       	 frame3.add(username);
       	 
       	 librarianname = new JTextField();
       	 librarianname.setFont(font);
       	 librarianname.setBounds(120,20,200, 30);
       	 librarianname.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyTyped(KeyEvent e) {
		            if (librarianname.getText().length() >= 30 ) // limit name to 30 characters
		                e.consume();
		        }
		    });
       	 frame3.add(librarianname);
       	 
       	 password = new JLabel("Password");
       	 password.setFont(font);
       	 password.setBounds(10,60,100,40);
       	 frame3.add(password);
       	 
       	 librarianpass = new JPasswordField();
       	 librarianpass.setFont(font);
       	 librarianpass.setBounds(120,70,200, 30);
       	 librarianpass.addKeyListener(new KeyAdapter() {
		        @SuppressWarnings("deprecation")
				@Override
		        public void keyTyped(KeyEvent e) {
		            if (librarianpass.getText().length() >= 50 ) // limit name to 30 characters
		                e.consume();
		        }
		    });
       	 frame3.add(librarianpass);
       	 
       	 JLabel label = new JLabel();
       	 label.setFont(font);
       	 label.setForeground(Color.RED);
       	 label.setVisible(false);
       	 label.setBounds(0,175,300,40);
       	 frame3.add(label);
       	 
       	 login = new JButton("Log In");
       	 login.setBounds(10,125,150,30);
       	 login.addActionListener(new ActionListener(){
       		 @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
       			 boolean condition = true;
       			 String user = librarianname.getText().trim().toString();
       			 String pass = librarianpass.getText().trim().toString();
       			 { 
       			 if(user.length()==0){
       				 condition = false;
       			 }
       			 if(pass.length()==0){
       				 condition = false;
       			 }
       			 
       		 }
       			 
       			 if(condition){
       				 Connection con;
       				 Statement stmt;
       				 boolean validId = false;
       				 String pass2 = "";
       				 try{
       					 Class.forName(JDBC_DRIVER);
       					 con = DriverManager.getConnection(DB_URL,USER,PASS);
       					 stmt = con.createStatement();
       					 
       					String sql = "select *from librarian";
		        	      ResultSet rs = stmt.executeQuery(sql);
		        	      
		        	      while(rs.next()){
		        	    	  String xyz = rs.getString(1);
		        	    	  if(xyz.equals(user)){ 
		        	    		  pass2=rs.getString(5);
				        	       validId=true;
				        	       break;
		        	    	  }
		        	      }
		        	      
		        	      if(validId){
		        	    	  if(pass.equals(pass2)){
		        	    		new LibrarianControl().showOptions2();
		        	    		frame3.dispose();
		        	    		label.setVisible(false);
		        	    	  }
		        	    	  else{
		        	    		  label.setText("Wrong password");
		        	    		  label.setVisible(true);
		        	    	  }
		        	      }
		        	      
		        	      else{
		        	    	  label.setText("No such user exist");
		        	    	  label.setVisible(true);
		        	      }
       					 
       				 }catch(SQLException e1){
       					 
       				 }catch(ClassNotFoundException e2){
       					 
       				 }catch(Exception e3){
       					 
       				 }
       			 }
       			 else{
       				 label.setText("No field can't be left blank");
       				 label.setVisible(true);
       			 }
       		 }
       	 });
       	 frame3.add(login);
       	 
       	 goBack = new JButton("Back");
       	 goBack.setBounds(235,125,150,30);
       	 goBack.addActionListener(new ActionListener(){
       		 public void actionPerformed(ActionEvent e){
       			frame3.dispose();
   			 new selectionPage().createPage();
       		 }
       	 });
       	 frame3.add(goBack);
       	 
       	 frame3.setSize(400, 250);
       	 frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	 frame3.setLocationRelativeTo(null);
       	 frame3.setVisible(true);
       	 frame3.setResizable(false);
        }
}
