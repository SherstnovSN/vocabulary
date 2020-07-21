package application;

import java.util.Scanner;

import controller.Controller;
import controller.ControllerImpl;
import validator.EngRusValidator;
import validator.Validator;
import view.View;
import view.ViewImpl;
import vocabulary.Vocabulary;
import vocabulary.VocabularyImpl;

public class Application {

    public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in);
	Scanner commandScanner = new Scanner(System.in);
	Validator validator = new EngRusValidator();
	Vocabulary vocabulary = new VocabularyImpl(validator);
	Controller controller = new ControllerImpl(vocabulary);
	View view = new ViewImpl(scanner, controller);
	
	boolean exit = false;
	while(!exit) {
	    	view.showCommands(validator.getExample());
		switch(commandScanner.nextInt()) {
			case 1:
			    view.addNewPositionToVocabulary();
			    break;
			case 2:
			    view.showVocabulary();
			    break;
			case 3:
			    view.showTranslation();
			    break;
			case 4:
			    view.deletePositionFromVocabulary();
			    break;
			case 0:
			    exit = true;
			    view.showExit();
		}
	}
	commandScanner.close();
    }
}
