package validator;

public class EngRusValidator implements Validator{
    
    private final String EXAMPLE = "book книга";
    
    @Override
    public String getExample() {
	return EXAMPLE;
    }
    
    @Override
    public boolean validate(String source, String translation) {
	if(source.matches("^[a-z]+$") && translation.matches("^[а-я]+$")) return true; 
	return false;
    }
}
