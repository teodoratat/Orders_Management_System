package bbl;

import DataAcces.ClientDAO;
import DataAcces.OrderDAO;
import DataAcces.OrderDAO;
import DataAcces.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Order;
import Model.Product;
import bbl.Validators.Validator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    OrderDAO orderDAO = new OrderDAO();

    public Order selectById(String id){
        Order order = orderDAO.findById(id);
        if(order == null) {
            throw new NoSuchElementException();
        }
        return order;

    }
    public List<Order> selectAllOrders(){
        List<Order> orders = orderDAO.findAll();
        if(orders == null){
            return new ArrayList<Order>();
        }
        return orders;
    }
    public boolean addNewOrder(Order order){
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        int id= order.getProductID();
        String pid = String.valueOf(id);
        Product product=productDAO.findById(pid);
        product.setStock(product.getStock()-order.getQuantity());
        if(product.getStock()==0)
        {
            productDAO.delete(product.getID());

        }
        productDAO.update(product);
        /*
        ProductDAO productDAO=new ProductDAO();
            int pid=order.getIdProduct();
            String prodID=String.valueOf(pid);
            Product product=productDAO.findById(prodID);
            int pStock=product.getStock();
            product.setStock(pStock-order.getQuantity());
            productDAO.update(product.toStringList());
            return orderDAO.insert(values);
         */

        return orderDAO.insert(order);
    }

    public boolean update(Order order){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.update(order);
    }
    public void delete(Order order) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.delete(order.getID());
    }

    /*public void generateBill(int id, Order order) {
        File file = new File("bill" + id + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert fw != null;
        PrintWriter pw = new PrintWriter(fw);
        ClientBLL clientBLL = new ClientBLL();
        Client client = clientBLL.selectById(Integer.toString(order.getID()));

        ProductBLL productBLL = new ProductBLL();

        Product product = productBLL.selectById(Integer.toString(order.getID()));

        OrderBLL orderBLL = new OrderBLL();

        pw.println("Order number: " + id);
        pw.println("Client: " + client.getName());
        pw.println("Product: "+ product.getName());
        pw.println("Quantity :" + order.getQuantity());
        pw.println("Total price: " + order.getQuantity()*product.getPrice());
        pw.close();

        System.out.println("bill printed!");

    }*/



    public void generateBill(Order o) {
        try {
            FileWriter myObj = new FileWriter("file" + o.getID() + ".txt");
            ClientBLL clientBLL = new ClientBLL();
            Client client = clientBLL.selectById(Integer.toString(o.getClientID()));
            ProductBLL productBLL = new ProductBLL();
            Product product = productBLL.selectById(Integer.toString(o.getProductID()));
            OrderBLL orderBLL = new OrderBLL();
            //myObj.write(o.toString());
            myObj.write("Order number: " + o.getID()+"\n");
            myObj.write("Client: " + client.getName()+"\n");
            myObj.write("Product: "+ product.getName()+"\n");
            myObj.write("Quantity :" + o.getQuantity()+"\n");
            myObj.write("Total price: " + o.getQuantity()*product.getPrice());
            myObj.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
