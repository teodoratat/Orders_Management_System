package bbl.Validators;

import Model.Client;

public class PhoneNumberValidator implements Validator<Client> {
    @Override
    public void validate(Model.Client client){
            if(client.getPhone().length()!=10 && client.getPhone().length()!=12)
                throw new IllegalArgumentException("The phone number is invalid!");
        if(client.getPhone().matches("[0-9]+")==false)
            throw new IllegalArgumentException("The phone number is invalid!");

        }
}
