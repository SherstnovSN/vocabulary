package DAO;

import domain.Position;
import domain.Vocabulary;

import java.util.List;

public interface PositionDAO {

    void add(Position position);

    List<Position> getAll();

    Position getFromAllVocabularies(String source);

    Position getFromVocabulary(String source, Vocabulary vocabulary);

    void edit(Position position);

    void delete(Position position);

}
