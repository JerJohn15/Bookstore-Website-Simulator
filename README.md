# Bookstore-Website-Simulator


#Project Summary:

This project was a college project for my Database Management class. The goal was to identify a real-world application where we would utilize a database and implement it using whatever languages we desired. I decided to create a simulator for purchasing books from a bookstore website. The program output is displayed as text from within a console.  In addition to coding the project, we needed to provide documentation that explains, our application's functionality, defines our targeted users, and includes an ER Diagram, to model the domain of our database used in our application.


Below, I've included a brief outline of the tools I used to implement my application, features that I incorporated into implementing it, and an explanation of what happens when the program is run. At the end, there is a screenshot of a demo of the program running.

#Features for the Application

Basic Features

-	Registering a new account
-	Logging into an existing account
-	Search books from within a database
-	Keep a saved record of books to be purchased in a checkout cart
-	Purchase books stored in a user’s checkout cart
-	Add an allowance to a user’s account

**User Friendly Features:**

-	Ability to log out from the account
-	Ability to handle invalid character input
-	Ability to go back to a previous page  
-	Ability to delete books from a user’s checkout cart

*In addition to these features, I have also designed my program, to handle, some common potential errors that could be made from within the program, based on user input.*

**Database Usage:**

- For storing a user's username, password, and allowance
- All of the books that are available for sale
- All of the books that are within a user's checkout cart

*The name of the database tables for which I have stored each of this information is called, "account", "item record", and "check out".*

#Tools Used:

-	**MySQL Workbench** (to create and test the database tables)
-	**C3PO** (an external library which utilizes the data pooling technique when connecting to the database)
-	**Eclipse IDE** (for coding and testing the finished project)
-	**Programming Languages:** Java, JDBC, MySQL


**Note:** I've created some SQL files containing the database tables needed for the databaseL. You will need to import them using MySQL Workbench. To download the files, go [here](https://drive.google.com/folderview?id=0B_Mzb0tpEYLWeGcyMHc3cFFlZ3M&usp=sharing)

#Project Setup

```
Open Windows Git Bash (or command line) and cd to your desired project directory

Type:
git clone https://github.com/JerJohn15/Bookstore-Website-Simulator.git

```

#Plan of Action
 The idea is to have the user login and after a successful login have a menu appear on the console, where the user can enter in commands to access features desirable to them. The following sections describes how this would work in more detail.

#Identifying Customer Needs
 Customers want a service that is convenient to them, so there needs to be a feature for searching a product and getting a result as well as an option for checking out an item. Since there exists a feature for checking out a user’s potentially purchased items, there should be a login and registration feature, as well as a database to store all accounts created on the site. Finally, menus need to be presented to the user so they can easily read options they might want to access, and interact with those options in the interface.

#Identifying Target Specifications

Below is a short description of what the program does when it is run:

**What happens when the program runs:**

*	Prompt for the user to login or register. If login credentials are successful, show user the different choices they have available to them on the site. If not, then user will have to restart the program and register a new account.
*	If searching, search for an item within the item_record table either by book title, or by a book category (this is a search filter). If the user decides they would like to purchase a book, add it to the cart.
*	If selecting check out menu, show the user choice to view the entire checkout cart, delete an item from the cart, delete entire cart, or make a purchase.
*	After performing the action indicated by the user, either return to the submenu (check out or search menu), in case the user wants to continue to use that feature, or return to the main menu.
* When done with program, enter in a command, l, to logout (exit the program)
*	Handle any potential errors the user may enter

In the following section, I will elaborate more on these points.

#Selecting and Implementing Design Concepts

###Project Structure

Below is an outline of the files created in this project.

```java
Bookstore-Website-Simulator
default package/
----src/
--------BookstoreSimulator.java//the MAIN file which runs the program and handles displaying the prompts for a user's login credentials
--------cashRegister.java// handles all the money transactions
--------DataSource.java// Configures the Database and C3PO
--------DB.java// contains the backend for the database
--------Menus.java// contains the front-end logic for each menu
lib/
--------c3p0-0.9.5.1.jar //Contains the C3PO library needed to create the data pooling
--------mchange-commons-java.0.2.10.jar // another file needed to use C3PO
--------mysql-connector-java.5.1.36-bin.jar//mysql connector required to use MySQL
Checkout_Menu.txt//contains the menu with the checkout options
Home_Menu.txt//contains the menu for the homepage with its options
```
###Menus

I have two menus: Home_Menu and Check_Out as text files that, when loaded, will show the user the different options they will be presented throughout the program. Home_Menu, is the main menu that will show the user options they can select to search for an item, change a user’s balance in their account, view their account balance, go to the checkout menu, or end the program, and Check_Out is the sub-menu of the main menu that lists options the user can call to access features within the checkout cart.  Below is a screen shot of the txt files:

![Home Menu](http://s29.postimg.org/3r455d9nb/home_menu.png)

`Screenshot of home menu`

![Checkout Menu](http://s28.postimg.org/v0rxl60b1/checkout_menu.png)

`Screenshot of checkout menu`

The files are loaded in the readMenu method in the Menus class. After a user has logged in, the home_menu is loaded in the homeMenu method. Each time a user accesses the home or check out menus, the following steps occur: (1) set the variable filename to the location of the menu files, (2) read menu from text file in readMenu, and (3) prompt the user to choose a selection from the menu from method, promptSelection. The following figure shows a sample code snippet from the homeMenu method using these steps:

![Home Menu Snippet](http://s8.postimg.org/7lppj8hud/home_menu_code_snipett.png)

There are other menus that will display throughout the program that do not get launched from a file. These are menus that prompt a user to enter in a specific phrase, or number if they are making a purchase. Many of these prompts are included in methods that have “prompt” in the name.  (see promptAddCheckout method in Menus for an example prompt)

To make the interface more user-friendly, I’ve included in most of my prompts ways for the user to return to both the main and check out menus, by entering the character, b, to go back to a previous section. This was incorporated so that users didn’t get frustrated if they, say found a book they were looking for but did not want to add it to their cart. (The next image below shows an example of this.) I also allow the user to make unlimited attempts (see do-while loops through the Menu class) to retry their inputs if they happen to enter in, perhaps, a book that doesn’t exist (more on that in the next section).

###Databases:

I’ve created my bookstore database using MySQL, and I’m accessing it using JDBC in java. I’m also using an external library, C3PO to perform a technique known as data pooling, which is used to set up a connection to a database and retrieve that same connection whenever you need it without having to reestablish a new connection each time you want to access the database, the latter process being extremely slow (see class DataSource and DB for DB configuration and data pooling implementation).


###ER Diagram

  The below diagram shows an ER Diagram for the tables: *account*, *item_record* and *check_out*. My *item_record* table stores all of the books that the bookstore has for the user to search on its website, the *check_out* table has all of the books that a user might purchase from the website, and the account stores a user’s login credentials, as well as their payment used for purchasing books. I created the primary key, username, because in the account table each person has a distinct username that identifies them. In the *item_record* table, book_title is the primary key because I do not want to store multiple books titles in the item_record table itself, however, the user still has the ability to add multiple books to their checkout cart (which is the reason why I’ve created book_title as a foreign key in check_out). Although realistically, a book_id would be considered a primary key as well, I opted not to include this as one, since I’m using the same id values to identify each book within its book category (ex. Romance has a book name with an id 1, Fiction has a book name with an id of 1… etc.).


  ![ER Diagram](http://s7.postimg.org/mf6w2tvej/er_diagram.png)
  
`ER Diagram showing design for the account, checkout and item_record databases`

  Although it doesn’t show this explicitly in the diagram itself, lines in the cardinality should show a many to many mapping for both, **has** and **can_purchase** relationships between the tables account and check_out, as well as check_out and item_record. Many user accounts can have many books to checkout, while many checkout carts from users can purchase many books from an item_record table. Both have non-identifying relationships, in their relations, because the foreign keys, username and book_title, can occur more than once (the same user can purchase multiple books, including duplicate ones).

###SQL Statements:

Below is a list of all of the queries that are used in my program and an overview of what they do (the text in bold are the method names where they are located in). There is also an sql script file, “Sample SQL Queries”, in the folder with sample data to be tested if desired. The process of executing these queries in my program are explained in the next section.  


1)	Searches for a user’s login credentials  - **handleLogin**

![SQL query1](http://s9.postimg.org/hsf7ubtun/sql_query1.png)


2)	Searches for a user’s account balance after a successful login - **handleLogin**

![SQL q2](http://s18.postimg.org/rxsm9frt5/sql_q2.png)


3)	Verifying a username when registering a new account - **handleRegistration**

![SQL q3](http://s24.postimg.org/9ab8cvjk5/sql_q3.png)


4)	Registers a new user to the website- **handleRegistration**

![SQL q4](http://s7.postimg.org/dp1zwmdyz/sql_q4.png)


5)	Updates a user’s account balance – **changeAccount**

![SQL q5](http://s15.postimg.org/t4cf52z3f/sql_q5.png)


6)	Searches for a specific book title - **handleSearch**

![SQL q6](http://s2.postimg.org/gu76mfa2h/sql6.png)


7)	Shows all categories in item_record, if searching by book category -**handleSearchFilter**

![SQL q7](http://s27.postimg.org/ys2vgzu77/sql_q7.png)


8)	Display all the books from a specific category being searched - **handleSearchFilter**

![SQL q8](http://s17.postimg.org/suxpjfiwf/sql_q8.png)


9)	Deletes all of a user’s books in their checkout cart – **handleCheckout**

![SQL q9](http://s28.postimg.org/m9zlyvj0t/sql_q9.png)


10)	Displays all items in a user’s checkout cart – **viewCheckout**

![SQL q10](http://s15.postimg.org/5riz0b8dn/sql_q10.png)


11)	Deletes a specific item from a user’s checkout - **handleCheckout**

![SQL q11](http://s7.postimg.org/9ucdz9yyj/sql_q11.png)


12)	Inserts a user’s desired item into their checkout cart by getting either the book id and book category or the book id and book title that is entered by the user – **promptAddCheckout**

![Sql q12](http://s11.postimg.org/4x7okzbs3/sql_q12.png)


13)	Calculates the total of all items in a user’s checkout cart – **handlePurchase**

![SQL q13](http://s8.postimg.org/t46td6ez9/sql_q13.png)


14)	Sets the remaining account balance back in a user’s account after calculating change –**handlePurchase**

![SQl q14](http://s9.postimg.org/azvfz2db3/sql_q14.png)


###Accessing the Database

I’ve created SQL queries, in addition to other operations (INSERT, DELETE, and UPDATE) that are used within the parts of my program that verifies a user’s credentials, updates a user’s account balance, searches for books in the bookstore catalogue, and manipulates data within a user’s checkout cart. In my Menus class, I have a variable, SQLstatement, where I set each SQL statement that will be executed. When the database is to be accessed, it will usually follows these steps: (1) set the SQL statement being performed, or (2) access the database and perform the action in the SQL statement. The following code snippet, from the method handleLogin, shows how this works:

![handleSearch1](http://s11.postimg.org/auy77lx4j/SQL_ex1.png)

`(1) Sets the query in SQLstatement, (2) checks if login credentials are found in account table, (3) prints the message inside IF statement, if true, and verifies that a login has been  successful.`

The above mentioned process is used for accessing the account and check_out tables in the database, but a slight variation is used when the user is searching for a book in the item_record table. Before I explain this process, it should be noted that when the user is searching for an item, they have the ability to search, via book title or by book category (read the comments in the method handleSearch in my Menus class for more information). In addition to setting the SQL statement in the variable, I have two Boolean variables, isItem_Record, and isCategory, that are used to indicate whether or not the database being accessed is from the item_record table, and if I would like to see just the categories within the item_record table or information about a discovered book in the inventory (book title, author, price). The following code snippets, from within methods, handleSearchFilter and handleSearch, shows how this process works:


![handlesearch2](http://s24.postimg.org/jinj7j7px/SQL_query_search.png)

 `In this example, from, handleSearchFilter, the SQL statement is set, then both variables are set to true to indicate that the user would like to see all categories from the item_record table`

![handleSearch3](http://s18.postimg.org/9wctvr5g9/handle_Search3.png)

`In this example, from handleSearch method, the isItem_Record variable is set to true to indicate that the user would like to see information about a discovered book. Note: Since isCategory is set by default to false, we do not have to set it to false here.`

In the DB class, the method handleQueries, checks for the values of isItem_Record and isCategory, and displays either all categories in the table or information regarding a discovered book. There is also a condition to handle the display of all the items in the checkout cart (See method for more information).

The above mentioned subsections explained the features that I felt needed the most elaboration. For the rest of my design implementation please refer to the comments within my code.

#Potential Bugs

While this application works, there are some bugs that could occur depending on the user's input. Below are some of the bugs I have identified:

**Verifying record action from the database**

- When searching for a book by category, the user isn't notified whether or not their book has been added to their checkout cart, after they have selected a book
by its book id.

- User is not notified that their book has been deleted from their checkout cart after typing in the book to delete.

- User is not shown all of their books in their checkout cart when they are
prompted to delete just a single  book from their cart.

 **Note:** To remedy the above issues, the user will have to go to the checkout menu enter the option to view all of their books in their checkout cart. (You could also look inside the database itself).

**Deleting Purchased Records from the Checkout Cart**

- After making a purchase, all of the items from within a user's checkout cart
 does not delete from the database.

 **Note:** The user will have to manually delete the records by entering
"c" from the checkout menu.

#Project Demo

Below is a demo of my project. Demonstration shows: a user logging into
an existing account, searching for a book by entering in a book title, adding
a searched book to the checkout cart and making a purchase.

![Demo](https://media.giphy.com/media/l0NwynxaONw6paOly/giphy.gif)
