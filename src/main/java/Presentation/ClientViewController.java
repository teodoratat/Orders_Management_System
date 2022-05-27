package Presentation;

import Model.Client;
import bbl.ClientBLL;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import start.OrderManagementApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Class that controls the window for the client
 */
public class ClientViewController implements Initializable {
    @FXML
    private TableView<Client> table ;
    @FXML
    private TableColumn<Client,Integer> idColumn;
    @FXML
    private TableColumn<Client,String> nameColumn;
    @FXML
    private TableColumn<Client,String> addressColumn;
    @FXML
    private TableColumn<Client,String> mailColumn;
    @FXML
    private TableColumn<Client,Integer> ageColumn;
    @FXML
    private TableColumn<Client,String> phoneColumn;

    private List<Client> clients = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.clients = new ClientBLL().selectAllClients();
        table.setItems(FXCollections.observableList(clients));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("ID"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        mailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mailColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("age"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("phone"));
        nameColumn.setOnEditCommit(ce -> {
            ce.getRowValue().setName(ce.getNewValue());
            new ClientBLL().update(ce.getRowValue());
        });
        mailColumn.setOnEditCommit(ce -> {
            ce.getRowValue().setEmail(ce.getNewValue());
            new ClientBLL().update(ce.getRowValue());
        });
        addressColumn.setOnEditCommit(ce -> {
            ce.getRowValue().setAddress(ce.getNewValue());
            new ClientBLL().update(ce.getRowValue());
        });
        ageColumn.setOnEditCommit(ce -> {
            ce.getRowValue().setAge(ce.getNewValue());
            new ClientBLL().update(ce.getRowValue());
        });
        phoneColumn.setOnEditCommit(ce -> {
            ce.getRowValue().setPhone(ce.getNewValue());
            new ClientBLL().update(ce.getRowValue());
        });
    }
    @FXML
    public void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
    @FXML
    public void onAddButtonClick() throws IOException {
      Client client = new Client(new Random().nextInt(10000),"DEFAULT","DEFAULT@gmail.com","DEFAULT",20,"0123456789");
      new ClientBLL().addNewClient(client);
      this.clients.add(client);

      FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("client.fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 600, 400);
      OrderManagementApp.primaryStage.setScene(scene);
    }
    @FXML
    public void onDeleteButtonClick() throws IOException {
        ObservableList<Client> singleClient;
        singleClient = table.getSelectionModel().getSelectedItems();
        for(Client client: singleClient){
            new ClientBLL().delete(client);
            this.clients.remove(client);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.pt2022_30423_tat_teodora_assigment3.HelloApplication.class.getResource("client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        OrderManagementApp.primaryStage.setScene(scene);
    }
}
