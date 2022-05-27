package Presentation;

import Model.Order;
import bbl.ClientBLL;
import bbl.OrderBLL;
import bbl.ProductBLL;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
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
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
/**
 * Class that controls the window for the order, where we have the tables
 */
public class OrderViewController implements Initializable {
    @FXML
    private TableView<Order> table ;
    @FXML
    private TableColumn<Order, Integer> idColumn;
    @FXML
    private TableColumn<Order, String> clientColumn;
    @FXML
    private TableColumn<Order, String> productColumn;
    @FXML
    private TableColumn<Order, Integer> quantityColumn;

    List<Order> orders = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.orders = new OrderBLL().selectAllOrders();
        table.setItems(FXCollections.observableList(orders));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        //clientColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(new ClientBLL().selectById((String.valueOf(getValue().getID())))));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
      /*  clientColumn.setCellValueFactory(p ->
        {
            try {
                return new ReadOnlyObjectWrapper(new ClientBLL().selectById(String.valueOf(p.getValue().getID())).getName());

            }
            catch (NoSuchElementException e){
                return new ReadOnlyStringWrapper("error");
            }

        });

        productColumn.setCellValueFactory(p ->
        {
            try {
                return new ReadOnlyObjectWrapper(new ProductBLL().selectById(String.valueOf(p.getValue().getID())).getName());
            }
            catch (NoSuchElementException e){
                return new ReadOnlyStringWrapper("error");
            }

        });*/

        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    }

    @FXML
    public void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
    @FXML
    public void onAddButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("place-order.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }


}
