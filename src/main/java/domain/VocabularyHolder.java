package domain;

import org.springframework.stereotype.Component;

@Component
public class VocabularyHolder {

    private Vocabulary vocabulary;

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
}
