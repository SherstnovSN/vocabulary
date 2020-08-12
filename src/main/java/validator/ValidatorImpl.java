package validator;

import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator {

    @Override
    public boolean validate(String word, String regex) {
        return word.matches(regex);
    }

}
