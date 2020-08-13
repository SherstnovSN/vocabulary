package service;

import domain.Position;
import domain.Vocabulary;

import java.util.List;

public interface PositionService {

    void addPosition(String source, String[] translations, Vocabulary vocabulary);

    Position getPositionById(int positionId);

    List<Position> getFromAllVocabularies(String search, String source);

    List<Position> getFromVocabulary(String search, String source, Vocabulary vocabulary);

    void addTranslation(int positionId, String[] translations);

    void deletePosition(int positionId);

    void deleteTranslation(int translationId);

}
