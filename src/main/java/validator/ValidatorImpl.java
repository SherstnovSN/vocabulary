package validator;

public class ValidatorImpl implements Validator {

    private String sourceRegex;
    private String translationRegex;

    @Override
    public boolean validate(String source, String translation) {
        return source.matches(sourceRegex) && translation.matches(translationRegex);
    }

    public void setSourceRegex(String sourceRegex) {
        this.sourceRegex = sourceRegex;
    }

    public void setTranslationRegex(String translationRegex) {
        this.translationRegex = translationRegex;
    }
}
