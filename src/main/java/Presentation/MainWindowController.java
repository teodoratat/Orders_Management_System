package Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import start.OrderManagementApp;

import java.io.IOException;
/**
 * Class that controls the main window
 */
public class MainWindowController {
    @FXML
    public void onClientsButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
    @FXML
    public void onOrdersButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("order.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);

    }
    @FXML
    public void onProductsButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("product.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);

    }
}
