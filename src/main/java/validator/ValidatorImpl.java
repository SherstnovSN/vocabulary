package validator;

import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator {

    @Override
    public boolean validate(String source, String sourceRegex) {
        return source.matches(sourceRegex);
    }

    @Override
    public boolean validate(String source, String sourceRegex, String translation, String translationRegex) {
        return source.matches(sourceRegex) && translation.matches(translationRegex);
    }

}
