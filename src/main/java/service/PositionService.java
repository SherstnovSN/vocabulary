package service;

import domain.Position;
import domain.Vocabulary;

import java.util.List;

public interface PositionService {

    void setVocabulary(Vocabulary vocabulary);

    boolean add(String source, String translation);

    List<Position> getAll();

    Position get(String source);

    boolean delete(String source);

}
