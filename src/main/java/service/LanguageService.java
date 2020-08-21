package service;

import domain.Language;

import java.util.List;

public interface LanguageService {

    List<Language> getAll();

    Language getById(int id);
}
