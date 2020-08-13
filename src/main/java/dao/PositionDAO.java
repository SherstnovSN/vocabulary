package dao;

import domain.Position;
import domain.Translation;
import domain.Vocabulary;

import java.util.List;

public interface PositionDAO {

    void add(Position position);

    Position getPositionById(int positionId);

    List<Position> getFromAllVocabulariesBySource(String source);

    List<Position> getFromAllVocabulariesByTranslation(String translation);

    List<Position> getFromVocabularyBySource(String source, Vocabulary vocabulary);

    List<Position> getFromVocabularyByTranslation(String source, Vocabulary vocabulary);

    Translation getTranslationById(int translationId);

    void delete(Position position);

}
