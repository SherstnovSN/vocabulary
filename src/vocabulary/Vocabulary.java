package vocabulary;

import java.util.HashMap;

public interface Vocabulary {
    
    boolean add(String source, String translation);
    
    HashMap<String, String> getAll();
    
    String get(String source);
    
    boolean delete(String source);

}
