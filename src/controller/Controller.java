package controller;

import java.util.Map;
import java.util.Set;

public interface Controller {
    
    void addNewPosition(String source, String translation);
    
    Set<Map.Entry<String, String>> getVocabulary();
    
    String getTranslation(String source);
    
    void deletePosition(String source);
    
}
