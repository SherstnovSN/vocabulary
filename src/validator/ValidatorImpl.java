package validator;

public class ValidatorImpl implements Validator {

    private String sourceRegex;
    private String translationRegex;

    public ValidatorImpl(String sourceRegex, String translationRegex) {
        this.sourceRegex = sourceRegex;
        this.translationRegex = translationRegex;
    }

    @Override
    public boolean validate(String source, String translation) {
        return source.matches(sourceRegex) && translation.matches(translationRegex);
    }
}
