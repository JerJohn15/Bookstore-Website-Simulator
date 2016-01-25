import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;




public class BookstoreSimulator {


	/**
	 * @details - This class launches the program. 
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 * @throws PropertyVetoException
	 */


	public static void main(String[] args) throws IOException, PropertyVetoException, SQLException   {

		handleCredentials();
	
	}

	/**
	 * @method - handleCredentials
	 * @detail - Presents the user with the option
	 * to login or register. 
	 * @postcond - Displays the home menu after a user
	 * has completed a login or registration. 
	 * @throws IOException
	 * @throws SQLException
	 * @throws PropertyVetoException
	 */

	public static void handleCredentials() throws IOException, SQLException, PropertyVetoException{
		String selection = "";
		Scanner input = new Scanner(System.in);
		Menus employee = new Menus();
		do{
			System.out.println("Welcome to Jeremiah's bookstore!"
					+ "\n l: Login \n r: Register \n");
			selection = input.next();
		}while(!selection.matches("[l|r]*"));
		switch(selection){
		case "l":
			employee.handleLogin();
			employee.homeMenu();
			break;
		case "r":
			employee.handleRegistration();
			employee.homeMenu();
			break;			
		default: 
			System.out.println("Invalid character. ");
			break;
		}
	}
}


