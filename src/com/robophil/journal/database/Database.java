package com.robophil.journal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	/**
	 * holds value of type MYSQL
	 */
	public final static String mysql="jdbc:mysql://localhost/";
	/**
	 * holds value of type SQLITE
	 */
	public final static String sqlite="jdbc:sqlite:";
	

	private Connection c = null;
	
	private ResultSet r = null;
	
	private Statement s = null;
	
	/**
	 * 
	 * @param type
	 * @param path
	 */
	public Database(String type,String path) {
		int con = Connection(type, path);
		
		//error connecting to database
		if(con == -1){
			return;
		}
		
		try {
			s = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error ==> "+e.getMessage());
		}
	}
	
	/**
	 * starts up a connection to the database
	 * @param type
	 * @param path
	 * @return
	 */
	private int Connection(String type,String path){
		if(type.equals(mysql)){
			System.out.println("Its a mysql connection");
			try {
				c = DriverManager.getConnection(mysql+path, "localhost", "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error connecting to the database");
				return -1;
			}
		}else if(type.equals(sqlite)){
			System.out.println("Its an sqlite connection");
			try {
				c = DriverManager.getConnection(sqlite+path);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error connecting to the database");
				return -1;
			}
		}else{
			System.out.println("invalid connection");
			return -1;
		}
		
		return 1;
	}
	
	/**
	 * used for executing update query that returns no resultSet.
	 * returns 1 if query was successful and -1 for otherwise
	 * @param query
	 * @return
	 */
	public int QueryUpdate(String query){
		try {
			s.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ==> "+e.getMessage());
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	/**
	 * used for executing SqL statements that returns result set
	 * returns null if there is an error
	 *
	 * @Note "Remember to always close resultSet after using it".
	 * @param query
	 * 
	 * @return RESULTSET
	 */
	public ResultSet Query(String query){
		try {
			r = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ==> "+e.getMessage());
			e.printStackTrace();
			r = null;
		}
		return r;
	}

}
