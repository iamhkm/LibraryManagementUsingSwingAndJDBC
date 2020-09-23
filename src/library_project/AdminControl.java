package library_project;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminControl {
	JFrame frame4;
	JButton addLibrarian,showLibrarian,deleteLibrarian,addClass,deleteClass,back;
      public void showOptions(){
    	  frame4 = new JFrame("Admin Section");
    	  frame4.getContentPane().setLayout(null);
    	  
    	  Font font = new Font("hkm",Font.BOLD,18);
    	  
    	  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    	  final String DB_URL = "jdbc:mysql://localhost/library";
    	  final String USER = "root";
    	  final String PASS = "hkm0101";
    	  
    	  
    	                  // Add New Librarian(Completed)
    	  
    	  addLibrarian = new JButton("Add Librarian");
    	  addLibrarian.setFont(font);
    	  addLibrarian.setBounds(100,10,200,40);
    	  addLibrarian.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame6 = new JFrame("Add Librarian");
    			  frame6.getContentPane().setLayout(null);
    			  
    			  Font font = new Font("hkm",Font.BOLD,18);
    			  Font font1 = new Font("hkm",Font.BOLD,14);
    			  
    			  JLabel lid = new JLabel("I'd");
    			  lid.setFont(font);
    			  lid.setBounds(10,10,100,40);
    			  frame6.add(lid);
    			  
    			  JTextField tid = new JTextField();
    			  tid.setFont(font1);
    			  tid.setBounds(120,10,250,40);
    			  tid.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (tid.getText().length() >= 30 ) // limit id to 30 characters
  			                e.consume();
  			        }
  			    });
    			  frame6.add(tid);
    			  
    			  JLabel lname = new JLabel("Name");
    			  lname.setFont(font);
    			  lname.setBounds(10,60,100,40);
    			  frame6.add(lname);
    			  
    			  JTextField tname = new JTextField();
    			  tname.setFont(font1);
    			  tname.setBounds(120,60,250,40);
    			  tname.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (tname.getText().length() >= 30 ) // limit name to 30 characters
  			                e.consume();
  			        }
  			    });
    			  frame6.add(tname);
    			  
    			  JLabel laddress = new JLabel("Address");
    			  laddress.setFont(font);
    			  laddress.setBounds(10,110,100,40);
    			  frame6.add(laddress);
    			  
    			  JTextField taddress = new JTextField();
    			  taddress.setFont(font1);
    			  taddress.setBounds(120,110,250,40);
    			  taddress.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (taddress.getText().length() >= 50 ) // limit address to 50 characters
  			                e.consume();
  			        }
  			    });
    			  frame6.add(taddress);
    			  
    			  JLabel lphoneNo = new JLabel("Contact No.");
    			  lphoneNo.setFont(font);
    			  lphoneNo.setBounds(10,160,110,40);
    			  frame6.add(lphoneNo);
    			  
    			  JTextField tphoneNo = new JTextField();
    			  tphoneNo.setFont(font1);
    			  tphoneNo.setBounds(120,160,250,40);
    			  tphoneNo.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (tphoneNo.getText().length() >= 10 ) // limit phone no to 10 characters
  			                e.consume();
  			        }
  			    });
    			  frame6.add(tphoneNo);
    			  
    			  JLabel lpassword = new JLabel("Password");
    			  lpassword.setFont(font);
    			  lpassword.setBounds(10,210,110,40);
    			  frame6.add(lpassword);
    			  
    			  JTextField tpassword = new JTextField();
    			  tpassword.setFont(font1);
    			  tpassword.setBounds(120,210,250,40);
    			  tpassword.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (tpassword.getText().length() >= 50 ) // limit password to 50 characters
  			                e.consume();
  			        }
  			    });
    			  frame6.add(tpassword);
    			  
    			  JLabel label = new JLabel();
    			  label.setFont(font);
    			  label.setForeground(Color.RED);
    			  label.setBounds(0,300,400,40);
    			  label.setVisible(false);
    			  frame6.add(label);
    			  
    			  JButton btn = new JButton("Add");
    			  btn.setFont(font1);
    			  btn.setBounds(150,260,100,40);
    			  btn.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  boolean status = true;
    					  String a = tid.getText().trim().toString();
    					  String b = tname.getText().trim().toString();
    					  String c = taddress.getText().trim().toString();
    					  String d = tphoneNo.getText().trim().toString();
    					  String p = tpassword.getText().trim().toString();
    					  String s = new String("");
    					  
    					  // Input filter
    					  {
    					  if(a.length()==0){
    						  s="Input field can't be left blank";
    						  status = false;
    					  }
    					  else if(b.length()==0){
    						  s="Input field can't be left blank";
    						  status=false;
    					  }
    					  else if(c.length()==0){
    						  s="Input field can't be left blank";
    						  status=false;
    					  }
    					  else if(p.length()==0){
    						  s="Input field can't be left blank";
    						  status=false;
    					  }
    					  else if(d.length()!=10){
    						  s="Contact Number should be of 10 digits";
    						  status=false;
    					  }
    					  else if(d.length()==10){
    	                	  try{
    	                		 Float.parseFloat(d);
    	                	  }catch(Exception ex){
    	                		  s="Not a valid phone number";
    	                		  status=false;
    	                	  }
    	                  }
    					  }
    					  if(status){
    						  Connection conn = null;
    				   	      Statement stmt = null;
    						  try{
    			        	      //STEP 2: Register JDBC driver
    			        	      Class.forName(JDBC_DRIVER);
    			        	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    			        	      
    			        	      stmt = conn.createStatement();
    			        	      String sql = "insert into librarian values('"+a+"','"+b+"','"+c+"',"+d+",'"+p+"')";
    			        	      stmt.executeUpdate(sql);
    			        	      label.setVisible(false);
    			        	      JOptionPane.showMessageDialog(null,"Librarian Added Successfully","Success",JOptionPane.OK_CANCEL_OPTION);
    			        	      frame6.dispose();
    			        	   }catch(SQLException se){
    			        		   label.setText("Librarian's Id already exist");
    			        	       label.setVisible(true);
    			        	   }catch(ClassNotFoundException ex){
    			        	      //Handle errors for Class.forName
    			        	      System.out.println("Driver Exception");
    			        	   }catch(Exception ex1){
    			        		   System.out.println("other exception");
    			        	   }
    					  }
    					  else{
    						 label.setText(s);
    						 label.setVisible(true);
    					  }
    				  }
    			  });
    			  frame6.add(btn);
    			  
    			  frame6.setSize(400,380);
    			  frame6.setVisible(true);
    			  frame6.setResizable(false);
    			  frame6.setLocationRelativeTo(null);
    		  }
    	  });
    	  frame4.add(addLibrarian);
    	  
    	                     // show list of librarians(Completed)
    	  
    	  showLibrarian = new JButton("Librarian's List");
    	  showLibrarian.setFont(font);
    	  showLibrarian.setBounds(100,70,200,40);
    	  showLibrarian.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  int x=0;
    			  
    			  Connection con;
    			  Statement stmt=null;
    			  
    			  try{
    				 Class.forName(JDBC_DRIVER);
    				 con = DriverManager.getConnection(DB_URL,USER,PASS);
    				 stmt = con.createStatement();
    				 
    				 String query = "SELECT COUNT(*) FROM librarian";   // Check if librarian table isn't empty
    				 ResultSet rs = stmt.executeQuery(query);
    				 
    				 while(rs.next()){
    					 x = rs.getInt(1);
    				 }
    				 
    			  }catch(Exception ex){
    				  System.out.println("query error");
    			  }
    			  
    			  if(x==0){
    				  JOptionPane.showMessageDialog(null,"Librarian list is empty");
    			  }
    			  else{
    				  
    				  String[] columnNames = {"I'd", "Name", "Address", "Contact No.","Password"};
    					JFrame frame1 = new JFrame("Database Search Result");
    					//frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    					frame1.setLayout(new BorderLayout()); 
    					//TableModel tm = new TableModel();
    					DefaultTableModel model = new DefaultTableModel();
    					model.setColumnIdentifiers(columnNames);
    					//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames()); 
    					//table = new JTable(model);
    					JTable table = new JTable();
    					table.setModel(model);
    					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    					table.setEnabled(false);
    					table.setFillsViewportHeight(true);
    					JScrollPane scroll = new JScrollPane(table);
    					scroll.setHorizontalScrollBarPolicy(
    					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    					scroll.setVerticalScrollBarPolicy(
    					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    					
    					TableColumn column = null;
    					column = table.getColumnModel().getColumn(0);   // set Column width
    					column.setPreferredWidth(250);
    					
    					column = table.getColumnModel().getColumn(1);   // set Column width
    					column.setPreferredWidth(250);
    					
    					column = table.getColumnModel().getColumn(2);   // set Column width
    					column.setPreferredWidth(374);
    					
    					column = table.getColumnModel().getColumn(3);   // set Column width
    					column.setPreferredWidth(100);
    					
    					column = table.getColumnModel().getColumn(4);   // set Column width
    					column.setPreferredWidth(374);
    					
    					String id= "";
    					String name= "";
    					String address = "";
    					long contact =0;
    					String password="";
    					try
    					{ 
    					Class.forName(JDBC_DRIVER); 
    					String sql = "select * from librarian";
    					
    					ResultSet rs = stmt.executeQuery(sql);
    					
    					while(rs.next())
    					{
    					id = rs.getString(1);
    					name = rs.getString(2);
    					address = rs.getString(3);
    					contact = rs.getLong(4); 
    					password = rs.getString(5);
    					model.addRow(new Object[]{id,name,address,contact,password});
    					}
    					}catch(Exception ex){
    						System.out.println("query error");
    					}

    					frame1.add(scroll);
    					frame1.setSize(600, 400);
    					frame1.setLocationRelativeTo(null);
    					frame1.setVisible(true);
    			  }
    		  }
    	  });
    	  frame4.add(showLibrarian);
    	  
    	                       // Delete Librarian(Completed)
    	  
    	  deleteLibrarian = new JButton("Delete Librarian");
    	  deleteLibrarian.setFont(font);
    	  deleteLibrarian.setBounds(100,130,200,40);
    	  deleteLibrarian.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame8 = new JFrame("Delete Librarian");
    			  frame8.getContentPane().setLayout(null);
    			  
    			  Font font = new Font("hkm",Font.BOLD,18);
    			  
    			  JLabel lb = new JLabel("Librarian's id");
    			  lb.setFont(font);
    			  lb.setBounds(0,10,150,50);
    			  frame8.add(lb);
    			  
    			  JTextField id = new JTextField();
    			  id.setFont(font);
    			  id.setBounds(140,20,200,30);
    			  id.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (id.getText().length() >= 20 ) // limit id to 30 characters
  			                e.consume();
  			        }
  			    });
    			  frame8.add(id);
    			  
    			  JLabel label = new JLabel();
    			  label.setFont(font);
    			  label.setForeground(Color.RED);
    			  label.setBounds(0,120,300,40);
    			  label.setVisible(false);
    			  frame8.add(label);
    			  
    			  JButton delete = new JButton("Delete");
    			  delete.setFont(font);
    			  delete.setBounds(130,70,150,40);
    			  delete.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					 String idfordelete = id.getText().toString();
    					 if(idfordelete.length()==0){
    						 label.setText("Id can't be left blank");
    						 label.setVisible(true);
    					 }
    					 else{
    					 Connection conn;
    					 Statement stmt;
    					 boolean validId = false;
    					 try{
			        	      //STEP 2: Register JDBC driver
			        	      Class.forName(JDBC_DRIVER);
			        	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			        	      
			        	      stmt = conn.createStatement();
			        	      
			        	      String sql2 = "select *from librarian";
			        	      ResultSet rs = stmt.executeQuery(sql2);
			        	      
			        	      while(rs.next()){
			        	    	  String xyz = rs.getString(1);
			        	    	  if(xyz.equals(idfordelete)){
					        	       validId=true;                 // check if librarian id exists
					        	       break;
			        	    	  }
			        	      }
			        	      
			        	      if(validId){
			        	    	  String sql = "delete from librarian where Id='"+idfordelete+"'";
			        	    	  stmt.executeUpdate(sql);
			        	    	  JOptionPane.showMessageDialog(null,"Librarian Deleted Successfully","Success",JOptionPane.OK_CANCEL_OPTION);
			        	    	  frame8.dispose();
			        	    	  label.setVisible(false);
			        	      }
			        	      else{
			        	    	  label.setText("Librarian's Id Doesn't exist");
				        	       label.setVisible(true);
			        	      }
			        	   }catch(SQLException se){
			        		   label.setText("Its a query error");
			        	       label.setVisible(true);
			        	   }catch(ClassNotFoundException ex){
			        	      //Handle errors for Class.forName
			        	      System.out.println("Driver Exception");
			        	   }catch(Exception ex1){
			        		   System.out.println("other exception");
			        	   }
    					 }
    				  }
    			  });
    			  frame8.add(delete);
    			  
    			  frame8.setSize(400,200);
    			  frame8.setVisible(true);
    			  frame8.setLocationRelativeTo(null);
    			  frame8.setResizable(false);
    			 
    		  }
    	  });
    	  frame4.add(deleteLibrarian);
    	  
    	                     // Add a new Class(Completed)
    	  
    	  addClass = new JButton("Add Class");
    	  addClass.setBounds(100,190,200,40);
    	  addClass.setFont(font);
    	  addClass.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame = new JFrame("Add Class");
    			  frame.setSize(400,225);
    			  frame.getContentPane().setLayout(null);
    			  
    			  JLabel lb= new JLabel("Class Name");
    			  lb.setFont(font);
    			  lb.setBounds(0,10,125,40);
    			  frame.add(lb);
    			  
    			  JTextField tb = new JTextField();
    			  tb.setFont(font);
    			  tb.setBounds(135, 15, 200, 40);
    			  tb.addKeyListener(new KeyAdapter() {
  			        @Override
  			        public void keyTyped(KeyEvent e) {
  			            if (tb.getText().length() >= 40 ) // limit class to 40 characters
  			                e.consume();
  			        }
  			    });
    			  frame.add(tb);
    			  
    			  JLabel error = new JLabel();
    			  error.setFont(font);
    			  error.setForeground(Color.RED);
    			  error.setBounds(10,150,375,40);
    			  error.setVisible(false);
    			  frame.add(error);
    			  
    			  JButton add = new JButton("Add");
    			  add.setFont(font);
    			  add.setBounds(145,80,100,40);
    			  add.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  error.setVisible(false);
    					  String  s= "";
    					  boolean condition = true;
    					  // Filter input
    					  if(tb.getText().trim().length()==0){
    						  s= "Input Field can't be left Blank";
    						  condition = false;
    					  }
    					  
    					  // No need to check if class already exist because class name is kept unique in class table and will be caught in catch block
    					  
    					  if(condition){
    						  Connection con;
    						  Statement stmt;
    						  
    						  try{
    							  Class.forName(JDBC_DRIVER);
    							  con = DriverManager.getConnection(DB_URL,USER,PASS);
    							  stmt = con.createStatement();
    							  
    							  String query = "insert into class values('"+tb.getText().trim().toString().toUpperCase()+"')";
    							  stmt.executeUpdate(query);
    							  error.setVisible(false);
    							  JOptionPane.showMessageDialog(null,"Class Added Successfullly","Success",JOptionPane.OK_CANCEL_OPTION);
    							  frame.dispose();
    						  }catch(Exception ex){
    							  
    							  s = "Can't Add Class, This Class Already Exists";
    							  error.setText(s);
    							  error.setVisible(true);
    						  }
    					  }
    					  else{
    						  error.setText(s);
							  error.setVisible(true);
    					  }
    					  
    				  }
    			  });
    			  frame.add(add);
    			  
    			  frame.setLocationRelativeTo(null);
    			  frame.setResizable(false);
    			  frame.setVisible(true);
    		  }
    	  });
    	  frame4.add(addClass);
    	  
    	                           // Delete an existing class(Completed)
    	  
    	  deleteClass = new JButton("Delete Class");
    	  deleteClass.setBounds(100,250,200,40);
    	  deleteClass.setFont(font);
    	  deleteClass.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame = new JFrame("Delete Class");
    			  frame.setSize(400,225);
    			  frame.getContentPane().setLayout(null);
    			  
    			  JLabel lb= new JLabel("Class Name");
    			  lb.setFont(font);
    			  lb.setBounds(20,15,125,40);
    			  frame.add(lb);
    			  
    			  Connection con;
    			  Statement stmt;
    			  ArrayList<String> al = new ArrayList<String>();
    			  
    			  try{
    				  Class.forName(JDBC_DRIVER);
    				  con = DriverManager.getConnection(DB_URL,USER,PASS);
    				  stmt = con.createStatement();
    				  
    				  String query = "select *from class";
    				  ResultSet rs = stmt.executeQuery(query);
    				  
    				  while(rs.next()){
    					  al.add(rs.getString(1));
    				  }
    				  
    			  }catch(Exception ex){
    				  System.out.println("query error");
    			  }
    			  
    			  
    			  String selection[] =  al.toArray(new String[al.size()]);
    			  
    			  JComboBox<String> box = new JComboBox<String>(selection);
    			  box.setFont(new Font("hkm",Font.BOLD,14));
    			  box.setBounds(150, 15, 150, 40);
    			  frame.add(box);
    			  
    			  JLabel error = new JLabel();
    			  error.setFont(font);
    			  error.setForeground(Color.RED);
    			  error.setBounds(10,150,375,40);
    			  error.setVisible(false);
    			  frame.add(error);
    			  
    			  JButton delete = new JButton("Delete");
    			  delete.setFont(font);
    			  delete.setBounds(145,80,100,40);
    			  delete.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  String  s= "";
    					  boolean condition = true;
    					  // Filter input
    					  if(box.getItemCount()==0){
    						  s= "No Class available to delete";
    						  condition = false;
    					  }
    					  
    					  // No need to check if class already exist because class name is kept unique in class table and will be caught in catch block
    					  
    					  if(condition){
    						  Connection con;
    						  Statement stmt;
    						  
    						  try{
    							  Class.forName(JDBC_DRIVER);
    							  con = DriverManager.getConnection(DB_URL,USER,PASS);
    							  stmt = con.createStatement();
    							  
    							  String query = "delete from class where Name='"+box.getSelectedItem().toString()+"'";
    							  stmt.executeUpdate(query);
    							  error.setVisible(false);
    							  JOptionPane.showMessageDialog(null,"Class Deleted Successfullly","Success",JOptionPane.OK_CANCEL_OPTION);
    							  frame.dispose();
    						  }catch(Exception ex){
    							 System.out.println("query error");
    						  }
    					  }
    					  else{
    						  error.setText(s);
							  error.setVisible(true);
    					  }
    					  
    				  }
    			  });
    			  frame.add(delete);
    			  
    			  frame.setLocationRelativeTo(null);
    			  frame.setResizable(false);
    			  frame.setVisible(true);
    		  }
    	  });
    	  frame4.add(deleteClass);
    	  
    	                        // Go Back
    	   
    	  back = new JButton("back");
    	  back.setBounds(294,292,100,30);
    	  back.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  frame4.dispose();
    			  new selectionPage().createPage();
    		  }
    	  });
    	  frame4.add(back);
    	  
    	  frame4.setSize(400,350);
    	  frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  frame4.setLocationRelativeTo(null);
    	  frame4.setResizable(false);
    	  frame4.setVisible(true);
      }
}
