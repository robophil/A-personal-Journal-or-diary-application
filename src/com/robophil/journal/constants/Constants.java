package com.robophil.journal.constants;



public class Constants {

	/**
	 * the currently logged in user
	 */
	public static String username;
	
	/**
	 * the time of login
	 */
	public static String time;
	
	public final static String theme1="/com/robophil/journal/res/bcg1.jpg";
	
	public final static String theme2="/com/robophil/journal/res/bcg2.jpg";
	
	public final static String theme3="/com/robophil/journal/res/bcg3.jpg";
	
	public final static String theme4="/com/robophil/journal/res/bcg4.jpg";
	
	public final static String theme5="/com/robophil/journal/res/bcg5.jpg";
	
	public final static String theme6="/com/robophil/journal/res/bcg6.jpg";
	
	public static String theme=theme4;
	
	public static String saveDate;
	
	/**
	 * set the theme
	 * @param i
	 * @return
	 */
	public static String currentheme(int i){
		
		switch (i+1) {
		case 1:
			theme = theme1;
			break;
		case 2:
			theme = theme2;
			break;
		case 3:
			theme = theme3;
			break;
		case 4:
			theme = theme4;
			break;
		case 5:
			theme = theme5;
			break;
		case 6:
			theme = theme6;
			break;

		default:
			break;
		}
		
		return theme;
	}
	

	
	
}
