package start;

import Model.Order;
import bbl.ClientBLL;
import bbl.OrderBLL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

public class OrderManagementApp extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
/*

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Order;
import Model.Product;
import bbl.OrderBLL;
import bbl.ProductBLL;

*/
/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 *//*

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {

        ProductBLL productBLL = new bbl.ProductBLL();

        Product client1 = new Product(4,"pants",22,300);


        try {
            productBLL.delete(client1);

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }

        // obtain field-value pairs for object through reflection
        ReflectionExample.retrieveProperties(client1);
        //MainInterface mainInterface = new MainInterface();
        //mainInterface.setVisible(true);
    }

}
*/
