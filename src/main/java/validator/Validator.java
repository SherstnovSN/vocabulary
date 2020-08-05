package validator;

public interface Validator {

    boolean validate(String source, String sourceRegex);

    boolean validate(String source, String sourceRegex, String translation, String translationRegex);
}
