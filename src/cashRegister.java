import java.util.Scanner;

/**
 * This class handles all of the back-end monetary transactions
 * that take place within the program
 * @author Jeremiah
 *
 */
public class cashRegister {

	private double budget = 0.0;
	private double total = 0.0;
	
	
	
	/**
	 * @method - handleChange 
	 * @details - Calculates the change 
	 * @precond - If user has more than the alloted amount of money
	 * to make a purchase, let user know.
	 * @postcond - The user is shown the left over balance, 
	 * and asked to delete all the items from their checkout 
	 * cart 
	 */
	public void handleChange(){
		if(total > budget){
			System.out.println("Cannot make purchase.");
		}
		else{
			budget = budget - total;
			System.out.println("new balance: $" + budget);
			System.out.println("You can now empty the check out cart.");
		}
	}




	/**
	 * @details - Sets the budget for a user's account 
	 * @param money
	 */
	public void setBudget(double money) {
		this.budget = money;
	}


	public double getBudget() {
		return	this.budget;
	}

	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	/**
	 * @method - showBudget
	 * @details - Displays the user's current account balance
	 */
	public void showBudget(){
		System.out.println("current balance: $" + budget);
	}



}
