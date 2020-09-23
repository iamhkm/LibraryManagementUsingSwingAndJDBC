package library_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class selectionPage {
	private JButton admin_login, librarian_login;
	private JFrame frame;
             void createPage(){
            	 frame = new JFrame("Select Choice");
            	 frame.getContentPane().setLayout(null);
            	 
            	 Font font = new Font("hkm",Font.BOLD,20);
            	 
            	 admin_login = new JButton("Admin login");
            	 admin_login.setBounds(50,30,200,40);
            	 admin_login.setFont(font);
            	 admin_login.addActionListener(new ActionListener(){
            		 public void actionPerformed(ActionEvent e){
            			 frame.dispose();
            			AdminLogin adlog = new AdminLogin();
            			adlog.draw();
            		 }
            	 });
            	 frame.add(admin_login);
            	 
            	 librarian_login = new JButton("Librarian login");
            	 librarian_login.setBounds(50,90,200,40);
            	 librarian_login.setFont(font);
            	 librarian_login.addActionListener(new ActionListener(){
            		 public void actionPerformed(ActionEvent e){
            			frame.dispose();
            			LibrarianLogin liblog = new LibrarianLogin();
            			liblog.draw2();
            		 }
            	 });
            	 frame.add(librarian_login);
            	 
            	 frame.setSize(300, 200);
            	 frame.setResizable(false);
            	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	 frame.setLocationRelativeTo(null);
            	 frame.setVisible(true);
             }
}
