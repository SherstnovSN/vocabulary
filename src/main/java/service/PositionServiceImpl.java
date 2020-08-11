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
    public Position getFromAllVocabularies(String source) {
        return positionDAO.getFromAllVocabularies(source);
    }

    @Override
    public Position getFromVocabulary(String source, Vocabulary vocabulary) {
        return positionDAO.getFromVocabulary(source, vocabulary);
    }

    @Override
    public boolean edit(String source, String translation) {
        Position position = positionDAO.getFromAllVocabularies(source);
        position.setTranslation(translation);
        positionDAO.edit(position);
        return true;
    }

    @Override
    public boolean delete(String source) {
        Position position = positionDAO.getFromAllVocabularies(source);
        positionDAO.delete(position);
        return true;
    }

}
