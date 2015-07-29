package com.robophil.journal.gui.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.robophil.journal.database.PersonalDatabase;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp_panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fullname;
	private JTextField uname;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private final PersonalDatabase database;

	/**
	 * Create the panel.
	 */
	public SignUp_panel(final JPanel rootpanel,final JFrame frame,final JPanel bgr) {
		setOpaque(false);
		setLayout(null);
		database = new PersonalDatabase();
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Sign up for a new account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(135, 78, 418, 263);
		add(panel);
		panel.setLayout(null);
		
		fullname = new JTextField();
		fullname.setBounds(175, 16, 237, 29);
		panel.add(fullname);
		fullname.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		fullname.setColumns(10);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(71, 16, 103, 29);
		panel.add(lblFullName);
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		uname = new JTextField();
		uname.setBounds(175, 70, 237, 29);
		panel.add(uname);
		uname.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		uname.setColumns(10);
		
		JLabel label = new JLabel("Username");
		label.setBounds(71, 70, 103, 29);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(71, 123, 103, 29);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 123, 237, 28);
		panel.add(passwordField);
		passwordField.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(175, 174, 237, 28);
		panel.add(passwordField_1);
		passwordField_1.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		
		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setBounds(6, 174, 168, 29);
		panel.add(lblRetypePassword);
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name="",un="",pw="",pw1="";
				name = uname.getText();
				un = uname.getText();
				passwordField.selectAll();
				passwordField_1.selectAll();
				pw = passwordField.getSelectedText();
				pw1 = passwordField_1.getSelectedText();
				
				if(name == null || un == null || pw == null || pw1 == null){
					JOptionPane.showMessageDialog(SignUp_panel.this, "1 or more field left blank", "Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				System.out.println(pw+"  "+pw1);
				if(!pw.equals(pw1)){
					JOptionPane.showMessageDialog(SignUp_panel.this, "Password miss-match", "Sign Up", JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
					passwordField_1.setText("");
					return;
				}
				boolean signup = database.Sign_up(un, pw);
				if(signup){
					JOptionPane.showMessageDialog(SignUp_panel.this, "Sign up completed\n"+name, "Sign Up", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(SignUp_panel.this, "Sign up could not be completed, try a different username", "Sign Up", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSignUp.setBounds(175, 233, 237, 23);
		panel.add(btnSignUp);
		btnSignUp.setFont(new Font("Sitka Text", Font.PLAIN, 19));
		
		JLabel lblSignIn = new JLabel("Sign in to access account ?");
		lblSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rootpanel.removeAll();
				rootpanel.add(new Login_panel(rootpanel,frame,bgr));
				SwingUtilities.updateComponentTreeUI(rootpanel);
			}
		});
		lblSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignIn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
		lblSignIn.setBounds(202, 350, 263, 31);
		add(lblSignIn);

	}

}
