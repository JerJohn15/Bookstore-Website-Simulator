import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class DB {

	/**
	 * This class handles all of the operations
	 * for accessing the db.
	 */


	private Connection connection = null;
	private Statement statement = null;
	private  ResultSet rs = null;
	private DataSource ds;
	protected boolean isItem_Record = false;
	protected String SQLstatement = "";
	protected boolean isCategory = false;
	protected boolean hasLoggedIn = false;
	protected cashRegister register = new cashRegister();
	
	
	
	/**
	 * @details - Gets a reference from the DataSource clas.
	 * @throws IOException
	 * @throws SQLException
	 * @throws PropertyVetoException
	 */
	public DB () throws IOException, SQLException, PropertyVetoException{
		ds = new DataSource();
	}



/**
 * @details - Gets the connection from the DB 
 * @throws SQLException
 * @throws IOException
 * @throws PropertyVetoException
 */
	public void retrieveConnection() throws SQLException, IOException, PropertyVetoException{
		connection = ds.getInstance().getConnection();
		statement = connection.createStatement();
	}

	/**
	 * @method - isEmpty` 
	 * @details - Checks to see if a record is in the checkout
	 * cart.
	 * @return - true or false 
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 * 
	 */

	public boolean isEmpty() throws IOException, PropertyVetoException{

		try {
			retrieveConnection();
			rs = statement.executeQuery(SQLstatement);
			//check for item not found first 
			if(rs.isBeforeFirst()== false){
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try 
			{ 
					rs.close();
			} 
			catch (SQLException e) 
			{e.printStackTrace();
			}
			if (statement != null)
				try 
			{ statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null)
				try {
					connection.close(); 
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * @method - fetchPayment
	 * @details - Retrieves a user's money from
	 * their bookstore account.
	 * @throws IOException
	 * @throws PropertyVetoException
	 */
	public void fetchPayment() throws IOException, PropertyVetoException{

		try {
			retrieveConnection();	
			rs = statement.executeQuery(SQLstatement);

		if(!rs.isBeforeFirst()){
			System.out.println("payment doesn't exist.");
		}else{
			while(rs.next()){
				
			register.setBudget(rs.getDouble(1));	
			}
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try 
			{ 
					rs.close();
			} 
			catch (SQLException e) 
			{e.printStackTrace();
			}
			if (statement != null)
				try 
			{ statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null)
				try {
					connection.close(); 
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * @method - retrieveTotal
	 * @details - Gets the price total in the checkout cart
	 * @throws IOException
	 * @throws PropertyVetoException
	 */
	public void retrieveTotal() throws IOException, PropertyVetoException{

		try {
			retrieveConnection();	
			rs = statement.executeQuery(SQLstatement);

			while(rs.next()){

				register.setTotal(rs.getDouble(1));
			}
			System.out.println("\nTotal: $" + register.getTotal() +"\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try 
			{ 
					rs.close();
			} 
			catch (SQLException e) 
			{e.printStackTrace();
			}
			if (statement != null)
				try 
			{ statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null)
				try {
					connection.close(); 
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}




	}

	/**
	 * @method - hasVerifiedLogin
	 * @details - Verifies a user's login credentials 
	 * @postcond - sets boolean variable to true or false
	 * @throws IOException
	 * @throws PropertyVetoException
	 * @return - true if username is found, otherwise, false
	 */
	public boolean hasVerifiedLogin() throws IOException, PropertyVetoException{



		try {
			retrieveConnection();	
			rs = statement.executeQuery(SQLstatement);
		
			if(!rs.isBeforeFirst()){
			
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try 
			{ 
					rs.close();
			} 
			catch (SQLException e) 
			{e.printStackTrace();
			}
			if (statement != null)
				try 
			{ statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null)
				try {
					connection.close(); 
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 
	 * @method - handleQueries
	 * @details - Handles the Query operations for the item_record
	 * and check_out databases. 
	 * @precond - after executing query, checks for which table to retrieve 
	 * the data from (either item_record or check_out).
	 *  @postcond - Prints out data from the table
	 * @throws IOException
	 * @throws PropertyVetoException
	 */
	public void handleQueries() throws IOException, PropertyVetoException{

		try {
			retrieveConnection();

			rs = statement.executeQuery(SQLstatement);
			//check for item not found first 
			if(!rs.isBeforeFirst()){
				System.out.println("Item not found or item doesn't exist.");
			}else{



				if(isItem_Record== true && isCategory == true){

					while(rs.next()){
						//Show just categories in items record
						System.out.println(rs.getString("book_category"));
					}

				}else if(isItem_Record == true && isCategory == false){
					while(rs.next()){
						// show all items in items_record table
						System.out.println( rs.getString("book_id") 
								+ "  " + rs.getString("book_category")
								+ "  " + rs.getString("book_title")+
								"  " + rs.getString("author")
								+ "  " + rs.getString("price"));
					}
				}else {
					while(rs.next()){
						//if both are false show all items in checkout
						System.out.println(rs.getString("book_title")
								+ "  " + rs.getString("author")+
								"  $" + rs.getString("price"));


					}
				}
				System.out.println("");
				isCategory = false;
				isItem_Record = false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try 
			{ 
					rs.close();
			} 
			catch (SQLException e) 
			{e.printStackTrace();
			}
			if (statement != null)
				try 
			{ statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null)
				try {
					connection.close(); 
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	/**
	 * @method - handleNonQueries
	 * @details - handles all non query operations (INSERT, DELETE,
	 * UPDATE) 
	 * @postcond- Displays an item not found message
	 * if SQL statement is invalid. 
	 * @throws IOException
	 * @throws PropertyVetoException
	 */
	public void handleNonQueries() throws IOException, PropertyVetoException{
		try {
			retrieveConnection();

			if(statement.executeUpdate(SQLstatement)==0){
				System.out.println("Data not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try 
			{ 
					rs.close();
			} 
			catch (SQLException e) 
			{e.printStackTrace();
			}
			if (statement != null)
				try 
			{ statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null)
				try {
					connection.close(); 
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}


