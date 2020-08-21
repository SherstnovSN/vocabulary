package service;

import dao.LanguageDAO;
import domain.Language;
import domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private LanguageDAO languageDAO;

    @Override
    public List<Language> getAll() {
        List<Language> languageList = languageDAO.getAll();
        for (Language language : languageList)
            language.getPositions().sort(Comparator.comparing(Position::getSource));
        return languageList;
    }

    @Override
    public Language getById(int id) {
        return languageDAO.getById(id);
    }

    @Autowired
    public void setLanguageDAO(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }
}
