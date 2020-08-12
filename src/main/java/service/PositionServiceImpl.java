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
import java.util.Set;

@Service
@Transactional
public class PositionServiceImpl implements PositionService{

    private Validator validator;

    private PositionDAO positionDAO;

    @Override
    public void addPosition(String source, String[] translations, Vocabulary vocabulary) {
        if (validator.validate(source, vocabulary.getSourceRegex())) {
            Position position = new Position();
            position.setSource(source);
            position.setVocabulary(vocabulary);
            position.setTranslations(createTranslationsSet(position, translations));
            positionDAO.add(position);
        }
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
    public void addTranslation(String source, String[] translations) {
        Position position = getFromAllVocabularies(source);
        addToTranslationsSet(position, translations);
    }

    @Override
    public void deletePosition(String source) {
        Position position = positionDAO.getFromAllVocabularies(source);
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

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Autowired
    public void setPositionDAO(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }
}