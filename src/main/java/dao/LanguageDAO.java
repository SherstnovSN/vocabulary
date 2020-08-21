package dao;

import domain.Language;

import java.util.List;

public interface LanguageDAO {

    List<Language> getAll();

    Language getById(int id);
}
