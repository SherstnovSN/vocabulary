package DAO;

import domain.Position;

import java.util.List;

public interface PositionDAO {

    void add(Position position);

    List<Position> getAll(int vocabularyId);

    Position get(String source);

    void delete(Position position);

}
