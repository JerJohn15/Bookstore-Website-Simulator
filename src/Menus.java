import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Menus extends DB{

	/**
	 * This class handles the front end operations
	 * within the user interface. 
	 * @throws IOException
	 * @throws SQLException
	 * @throws PropertyVetoException
	 */

	public Menus() throws IOException, SQLException, PropertyVetoException {
		super();
	}


	private String fileName = "";
	private Scanner file = null;
	private Scanner input = new Scanner(System.in);
	private String selection = "";
	private String category = "";
	private String username = "";


	/**
	 * @method - homeMenu
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @details - Asks the user for 
	 * a selection and handles the input.
	 */
	public void homeMenu() throws IOException, PropertyVetoException{


		boolean isDone = false;
		while(isDone != true){
			//keep in loop until user clicks done 
			fileName = "Home_Menu.txt";
			readMenu();
			promptSelection();

			switch(selection){

			case "s":
				handleSearch();
				break;
			case "c":
				handleCheckout();
				break;
			case "b":
				register.showBudget();
				break;
			case "a":
				changePayment();
				break;
			case "l":
				endProgram();
				break;
			default:
				System.out.println("Invalid Option!");
				break;
			}

		}
	}


	/**
	 *@method - readMenu
	 *@details - Reads main menu file
	 *and prompts user for an option selection.
	 *
	 */
	public void readMenu() throws FileNotFoundException{


		try
		{
			file = new Scanner(new File(fileName));

		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " + 
					fileName);
			System.exit(0);


		}
		while(file.hasNextLine()){

			String line = file.nextLine();
			System.out.println(line);

		}
	}

	/**
	 * @method - promptSelection 
	 * @details - prompts for a menu selection 

	 */

	public void promptSelection(){
		do{
			System.out.println("Choose an option: ");
			selection = input.next();

		}while(!selection.matches("[a-zA-Z\\s]*"));
		selection = selection.toLowerCase();
		System.out.println("");
	}




	/**
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @method - 
	 * @details - Prompts a user to login
	 * with their credentials
	 * @postcond - Verifies credentials 
	 * in a DB. Then retrieves the budget from the user's
	 * account.
	 *  
	 */

	public void handleLogin() throws IOException, PropertyVetoException{
		String password = "";

		do{
			System.out.println("Enter a username: ");
			username = input.next();

			System.out.println("Enter a password");
			password = input.next();

			//if true print user has been logged in 
			SQLstatement = "SELECT username, password"
					+ " FROM bookstore.account WHERE username = '"
					+ username + "' AND password = '" + password + "'";

			if(hasVerifiedLogin() == true){

				System.out.println("welcome back: " + username);
				hasLoggedIn = true;
			}

		}while(hasLoggedIn == false);
		System.out.println("");
		//load payment 
		SQLstatement = "SELECT payment FROM bookstore.account "
				+ "WHERE username = '"
				+ username + "'";
		fetchPayment();

	}


	/**
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @method - handleRegistration
	 * @details - Registers a user
	 * @precond - Asks for username,
	 * password
	 * @postcond - checks if username exists in database
	 */
	public void handleRegistration() throws IOException, PropertyVetoException{
		input = new Scanner(System.in);
		String password = "";

		do{
			System.out.println("Enter a username: ");
			username = input.next();

			System.out.println("Enter a password");
			password = input.next();
			//if name is valid add username to db

			SQLstatement = "SELECT username FROM bookstore.account WHERE username = '"
					+ username + "'";

			if(hasVerifiedLogin() == false){

				SQLstatement = "INSERT INTO bookstore.account (username,password) VALUES('"
						+ username +"','" + password + "')";
				handleNonQueries();
				System.out.println("Your account has been created. ");
				hasLoggedIn = true;

			}

		}while(hasLoggedIn == false);
		System.out.println("");


	}


	/**
	 * @method - changePayment
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @details - Updates a user's payment 
	 * @precond - Displays a users current balance and prompts for a new one.
	 * @postcond - Adds a user's balance to the account database and
	 * displays the new balance.
	 */


	public void changePayment() throws IOException, PropertyVetoException{

		register.showBudget();
		System.out.println("Enter an amount: ");
		register.setBudget(input.nextDouble());

		//update payment 
		SQLstatement = "UPDATE bookstore.account SET payment =" 
				+ register.getBudget() +" WHERE username = '" + username + "'";
		handleNonQueries();
		register.showBudget();
		
	}


	/**
	 * 
	 * @method - handleSearch
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @method - handleSearch
	 * @details - Searches for an item. (The user
	 * can choose to see all books by a specific category
	 * or enter a title of a book.)
	 * @postcond- If a book has been found, 
	 * the user can choose to add an item to checkout cart
	 *  or go back to search menu.
	 * 
	 */

	public void handleSearch() throws IOException, PropertyVetoException{

		input = new Scanner(System.in);
		boolean isFound = false;
		while(isFound != true){

			do{
				System.out.println("Type in book title or enter c to see book genres (b go back to main menu)");
				selection = input.nextLine();
			}while(!selection.matches("[\\w\\s]*"));


			switch(selection){
			case "c":		
				handleSearchFilter();
				promptAddCheckout();
				break;
			case "l":
				endProgram();
				break;
			case "b":
				isFound = true;
				break;
			default://searches by book title
				isItem_Record = true;
				SQLstatement = "SELECT book_id, book_category, book_title, author, price"
						+ " FROM bookstore.item_record WHERE book_title = '" +
						selection + "'";
				handleQueries();
				promptAddCheckout();
				break;
			}
		}
	}



	/**
	 * @method - handleSearchFilter
	 * @details - Filters the DB by a specific category defined
	 * by the user. 
	 * @precond - Shows user all book categories and prompts for 
	 * a category. 
	 * @postcond - Shows all books within that category.
	 * @throws IOException
	 * @throws PropertyVetoException
	 */
	public void handleSearchFilter() throws IOException, PropertyVetoException{
		input = new Scanner(System.in);
		//shows all categories
		SQLstatement = "SELECT DISTINCT book_category "
				+ "FROM bookstore.item_record";
		isItem_Record = true;
		isCategory = true;
		handleQueries();

		do{
			System.out.println("Type in a category: ");
			category = input.nextLine();
		}while(!category.matches("[\\w\\s]*"));

		//show all book titles in a specific category

		SQLstatement = "SELECT book_id, book_category, book_title, author, price"
				+ " FROM bookstore.item_record WHERE book_category = '" +
				category + "' GROUP BY book_id";
		isItem_Record = true;
		handleQueries();
	}


	/**
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @method - handleCheckout
	 * @details - Presents options for 
	 * checking out items. 
	 * @precond - A user can either view 
	 * all items in checkout, update an item's
	 * quantity, delete an item from the list,
	 * , delete all items from their checkout,
	 * or make a purchase. 
	 * @postcond - Performs the operations
	 * that the user selects.
	 */
	public void handleCheckout() throws IOException, PropertyVetoException{

		boolean hasFinished = false;

		while(hasFinished!=true){
			fileName = "Checkout_Menu.txt";
			readMenu();
			promptSelection();
			switch(selection){
			case "v":
				viewCheckout();
				break;
			case "p":
				handlePurchase();
				break;
			case "d":
				promptItemDelete();
				break;
			case "c":
				SQLstatement = "DELETE FROM bookstore.check_out WHERE username = '"
						+ username + "'";
				handleNonQueries();
				System.out.println("Cart has been deleted.");
				break;
			case "b":
				hasFinished = true;		
				break;
			case "l":
				endProgram();
				break;	
			default: 
				System.out.println("Invalid input.");
				break;
			}
		}
	}




	/**
	 * @method - viewCheckout
	 * @details - Displays all items in the checkout menu 
	 * @precond - Checks if checkout cart is empty first
	 * @postcond - Displays a message if cart is empty 
	 * @throws IOException
	 * @throws PropertyVetoException
	 */

	public void viewCheckout() throws IOException, PropertyVetoException{

		SQLstatement = "SELECT book_title,author,price FROM bookstore.check_out WHERE "
				+ "username = '"+ username + "'";

		if(isEmpty()== false){
			//SQLstatement = "SELECT book_title,author,price FROM bookstore.check_out "
				//	+ "WHERE username = '" + username + "'";
			isItem_Record = false;
			handleQueries();
		}else{
			System.out.println("Checkout is empty.");

		}
	}


	/**
	 * @method - promptItemDelete
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @details - Deletes a single book from checkout.
	 *@precond - Verifies whether or not the checkout DB is empty
	 * @postcond - Displays a message if cart is empty 
	 * 
	 * 
	 */
	public void promptItemDelete() throws IOException, PropertyVetoException{
		input = new Scanner(System.in);
		//checks if checkout cart is empty
		SQLstatement = "SELECT book_title,author,price FROM bookstore.check_out WHERE "
				+ "username = '"+ username + "'";
		if(isEmpty()== false){

			do{
				System.out.println("Enter in a  book title to delete it from the cart: ");
				selection = input.nextLine();
			}while(!selection.matches("[\\w\\s]*"));
			switch(selection){

			case "b":
				break;
			default:
				SQLstatement = "DELETE FROM bookstore.check_out "
						+ "WHERE username = '"+ username + "'"
						+ " AND book_title = '" + selection + "' limit 1";
				handleNonQueries();
				break;
			}
		}else{
			System.out.println("Checkout is empty.");
		}

	}

	/**
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * @method - promptAddCheckout
	 * @details - Prompts and handles the 
	 * adding of an item to a checkout. 
	 */
	public void promptAddCheckout() throws IOException, PropertyVetoException{
		input = new Scanner(System.in);
		String	value = "";
		do{
			System.out.println("Enter the book's id to add to checkout cart"
					+ " or enter, b,to go back to search menu:");

			value = input.nextLine();
		}while(!value.matches("[1-9]|10|[be]"));

		switch(value){
		case "b":	
			break;
		case "l":
			endProgram();
			break;	
		default:
			SQLstatement = "INSERT INTO bookstore.check_out"
					+ " SELECT username,book_title, author, price"
					+ " FROM bookstore.item_record, bookstore.account"
					+ " WHERE book_id = ' " + value + "' AND book_category = '"
					+ category + "' AND username = '" + username + "'"
					+ "OR book_id = ' " + value + "' AND book_title = '" + selection + "'"
					+ " AND username = '" + username +"'";
			handleNonQueries();
			break;


		}

	}

	/**
	 * @method - handlePurchase
	 * @details - Handles a user's purchase
	 * @precond - Verifies whether or not the checkout DB is empty
	 * @postcond - If not empty,shows user items to be checked out, the grand 
	 * total, and calculates the change after given a budget. 
	 *  
	 * @throws IOException
	 * @throws PropertyVetoException
	 */
	public void handlePurchase() throws IOException, PropertyVetoException{

		SQLstatement = "SELECT book_title,author,price FROM bookstore.check_out WHERE "
				+ "username = '"+ username + "'";

		if(isEmpty() == false){	
			viewCheckout();
			//calculate total of all prices
			SQLstatement = "SELECT SUM(price) FROM bookstore.check_out WHERE"
					+ " username = '" + username+ "'";
			retrieveTotal();
			//extract change from cashregister
			register.showBudget();
			register.handleChange();

			//place new budget back in db 
			SQLstatement = "UPDATE bookstore.account SET payment =" 
					+ register.getBudget() +" WHERE username = '" + username + "'";
			handleNonQueries();
		}else{
			System.out.println("Checkout is empty.");
		}
	}


	/**
	 * @method - endProgram
	 * @details - Ends the program
	 */
	public void endProgram(){
		System.out.println("Logging out...");
		System.exit(0);
	}



}
