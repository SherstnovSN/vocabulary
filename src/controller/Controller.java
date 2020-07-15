package controller;

import java.util.Map;
import java.util.Set;

public interface Controller {
    
    void addNewPosition();
    
    Set<Map.Entry<String, String>> getVocabulary();
    
    String getTranslation();
    
    void deletePosition();
    
}
