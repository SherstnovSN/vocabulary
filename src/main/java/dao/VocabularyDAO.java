package dao;

import domain.Vocabulary;

import java.util.List;

public interface VocabularyDAO {

    List<Vocabulary> getAll();

    Vocabulary getById(int id);
}
