# Bookstore-Website-Simulator


#Project Summary: 

This project was a college project for my Database Management class. The goal was to identify a real-world application where we would utilize a database and implement it using whatever languages we desired. I decided to create a simulator for purchasing books from a bookstore website. The program output is displayed as text from within a console.  In addition to coding the project, we needed provide documentation that explains, our application functionalities, defines our targeted users, and include an ER Diagram, to model the domain of our database used in our application. 


Below, I've included a brief outline of the tools I used to implement my application, features that I incorporated into implementing it, and an explaination of what happens when the program is run. For a more detailed description of how I designed this project,  
[download](https://drive.google.com/open?id=0B_Mzb0tpEYLWRTNGWFNaR2R5TWc)  the following documentation. 



#Whats Contained in the Document:

In my document I explain, why I chose to create a simulation of an e-commerce bookstore website, identify the targeted design features I wanted to implement,  outline the design of each of the classes, tables and SQL queries used in my database, and how I access the database in the backend. In addition, I've also included an ER-Diagram, which details the relationships each of my tables have in the database, and a screen shot of a sample test run of the program. Finally, I offer a conclusion, explaining some of the challenges I faced while working on it.

#Features for the Application

Basic Features 

-	Registering a new account 
-	Logging into an existing account 
-	Search books from within a database
-	Keep a saved record of books to be purchased in a checkout cart
-	Purchase books stored in a user’s checkout cart 
-	Add an allowance to a user’s account

User Friendly Features:

-	Ability to log out from the account
-	Ability to handle invalid character input 
-	Ability to go back to a previous page  
-	Ability to delete books from a user’s checkout cart 

* In addition to these features, I have also designed my program, to handle, some common potential errors that could be made from within the program, based on user input. *
Database Usage: 

- For storing a user's username, password, and allowance
- All of the books that are available for sale
- All of the books that are within a user's checkout cart 

* The name of the database tables for which I have stored each of this information is called, "account", "item record", and "check out". * 

#Tools Used:

-	**MySQL Workbench** (to create and test the database tables)
-	**C3PO** (an external library which utilizes the data pooling technique when connecting to the database)
-	**Eclipse IDE** (for coding and testing the finished project)
-	**Programming Languages:** Java, JDBC, MySQL


**Note: ** I've created some SQL files containing the database tables needed for the databaseL. You will need to import them using MySQL Workbench. To download the files, go [here](https://drive.google.com/folderview?id=0B_Mzb0tpEYLWeGcyMHc3cFFlZ3M&usp=sharing)










