package service;

import domain.Vocabulary;

import java.util.List;

public interface VocabularyService {

    List<Vocabulary> getAll();

    Vocabulary getById(int id);
}
