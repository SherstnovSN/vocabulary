package service;

import DAO.PositionDAO;
import domain.Position;
import domain.VocabularyHolder;
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

    @Autowired
    private VocabularyHolder vocabularyHolder;

    @Override
    public boolean add(String source, String translation) {
        String sourceRegex = vocabularyHolder.getVocabulary().getSourceRegex();
        String translationRegex = vocabularyHolder.getVocabulary().getTranslationRegex();
        if (validator.validate(source, sourceRegex, translation, translationRegex)) {
            Position position = new Position();
            position.setSource(source);
            position.setTranslation(translation);
            position.setVocabulary(vocabularyHolder.getVocabulary());
            positionDAO.add(position);
            return true;
        }
        return false;
    }

    @Override
    public List<Position> getAll() {
        int vocabularyId = vocabularyHolder.getVocabulary().getId();
        return positionDAO.getAll(vocabularyId);
    }

    @Override
    public Position get(String source) {
        String sourceRegex = vocabularyHolder.getVocabulary().getSourceRegex();
        if (validator.validate(source, sourceRegex)) return positionDAO.get(source);
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
