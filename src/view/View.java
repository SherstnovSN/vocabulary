package view;

public interface View {

    String selectCommand();
    
    void addNewPositionToVocabulary();
    
    void showVocabulary();
    
    void showTranslation();
    
    void deletePositionFromVocabulary();
    
    void showExit();
    
}
