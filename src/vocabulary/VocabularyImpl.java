package vocabulary;

import data_access.DataAccess;
import validator.Validator;

import java.util.Map;
import java.util.Set;

public class VocabularyImpl implements Vocabulary {

    private Validator validator;
    private DataAccess dataAccess;

    public VocabularyImpl(Validator validator, DataAccess dataAccess) {
        this.validator = validator;
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean add(String source, String translation) {
        if (validator.validate(source, translation)) {
            dataAccess.add(source, translation);
            return true;
        }
        return false;
    }

    @Override
    public Set<Map.Entry<String, String>> getAll() {
        return dataAccess.getAll().entrySet();
    }

    @Override
    public String get(String source) {
        return dataAccess.get(source);
    }

    @Override
    public boolean delete(String source) {
        dataAccess.delete(source);
        return true;
    }

}
