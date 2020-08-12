package service;

import domain.Position;
import domain.Translation;
import domain.Vocabulary;

public interface PositionService {

    boolean addPosition(String source, String[] translations, Vocabulary vocabulary);

    Position getFromAllVocabularies(String source);

    Position getFromVocabulary(String source, Vocabulary vocabulary);

    Translation getTranslationById(int translationId);

    boolean addTranslation(String source, String[] translations);

    boolean deletePosition(String source);

    boolean deleteTranslation(int translationId);

}
