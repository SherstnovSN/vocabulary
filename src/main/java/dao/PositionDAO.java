package dao;

import domain.Language;
import domain.Position;
import domain.Translation;

public interface PositionDAO {

    void addPosition(Position position);

    Position getPositionById(int positionId);

    Position getPositionBySourceAndLanguage(String source, Language sourceLanguage);

    Translation getTranslationById(int translationId);

    Translation getTranslationByWordAndLanguage(String word, Language translationLanguage);

    void deletePosition(Position position);

    void deleteTranslation(Translation translation);

}
