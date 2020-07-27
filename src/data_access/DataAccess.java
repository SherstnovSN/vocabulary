package data_access;

import java.util.HashMap;

public interface DataAccess {

    void add(String source, String translation);

    HashMap<String, String> getAll();

    String get(String source);

    void delete(String source);

}
