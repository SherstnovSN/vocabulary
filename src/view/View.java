package view;

import java.util.Map;
import java.util.Set;

public interface View {

    void showVocabularies();

    void showCommands();
    
    void showVocabulary(Set<Map.Entry<String, String>> vocabularyEntry);
    
    void showTranslation(String translation);
    
    void showExit();
    
}
