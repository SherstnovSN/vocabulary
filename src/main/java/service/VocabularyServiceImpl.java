package service;

import dao.VocabularyDAO;
import domain.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VocabularyServiceImpl implements VocabularyService {

    private VocabularyDAO vocabularyDAO;

    @Override
    public List<Vocabulary> getAll() {
        return vocabularyDAO.getAll();
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
