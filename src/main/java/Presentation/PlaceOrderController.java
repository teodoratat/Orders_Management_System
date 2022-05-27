package Presentation;

import Model.Client;
import Model.Order;
import Model.Product;
import bbl.ClientBLL;
import bbl.OrderBLL;
import bbl.ProductBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import start.OrderManagementApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client,Integer> clientIdColumn;
    @FXML
    private TableColumn<Client, String> clientNameColumn;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private Label errorLabel;
    @FXML
    private Spinner<Integer> stockSpinner;

    private List<Client> clients = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,1));
        clients = new ClientBLL().selectAllClients();
        products = new ProductBLL().selectAllProducts();
        clientTable.setItems(FXCollections.observableList(clients));
        productTable.setItems(FXCollections.observableList(products));
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("ID"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
    }

    @FXML
    public void onPlaceOrderButtonClick() throws IOException {
        ObservableList<Product> selectedProducts;
        selectedProducts = productTable.getSelectionModel().getSelectedItems();
        ObservableList<Client> selectedClient;
        selectedClient = clientTable.getSelectionModel().getSelectedItems();
        if(selectedProducts.size()!=1 || selectedClient.size()!=1){
            errorLabel.setVisible(true);
            return;
        }
        if(selectedProducts.get(0).getStock()<stockSpinner.getValue()){
            errorLabel.setVisible(true);
            return;
        }
        Order order = new Order(new Random().nextInt(10000),selectedClient.get(0).getID(),selectedProducts.get(0).getID(),stockSpinner.getValue());

        new OrderBLL().addNewOrder(order);
        selectedProducts.get(0).setStock(selectedProducts.get(0).getStock()-stockSpinner.getValue());
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("order.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
        new OrderBLL().generateBill(order);

    }
}