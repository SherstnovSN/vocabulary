package service;

import domain.Position;
import domain.Vocabulary;

public interface PositionService {

    void addPosition(String source, String[] translations, Vocabulary vocabulary);

    Position getFromAllVocabularies(String source);

    Position getFromVocabulary(String source, Vocabulary vocabulary);

    void addTranslation(String source, String[] translations);

    void deletePosition(String source);

    void deleteTranslation(int translationId);

}
