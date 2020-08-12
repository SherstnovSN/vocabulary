package dao;

import domain.Position;
import domain.Translation;
import domain.Vocabulary;

public interface PositionDAO {

    void add(Position position);

    Position getFromAllVocabularies(String source);

    Position getFromVocabulary(String source, Vocabulary vocabulary);

    Translation getTranslationById(int translationId);

    void delete(Position position);

}
