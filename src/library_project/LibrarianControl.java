package library_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LibrarianControl {
	JFrame frame5;
	JButton issueBook,returnBook,renewBook,searchDetails,addBook,deleteBook,back;
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  final String DB_URL = "jdbc:mysql://localhost/library";
	  final String USER = "root";
	  final String PASS = "hkm0101";
      public void showOptions2(){
    	  frame5 = new JFrame("Librarian Section");
    	  frame5.getContentPane().setLayout(null);
    	  
          Font font = new Font("hkm",Font.BOLD,18);
          Font font1 = new Font("hkm",Font.BOLD,18);
		  Font font2 = new Font("hkm",Font.BOLD,14);
		  
		                               // Issuing a book coding (Completed)
    	  
    	  issueBook = new JButton("Issue Book");
    	  issueBook.setFont(font);
    	  issueBook.setBounds(100,10,200,40);                     
    	  issueBook.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame9 = new JFrame("Issue Book");
    			  frame9.getContentPane().setLayout(null);
    			  
    			  JLabel detail = new JLabel("Student's Details");
    			  detail.setBounds(100,10,200,30);
    			  detail.setFont(new Font("hkm",Font.BOLD,20));	  
    			  frame9.add(detail);
    			  
    			  JLabel lrollno = new JLabel("Roll No.");
    			  lrollno.setFont(font1);
    			  lrollno.setBounds(10,50,100,40);
    			  frame9.add(lrollno);
    			  
    			  JTextField trollno = new JTextField();
    			  trollno.setFont(font2);
    			  trollno.setBounds(120,50,250,40);
    			  trollno.setToolTipText("must be a number");
    			  trollno.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (trollno.getText().length() >= 20 ) // limit roll no to 20 characters
    			                e.consume();
    			        }
    			    });
    			  frame9.add(trollno);
    			  
    			  JLabel lname = new JLabel("Name");
    			  lname.setFont(font1);
    			  lname.setBounds(10,100,100,40);
    			  frame9.add(lname);
    			  
    			  JTextField tname = new JTextField();
    			  tname.setFont(font2);
    			  tname.setBounds(120,100,250,40);
    			  tname.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (tname.getText().length() >= 30 ) // limit name to 30 characters
    			                e.consume();
    			        }
    			    });
    			  frame9.add(tname);
    			  
    			  JLabel lphoneNo = new JLabel("Contact No.");
    			  lphoneNo.setFont(font1);
    			  lphoneNo.setBounds(10,150,110,40);
    			  frame9.add(lphoneNo);
    			  
    			  JTextField tphoneNo = new JTextField();
    			  tphoneNo.setFont(font2);
    			  tphoneNo.setBounds(120,150,250,40);
    			  tphoneNo.setToolTipText("must be a valid 10 digit number");
    			  tphoneNo.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (tphoneNo.getText().length() >= 10 ) // limit phone number to 10 characters
    			                e.consume();
    			        }
    			    });
    			  frame9.add(tphoneNo);
    			  
    			  JLabel student_class = new JLabel("Class");
    			  student_class.setFont(font1);
    			  student_class.setBounds(10,200,110,40);
    			  frame9.add(student_class);
    			  
    			  
    			  /* Step1. Retrieve book name and author name from book table
    			   * Step2. Retrieve class from class table
    			   * Step3. Put all these data in various ArrayList
    			   * Step4. Convert ArrayList into Arrays
    			   * Step5. Put these arrays into corresponding JComboBoxes
    			   */
    			  Connection con;
    			  Statement stmt;
    			  ArrayList<String> al = new ArrayList<String>();
    			  ArrayList<String> al2 = new ArrayList<String>();
    			  try{
    				  Class.forName(JDBC_DRIVER);
    				  con = DriverManager.getConnection(DB_URL,USER,PASS);
    				  stmt = con.createStatement();
    				  
    				  String sql = "select *from class";
    				  ResultSet rs = stmt.executeQuery(sql);
    				  
    				  while(rs.next()){
    					  String s = rs.getString(1);
    					  al.add(s);
    				  }
    				  
    				  String sql2 = "select *from book";
    				  ResultSet rs2 = stmt.executeQuery(sql2);
    				  
    				  while(rs2.next()){
    					  String s = rs2.getString(1);
    					  if(al2.contains(s)){
    						  continue;
    					  }
    					  al2.add(s);
    				  }  
    				  
    				 
    				  
    			  }catch(SQLException ex){
    				  System.out.println("query Exception");
    			  }catch(ClassNotFoundException ex2){
    				  System.out.println("Driver Exception");
    			  }catch(Exception ex3){
    				  System.out.println("other Exception");
    			  }
    			  
    			 
    			  String selection[] = al.toArray(new String[al.size()]);
    			  JComboBox<String> cb = new JComboBox<String>(selection);
    			  cb.setBounds(125,205,100,30);
    			  frame9.add(cb);  
    			  
    			  JLabel book = new JLabel("Book");
    			  book.setFont(font1);
    			  book.setBounds(10,250,110,40);
    			  frame9.add(book);
    			  
    			  JComboBox<String> cb3 = new JComboBox<String>();
    			  cb3.setBounds(280,255,100,30);
    			  frame9.add(cb3); 
    			   
    			  String selection2[] = al2.toArray(new String[al2.size()]);
    			  JComboBox<String> cb2 = new JComboBox<String>(selection2);
    			  
    			  // In the author ComboBox we need to give only those author who have written book selected in book ComboBox
    			  cb2.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  cb3.removeAllItems();
    					  ArrayList<String> al3 = new ArrayList<String>();
    					  String s = cb2.getSelectedItem().toString();
    					  Connection con;
    					  Statement stmt;
    					  try{
    						  Class.forName(JDBC_DRIVER);
    						  con = DriverManager.getConnection(DB_URL,USER,PASS);
    						  stmt = con.createStatement();
    						  
    						  String query = "select Author_Name from book where Book_Name='"+s+"'";
    						  System.out.println(query);
    						  ResultSet rs = stmt.executeQuery(query);
    						  
    						  while(rs.next()){
    							  String s1 = rs.getString(1);
    							  al3.add(s1);
    						  }
    						  System.out.println(al3);
    						  
    					  }catch(Exception exp){
    						System.out.println("query error");  
    					  }
    					  
    					  System.out.println("OK");
    					  String selection3[] = new String[al3.size()];
    					  System.out.println("OK");
    	    			  al3.toArray(selection3); 
    	    			  System.out.println("OK");
    	    			  for(int i=0;i<selection3.length;i++){
    	    				  cb3.addItem(selection3[i]);
    	    			  }
    					  
    				  }
    			  });
    			  cb2.setBounds(75,255,100,30);
    			  frame9.add(cb2);  
    			  
    			  JLabel author = new JLabel("Author");
    			  author.setFont(font1);
    			  author.setBounds(200,250,80,40);
    			  frame9.add(author);
    			   
    			 /* String selection3[] = new String[al3.size()];
    			  al3.toArray(selection3);
    			  JComboBox<String> cb3 = new JComboBox<String>(selection3);
    			  cb3.setBounds(280,255,100,30);
    			  frame9.add(cb3);  
    			  */
    			   
    			  JLabel label = new JLabel();
    			  label.setForeground(Color.RED);
    			  label.setFont(new Font("hkm",Font.BOLD,16));
    			  label.setBounds(0,390,400,40);
    			  label.setVisible(false);
    			  frame9.add(label);
    			  
    			  JLabel label2= new JLabel();
    			  label2.setForeground(Color.RED);
    			  label2.setFont(new Font("hkm",Font.BOLD,16));
    			  label2.setBounds(0,370,400,40);
    			  label2.setVisible(false);
    			  frame9.add(label2);
    			  
    			  JButton btn = new JButton("Issue");
    			  btn.setFont(font2);
    			  btn.setBounds(125,325,150,30);
    			  btn.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
						String rollno = trollno.getText().trim().toString();
    					  String name = tname.getText().trim().toString();
    					  @SuppressWarnings("unused")
						String contact = tphoneNo.getText().trim().toString();
    					  boolean criteria = true;
    					  String ss ="";
    					  
    					  long y=0;
    					  
    					  // Filtering the ComboBox(Check if ComboBox isn't empty)
    					  if(cb.getItemCount()==0){
    						  criteria=false;
    						  ss="Ask admin to add class, no class available";
    						  
    					  }
    					  
    					  else if(cb2.getItemCount()==0 || cb3.getItemCount()==0){
    						  criteria = false;
    						  ss = "No book available to issue add book first";
    					  }
    					  
    					  // Filtering if any input field isn't empty
    					  else if(trollno.getText().trim().toString().length()==0 || tname.getText().trim().toString().length()==0 || tphoneNo.getText().trim().toString().length()==0){
    						  criteria=false;
    						  ss="Input field can't be left blank";
    					  }
    					  
    					  // Filter for roll number to be of number type
    					  else if(trollno.getText().trim().toString().length()!=0){
    						  try{
    						  Long.parseLong(trollno.getText().trim().toString());
    						  if(tphoneNo.getText().trim().toString().length()!=10){
        						  criteria=false;
        						  ss = "Phone number should contain 10 digit";
        					  }
        					  
        					 // Filter if 10 digit input is of type number 
        					  if(tphoneNo.getText().trim().toString().length()==10){
        						  try{y=Long.parseLong(tphoneNo.getText().trim().toString());
        						  }catch(Exception ex2){
        							  criteria=false;
        							  ss = "Not a valid phone number";
        						  }
        					  }
						  }catch(Exception ex2){
							  criteria=false;
							  ss = "Roll Number should be number only";
						  }
    					  }
    					      					 
    					
    					  // Here we will check if enough book is remaining to issue a new book
    					  try{
    						  Class.forName(JDBC_DRIVER); 
    						  Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
    	    				  Statement stmt = con.createStatement();
    	    				  String sql = "select *from book";
    	    				  ResultSet rs = stmt.executeQuery(sql);
    	    				  
    	    				  while(rs.next()){
    	    					  String s = rs.getString(1);
    	    					  String s2 = rs.getString(2);
    	    					  int i = rs.getInt(5);
    	    					  
    	    					  
    	    					  if(s.equals(cb2.getSelectedItem().toString()) && s2.equals(cb3.getSelectedItem().toString())){
    	    						  if(i<=0){
    	    							  criteria=false;
    	    							  ss="Not enough book to issue check book status";
    	    						  }
    	    					  }
    	    				  }
    					  }catch(Exception excep){
    						  System.out.println("Exception in query");
    					  } 
    					  // Here the main functions are working after filtering all inputs
    					  if(criteria){
    						  
    						  String classofStudent =cb.getSelectedItem().toString();
        					  String bookIssued = cb2.getSelectedItem().toString();
        					  String author = cb3.getSelectedItem().toString();
    						    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    							Calendar c = Calendar.getInstance();
    							c.setTime(new Date());// Now use today date.
    							String cd = sdf.format(c.getTime());
    							c.add(Calendar.DATE, 15);// Set renew data after 15 days
    							String rd = sdf.format(c.getTime());
    						  
    						  Connection con;
    						  Statement stmt;
    						  
    						  
    						  
    						  try{
    							  Class.forName(JDBC_DRIVER);
    							  con = DriverManager.getConnection(DB_URL,USER,PASS);
    							  stmt = con.createStatement();
    							  
    							  String query = "insert into student values("+Long.parseLong(rollno)+",'"+name+"',"+y+",'"+classofStudent+"','"+bookIssued+"','"+author+"','"+cd+"','"+rd+"')";
    							  stmt.executeUpdate(query);
    							  
    							  
    							  String query2 = "UPDATE book SET Issued=(Issued+1),Remaining=(Remaining-1) WHERE Book_Name='"+bookIssued+"' "+"and Author_Name='"+author+"' "+"and Remaining > 0";
    							  stmt.executeUpdate(query2);
    							  System.out.println("OK");
    							  
    							  JOptionPane.showMessageDialog(null,"Book Issued to "+name+" successfully","Success",JOptionPane.OK_CANCEL_OPTION);

    							  
    							  label2.setVisible(false);
    							  frame9.dispose();
    						  }catch(SQLException e4){
    							 label2.setText("Book Already Issued To This Student");
    							 label2.setVisible(true);
    						  } catch (ClassNotFoundException e1) {
								System.out.println("Driver error");
							}catch(Exception e5){
								System.out.println("other exception");
							}
    					  }
    					  else{
    						  label2.setText(ss);
    						  label2.setVisible(true);
    					  }
    					  
    				  }
    			  });
    			  frame9.add(btn);
    			  
    			  frame9.setSize(400, 450);
    			  frame9.setLocationRelativeTo(null);
    			  frame9.setResizable(false);
    			  frame9.setVisible(true);
    		  }
    	  });
    	  frame5.add(issueBook);
    	  
    	                                      // Returning a book coding (Completed)
    	  
    	  returnBook = new JButton("Return Book");
    	  returnBook.setFont(font);                                   
    	  returnBook.setBounds(100,70,200,40);
    	  returnBook.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame10 = new JFrame("Return Book");
    			  frame10.getContentPane().setLayout(null);
    			  
    			  JLabel lroll= new JLabel("Roll No.");
    			  lroll.setFont(font1);
    			  lroll.setBounds(10,10,110,40);
    			  frame10.add(lroll);
    			  
    			  JTextField troll = new JTextField();
    			  troll.setFont(font2);
    			  troll.setBounds(120, 10, 250, 40);
    			  troll.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (troll.getText().length() >= 20 ) // limit roll no to 20 characters
    			                e.consume();
    			        }
    			    });
    			  frame10.add(troll);
    			  
    			   JLabel lb = new JLabel();
    			   lb.setFont(font);
    			   lb.setBounds(10,130,350,30); 
    			   lb.setForeground(Color.RED);
    			   lb.setVisible(false);
    			   frame10.add(lb); 
    			   
    			  
    			  JButton btn = new JButton("Return");
    			  btn.setFont(font2);
    			  btn.setBounds(125,90,150,30);
    			  btn.addActionListener(new ActionListener(){
    				 public void actionPerformed(ActionEvent e){
    					 String s = troll.getText().trim().toString();
    					 String s1 = new String();
    					 boolean condition = true;
    					 
    					 // Input filter for roll number
    					 if(s.length()==0){
    						 s1 = "Input field can't be left blank";
    						 condition = false;
    					 }
    					 
    					 // Roll number filter for number type
    					 else if(s.length()!=0){ try{
    						 Long.parseLong(s);
    					 }catch(Exception ex){
    						 s1= "Roll number should be valid number";
    						 condition = false;
    					 }
    					 }
    					 
    					 
    					 
    					 // Check if book issued to this roll number
    					 
    					     Connection con;
    						 Statement stmt;
    						 try{
    						 Class.forName(JDBC_DRIVER);
    						 con = DriverManager.getConnection(DB_URL,USER,PASS);
    						 stmt = con.createStatement();
    						 
    						 String s2 = "select *from student";
    						 ResultSet rs = stmt.executeQuery(s2);
    						 
    						 while(rs.next()){
    							 if(rs.getLong(1)==Long.parseLong(s)){
    								 condition=true;
    								 break;
    							 }
    							 else{
    								 condition=false;
    								 s1="No book issued to this roll number";
    							 }
    						 }
    						 
    					 }catch(Exception exe){
    						 System.out.println("Query error");
    					 }
    					 
    					 if(condition){
    						 // Now retrieve the current date and renew date
    						 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    							Calendar c = Calendar.getInstance();
    							c.setTime(new Date());// Now use today date.
    							String cd = sdf.format(c.getTime());
    							Connection conn;
    							Statement stmtt;
    							String ss1= "";
    							
    							try{
    							Class.forName(JDBC_DRIVER);
    							conn = DriverManager.getConnection(DB_URL,USER,PASS);
    							stmtt = conn.createStatement();
    							String ss= "select Renew_Date from student where RollNo="+Long.parseLong(s);
    							ResultSet rs = stmtt.executeQuery(ss);
    							
    							while(rs.next()){
    								ss1=rs.getString(1);
    								break;
    							}
    							
    							}catch(Exception ex){
    								System.out.println("Query error");
    							}
    							
    							// Now check if renew date has been passed so that we can impose fine
    							
    							System.out.println(ss1);
    							
    							String dateStart = ss1;
    							String dateStop = cd;
    							System.out.println(dateStart);
    							System.out.println(dateStop);
    							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    							Date d1 = null;
    							Date d2 = null;
    							long diff;
    							long diffDays;

    							try {
    								d1 = format.parse(dateStart);
    								d2 = format.parse(dateStop);
    								diff = d2.getTime() - d1.getTime();
    								diffDays = diff / (24 * 60 * 60 * 1000);
    								System.out.println(diffDays);
    								
    								if(diffDays>0){
    									//"Return date was "+ss1+". Now Collect fine of "+diffDays+"for "+diffDays+" Days"
    									String buttons[]={"Yes","No"};
    							        int optionReturns = JOptionPane.showOptionDialog(null
    							                                                        ,"Return date was "+ss1+". Now Collect fine of "+diffDays+"rupees for "+diffDays+" Days\n"+"Fine Collected?"
    							                                                        ,"Collect Fine"
    							                                                        ,JOptionPane.NO_OPTION
    							                                                        ,JOptionPane.PLAIN_MESSAGE
    							                                                        ,null
    							                                                        ,buttons
    							                                                        ,"default");
    							        if(optionReturns == 0){
    							        	Connection connn;
    							        	Statement stmttt;
    							        	try{
    							        		
    							        		
    							        		String a="",b="";
    			    							Class.forName(JDBC_DRIVER);
    			    							connn = DriverManager.getConnection(DB_URL,USER,PASS);
    			    							stmttt = connn.createStatement();
    			    							
    			    							String query= "select Book_Issued,Book_Author from student where RollNo="+Long.parseLong(s);
    			    							ResultSet rs = stmttt.executeQuery(query);
    			    							
    			    							while(rs.next()){
    			    								a = rs.getString(1);
    			    								b=rs.getString(2);
    			    							}
    			    							
    			    							String query2 = "UPDATE book SET Issued=(Issued-1),Remaining=(Remaining+1) WHERE Book_Name='"+a+"' "+"and Author_Name='"+b+"' ";
    			    							stmttt.executeUpdate(query2);
    			    							
    			    							
    			    							String ss= "delete from student where RollNo="+Long.parseLong(s);
    			    							stmttt.executeUpdate(ss);
    			    							
    			    							
    			    							JOptionPane.showMessageDialog(null,"Book Returned Successfully");
    			    							}catch(Exception ex){
    			    								System.out.println("Query error");
    			    							}
    							            
    							        }
    							        else if(optionReturns == 1)
    							            JOptionPane.showMessageDialog(null,"Try again with collecting fine");
    							        else if(optionReturns == -1)
    							            JOptionPane.showMessageDialog(null,"Error!!!");
    								}
    								
    								else{
    									try{
    										String a = "",b="";
			    							Class.forName(JDBC_DRIVER);
			    							conn = DriverManager.getConnection(DB_URL,USER,PASS);
			    							stmtt = conn.createStatement();
			    							
			    							String query= "select Book_Issued,Book_Author from student where RollNo="+Long.parseLong(s);
			    							ResultSet rs = stmtt.executeQuery(query);
			    							
			    							while(rs.next()){
			    								a = rs.getString(1);
			    								b=rs.getString(2);
			    							}
			    							
			    							String query2 = "UPDATE book SET Issued=(Issued-1),Remaining=(Remaining+1) WHERE Book_Name='"+a+"' "+"and Author_Name='"+b+"' ";
			    							stmtt.executeUpdate(query2);
			    							
			    							String ss= "delete from student where RollNo="+Long.parseLong(s);
			    							stmtt.executeUpdate(ss);
			    							JOptionPane.showMessageDialog(null,"Book Returned Successfully");
			    							}catch(Exception ex){
			    								System.out.println("Query error");
			    							}
							            
    								}
    								
    								} catch (Exception exp) {
    								System.out.println("exception");
    							}
    						 
    					 }
    					 else{
    						 lb.setText(s1);
    						 lb.setVisible(true);
    					 }
    					 
    				 } 
    			  });
    			  frame10.add(btn);
    			  
    			  frame10.setSize(400,200);
    			  frame10.setLocationRelativeTo(null);
    			  frame10.setResizable(false);
    			  frame10.setVisible(true);
    		  }
    	  });
    	  frame5.add(returnBook);
    	  
    	                                    // Renewing a book coding (Completed)
    	  
    	  renewBook = new JButton("Renew Book");
    	  renewBook.setFont(font);
    	  renewBook.setBounds(100,130,200,40);
    	  renewBook.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame = new JFrame("Renew Book");
    			  frame.getContentPane().setLayout(null);
    			  JLabel lroll= new JLabel("Roll No.");
    			  lroll.setFont(font1);
    			  lroll.setBounds(10,10,110,40);
    			  frame.add(lroll);
    			  
    			  JTextField troll = new JTextField();
    			  troll.setFont(font2);
    			  troll.setBounds(120, 10, 250, 40);
    			  troll.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (troll.getText().length() >= 20 ) // limit to 3 characters
    			                e.consume();
    			        }
    			    });
    			  frame.add(troll);
    			  
    			  JLabel lb = new JLabel();
   			      lb.setFont(font);
   			      lb.setBounds(10,130,350,30); 
   			      lb.setForeground(Color.RED);
   			      lb.setVisible(false);
   			      frame.add(lb); 
    			  
    			  JButton btn = new JButton("Renew");
    			  btn.setFont(font2);
    			  btn.setBounds(125,90,150,30);
    			  btn.addActionListener(new ActionListener(){
    				 @SuppressWarnings("resource")
					public void actionPerformed(ActionEvent e){
    					 String s = troll.getText().trim().toString();
    					 String s1 = new String();
    					 boolean condition = true;
    					 
    					 // Input filter for roll number
    					 if(s.length()==0){
    						 s1 = "Input field can't be left blank";
    						 condition = false;
    					 }
    					 
    					 // Roll number filter for number type
    					 else if(s.length()!=0){ try{
    						 Long.parseLong(s);
    					 }catch(Exception ex){
    						 s1= "Roll number should be valid number";
    						 condition = false;
    					 }
    					 }
    					 
    					 
    					 
    					 // Check if book issued to this roll number
    					 
    					     Connection con;
    						 Statement stmt;
    						 try{
    						 Class.forName(JDBC_DRIVER);
    						 con = DriverManager.getConnection(DB_URL,USER,PASS);
    						 stmt = con.createStatement();
    						 
    						 String s2 = "select *from student";
    						 ResultSet rs = stmt.executeQuery(s2);
    						 
    						 while(rs.next()){
    							 if(rs.getLong(1)==Long.parseLong(s)){
    								 condition=true;
    								 break;
    							 }
    							 else{
    								 condition=false;
    								 s1="No book issued to this roll number";
    							 }
    						 }
    						 
    					 }catch(Exception exe){
    						 System.out.println("Query error");
    					 }
    					 
    					 if(condition){
    						 // Now retrieve the current date and renew date
    						 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    							Calendar c = Calendar.getInstance();
    							c.setTime(new Date());// Now use today date.
    							String cd = sdf.format(c.getTime());
    							c.add(Calendar.DATE, 15); // Adding 5 days
    							String output = sdf.format(c.getTime());
    							Connection conn;
    							Statement stmtt;
    							String ss1= "";
    							
    							try{
    							Class.forName(JDBC_DRIVER);
    							conn = DriverManager.getConnection(DB_URL,USER,PASS);
    							stmtt = conn.createStatement();
    							String ss= "select Renew_Date from student where RollNo="+Long.parseLong(s);
    							ResultSet rs = stmtt.executeQuery(ss);
    							
    							while(rs.next()){
    								ss1=rs.getString(1);
    								break;
    							}
    							
    							}catch(Exception ex){
    								System.out.println("Query error");
    							}
    							
    							// Now check if renew date has been passed so that we can impose fine
    							
    							System.out.println(ss1);
    							
    							String dateStart = ss1;
    							String dateStop = cd;
    							System.out.println(dateStart);
    							System.out.println(dateStop);
    							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    							Date d1 = null;
    							Date d2 = null;
    							long diff;
    							long diffDays;

    							try {
    								d1 = format.parse(dateStart);
    								d2 = format.parse(dateStop);
    								diff = d2.getTime() - d1.getTime();
    								diffDays = diff / (24 * 60 * 60 * 1000);
    								System.out.println(diffDays);
    								
    								if(diffDays>0){
    									//"Return date was "+ss1+". Now Collect fine of "+diffDays+"for "+diffDays+" Days"
    									String buttons[]={"Yes","No"};
    							        int optionReturns = JOptionPane.showOptionDialog(null
    							                                                        ,"Return date was "+ss1+". Now Collect fine of "+diffDays+" rupees for "+diffDays+" Days\n"+"Fine Collected?"
    							                                                        ,"Collect Fine"
    							                                                        ,JOptionPane.NO_OPTION
    							                                                        ,JOptionPane.PLAIN_MESSAGE
    							                                                        ,null
    							                                                        ,buttons
    							                                                        ,"default");
    							        if(optionReturns == 0){
    							        	try{
    			    							Class.forName(JDBC_DRIVER);
    			    							conn = DriverManager.getConnection(DB_URL,USER,PASS);
    			    							stmtt = conn.createStatement();
    			    							String query = "update student set Issue_Date='"+cd+"',Renew_Date='"+output+"' where RollNo="+Long.parseLong(s);
    			    							stmtt.executeUpdate(query);
    			    							JOptionPane.showMessageDialog(null,"Book Renewed Successfully");
    			    							}catch(Exception ex){
    			    								System.out.println("Query error");
    			    							}
    							            
    							        }
    							        else if(optionReturns == 1)
    							            JOptionPane.showMessageDialog(null,"Try again with collecting fine");
    							        else if(optionReturns == -1)
    							            JOptionPane.showMessageDialog(null,"Error!!!");
    								}
    								
    								else{
    									try{
			    							Class.forName(JDBC_DRIVER);
			    							conn = DriverManager.getConnection(DB_URL,USER,PASS);
			    							stmtt = conn.createStatement();
			    							String query = "update student set Issue_Date='"+cd+"',Renew_Date='"+output+"' where RollNo="+Long.parseLong(s);
			    							stmtt.executeUpdate(query);
			    							JOptionPane.showMessageDialog(null,"Book Renewed Successfully");
			    							}catch(Exception ex){
			    								System.out.println("Query error");
			    							}
							            
    								}
    								
    								} catch (Exception exp) {
    								System.out.println("exception");
    							}
    						 
    					 }
    					 else{
    						 lb.setText(s1);
    						 lb.setVisible(true);
    					 }
    				 } 
    			  });
    			  frame.add(btn);
    			  
    			  frame.setSize(400,200);
    			  frame.setLocationRelativeTo(null);
    			  frame.setResizable(false);
    			  frame.setVisible(true);
    		  }
    	  });
    	  frame5.add(renewBook);
    	  
    	                                      // Searching detail coding (Remaining)
    	  
    	  searchDetails = new JButton("Search Details");
    	  searchDetails.setFont(font);
    	  searchDetails.setBounds(100,190,200,40);
    	  searchDetails.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  
    			  Connection con;
    			  Statement stmt;
    			  
    			  JFrame frame11 = new JFrame("Search");
    			  frame11.getContentPane().setLayout(null);
    			  
    			  JLabel searchFor = new JLabel("Search About");
    			  searchFor.setBounds(125,10,200,30);
    			  searchFor.setFont(new Font("hkm",Font.BOLD,20));	  
    			  frame11.add(searchFor);
    			  
    			  JRadioButton book = new JRadioButton("Book");
    			  book.setFont(font1);
    			  book.setBounds(30,50,100,30);
    			  book.setSelected(true);
    			  
    			  JRadioButton student = new JRadioButton("Student");
    			  student.setFont(font1);
    			  student.setBounds(250,50,100,30);
    			  student.setSelected(false);
    			   
    			  ButtonGroup bg = new ButtonGroup();
    			  bg.add(book);
    			  bg.add(student);
    			  
    			  frame11.add(book);
    			  frame11.add(student);
    			  
    			  // First panel
    			  JPanel panel = new JPanel();
    			  panel.setBounds(0,100,400,200);
    			  panel.setLayout(null);
    			  
    			  JLabel label = new JLabel("Name");
    			  label.setFont(font);
    			  label.setBounds(0,0,150,40);
    			  panel.add(label);
    			  
    			  ArrayList<String> al = new ArrayList<String>();   
    			  
    			  try{
    				  Class.forName(JDBC_DRIVER);
    				  con = DriverManager.getConnection(DB_URL,USER,PASS);
    				  stmt = con.createStatement();
    				  
    				  String sql = "select *from book";
    				  ResultSet rs = stmt.executeQuery(sql);
    				  
    				  while(rs.next()){
    					  String s = rs.getString(1);
    					  if(al.contains(s)){
    						  continue;
    					  }
    					  else{
    						  al.add(s);
    					  }
    				  }
    				  }catch(SQLException ex){
    				  System.out.println("query Exception");
    			  }catch(ClassNotFoundException ex2){
    				  System.out.println("Driver Exception");
    			  }
    			  
    			  String column[] = al.toArray(new String[al.size()]);
    			  
    			  JComboBox<String> name = new JComboBox<String>(column);
    			  name.setBounds(150,0,150,40);
    			  panel.add(name);
    			  
    			  
    			  JLabel lb = new JLabel("OR");
    			  lb.setForeground(Color.RED);
    			  lb.setFont(new Font("hkm",Font.BOLD,16));
    			  lb.setBounds(180,60,200,40);
    			  panel.add(lb);
    			  
    			 JRadioButton showAll = new JRadioButton("Show All Books");
    			 showAll.setSelected(false);
    			 showAll.setFont(new Font("hkm",Font.BOLD,14));
    			 showAll.setBounds(120,120,200,40);
    			 panel.add(showAll);
    			 
    			  
    			  panel.setVisible(true);
    			  frame11.add(panel);
    			  
    			  
    			  // Second Panel
    			  JPanel panel2 = new JPanel();
    			  panel2.setBounds(0,100,400,200);
    			  panel2.setLayout(null);
    			  
    			  JLabel label3 = new JLabel("Roll No.");
    			  label3.setFont(font1);
    			  label3.setBounds(0,0,150,40);
    			  panel2.add(label3);
    			  
    			  JTextField trollno = new JTextField();
    			  trollno.setFont(font2);
    			  trollno.setBounds(150, 0, 230, 40);
    			  trollno.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (trollno.getText().length() >= 20) // limit roll no to 20 characters
    			                e.consume();
    			        }
    			    });
    			  panel2.add(trollno);
    			  
    			  JLabel lb2 = new JLabel("OR");
    			  lb2.setForeground(Color.RED);
    			  lb2.setFont(new Font("hkm",Font.BOLD,16));
    			  lb2.setBounds(180,60,200,40);
    			  panel2.add(lb2);
    			  
    			 JRadioButton showAll2 = new JRadioButton("Show All Student");
    			 showAll2.setSelected(false);
    			 showAll2.setFont(new Font("hkm",Font.BOLD,14));
    			 showAll2.setBounds(120,120,200,40);
    			 panel2.add(showAll2);
    			  
    			  panel2.setVisible(false);
    			  frame11.add(panel2);
    			  
    			  
    			  // Make panel first visible and panel second invisible on clicking on book radioButton
    			  book.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						panel.setVisible(true);
						panel2.setVisible(false);
						
					}
    				  
    			  });
    			  
    			// Make panel first invisible and panel second visible on clicking on student radioButton
    			  student.addActionListener(new ActionListener(){

  					@Override
  					public void actionPerformed(ActionEvent arg0) {
  						panel.setVisible(false);
  						panel2.setVisible(true);
  						
  					}
      				  
      			  });
    			  
    			  JLabel lab = new JLabel();  // label for showing error
    			  lab.setForeground(Color.RED);
    			  lab.setFont(new Font("hkm",Font.BOLD,16));
    			  lab.setBounds(0, 350, 400, 40);
    			  lab.setVisible(false);
    			  frame11.add(lab);
    			  
    			  JButton search = new JButton("Search");
    			  search.setFont(new Font("hkm",Font.BOLD,14));
    			  search.setBounds(150,300,100,40);
    			  search.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  if(book.isSelected()){
    						    //create table that will be same for both case (if all book or particular book is selected
    						    Connection con=null;
    						    Statement stmtt = null;
    						    String[] columnNames = {"Book", "Author", "Total", "Issued","Remaining"};
    	    					JFrame frame1 = new JFrame("Book Details");
    	    					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    	    					column.setPreferredWidth(100);
    	    					
    	    					column = table.getColumnModel().getColumn(3);   // set Column width
    	    					column.setPreferredWidth(100);
    	    					
    	    					column = table.getColumnModel().getColumn(4);   // set Column width
    	    					column.setPreferredWidth(100);
    	    					frame1.add(scroll);
    	    					frame1.setSize(810, 400);
    	    					frame1.setResizable(false);
    	    					frame1.setLocationRelativeTo(null);
    	    					frame1.setVisible(false);
    	    					
    	    					String book= "";
    	    					String author= "";
    	    					int total = 0;
    	    					int issued =0;
    	    					int remaining=0;
    						  if(showAll.isSelected()){
    							            // here show the list of all the books available
    							  int x =0;
    							try{
    							Class.forName(JDBC_DRIVER);
    							con = DriverManager.getConnection(DB_URL,USER,PASS);
    							stmtt = con.createStatement();
    							
    							String query = "SELECT COUNT(*) FROM book";        // check if book table isn't empty
    							ResultSet rs = stmtt.executeQuery(query);
    							
    							while(rs.next()){
    								x = rs.getInt(1);
    							}
    							
    							if(x==0){
    								lab.setText("List is Empty");
    								lab.setVisible(true);
    							}
    							else{
    								try
    		    					{ 
    		    					String sql = "select * from book";
    		    					
    		    					ResultSet rs2 = stmtt.executeQuery(sql);
    		    					
    		    					while(rs2.next())
    		    					{   book=rs2.getString(1);
    		    					    author=rs2.getString(2);
    		    					    total=rs2.getInt(3);
    		    					    issued=rs2.getInt(4);
    		    					    remaining=rs2.getInt(5);
    		    						model.addRow(new Object[]{book,author,total,issued,remaining});
    		    					}
    		    					frame1.setVisible(true);
    		    					lab.setVisible(false);
    		    					}catch(Exception ex){
    		    						System.out.println("query error");
    		    					}
    							}
    							}catch(Exception ex){
    								System.out.println("exception caught");
    							}  
    						  }
    						  else{
    							                // here show the details of only selected book
    							  
    							  if(name.getItemCount()==0){
    	    						 lab.setText("List Is Empty");
    	    						 lab.setVisible(true);
    	    					  }
    							  
    							  else{
    								  String bookName = name.getSelectedItem().toString();  
    								  System.out.println(bookName);
    								  try{
    									  Class.forName(JDBC_DRIVER);
    									  con= DriverManager.getConnection(DB_URL,USER,PASS);
    									  stmtt  = con.createStatement();
    									  String query = "select *from book where Book_Name='"+bookName+"'";
    									  System.out.println(query);
    									  ResultSet rs2 = stmtt.executeQuery(query);
    									  
    									  while(rs2.next())
    	    		    					{   book=rs2.getString(1);
    	    		    					    author=rs2.getString(2);
    	    		    					    total=rs2.getInt(3);
    	    		    					    issued=rs2.getInt(4);
    	    		    					    remaining=rs2.getInt(5);
    	    		    						model.addRow(new Object[]{book,author,total,issued,remaining});
    	    		    					}
    	    		    					frame1.setVisible(true);
    	    		    					lab.setVisible(false);
    									  
    								  }catch(Exception ex){
    									  System.out.println("sql error");
    								  }
    							  }
    						  }
    					  }
    					  else{
    						  //create table that will be same for both case (if all student or particular student is selected
  						    Connection con=null;
  						    Statement stmtt = null;
  						    String[] columnNames = {"Roll No.", "Name", "Contact No.", "Class","Book","Author","Issue Date","Renew Date"};
  	    					JFrame frame1 = new JFrame("Student Details");
  	    					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
  	    					column.setPreferredWidth(150);
  	    					
  	    					column = table.getColumnModel().getColumn(1);   // set Column width
  	    					column.setPreferredWidth(250);
  	    					
  	    					column = table.getColumnModel().getColumn(2);   // set Column width
  	    					column.setPreferredWidth(100);
  	    					
  	    					column = table.getColumnModel().getColumn(3);   // set Column width
  	    					column.setPreferredWidth(300);
  	    					
  	    					column = table.getColumnModel().getColumn(4);   // set Column width
  	    					column.setPreferredWidth(250);
  	    					
  	    					column = table.getColumnModel().getColumn(5);   // set Column width
  	    					column.setPreferredWidth(250);
  	    					
  	    					column = table.getColumnModel().getColumn(6);   // set Column width
  	    					column.setPreferredWidth(150);
  	    					
  	    					column = table.getColumnModel().getColumn(7);   // set Column width
  	    					column.setPreferredWidth(150);
  	    					
  	    					frame1.add(scroll);
  	    					frame1.setSize(810, 400);
  	    					frame1.setResizable(false);
  	    					frame1.setLocationRelativeTo(null);
  	    					frame1.setVisible(false);
  	    					
  	    					long stu_roll= 0;
  	    					String stu_name= "";
  	    					long stu_contact =0;
  	    					String stu_class ="";
  	    					String stu_book="";
  	    					String stu_author="";
  	    					String stu_issue="";
  	    					String stu_renew="";
    						  
    						  
    						  if(showAll2.isSelected()){
    							  // here show the list of all the students
    							  int x =0;
      							try{
      							Class.forName(JDBC_DRIVER);
      							con = DriverManager.getConnection(DB_URL,USER,PASS);
      							stmtt = con.createStatement();
      							
      							String query = "SELECT COUNT(*) FROM student";        // check if book table isn't empty
      							ResultSet rs = stmtt.executeQuery(query);
      							
      							while(rs.next()){
      								x = rs.getInt(1);
      							}
      							
      							if(x==0){
      								lab.setText("List is Empty");
      								lab.setVisible(true);
      							}
      							else{
      								try
      		    					{ 
      		    					String sql = "select * from student";
      		    					
      		    					ResultSet rs2 = stmtt.executeQuery(sql);
      		    					
      		    					while(rs2.next())
      		    					{ 
      		    						stu_roll=rs2.getLong(1);
      		    						stu_name=rs2.getString(2);
      		    						stu_contact=rs2.getLong(3);
      		    						stu_class=rs2.getString(4);
      		    						stu_book=rs2.getString(5);
      		    						stu_author=rs2.getString(6);
      		    						stu_issue=rs2.getString(7);
      		    						stu_renew=rs2.getString(8);
      		    						
      		    						
      		    						model.addRow(new Object[]{stu_roll,stu_name,stu_contact,stu_class,stu_book,stu_author,stu_issue,stu_renew});
      		    					}
      		    					frame1.setVisible(true);
      		    					lab.setVisible(false);
      		    					}catch(Exception ex){
      		    						System.out.println("query error");
      		    					}
      							}
      							}catch(Exception ex){
      								System.out.println("exception caught");
      							}  
    						  }
    						  else{
    							  // here show the details of only selected student
    							  String s = trollno.getText().trim().toString();
    							  
    							  if(s.length()==0){
    								  lab.setText("Please Fill Roll No. Field");
    								  lab.setVisible(true);
    							  }
    							  else if(s.length()!=0){
    								  try{
    									  Long.parseLong(s);
    									  Class.forName(JDBC_DRIVER);
    									  con = DriverManager.getConnection(DB_URL,USER,PASS);
    									  stmtt = con.createStatement();
    									  
    									  String query = "select *from student where RollNo="+Long.parseLong(s);
    									  ResultSet rs = stmtt.executeQuery(query);
    									  
    									  // see if no book issued to this roll no
    									  int i=0;
    									  while(rs.next()){
    										  i++;
    									  }
    									  
    									  System.out.println(i);
    									  
    									  if(i==0){
    										  lab.setText("No Book Issued To This Roll No.");
    	    								  lab.setVisible(true);  
    									  }
    									  
    									  else{
    										  String query2 = "select *from student where RollNo="+Long.parseLong(s);
        									  ResultSet rs2 = stmtt.executeQuery(query2);
    		      		    					while(rs2.next())
    		      		    					{ 
    		      		    						stu_roll=rs2.getLong(1);
    		      		    						stu_name=rs2.getString(2);
    		      		    						stu_contact=rs2.getLong(3);
    		      		    						stu_class=rs2.getString(4);
    		      		    						stu_book=rs2.getString(5);
    		      		    						stu_author=rs2.getString(6);
    		      		    						stu_issue=rs2.getString(7);
    		      		    						stu_renew=rs2.getString(8);
    		      		    						
    		      		    						
    		      		    						model.addRow(new Object[]{stu_roll,stu_name,stu_contact,stu_class,stu_book,stu_author,stu_issue,stu_renew});
    		      		    					}
    		      		    					frame1.setVisible(true);
    		      		    					lab.setVisible(false);
    									  }
    									  
    								  }catch(Exception ex){
    									  lab.setText("Roll No. should be a valid number");
    									  lab.setVisible(true);
    								  }
    							  }
    						  }
    					  }
    				  }
    			  });
    			  frame11.add(search);
    			  
    			  frame11.setSize(400, 420);
    			  frame11.setResizable(false);
    			  frame11.setLocationRelativeTo(null);
    			  frame11.setVisible(true);
    		  }
    	  });
    	  frame5.add(searchDetails);
    	  
    	                                       //Adding a new book (Completed)
    	  
    	  addBook = new JButton("Add Book");
    	  addBook.setFont(font);
    	  addBook.setBounds(100,250,200,40);
    	  addBook.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame12 = new JFrame("Add Book");
    			  frame12.getContentPane().setLayout(null);
    			  
    			  JLabel lbook = new JLabel("Book Name");
    			  lbook.setFont(font1);
    			  lbook.setBounds(10,50,130,40);
    			  frame12.add(lbook);
    			  
    			  JTextField tbook = new JTextField();
    			  tbook.setFont(font2);
    			  tbook.setBounds(150,50,220,40);
    			  tbook.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (tbook.getText().length() >= 30 ) // limit book name to 30 characters
    			                e.consume();
    			        }
    			    });
    			  frame12.add(tbook);
    			  
    			  JLabel lauthor = new JLabel("Author Name");
    			  lauthor.setFont(font1);
    			  lauthor.setBounds(10,100,130,40);
    			  frame12.add(lauthor);
    			  
    			  JTextField tauthor = new JTextField();
    			  tauthor.setFont(font2);
    			  tauthor.setBounds(150,100,220,40);
    			  tauthor.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (tauthor.getText().length() >= 30 ) // limit author number to 30 characters
    			                e.consume();
    			        }
    			    });
    			  frame12.add(tauthor);
    			  
    			  JLabel lquantity = new JLabel("Quantity");
    			  lquantity.setFont(font1);
    			  lquantity.setBounds(10,150,130,40);
    			  frame12.add(lquantity);
    			  
    			  JTextField tquantity = new JTextField();
    			  tquantity.setFont(font2);
    			  tquantity.setBounds(150,150,220,40);
    			  tquantity.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (tquantity.getText().length() >= 5 ) // limit quantity to 5 characters
    			                e.consume();
    			        }
    			    });
    			  frame12.add(tquantity);
    			  
    			  JButton btn = new JButton("Add");
    			  btn.setFont(font2);
    			  btn.setBounds(130,230,150,40);
    			  frame12.add(btn);
    			  
    			  JLabel lb = new JLabel();
    			  lb.setFont(font);
    			  lb.setBounds(10,290,400,30);
    			  lb.setForeground(Color.RED);
    			  lb.setVisible(false);
    			  frame12.add(lb);
    			  
    			  btn.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  String book_name=tbook.getText().trim().toString();
    					  String author = tauthor.getText().trim().toString();
    					  String quantity = tquantity.getText().trim().toString();
    					  String s="";
    					  boolean condition=true;
    					  //Filtering the Input
    					  if(book_name.length()==0 || author.length()==0 || quantity.length()==0){
    						  s="input field can't be left blank";
    						  condition = false;
    					  }
    					  
    					  // Filter the quantity filed to be valid number
    					  try{
    						 Integer.parseInt(quantity);
    					  }catch(Exception ex){
    						 s="quantity should be a valid number";
    						 condition =false;
    					  }
    					  
    					  Connection conn;
    					  Statement stmt;
    					  
    					  if(condition){
    					  try{
    						  boolean check=true;
    						  Class.forName(JDBC_DRIVER);
							  conn = DriverManager.getConnection(DB_URL,USER,PASS);
							  stmt = conn.createStatement();
							  
							  String query = "select *from book";
							  ResultSet rs = stmt.executeQuery(query); 
							  
							  while(rs.next()){
								  // check if book already exist
								  if(rs.getString(1).equals(book_name) && rs.getString(2).equals(author)){
									  check = false;
									  break;
								  }
							  }
							  
							  if(check){
								 String query1 = "insert into book values('"+book_name+"','"+author+"',"+quantity+","+0+","+quantity+")";
								 System.out.println(query1);
								 stmt.executeUpdate(query1);
								 frame12.dispose();
								 JOptionPane.showMessageDialog(null,"Book Added Successfully","Success",JOptionPane.OK_CANCEL_OPTION);
							  }
							  else{
								  String query2 = "update book set Total=(Total+"+quantity+"),Remaining=(Remaining+"+quantity+") where Book_Name='"+book_name+"' and Author_Name='"+author+"'";
								  System.out.println(query2);
								  stmt.executeUpdate(query2);
								  frame12.dispose();
								  JOptionPane.showMessageDialog(null,"Book Added to the recently existed list","Success",JOptionPane.OK_CANCEL_OPTION);
							  }
							  
    					  }catch(Exception ex){
    						  System.out.println("Query Error!!");
    					  }
    					  }
    					  else{
    						  lb.setText(s);
    						  lb.setVisible(true);
    					  }
    					  
    				  }
    			  });
    			  
    			  frame12.setSize(400,350);
    			  frame12.setResizable(false);
    			  frame12.setLocationRelativeTo(null);
    			  frame12.setVisible(true);
    		  }
    	  });
    	  frame5.add(addBook);
    	  
    	                                   // Deleting a book coding (Completed)
    	  
    	  deleteBook = new JButton("Delete Book");
    	  deleteBook.setFont(font);
    	  deleteBook.setBounds(100,310,200,40);
    	  deleteBook.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  JFrame frame13 = new JFrame("Delete Book");
    			  frame13.getContentPane().setLayout(null);
    			  
    			  JLabel lbook = new JLabel("Book Name");
    			  lbook.setFont(font1);
    			  lbook.setBounds(10,50,130,40);
    			  frame13.add(lbook);
    			  
    			  
    			  Statement stmt;
    			  Connection con;
    			  
    			   ArrayList<String> al = new ArrayList<String>();
    			  try{
    				  Class.forName(JDBC_DRIVER);
    				  con = DriverManager.getConnection(DB_URL,USER,PASS);
    				  stmt = con.createStatement();
    				  
    				  
    				  String sql2 = "select *from book";
    				  ResultSet rs2 = stmt.executeQuery(sql2);
    				  
    				  while(rs2.next()){
    					  String s = rs2.getString(1);
    					  if(al.contains(s)){
    						  continue;
    					  }
    					  al.add(s);
    					  
    				  }  
    				  System.out.println(al); 
    			  }catch(SQLException ex){
    				  System.out.println("query Exception");
    			  }catch(ClassNotFoundException ex2){
    				  System.out.println("Driver Exception");
    			  }catch(Exception ex3){
    				  System.out.println("other Exception");
    			  }
    			  
    			  
    			  JComboBox<String> cb2 = new JComboBox<String>();
    			  cb2.setBounds(150,100,150,30);      
    			  frame13.add(cb2); 
    			 
    			  String selection[] = al.toArray(new String[al.size()]);
    			  JComboBox<String> cb1 = new JComboBox<String>(selection);
    			  cb1.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  System.out.println("OK");
    					  cb2.removeAllItems();
    					  System.out.println("OK");
    					  ArrayList<String> al3 = new ArrayList<String>();
    					  String s = cb1.getSelectedItem().toString();
    					  Connection con;
    					  Statement stmt;
    					  try{
    						  Class.forName(JDBC_DRIVER);
    						  con = DriverManager.getConnection(DB_URL,USER,PASS);
    						  stmt = con.createStatement();
    						  
    						  String query = "select Author_Name from book where Book_Name='"+s+"'";
    						  System.out.println(query);
    						  ResultSet rs = stmt.executeQuery(query);
    						  
    						  while(rs.next()){
    							  String s1 = rs.getString(1);
    							  al3.add(s1);
    						  }
    						  System.out.println(al3);
    						  
    					  }catch(Exception exp){
    						System.out.println("query error");  
    					  }
    					  
    					  System.out.println("OK");
    					  String selection3[] = new String[al3.size()];
    					  System.out.println("OK");
    	    			  al3.toArray(selection3); 
    	    			  System.out.println("OK");
    	    			  for(int i=0;i<selection3.length;i++){
    	    				  cb2.addItem(selection3[i]);
    	    			  }
    				  }
    			  });
    			  cb1.setBounds(150,50,150,30);        
    			  frame13.add(cb1);  
    			   
    			  
    			  
    			  
    			  JLabel lauthor = new JLabel("Author Name");
    			  lauthor.setFont(font1);
    			  lauthor.setBounds(10,100,130,40);
    			  frame13.add(lauthor);
    			  
    			  JLabel lquantity = new JLabel("Quantity");
    			  lquantity.setFont(font1);
    			  lquantity.setBounds(10,150,80,40);
    			  frame13.add(lquantity);
    			  
    			  JTextField tquantity = new JTextField();
    			  tquantity.setFont(font1);
    			  tquantity.setBounds(100, 155, 65, 30);
    			  tquantity.addKeyListener(new KeyAdapter() {
    			        @Override
    			        public void keyTyped(KeyEvent e) {
    			            if (tquantity.getText().length() >= 5 ) // limit quantity to 5 characters
    			                e.consume();
    			        }
    			    });
    			  frame13.add(tquantity);
    			  
    			  
    			  JLabel or = new JLabel("OR");
    			  or.setFont(new Font("hkm",Font.BOLD,20));
    			  or.setForeground(Color.RED);
    			  or.setBounds(200,150,50,40);
    			  frame13.add(or);
    			  
    			  
    			  JRadioButton jbtn = new JRadioButton("Delete All");
    			  jbtn.setFont(font1);
    			  jbtn.setSelected(false);
    			  jbtn.setBounds(250,150,150,40);
    			  frame13.add(jbtn);
    			  
    			  JLabel msg = new JLabel();
    			  msg.setFont(new Font("hkm",Font.BOLD,12));
    			  msg.setForeground(Color.RED);
    			  msg.setBounds(10,300,400,30);
    			  msg.setVisible(false);
    			  frame13.add(msg);
    			  
    			  JButton btn = new JButton("Delete");
    			  btn.setFont(font2);
    			  btn.setBounds(150,230,150,40);
    			  btn.addActionListener(new ActionListener(){
    				  public void actionPerformed(ActionEvent e){
    					  
    					  
    					  // Check if RadioButton is selected then we will delete the book completely
    					  if(jbtn.isSelected()){
    						  String ss = "";
    						  boolean condition = true;
    						// Filtering the ComboBox(Check if ComboBox isn't empty)
        					  if(cb1.getItemCount()==0 || cb2.getItemCount()==0){
        						  condition=false;
        						  ss="No book is available add book first";  
        					  }
        					  
        					  // Filter if no book is assigned then we will not be able to delete the book
        					  else{
        						  Connection con;
        						  Statement stmt;
        						  
        						  try{
        							  String c = cb1.getItemAt(cb1.getSelectedIndex());
        							  String d = cb2.getItemAt(cb2.getSelectedIndex());
        							  Class.forName(JDBC_DRIVER);
        							  con = DriverManager.getConnection(DB_URL,USER,PASS);
        							  stmt = con.createStatement();
        							  
        							  String query = "select Issued from book where Book_Name='"+c+"' and Author_Name='"+d+"'";
        							  System.out.println(query);
        							  ResultSet rs = stmt.executeQuery(query);
        							  
        							  while(rs.next()){
        								  long s = rs.getLong(1);
        								  System.out.println(s);
        								  if(s>0){
        									  condition=false;
        									  ss = "Some book are issued can't delete book now";
        									  break;
        								  }
        								  
        							  }
        						  }catch(Exception ex){
        							  System.out.println("Query Error");
        						  }
        					  }
        					  
        					  if(condition){
        						  Connection con;
        						  Statement stmt;
        						  String c = cb1.getItemAt(cb1.getSelectedIndex());
    							  String d = cb2.getItemAt(cb2.getSelectedIndex());
        						  
        						  try{
        							  Class.forName(JDBC_DRIVER);
        							  con = DriverManager.getConnection(DB_URL,USER,PASS);
        							  stmt = con.createStatement();
        							  
        							  String query = "delete from book where Book_Name='"+c+"' and Author_Name='"+d+"'"; System.out.println(query);
        							  stmt.executeUpdate(query);
        							  JOptionPane.showMessageDialog(null,"Book Deleted Successfully","Success",JOptionPane.OK_CANCEL_OPTION);
        							  frame13.dispose();
        							  msg.setVisible(false);
        						  }catch(Exception ex){
        							  System.out.println("Query Error");
        						  }
        					  }
        					  else{
        						  msg.setText(ss);
        						  msg.setVisible(true);
        					  }
        					  
    					  }
    					  
    					  // If RadioButton isn't selected the we will delete the given quantity only
    					  else{
    						boolean condition = true;
    						String ss = new String();
    						  // Check for quantity field empty status
    						if(tquantity.getText().trim().toString().length()==0){
    							ss= "Input field can't be left blank";
    							condition = false;
    						}
    						
    						// Check that quantity is a valid number
    						else if(tquantity.getText().trim().toString().length()!=0){
    							try{
    								Long.parseLong(tquantity.getText().trim().toString());
    							}catch(Exception exp){
    								ss = "Quantity should be a valid number";
    								condition = false;
    							}
    						}
    						
    						
    							Connection con;
      						    Statement stmt;
      						  
      						  try{
      							  String c = cb1.getItemAt(cb1.getSelectedIndex());
      							  String d = cb2.getItemAt(cb2.getSelectedIndex());
      							  Class.forName(JDBC_DRIVER);
      							  con = DriverManager.getConnection(DB_URL,USER,PASS);
      							  stmt = con.createStatement();
      							  
      							  String query = "select Remaining from book where Book_Name='"+c+"' and Author_Name='"+d+"'";
      							  System.out.println(query);
      							  ResultSet rs = stmt.executeQuery(query);
      							  
      							  while(rs.next()){
      								  long s = rs.getLong(1);
      								  System.out.println(s);
      								  if(s<Long.parseLong(tquantity.getText().trim().toString())){
      									  condition=false;
      									  ss = "Remaining book are less then the quanity you want to delete";
      									  break;
      								  }
      								  
      							  }
      						  }catch(Exception ex){
      							  System.out.println("Query Error");
      						  }
    						
    						
    						if(condition){
    							
    							try{
    							 String c = cb1.getItemAt(cb1.getSelectedIndex());
    							  String d = cb2.getItemAt(cb2.getSelectedIndex());
    							  Class.forName(JDBC_DRIVER);
    							  con = DriverManager.getConnection(DB_URL,USER,PASS);
    							  stmt = con.createStatement();
    							  
    							  String query2 = "select Issued,Remaining from book where Book_Name='"+c+"' and Author_Name='"+d+"'";
    							  System.out.println(query2);
    							  ResultSet rs = stmt.executeQuery(query2);
    							  
    							  while(rs.next()){
    								  if(rs.getLong(1)==0 && rs.getLong(2)<=Long.parseLong(tquantity.getText().trim().toString())){
    									  String query = "delete from book where Book_Name='"+c+"' and Author_Name='"+d+"'"; System.out.println(query);
            							  stmt.executeUpdate(query);
            							  JOptionPane.showMessageDialog(null,Long.parseLong(tquantity.getText())+" books deleted Successfully","Success",JOptionPane.OK_CANCEL_OPTION);
    	    							  frame13.dispose();
    								  }
    								  
    								  else{
    									  String query = "update book set Remaining=(Remaining-"+Long.parseLong(tquantity.getText().trim().toString())+"),Total=Total-"+Long.parseLong(tquantity.getText().trim().toString()) +" where Book_Name='"+c+"' and Author_Name='"+d+"'";
    	    							  
    	    							  System.out.println(query);
    	    							  stmt.executeUpdate(query);
    	    							  JOptionPane.showMessageDialog(null,Long.parseLong(tquantity.getText())+" books from total deleted Successfully","Success",JOptionPane.OK_CANCEL_OPTION);
    	    							  frame13.dispose(); 
    								  }
    								  
    							  }
    							  
    							  
    							}catch(Exception exp){
    								System.out.println("query error");
    							}
    						}
    						else{
    							 msg.setText(ss);
       						     msg.setVisible(true);
    						}
    						  
    					  }  
    				  }
    			  });
    			  frame13.add(btn);
    			  
    			  
    			  frame13.setSize(420,380);
    			  frame13.setResizable(false);
    			  frame13.setLocationRelativeTo(null);
    			  frame13.setVisible(true);
    		  }
    	  });
    	  frame5.add(deleteBook);
    	  
    	                            // Go back to librarian login page (Completed)
    	  
    	  back = new JButton("back");
    	  back.setBounds(290,370,100,30);
    	  back.addActionListener(new ActionListener(){
    		  public void actionPerformed(ActionEvent e){
    			  frame5.dispose();
    			  new selectionPage().createPage();
    		  }
    	  });
    	  frame5.add(back);
    	  
    	  frame5.setSize(400,430 );
    	  frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  frame5.setResizable(false);
    	  frame5.setLocationRelativeTo(null);
    	  frame5.setVisible(true);
      }
}
