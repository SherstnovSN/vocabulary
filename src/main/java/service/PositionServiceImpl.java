package service;

import dao.PositionDAO;
import domain.Language;
import domain.Position;
import domain.Translation;
import ecxeption.InvalidWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validator.Validator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    private Validator validator;

    private PositionDAO positionDAO;

    @Override
    public void addPosition(String source, String[] translations, Language sourceLanguage, Language translationLanguage) throws InvalidWordException {
        if (validator.validate(source, sourceLanguage.getRegex())) {
            Position position = positionDAO.getPositionBySourceAndLanguage(source, sourceLanguage);
            if (position == null) {
                position = createAndFillPosition(source, translations, sourceLanguage, translationLanguage);
                positionDAO.addPosition(position);
                List<Position> reversedPositionsList = reversePosition(source, translations, sourceLanguage, translationLanguage);
                addPositionsAfterReverse(reversedPositionsList);
            }
        } else throw new InvalidWordException("Введенное слово не соответствует выбранному языку");
    }

    @Override
    public Position getPositionById(int positionId) {
        return positionDAO.getPositionById(positionId);
    }

    @Override
    public Position getPositionBySourceAndLanguage(String source, Language sourceLanguage) {
        return positionDAO.getPositionBySourceAndLanguage(source, sourceLanguage);
    }

    @Override
    public List<Translation> getTranslations(String source, Language sourceLanguage, Language translationLanguage) {
        List<Translation> translationsMatchedToLanguage = new ArrayList<>();
        Position position = positionDAO.getPositionBySourceAndLanguage(source, sourceLanguage);
        if (position != null) {
            List<Translation> translations = position.getTranslations();
            for (Translation translation : translations) {
                if (translation.getLanguage().getId() == translationLanguage.getId())
                    translationsMatchedToLanguage.add(translation);
            }
        }
        return translationsMatchedToLanguage;
    }

    @Override
    public void addTranslation(int positionId, String[] translations, Language translationLanguage) throws  InvalidWordException {
        Position position = getPositionById(positionId);
        addToTranslationsList(position, translations, translationLanguage);
        List<Position> reversedPositionsList = reversePosition(position.getSource(), translations, position.getLanguage(), translationLanguage);
        addPositionsAfterReverse(reversedPositionsList);
    }

    @Override
    public void deletePosition(int positionId) {
        Position position = positionDAO.getPositionById(positionId);
        for (Translation translation : position.getTranslations()) {
            if (translation.getPositions().size() == 1) positionDAO.deleteTranslation(translation);
        }
        positionDAO.deletePosition(position);
    }

    @Override
    public void deleteTranslation(int positionId, int translationId) {
        Position position = positionDAO.getPositionById(positionId);
        Translation translation = positionDAO.getTranslationById(translationId);
        if (translation.getPositions().size() == 1) positionDAO.deleteTranslation(translation);
        position.getTranslations().remove(translation);
    }

    public List<Translation> createTranslationsList(Position position, String[] translations, Language translationLanguage) throws InvalidWordException {
        List<Translation> translationsList = new ArrayList<>();
        for (String translationWord : translations) {
            if (validator.validate(translationWord, translationLanguage.getRegex())) {
                Translation translation = positionDAO.getTranslationByWordAndLanguage(translationWord, translationLanguage);
                if (translation == null) translation = createAndFillTranslation(position, translationWord, translationLanguage);
                translationsList.add(translation);
            } else throw new InvalidWordException("Введенный перевод не соответствует выбранному языку");
        }
        return translationsList;
    }

    public void addToTranslationsList(Position position, String[] translations, Language translationLanguage) throws InvalidWordException {
        for (String translationWord : translations) {
            if (validator.validate(translationWord, translationLanguage.getRegex())) {
                Translation translation = positionDAO.getTranslationByWordAndLanguage(translationWord, translationLanguage);
                if (translation == null) translation = createAndFillTranslation(position, translationWord, translationLanguage);
                position.getTranslations().add(translation);
            } else throw new InvalidWordException("Введенный перевод не соответствует выбранному языку");
        }
    }

    public Translation createAndFillTranslation(Position position, String translationWord, Language translationLanguage) {
        Translation translation = new Translation();
        translation.setWord(translationWord);
        List<Position> positionsSet = new ArrayList<>();
        positionsSet.add(position);
        translation.setPositions(positionsSet);
        translation.setLanguage(translationLanguage);
        return translation;
    }

    public Position createAndFillPosition(String source, String[] translations, Language sourceLanguage, Language translationLanguage) throws InvalidWordException {
        Position position = new Position();
        position.setSource(source);
        position.setLanguage(sourceLanguage);
        position.setTranslations(createTranslationsList(position, translations, translationLanguage));
        return position;
    }

    public List<Position> reversePosition(String source, String[] translations, Language sourceLanguage, Language translationLanguage) throws InvalidWordException {
        List<Position> positionsList = new ArrayList<>();
        String[] sources = new String[]{source};
        for (String translationWord : translations) {
            if (validator.validate(translationWord, translationLanguage.getRegex())) {
                Position position = positionDAO.getPositionBySourceAndLanguage(translationWord, translationLanguage);
                if (position == null) positionsList = fillReversedPositionsList(translationWord, sources, translationLanguage, sourceLanguage, positionsList);
                else addToTranslationsList(position, sources, sourceLanguage);
            }
        }
        return positionsList;
    }

    public List<Position> fillReversedPositionsList(String source, String[] translations, Language sourceLanguage, Language translationLanguage, List<Position> positionsList) throws InvalidWordException {
        Position position = createAndFillPosition(source, translations, sourceLanguage, translationLanguage);
        positionsList.add(position);
        return positionsList;
    }

    public void addPositionsAfterReverse(List<Position> positionsList) {
        for (Position position : positionsList) {
            Position checkPosition = positionDAO.getPositionBySourceAndLanguage(position.getSource(), position.getLanguage());
            if (checkPosition == null) positionDAO.addPosition(position);
            else checkPosition.getTranslations().add(position.getTranslations().get(0));
        }
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