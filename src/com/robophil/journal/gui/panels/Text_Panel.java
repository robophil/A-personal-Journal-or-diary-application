package com.robophil.journal.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.robophil.journal.constants.Constants;
import com.robophil.journal.database.PersonalDatabase;
import com.robophil.journal.gui.Journal;

public class Text_Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_title;
	private JTextField txtTheDateIs;
	private JTextArea textArea;
	private JComboBox<Object> combofont;
	private JComboBox<Object> combostyle;
	private JComboBox<Object> combosize;
	private boolean done = false;
	private PersonalDatabase database;
	private JComboBox<Object> comboBox_month;
	private JComboBox<Object> comboBox_day;
	private JComboBox<Object> comboBox_year;

	/**
	 * Create the panel.
	 */
	public Text_Panel(final JFrame frame,final JPanel bgr) {
		setOpaque(false);
		setLayout(null);
		database = new PersonalDatabase();
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(0, 0, 251, 683);
		add(panel);
		panel.setLayout(null);
		
		comboBox_month = new JComboBox<>();
		comboBox_month.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					load_and_save();
				}
			}
		});
		comboBox_month.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		String[] months = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		comboBox_month.setModel(new DefaultComboBoxModel<Object>(months));
		comboBox_month.setBounds(0, 83, 108, 27);
		panel.add(comboBox_month);
		
		comboBox_day = new JComboBox<Object>();
		comboBox_day.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					load_and_save();
				}
				
			}
		});
		comboBox_day.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		String[] day = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		comboBox_day.setModel(new DefaultComboBoxModel<Object>(day));
		comboBox_day.setBounds(103, 83, 52, 27);
		panel.add(comboBox_day);
		
		comboBox_year = new JComboBox<Object>();
		comboBox_year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					load_and_save();
				}
			}
		});
		comboBox_year.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		String[] year = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
		comboBox_year.setModel(new DefaultComboBoxModel<Object>(year));
		comboBox_year.setBounds(157, 83, 84, 27);
		panel.add(comboBox_year);
		
		combofont = new JComboBox<Object>();
		combofont.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					setFontStyle(""+combofont.getSelectedItem(), combostyle.getSelectedIndex(), combosize.getSelectedIndex());
				}
			}
		});
		String[] myfont = {"Tempus Sans ITC", "Tahoma", "Sylfaen", "Tw Cen MT", "Yu Mincho Demibold", "Yu Gothic Light", "Virtual DJ", "Meiryo UI", "Calibri", "Cambria", "Arial", "Algerian", "Times New Roman"};
		combofont.setModel(new DefaultComboBoxModel<Object>(myfont));
		combofont.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		combofont.setBounds(10, 176, 231, 27);
		panel.add(combofont);
		
		combostyle = new JComboBox<Object>();
		combostyle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					setFontStyle(""+combofont.getSelectedItem(), combostyle.getSelectedIndex(), combosize.getSelectedIndex());
				}
			}
		});
		String[] style = {"Plain", "Bold", "Italic"};
		combostyle.setModel(new DefaultComboBoxModel<Object>(style));
		combostyle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		combostyle.setBounds(10, 214, 76, 27);
		panel.add(combostyle);
		
		combosize = new JComboBox<Object>();
		combosize.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					setFontStyle(""+combofont.getSelectedItem(), combostyle.getSelectedIndex(), combosize.getSelectedIndex());
				}
			}
		});
		String[] size = {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		combosize.setModel(new DefaultComboBoxModel<Object>(size));
		combosize.setSelectedIndex(7);
		combosize.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		combosize.setBounds(165, 214, 76, 27);
		panel.add(combosize);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savesMsg(Constants.saveDate);
			}
		});
		btnSave.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 19));
		btnSave.setBounds(10, 372, 231, 27);
		panel.add(btnSave);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Journal().setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 19));
		btnLogOut.setBounds(10, 410, 231, 27);
		panel.add(btnLogOut);
		
		JLabel lblWelcome = new JLabel("Welcome, "+Constants.username);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblWelcome.setBounds(10, 469, 231, 27);
		panel.add(lblWelcome);
		
		JLabel lblLoggedIn = new JLabel(Constants.time);
		lblLoggedIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoggedIn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblLoggedIn.setBounds(10, 507, 231, 27);
		panel.add(lblLoggedIn);
		
		final JComboBox<Object> comboBox_6 = new JComboBox<Object>();
		comboBox_6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Constants.currentheme(comboBox_6.getSelectedIndex());
					bgr.repaint();
				}
			}
		});
		String[] theme ={"Theme 1", "Theme 2", "Theme 3", "Theme 4", "Theme 5", "Theme 6"};
		comboBox_6.setModel(new DefaultComboBoxModel<Object>(theme));
		comboBox_6.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		comboBox_6.setBounds(10, 293, 231, 27);
		comboBox_6.setSelectedIndex(3);
		panel.add(comboBox_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK, 3, true));
		scrollPane.setBounds(249, 0, 958, 683);
		add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		scrollPane.setColumnHeaderView(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 3));
		
		JMenuBar menuBar = new JMenuBar();
		panel_1.add(menuBar);
		
		JMenu mnEdit = new JMenu("Options");
		mnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnEdit);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savesMsg(Constants.saveDate);
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnEdit.add(mntmSave);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "A journal for pouring out the fun, emotional and the not so fun part of your day\n"
						+ "To save, click 'Ctrl + S' or click the save button", "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mnEdit.add(mntmHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "A Philip Oghenerobo Balogun production\nOghenerobo@gmail.com\n08120714903   09038256654\nCopyright @ 2014", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnEdit.add(mntmAbout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnEdit.add(mntmExit);
		
		txtTheDateIs = new JTextField();
		txtTheDateIs.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		txtTheDateIs.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheDateIs.setText("Monday - January 1, 1999");
		txtTheDateIs.setEditable(false);
		txtTheDateIs.setToolTipText("On this date you wrote for the first time on this page");
		panel_1.add(txtTheDateIs);
		txtTheDateIs.setColumns(10);
		
		textField_title = new JTextField();
		textField_title.setHorizontalAlignment(SwingConstants.CENTER);
		textField_title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		textField_title.setToolTipText("Pls set the tittle here");
		panel_1.add(textField_title);
		textField_title.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setText("Dear diary,\r\n\t\t");
		textArea.setMargin(new Insets(15, 10, 10, 10));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 17));
		scrollPane.setViewportView(textArea);
		
		
		//set the journal date to today
		Calendar c=Calendar.getInstance();
		comboBox_month.setSelectedIndex(c.get(Calendar.MONTH));
		comboBox_day.setSelectedIndex(c.get(Calendar.DAY_OF_MONTH)-1);
		comboBox_year.setSelectedIndex(getyearindex(c.get(Calendar.YEAR)));
		Constants.saveDate=""+comboBox_month.getSelectedItem()+comboBox_day.getSelectedItem()+comboBox_year.getSelectedItem();
		txtTheDateIs.setText(comboBox_month.getSelectedItem()+", "+comboBox_day.getSelectedItem()+" "+comboBox_year.getSelectedItem());
		
		//check if all elements are done loading on this panel
		done = true;
		
		//load if any message exists for the current day
		load();
	}
	
	/**
	 * set the font style of the page
	 * @param font
	 * @param style
	 * @param size
	 */
	private void setFontStyle(String font, int style, int size){
		int mystyle=0;
		if(0 == style)
			mystyle = Font.PLAIN;
		if(1 == style)
			mystyle = Font.BOLD;
		if(2 == style)
			mystyle = Font.ITALIC;
		if(done)
			textArea.setFont(new Font(font, mystyle, size+10));
	}
	
	/**
	 * returns the current year index
	 * @param k
	 * @return
	 */
	private int getyearindex(int k){
		String[] year1 = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
		for (int i = 0; i < year1.length; i++) {
			if((""+k).equals(year1[i])){
				System.out.println("year is"+Calendar.YEAR);
				return i;
			}
				
		}
		return -1;
	}
	
	/**
	 * loads prevs messages and saves new 
	 */
	private void load_and_save(){
		if(done){
			savesMsg(Constants.saveDate);//save with previous date
			Constants.saveDate=""+comboBox_month.getSelectedItem()+comboBox_day.getSelectedItem()+comboBox_year.getSelectedItem();//change to current date
			String m=database.getMessage(Constants.username, Constants.saveDate);//get the message of the day
			textArea.setText(m);//set the message
			String t=database.getTitle(Constants.username, Constants.saveDate);//get the title
			textField_title.setText(t);//set the title
			txtTheDateIs.setText(comboBox_month.getSelectedItem()+", "+comboBox_day.getSelectedItem()+" "+comboBox_year.getSelectedItem());//update display date
		}
	}
	
	/**
	 * load message and title for this date
	 */
	private void load() {
		if (database.prev_save(Constants.username, Constants.saveDate)) {
			String m = database.getMessage(Constants.username,
					Constants.saveDate);//get the message of the day
			textArea.setText(m);//set the message
			String t = database
					.getTitle(Constants.username, Constants.saveDate);//get the title
			textField_title.setText(t);//set the title
			txtTheDateIs.setText(comboBox_month.getSelectedItem() + ", "
					+ comboBox_day.getSelectedItem() + " "
					+ comboBox_year.getSelectedItem());//update display date
		}
	}

	/**
	 * saves the message
	 * @param _date
	 */
	private void savesMsg(final String _date) {
		String t=textField_title.getText();
		String m=textArea.getText();
		if(t==null)
			t="";
		if(m==null)
			m="";
		database.update_message(Constants.username, t, m, _date);
	}
	
	
	
}