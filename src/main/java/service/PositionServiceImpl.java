package service;

import dao.PositionDAO;
import domain.Position;
import domain.Translation;
import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.Validator;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PositionServiceImpl implements PositionService{

    private Validator validator;

    private PositionDAO positionDAO;

    @Override
    public void addPosition(String source, String[] translations, Vocabulary vocabulary) {
        if (validator.validate(source, vocabulary.getSourceRegex())) {
            positionDAO.add(createAndFillPosition(source, translations, vocabulary));
        }
    }

    @Override
    public Position getPositionById(int positionId) {
        return positionDAO.getPositionById(positionId);
    }

    @Override
    public List<Position> getFromAllVocabularies(String search, String source) {
        if (search.equals("source")) return positionDAO.getFromAllVocabulariesBySource(source);
        else return positionDAO.getFromAllVocabulariesByTranslation(source);
    }

    @Override
    public List<Position> getFromVocabulary(String search, String source, Vocabulary vocabulary) {
        if (search.equals("source")) return positionDAO.getFromVocabularyBySource(source, vocabulary);
        else return positionDAO.getFromVocabularyByTranslation(source, vocabulary);
    }

    @Override
    public void addTranslation(int positionId, String[] translations) {
        Position position = getPositionById(positionId);
        addToTranslationsSet(position, translations);
    }

    @Override
    public void deletePosition(int positionId) {
        Position position = positionDAO.getPositionById(positionId);
        positionDAO.delete(position);
    }

    @Override
    public void deleteTranslation(int translationId) {
        Translation translation = positionDAO.getTranslationById(translationId);
        translation.getPosition().getTranslations().remove(translation);
    }

    public Set<Translation> createTranslationsSet(Position position, String[] translations) {
        Set<Translation> translationsSet = new HashSet<>();
        for (String translationWord : translations) {
            if (validator.validate(translationWord, position.getVocabulary().getTranslationRegex()))
                translationsSet.add(createAndFillTranslation(position, translationWord));
        }
        return translationsSet;
    }

    public void addToTranslationsSet(Position position, String[] translations) {
        for (String translationWord : translations) {
            if (validator.validate(translationWord, position.getVocabulary().getTranslationRegex()))
                position.getTranslations().add(createAndFillTranslation(position, translationWord));
        }
    }

    public Translation createAndFillTranslation(Position position, String translationWord) {
        Translation translation = new Translation();
        translation.setWord(translationWord);
        translation.setPosition(position);
        return translation;
    }

    public Position createAndFillPosition(String source, String[] translations, Vocabulary vocabulary) {
        Position position = new Position();
        position.setSource(source);
        position.setVocabulary(vocabulary);
        position.setTranslations(createTranslationsSet(position, translations));
        return position;
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Autowired
    public void setPositionDAO(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }
}