package Model;

import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

/**
 * Class that represents the Client instance
 **/
public class Client {
    private int ID;
    private String name;
    private String address;
    private String email;
    private int age;
    private String phone;

    public Client(int parseInt, String s, String s1, String s2) {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Client() {
    }

    public Client(int clientID, String name, String email, String address,int age, String phone) {
        super();
        this.ID = clientID;
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
        this.phone=phone;
    }


    public int getID() {
        return ID;
    }

    public void setID(int clientID) {
        this.ID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> clientDetails(){
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(ID));
        list.add(name);
        list.add(email);
        list.add(phone);
        return list;
    }
    @Override
    public String toString() {
        return "Client [clientID:" + ID + ", name:" + name + ", address:" + address + ", email:" + email + ", age:" + age
                + "phone number: " +phone+ "]";
    }

}
