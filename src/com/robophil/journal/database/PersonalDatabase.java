package com.robophil.journal.database;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDatabase extends Database{
	/**
	 * path to database file
	 */
	private final static String path=new File("").getAbsolutePath()+"/database/";
	
	private String loginCheck = "Select * from users where _username = '%s' and _password = '%s'";
	
	private String prevSave = "Select * from messages where _username = '%s' and _date = '%s'";
	
	private String signup = "Insert into users(_id, _username, _password) values (null, '%s', '%s')";
	
	private String newMsg = "Insert into messages(_id, _username, _title, _message, _date) values (null, '%s', '%s', '%s', '%s')";
	
	private String updateMsg = "Update messages set _message = '%s', _title = '%s'  where _username = '%s' and _date = '%s' ";
	
	private String loadMsg = "select _message, _title from messages where _username = '%s' and _date = '%s' ";
	
	
	/**
	 * constructor for the subclass
	 */
	public PersonalDatabase() {
		// TODO Auto-generated constructor stub
		super(Database.sqlite, path+"personal_database.sqlite");
	}
	
	/**
	 * authenticate a valid login detail
	 * @param un
	 * @param pw
	 * @return true or false if the login details match
	 */
	public boolean login_check(String un, String pw){
		un = encyrpt(un);
		pw = encyrpt(pw);
		ResultSet r = Query(String.format(loginCheck, un,pw));
		int i=0;
		try {
			while(r.next()){
				i++;
			}
			if(i == 1)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error ==> "+e.getMessage());
		}
		finally{
			try {
				r.close();
			} catch (SQLException e) {
			}
		}
		
		return false;
	}
	
	/**
	 * signs a new user up
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean Sign_up(String un, String pw){
		boolean userexist = username_check(un);
		
		if(!userexist){
			un = encyrpt(un);
			pw = encyrpt(pw);
			int i = QueryUpdate(String.format(signup, un,pw));
			if(i == 1)
				return true;
		}
		
		return false;
	}
	
	/**
	 * checks if a userName already exist
	 * @param un
	 * @return
	 */
	private boolean username_check(String un){
		un = encyrpt(un);
		ResultSet r = Query("Select * from users where _username = '"+un+"'");
		int i = 0;
		try {
			while(r.next()){
				i++;
			}
			if(i == 1)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error ==> "+e.getMessage());
		}
		finally{
			try {
				r.close();
			} catch (SQLException e) {
			}
		}
		return false;
	}
	
	/**
	 * update the message for a particular date
	 * @param un
	 * @param message
	 * @param date
	 * @return
	 */
	public void update_message(String un, String title, String message, String date){
		boolean prevsave = prev_save(un, date);
		message = encyrpt(message);
		title = encyrpt(title);
		un = encyrpt(un);
		
		if(prevsave){
			QueryUpdate(String.format(updateMsg, message,title,un,date));
		}else{
			QueryUpdate(String.format(newMsg, un,title,message,date));
		}
	}
	
	/**
	 * check if a save has been made on this data before
	 * @param un
	 * @param date
	 * @return
	 */
	public boolean prev_save(String un, String date){
		un = encyrpt(un);
		ResultSet r = Query(String.format(prevSave, un,date));
		int i = 0;
		try {
			while(r.next()){
				i++;
			}
			if(i == 1)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error ==> "+e.getMessage());
		}
		finally{
			try {
				r.close();
			} catch (SQLException e) {
			}
		}
		return false;
	}
	
	/**
	 * Get the message stored for a user for a particular date
	 * @param user
	 * @param date
	 * @return
	 */
	public String getMessage(String user, String date){
		user = encyrpt(user);
		ResultSet r = Query(String.format(loadMsg, user,date));
		String msg = "";
		if(r == null){
			System.out.println("NULL IN MSG");
			return "";
		}
		try {
			while(r.next()){
				msg = r.getString("_message");
				msg = decyrpt(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				r.close();
			} catch (SQLException e) {
			}
		}
		
		return msg;
	}
	
	/**
	 * gets the title store for the message on the specified date
	 * @param user
	 * @param date
	 * @return
	 */
	public String getTitle(String user, String date){
		user = encyrpt(user);
		ResultSet r = Query(String.format(loadMsg, user,date));
		String title = "";
		if(r == null){
			System.out.println("NULL IN TITLE");
			return "";
		}
		try {
			while(r.next()){
				title = r.getString("_title");
				title = decyrpt(title);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				r.close();
			} catch (SQLException e) {
			}
		}
		
		return title;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * for encrypting data
	 * @param data
	 * @return
	 */
	private String encyrpt(String data){
		byte[] encryptData = data.getBytes();
		for (int i = 0; i < encryptData.length; i++) {
			encryptData[i] = (byte) (encryptData[i] + 4);
		}
		data = "";
		try {
			data = new String(encryptData,"UTF-8");
			data = data.replaceAll("'", "''");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * for decrypting data
	 * @param data
	 * @return
	 */
	private String decyrpt(String data){
		byte[] encryptData = data.getBytes();
		for (int i = 0; i < encryptData.length; i++) {
			encryptData[i] = (byte) (encryptData[i] - 4);
		}
		data = "";
		try {
			data = new String(encryptData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}