package ua.goit.offline.chat.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
@Component
public class ValidationService implements Validator {

    private List<Validator> validators;

    @Autowired
    public ValidationService(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(String validate) {
        for (Validator validator: validators) {
            if (!validator.isValid(validate)) {
                return false;
            }
        }
        return true;

    }
}
