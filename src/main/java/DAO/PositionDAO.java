package DAO;

import domain.Position;

import java.util.List;

public interface PositionDAO {

    void add(Position position);

    List<Position> getAll();

    Position getFromAllVocabularies(String source);

    void edit(Position position);

    void delete(Position position);

}
