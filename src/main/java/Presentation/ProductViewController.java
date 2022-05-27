package Presentation;

import Model.Client;
import Model.Product;
import bbl.ClientBLL;
import bbl.ProductBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import start.OrderManagementApp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ProductViewController implements Initializable {
    @FXML
    private TableView<Product> table ;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;

    List<Product> products = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.products = new ProductBLL().selectAllProducts();
        table.setItems(FXCollections.observableList(products));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        nameColumn.setOnEditCommit(pe -> {
            pe.getRowValue().setName(pe.getNewValue());
            new ProductBLL().update(pe.getRowValue());
        });
        priceColumn.setOnEditCommit(ce -> {
            ce.getRowValue().setPrice(ce.getNewValue());
            new ProductBLL().update(ce.getRowValue());
        });
        stockColumn.setOnEditCommit(pe -> {
            pe.getRowValue().setStock(pe.getNewValue());
            new ProductBLL().update(pe.getRowValue());
        });
    }

    @FXML
    public void onButtonBackClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
    @FXML
    public void onAddButtonClick() throws IOException {
        Product product = new Product(new Random().nextInt(10000),"DEFAULT",0,0);
        new ProductBLL().addNewProduct(product);
        this.products.add(product);
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("product.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
    @FXML
    public void onDeleteButtonClick() throws IOException {
        ObservableList<Product> singleProduct;
        singleProduct = table.getSelectionModel().getSelectedItems();
        for(Product product: singleProduct){
            new ProductBLL().delete(product);
            this.products.remove(product);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("product.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
}

