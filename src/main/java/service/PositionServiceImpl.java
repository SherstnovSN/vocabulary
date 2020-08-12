package service;

import DAO.PositionDAO;
import domain.Position;
import domain.Translation;
import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.Validator;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class PositionServiceImpl implements PositionService{

    @Autowired
    private Validator validator;

    @Autowired
    private PositionDAO positionDAO;

    @Override
    public boolean addPosition(String source, String[] translations, Vocabulary vocabulary) {
        if (validator.validate(source, vocabulary.getSourceRegex())) {
            Position position = new Position();
            position.setSource(source);
            position.setVocabulary(vocabulary);
            position.setTranslations(createTranslationsSet(position, translations));
            positionDAO.add(position);
            return true;
        }
        return false;
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
    public Translation getTranslationById(int translationId) {
        return positionDAO.getTranslationById(translationId);
    }

    @Override
    public boolean addTranslation(String source, String[] translations) {
        Position position = getFromAllVocabularies(source);
        addToTranslationsSet(position, translations);
        return true;
    }

    @Override
    public boolean deletePosition(String source) {
        Position position = positionDAO.getFromAllVocabularies(source);
        positionDAO.delete(position);
        return true;
    }

    @Override
    public boolean deleteTranslation(int translationId) {
        Translation translation = getTranslationById(translationId);
        translation.getPosition().getTranslations().remove(translation);
        return true;
    }

    public Set<Translation> createTranslationsSet(Position position, String[] translations) {
        Set<Translation> translationsSet = new HashSet<>();
        for (String translationWord : translations) {
            if (validator.validate(translationWord, position.getVocabulary().getTranslationRegex())) {
                Translation translation = new Translation();
                translation.setWord(translationWord);
                translation.setPosition(position);
                translationsSet.add(translation);
            }
        }
        return translationsSet;
    }

    public void addToTranslationsSet(Position position, String[] translations) {
        for (String translationWord : translations) {
            if (validator.validate(translationWord, position.getVocabulary().getTranslationRegex())) {
                Translation translation = new Translation();
                translation.setWord(translationWord);
                translation.setPosition(position);
                position.getTranslations().add(translation);
            }
        }
    }

}
