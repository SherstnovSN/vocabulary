package service;

import java.util.Map;
import java.util.Set;

public interface VocabularyService {

    boolean add(String source, String translation);

    Set<Map.Entry<String, String>> getAll();

    String get(String source);

    boolean delete(String source);

}
