package service;

import dao.VocabularyDAO;
import domain.Position;
import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class VocabularyServiceImpl implements VocabularyService {

    private VocabularyDAO vocabularyDAO;

    @Override
    public List<Vocabulary> getAll() {
        List<Vocabulary> vocabularyList = vocabularyDAO.getAll();
        for (Vocabulary vocabulary : vocabularyList)
            vocabulary.getPositions().sort(Comparator.comparing(Position::getSource));
        return vocabularyList;
    }

    @Override
    public Vocabulary getById(int id) {
        return vocabularyDAO.getById(id);
    }

    @Autowired
    public void setVocabularyDAO(VocabularyDAO vocabularyDAO) {
        this.vocabularyDAO = vocabularyDAO;
    }
}
