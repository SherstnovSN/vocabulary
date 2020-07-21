package vocabulary;

import java.util.HashMap;

import validator.Validator;

public class VocabularyImpl implements Vocabulary{
    
    private Validator validator;
    private HashMap<String, String> vocabulary = new HashMap<String, String>();
    
    public VocabularyImpl(Validator validator) {
	this.validator = validator;
    }
    
    @Override
    public boolean add(String source, String translation) {
	if(validator.validate(source, translation)) {
	    vocabulary.put(source, translation);
	    return true;
	}
	return false;
    }
    
    @Override
    public HashMap<String, String> getAll() {
	return vocabulary;
    }
    
    @Override
    public String get(String source) {
	return vocabulary.get(source);
    }
    
    @Override
    public boolean delete(String source) {
	vocabulary.remove(source);
	return true;
    }

}
