package service;

import DAO.VocabularyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.Validator;

import java.util.Map;
import java.util.Set;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    private Validator validator;

    @Autowired
    private VocabularyDAO vocabularyDAO;

    @Override
    public void setVocabulary(Validator validator, String tableName) {
        this.validator = validator;
        vocabularyDAO.setTableName(tableName);
    }

    @Override
    public boolean add(String source, String translation) {
        if (validator.validate(source, translation)) {
            vocabularyDAO.add(source, translation);
            return true;
        }
        return false;
    }

    @Override
    public Set<Map.Entry<String, String>> getAll() {
        return vocabularyDAO.getAll().entrySet();
    }

    @Override
    public String get(String source) {
        return vocabularyDAO.get(source);
    }

    @Override
    public boolean delete(String source) {
        vocabularyDAO.delete(source);
        return true;
    }

}
