# Bookstore-Website-Simulator

I've created a wiki with a detailed description of how I implemented each feature into this assignment. To read it go [here](https://github.com/JerJohn15/Bookstore-Website-Simulator/wiki/Design-Plan)

I've also created a [Test Plan](https://github.com/JerJohn15/Bookstore-Website-Simulator/wiki/Test-Plan) with a demo of unit tests using Junit.

###Project Summary:

This was a college project for my Database Management course. The goal was to identify a real-world application where we would utilize a database and implement it using whatever programming language/s we desired. I decided to create a simulator for purchasing books from a bookstore website. The program output is displayed as text from within a console.  In addition to coding the project, we needed to provide documentation that explains, our application's functionality, defines our targeted users, and includes an ER Diagram, to model the domain of our database used in our application.


###Features for the Application

**Basic Features**

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
-  For storing all of the books that are available for sale
- For storing all of the books that are within a user's checkout cart

*The name of the database tables for which I have stored each of this information is called, "account", "item_record", and "check _out".*

###Tools Used:

-	**MySQL Workbench** (to create and test the database tables)
-	**C3PO** (an external library which utilizes the data pooling technique when connecting to the database)
-	**Eclipse IDE** (for coding and testing the finished project)
-	**Programming Languages:** Java, JDBC, MySQL
- **Junit**

**Note:** I've created some SQL files containing the database tables needed for the database. You will need to import them using MySQL Workbench. To download the files, go [here](https://drive.google.com/folderview?id=0B_Mzb0tpEYLWeGcyMHc3cFFlZ3M&usp=sharing)

###Project Setup

```java
//Open Windows Git Bash (or command line), cd to your desired project directory, and type

git clone https://github.com/JerJohn15/Bookstore-Website-Simulator.git

```


###Project Demo

Below is a demo of my project. The demonstration shows: a user logging into
an existing account, searching for a book by entering in a book title, adding
a searched book to the checkout cart, and making a purchase.

![Demo](https://media.giphy.com/media/l0NwynxaONw6paOly/giphy.gif)
