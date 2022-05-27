package Model;

import java.util.ArrayList;
/**
 * Class that represents the Order instance
 **/
public class Order {

    private int ID;
    private int clientID;
    private int productID;
    private int quantity;

    public Order(int orderID,int clientID, int productID, int quantity)
    {
        this.ID=orderID;
        this.clientID=clientID;
        this.productID=productID;
        this.quantity=quantity;
    }

    public Order(){

    }
    public int getID() {
        return ID;
    }

    public void setID(int orderID) {
        this.ID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> toStringList(Order order) {
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(ID));
        list.add(Integer.toString(clientID));
        list.add(Integer.toString(productID));
        list.add(Integer.toString(quantity));
        return list;
    }
    /*public String toString(Order order) {
        return "Client{" +
                "id=" + order.getClientID() +
                "Client name" + order
                ", product='" + order.getProductID() + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }*/

}
