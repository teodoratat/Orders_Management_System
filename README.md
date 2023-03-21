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
### Architectural patterns
Singleton Pattern - It is an OOP design mode that involves using a single instance of an object to solve a problem. In this case, it was used to manage the application's connection to the database, including establishing a connection and closing it or other objects such as Statements and ResultSets.
## GUI
Here I have used JavaFX to help me with the four windows that put together the application.   
The file “mainWindow.fxml” is controlled by the class MainWindowController and it is shown three buttons for the user to choose on which table it wants to operate:
### 
![image](https://user-images.githubusercontent.com/79631600/226592507-b08d2dfb-bb5d-4e9b-a1f6-fae4b1c03f7f.png)   
 The file “client.fxml” is controlled by the class ClientsWindowController and it is shown three buttons for the user to choose what operation wants to perform:
 ###
 ![image](https://user-images.githubusercontent.com/79631600/226592665-6ca334ac-94c4-46c5-a0ac-3ed1d00ea6e6.png)   
 The file “product.fxml” is controlled by the class ProductsWindowController and it is shown three buttons for the user to choose what operation wants to perform:
 ###
 ![image](https://user-images.githubusercontent.com/79631600/226592800-6ae14d15-8c6c-4e4b-b500-4b551e5b6126.png)
   The file “order.fxml” is controlled by the class OrderWindowController and it is shown three buttons for the user to choose what operation wants to perform, but it is different from the Client and Product window because when pressing the “add” button, the window “place-order.fxml” controlled by the PlaceOrderWindowController. 
   
   ###
   ![image](https://user-images.githubusercontent.com/79631600/226592898-0594f19e-a4b7-4e01-94d2-d3e9645834fe.png)
   
   
   Here is presented the place-order functionality:
   ### 
 ![image](https://user-images.githubusercontent.com/79631600/226593091-df67b4f0-619a-4d81-9c1d-f5f0f22af280.png)
   
The user can select the client and the product the client wants to order, and also the quantity. If the quantity is bigger than the stock, an error message is shown. If not, the order is added in the order window and the stock in the product class is decreased. In case the stock is equal to the quantity, the product is deleted.
## Conclusions 
This project has been a great help in getting used to Java Reflection. Java Reflection is a unique technique, specific to Java, but very useful, allowing even the abstraction of extracting a class from a ResultSet. Dynamic invocation of methods and obtaining certain markers placed on a variable instance have a very high applicability in projects with certain very repetitive things, such as a database application, where queries and obtaining an object from it have a similar pattern.
Another thing I learned from this project is working with Annotations, which again are very useful, especially used in Reflection to mark certain things about a variable instance, method, or class.
Regarding working with a Java database, I refreshed my memory related to creating and closing a connection to a database, along with basic knowledge about queries (their structure, their Java call, trigger creation)
I also realized that Tables in JavaFX are much more complex and versatile than I thought. In trying to customize Tables with TableCellRenderer and TableCellEditor, we realized that they can replace or make editing much easier than JPanel or JTable with multiple editable components.


