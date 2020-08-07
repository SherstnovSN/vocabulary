package service;

import domain.Position;

import java.util.List;

public interface PositionService {

    boolean add(String source, String translation);

    List<Position> getAll();

    Position get(String source);

    boolean delete(String source);

}
