package application;

import controller.Controller;
import controller.ControllerImpl;
import validator.Validator;
import validator.ValidatorImpl;
import view.View;
import view.ViewImpl;
import vocabulary.Vocabulary;
import vocabulary.VocabularyImpl;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Validator engValidator = new ValidatorImpl("^[a-z]{4}$", "^[а-я]+$");
        Vocabulary engVocabulary = new VocabularyImpl(engValidator);
        Controller controller = new ControllerImpl(engVocabulary);
        View view = new ViewImpl(scanner, controller);

        boolean exit = false;
        while (!exit) {
            String command = view.selectCommand();
            switch (command) {
                case "1":
                    view.addNewPositionToVocabulary();
                    break;
                case "2":
                    view.showVocabulary();
                    break;
                case "3":
                    view.showTranslation();
                    break;
                case "4":
                    view.deletePositionFromVocabulary();
                    break;
                case "0":
                    exit = true;
                    view.showExit();
            }
        }
    }
}
