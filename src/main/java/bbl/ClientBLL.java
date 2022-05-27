package bbl;

import DataAcces.ClientDAO;
import Model.Client;
import bbl.Validators.ClientAgeValidator;
import bbl.Validators.EmailValidator;
import bbl.Validators.PhoneNumberValidator;
import bbl.Validators.Validator;

import java.util.List;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;
    //InputClientView view;
    public ClientBLL()
    {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientAgeValidator());
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
        clientDAO = new ClientDAO();
    }
    public Client selectById(String id){
        Client client = clientDAO.findById(id);
        if(client == null) {
            //throw new NoSuchElementException();
            //JOptionPane.showMessageDialog(view,"The client with id=" + id + " was not found!");
        }
        return client;

    }
    public List<Client> selectAllClients(){
        List<Client> clients = clientDAO.findAll();
        if(clients == null){
            throw new NoSuchElementException();
        }
        return clients;
    }
    public boolean addNewClient(Client client){
        ClientDAO clientDAO = new ClientDAO();
        for(Validator<Client> i: validators){
            i.validate(client);
        }
        return clientDAO.insert(client);
    }

    public boolean update(Client client){
        ClientDAO clientDAO = new ClientDAO();
        for(Validator<Client> i: validators){
            i.validate(client);
        }
        return clientDAO.update(client);
    }
    public void delete(Client client) {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.delete(client.getID());
    }

}
