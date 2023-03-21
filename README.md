# Orders_Management_System

## Project specifications 
Consider an application Orders Management for processing client orders for a warehouse. Relational databases should be used to store the products, the clients, and the orders.
The application should be designed according to the layered architecture pattern and should use (minimally) the following classes:
## Implementation
### Functional requirements
Persistence of information. Customer, product, and order information must persist after closing the application, which requires the use of a relational database.
Managing operations from a graphical interface. It is more natural that in such a scenario where users are not programmers to make a graphical interface for managing operations. From a menu, you can choose whether you want operations on customers products or orders.
Automatically create a table. It is necessary to display in the graphical interface the information from the database in a JTable. Furthermore, its header containing the displayed attribute names must be generated automatically by Reflection
Data validation. This aspect refers to the verification of the correctness of the data before entering them in the database. When designing an application for a user, the chances are high that a person will make a mistake when he wants to assign values to some fields, either inadvertently or out of pure sociopathic intentions. Because the table fields in the database depend on implementation, cases of erroneous data and their actual treatment will be discussed later.
### Non-functional requirements
Issuing a receipt for an order. A receipt containing the information related to any order, as well as real-time orders, would be a good thing to have so that an employee does not have to do it manually every time.
Query automation. The syntax of MySQL queries can be reduced to a pattern that depends only on the type of operation (delete, edit, select, insert). Therefore, knowing Java Reflection techniques, they can be easily generated as long as the tables are mapped in Java with exactly the same name as in MySQL.
