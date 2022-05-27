package bbl.Validators;

import Model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 100;
    @Override
    public void validate(Model.Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }
    }

}
