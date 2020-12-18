# Dice-Roller
This Spring Boot Web Application in MVC style simulates dice rolling operations. 
The structure of the application includes a view module, data module and controller module.

The View Module

In the index.html page, the user fills in the number of times they would like the dice rolled and select a number 
of how many sides each dice has (4, 5, 8, 10, 12 or 20). It is clear that all dices have the same number of faces.
The form on the index.html is processed by a method called rollDice which is a part of the HomeController class.
The header, footer and the image of the dice on both html pages is provided in the supplement.html page.

The rollResult.html page is a Thymeleaf template which retrieves the current dice object from the HomeController and
outputs the expected result.The 'Same Again' hyperlink links back tothe controller so the exact same number and type
of dice are rolled. The 'New Roll' hyperlink removes all records of the previous rolling from the database as well as 
clears all dice objects in the controller. The hyperlink also redirects the user to the index.html page to start a new
rolling of the dice.

The Data Module

Dice.java
The object representing the dice is a bean with the following properties; number of dice, number of sides per die,
sum of all dice faces and results. The result property contatins an array representing the result of rolling the dice.
Each dice rolls an integer from 1 to N, where N is the number of sides. 

DatabaseAccess.java
This class contains insertDice, diceList, and diceDelete CRUD methods. A record will be create for each dice rolling which 
includes the number of die as well as the type of die and gets stored in an H2 Database which was created.

Schema.sql
The SQL scripts create a table called dice in the in-memory H2 Database to be used for storing dice rolling records.
Each dice object is stored in a new table row (record).

The Controller Module

HomeController.java
This controller's purpose is to verify he input is correct (parameters must exist and contain non negative integers), then 
create an object representing the dice. After that, the object is instructed to simulate rolling dice as requested by the
user and forward the request to the view. If something is with the parameters, it is forwarder to an error page which says
"You entered invalid data." and shows a hyperlink that redirects user back to the index.html page.

FindTotalController.java
This is a rest controller that is responsible for calculating the sum of all dices. The method receives a dice object from
the HomeController and sums up all dice faces that were recorded in the "result" field of the dice object (bean). Upon receiving 
the returned value from the rest controller, HomeController will update its dice object accordingly and performs the required CRUD
operations in the database. Finally, it would dispatch to RollResult.html page to display the rolling result.
