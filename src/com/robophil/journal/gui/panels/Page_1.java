package com.robophil.journal.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.robophil.journal.constants.Constants;

public class Page_1 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Page_1(final JFrame frame,final JPanel bgr) {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(344, 100, 707, 568);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		Login_panel login_panel = new Login_panel(panel,frame,bgr);
		panel.add(login_panel);
		
		JLabel lblMyPersonalJournnal = new JLabel("My Personal Journal");
		lblMyPersonalJournnal.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 82));
		lblMyPersonalJournnal.setBounds(315, 11, 766, 78);
		add(lblMyPersonalJournnal);
		
		JLabel lblV = new JLabel("V 1.0");
		lblV.setFont(new Font("Traditional Arabic", Font.PLAIN, 26));
		lblV.setBounds(1051, 11, 68, 33);
		add(lblV);
		
		SwingUtilities.updateComponentTreeUI(panel);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Page_1.class.getResource(Constants.theme)).getImage(), 0, 0, this);
	}
}
