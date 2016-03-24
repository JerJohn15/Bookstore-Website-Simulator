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

		String SQLquery = "";
		DB database;
		String username = "";
		String password = "";
		public boolean hasLoggedIn( ) throws IOException, SQLException, PropertyVetoException{

			 username = "admin";
			 password = "bookstore";

			SQLquery = "SELECT username, password"
					+ " FROM bookstore.account WHERE username = '"
					+ username + "' AND password = '" + password + "'";
							database = new DB();

			database.setSQLstatement(SQLquery);


			if(database.hasVerifiedLogin()){
				return true;

			}
			return false;
		}


		@Test
		public void handleLogin() throws IOException, SQLException, PropertyVetoException{

			
			boolean isValid = hasLoggedIn();
			
			
			assertEquals("Enter in a valid username and password to log into the website",true,isValid);

		}
		
		public boolean hasRegistered() throws IOException, PropertyVetoException, SQLException{
			//make the username and password values something different.
			username = "MrJava";
			password = "code4life";
			
			SQLquery = "SELECT username FROM bookstore.account WHERE username = '"
					+ username + "'";
					
			database = new DB();
			
			database.setSQLstatement(SQLquery);
			
			if(!database.hasVerifiedLogin()){
				
				SQLquery = "INSERT INTO bookstore.account (username,password) VALUES('"
						+ username +"','" + password + "')";
				
				database.setSQLstatement(SQLquery);

				
				database.handleNonQueries();
				return true;
				
			}
			
			return false;
		}
		
		
		@Test
		public void handleRegistration() throws IOException, PropertyVetoException, SQLException{
			
			boolean isValid = hasRegistered();
			
			
			assertEquals("Enter in a username and password to register a new account", true, isValid);
			
		}
		

	}



	public class FunctionalTests{

		//insert test cases for the backend here.


	}

}
