package DAO;

import java.util.HashMap;

public interface VocabularyDAO {

    void add(String source, String translation);

    HashMap<String, String> getAll();

    String get(String source);

    void delete(String source);

    void setTableName(String tableName);

}
