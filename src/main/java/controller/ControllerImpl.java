package controller;

import org.springframework.beans.factory.annotation.Autowired;
import vocabulary.Vocabulary;

import java.util.Map;
import java.util.Set;

public class ControllerImpl implements Controller {

    private Vocabulary vocabulary;

    @Override
    public void addNewPosition(String source, String translation) {
        vocabulary.add(source, translation);
    }

    @Override
    public Set<Map.Entry<String, String>> getVocabulary() {
        return vocabulary.getAll();
    }

    @Override
    public String getTranslation(String source) {
        return vocabulary.get(source);
    }

    @Override
    public void deletePosition(String source) {
        vocabulary.delete(source);
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
}
