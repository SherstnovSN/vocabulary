package validator;

public interface Validator {
    
    String getExample();
    
    boolean validate(String source, String translation);
    
}
