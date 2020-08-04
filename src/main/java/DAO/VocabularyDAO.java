package DAO;

import domain.Position;

import java.util.List;

public interface VocabularyDAO {

    void add(Position position);

    List<Position> getAll();

    Position get(String source);

    void delete(Position position);

}
