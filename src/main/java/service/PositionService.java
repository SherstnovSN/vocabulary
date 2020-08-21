package service;

import domain.Language;
import domain.Position;
import domain.Translation;

import java.util.List;

public interface PositionService {

    void addPosition(String source, String[] translations, Language sourceLanguage, Language translationLanguage);

    Position getPositionById(int positionId);

    Position getPositionBySourceAndLanguage(String source, Language sourceLanguage);

    List<Translation> getTranslations(String source, Language sourceLanguage, Language translationLanguage);

    void addTranslation(int positionId, String[] translations, Language translationLanguage);

    void deletePosition(int positionId);

    void deleteTranslation(int positionId, int translationId);

}
