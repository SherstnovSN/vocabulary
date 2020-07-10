package vocabulary;

import java.util.HashMap;

public class VocabularyImpl implements Vocabulary{
    
    private HashMap<String, String> vocabulary = new HashMap<String, String>();
    
    @Override
    public boolean add(String source, String translation) {
	vocabulary.put(source, translation);
	return true;
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
