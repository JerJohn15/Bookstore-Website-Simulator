import static org.junit.Assert.*;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class BookstoreTests {
	/**
	 *  This file contains all of the tests being performed. 
	 *  
	 *  All user tests are performed within the 'UserTests' class, 
	 *  while the Functional Tests, are done in the 'Functional Tests'
	 *  file. 
	 * @author Jeremiah
	 *
	 */
	
	
	
	
	public static class UserTests{

		String SQLstatement = "";
		DB database;
		public boolean hasLoggedIn(String username, String password ) throws IOException, SQLException, PropertyVetoException{
			
			String user = username;
			String pw = password;
			
			SQLstatement = "SELECT username, password"
					+ " FROM bookstore.account WHERE username = '"
					+ user + "' AND password = '" + pw + "'";
			
			database = new DB();
			
			if(database.hasVerifiedLogin()){
				return true;

			}
			return false;
		}
		
		
		@Test
		public void handleLogin() throws IOException, SQLException, PropertyVetoException{
			
			String username = "admin";
			String password = "bookstore";
			
			boolean isValid = hasLoggedIn(username,password);
			
			assertEquals(true,isValid);
			
		}
		
	}
	
	
	
	public class FunctionalTests{
		
		//insert test cases for the backend here. 
		
		
	}
	
}
