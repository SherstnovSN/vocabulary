package application;

import controller.Controller;
import controller.ControllerImpl;
import data_access.DataAccess;
import data_access.DataAccessDB;
import data_access.DataAccessFile;
import validator.Validator;
import validator.ValidatorImpl;
import view.View;
import view.ViewImpl;
import vocabulary.Vocabulary;
import vocabulary.VocabularyImpl;

import java.io.File;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException ex) {
            System.out.println(ex.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        View view = new ViewImpl();
        Vocabulary vocabulary;

        String engTableName = "eng_rus";
        DataAccess engDataAccess = new DataAccessDB(engTableName);
        Validator engValidator = new ValidatorImpl("^[a-z]{4}$", "^[а-я]+$");
        Vocabulary engVocabulary = new VocabularyImpl(engValidator, engDataAccess);

        File numFile = new File("C://vocabularies/num_vocabulary.txt");
        DataAccess numDataAccess = new DataAccessFile(numFile);
        Validator numValidator = new ValidatorImpl("^[0-9]{5}$", "^[а-я]+$");
        Vocabulary numVocabulary = new VocabularyImpl(numValidator, numDataAccess);

        view.showVocabularies();

        switch (scanner.nextLine()) {
            case "1":
                vocabulary = engVocabulary; break;
            case "2":
                vocabulary = numVocabulary; break;
            default:
                vocabulary = engVocabulary;
        }

        Controller controller = new ControllerImpl(vocabulary);

        boolean exit = false;
        while (!exit) {
            view.showCommands();
            switch (scanner.nextLine()) {
                case "1":
                    String newPosition = scanner.nextLine();
                    String[] newPositionArray = newPosition.trim().split(" ");
                    controller.addNewPosition(newPositionArray[0], newPositionArray[1]);
                    break;
                case "2":
                    view.showVocabulary(controller.getVocabulary());
                    break;
                case "3":
                    String source = scanner.nextLine();
                    view.showTranslation(controller.getTranslation(source));
                    break;
                case "4":
                    controller.deletePosition(scanner.nextLine());
                    break;
                case "0":
                    exit = true;
                    view.showExit();
            }
        }
    }
}