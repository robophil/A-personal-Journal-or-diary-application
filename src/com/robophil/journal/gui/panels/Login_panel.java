package com.robophil.journal.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.robophil.journal.constants.Constants;
import com.robophil.journal.database.PersonalDatabase;
import com.robophil.journal.gui.Journal;

public class Login_panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private final PersonalDatabase database;

	/**
	 * Create the panel.
	 */
	public Login_panel(final JPanel rootPanel,final JFrame frame,final JPanel bgr) {
		setOpaque(false);
		setLayout(null);
		database = new PersonalDatabase();
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Enter Login Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(157, 86, 353, 170);
		add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(110, 16, 237, 29);
		panel.add(textField);
		textField.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 16, 103, 29);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 69, 103, 29);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginSuccesfully(frame,bgr);
			}
		});
		passwordField.setBounds(110, 69, 237, 28);
		panel.add(passwordField);
		passwordField.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginSuccesfully(frame,bgr);
			}
		});
		btnNewButton.setBounds(110, 140, 237, 23);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Sitka Text", Font.PLAIN, 19));
		
		JLabel label = new JLabel("Sign up for a new account ?");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rootPanel.removeAll();
				rootPanel.add(new SignUp_panel(rootPanel,frame,bgr));
				SwingUtilities.updateComponentTreeUI(rootPanel);
			}
		});
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
		label.setBounds(204, 267, 277, 31);
		add(label);
		
	}

	/**
	 * if details match, welcome user 
	 * @param frame
	 */
	private void loginSuccesfully(final JFrame frame,final JPanel bgr) {
		String un="",pw="";
		un = textField.getText();
		passwordField.selectAll();
		pw = passwordField.getSelectedText();
		//avoid null pointers
		if(un == null || pw == null){
			JOptionPane.showMessageDialog(Login_panel.this, "1 or more field left blank", "Login", JOptionPane.WARNING_MESSAGE);
			return;
		}
		boolean loginvalid = database.login_check(un, pw);
		if(loginvalid){
			Constants.username = un;
			Constants.time = DateFormat.getInstance().format(new Date());
			Journal.contentPane.removeAll();
			Journal.contentPane.setLayout(new BorderLayout());
			Journal.contentPane.add(new Text_Panel(frame,bgr),BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(Journal.contentPane);
		}else{
			JOptionPane.showMessageDialog(Login_panel.this, "INVALID LOGIN DETAILS", "Login error", JOptionPane.WARNING_MESSAGE);
		}
		passwordField.setText("");
	}
}
