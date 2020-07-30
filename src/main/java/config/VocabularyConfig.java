package config;

import controller.Controller;
import controller.ControllerImpl;
import data_access.DataAccess;
import data_access.DataAccessDB;
import data_access.DataAccessFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import validator.Validator;
import validator.ValidatorImpl;
import vocabulary.Vocabulary;
import vocabulary.VocabularyImpl;

import java.io.File;

@Configuration
@ComponentScan(basePackages = "view")
public class VocabularyConfig {

    @Bean
    public Validator engValidator() {
        return new ValidatorImpl("^[a-z]{4}$", "^[а-я]+$");
    }

    @Bean
    public Validator numValidator() {
        return new ValidatorImpl("^[0-9]{5}$", "^[а-я]+$");
    }

    @Bean
    public DataAccess engDataAccess() {
        return new DataAccessDB("eng_rus");
    }

    @Bean
    public DataAccess numDataAccess() {
        File numFile = new File("C://vocabularies/num_vocabulary.txt");
        return new DataAccessFile(numFile);
    }

    @Bean
    public Vocabulary engVocabulary() {
        VocabularyImpl vocabulary = new VocabularyImpl();
        vocabulary.setValidator(engValidator());
        vocabulary.setDataAccess(engDataAccess());
        return vocabulary;
    }

    @Bean
    public Vocabulary numVocabulary() {
        VocabularyImpl vocabulary = new VocabularyImpl();
        vocabulary.setValidator(numValidator());
        vocabulary.setDataAccess(numDataAccess());
        return vocabulary;
    }

    @Bean
    public Controller engController() {
        ControllerImpl controller = new ControllerImpl();
        controller.setVocabulary(engVocabulary());
        return controller;
    }

    @Bean
    public Controller numController() {
        ControllerImpl controller = new ControllerImpl();
        controller.setVocabulary(numVocabulary());
        return controller;
    }
}
