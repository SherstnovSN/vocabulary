package service;

import domain.Position;
import validator.Validator;

import java.util.List;

public interface VocabularyService {

    boolean add(String source, String translation);

    List<Position> getAll();

    Position get(String source);

    boolean delete(String source);

}
