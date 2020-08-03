package service;

import validator.Validator;

import java.util.Map;
import java.util.Set;

public interface VocabularyService {

    void setVocabulary(Validator validator, String tableName);

    boolean add(String source, String translation);

    Set<Map.Entry<String, String>> getAll();

    String get(String source);

    boolean delete(String source);

}
