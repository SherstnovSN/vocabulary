package config;

import controller.Controller;
import controller.ControllerImpl;
import data_access.DataAccess;
import data_access.DataAccessDB;
import data_access.DataAccessFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import validator.Validator;
import validator.ValidatorImpl;
import vocabulary.Vocabulary;
import vocabulary.VocabularyImpl;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@ComponentScan(basePackages = "view")
@PropertySource("classpath:database.properties")
public class VocabularyConfig {

    @Autowired
    private Environment environment;

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

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
