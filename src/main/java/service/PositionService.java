package service;

import domain.Position;
import domain.Vocabulary;

import java.util.List;

public interface PositionService {

    boolean add(String source, String translation, Vocabulary vocabulary);

    List<Position> getAll();

    Position getFromAllVocabularies(String source);

    boolean edit(String source, String translation);

    boolean delete(String source);

}
