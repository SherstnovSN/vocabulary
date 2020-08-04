package service;

import DAO.VocabularyDAO;
import domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import validator.Validator;

import java.util.List;

@Service
@Transactional
public class VocabularyServiceImpl implements VocabularyService {

    @Autowired
    private Validator validator;

    @Autowired
    private VocabularyDAO vocabularyDAO;

    @Override
    public boolean add(String source, String translation) {
        if (validator.validate(source, translation)) {
            Position position = new Position();
            position.setSource(source);
            position.setTranslation(translation);
            vocabularyDAO.add(position);
            return true;
        }
        return false;
    }

    @Override
    public List<Position> getAll() {
        return vocabularyDAO.getAll();
    }

    @Override
    public Position get(String source) {
        return vocabularyDAO.get(source);
    }

    @Override
    public boolean delete(String source) {
        Position position = get(source);
        vocabularyDAO.delete(position);
        return true;
    }

}
