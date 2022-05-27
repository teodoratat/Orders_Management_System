package bbl;

import DataAcces.OrderDAO;
import DataAcces.ProductDAO;
import Model.Order;
import Model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    ProductDAO productDAO = new ProductDAO();

    public Product selectById(String id){
        Product product = productDAO.findById(id);
        if(product == null) {
            throw new NoSuchElementException();
            //JOptionPane.showMessageDialog(view,"The Order with id=" + id + " was not found!");
        }
        return product;

    }
    public List<Product> selectAllProducts(){
        List<Product> products = productDAO.findAll();
        if(products == null){
            throw new NoSuchElementException();
        }
        return products;
    }
    public boolean addNewProduct(Product product){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.insert(product);
    }

    public boolean update(Product product){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.update(product);
    }
    public void delete(Product product) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.delete(product.getID());
    }

}
