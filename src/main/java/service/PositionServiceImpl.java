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

    @Override
    public boolean add(String source, String translation, Vocabulary vocabulary) {
        if (validator.validate(source, vocabulary.getSourceRegex(), translation, vocabulary.getTranslationRegex())) {
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
        return positionDAO.getAll();
    }

    @Override
    public Position get(String source) {
        Position position = positionDAO.get(source);
        if (position == null) {
            position = new Position();
            position.setSource("отсутствует");
            position.setTranslation("отсутствует");
        }
        return position;
    }

    @Override
    public boolean delete(String source) {
        Position position = positionDAO.get(source);
        positionDAO.delete(position);
        return true;
    }

}
