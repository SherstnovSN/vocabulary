package controller;

import vocabulary.Vocabulary;

import java.util.Map;
import java.util.Set;

public class ControllerImpl implements Controller {

    private Vocabulary vocabulary;

    public ControllerImpl(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    @Override
    public void addNewPosition(String source, String translation) {
        vocabulary.add(source, translation);
    }

    @Override
    public Set<Map.Entry<String, String>> getVocabulary() {
        return vocabulary.getAll().entrySet();
    }

    @Override
    public String getTranslation(String source) {
        return vocabulary.get(source);
    }

    @Override
    public void deletePosition(String source) {
        vocabulary.delete(source);
    }
}
