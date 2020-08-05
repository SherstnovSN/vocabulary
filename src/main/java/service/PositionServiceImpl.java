package service;

import DAO.PositionDAO;
import domain.Position;
import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.Validator;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService{

    @Autowired
    private Validator validator;

    @Autowired
    private PositionDAO positionDAO;

    private Vocabulary vocabulary;

    @Override
    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    @Override
    public boolean add(String source, String translation) {
        if (validator.validate(source, translation, vocabulary.getSourceRegex(), vocabulary.getTranslationRegex())) {
            Position position = new Position();
            position.setSource(source);
            position.setTranslation(translation);
            position.setVocabulary(vocabulary);
            positionDAO.add(position);
            return true;
        }
        return false;
    }

    @Override
    public List<Position> getAll() {
        return positionDAO.getAll(vocabulary.getId());
    }

    @Override
    public Position get(String source) {
        if (validator.validate(source, vocabulary.getSourceRegex())) return positionDAO.get(source);
        return null;
    }

    @Override
    public boolean delete(String source) {
        Position position = get(source);
        if (position != null) {
            positionDAO.delete(position);
            return true;
        }
        return false;
    }

}
