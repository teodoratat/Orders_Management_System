package Model;
/**
 * Class that represents the Product instance
 **/
import java.util.ArrayList;
public class Product {
    public int  ID;
    public String name;
    public int stock;
    public double price;

    public Product(){}

    public Product(int  productID, String description, int stock, double price){
        this. ID= productID;
        this.name =description;
        this.stock=stock;
        this.price=price;
    }

    public int getID() {
        return  ID;
    }

    public void setID(int  productID) {
        this. ID =  productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString( ID));
        list.add(name);
        list.add(Integer.toString(stock));
        list.add(Double.toString(price));

        return list;
    }
    public String toString() {
        return "Product [productID:" + ID + ", name:" + name + ", price:" + price + ", stock: "+ stock+ " ]";
    }
}
