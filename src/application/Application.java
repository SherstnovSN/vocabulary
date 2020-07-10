package application;

import java.util.Scanner;

import controller.Controller;
import controller.ControllerImpl;
import view.View;
import view.ViewImpl;
import vocabulary.Vocabulary;
import vocabulary.VocabularyImpl;

public class Application {

    public static void main(String[] args) {
	
	Vocabulary vocabulary = new VocabularyImpl();
	View view = new ViewImpl();
	Controller controller = new ControllerImpl(view, vocabulary);
	Scanner commandScanner = new Scanner(System.in);
	
	boolean exit = false;
	while(!exit) {
	    	controller.start();
		int command = commandScanner.nextInt();
		switch(command) {
			case 1:
			    controller.addNewPosition();
			    break;
			case 2:
			    controller.getVocabulary();
			    break;
			case 3:
			    controller.getTranslation();
			    break;
			case 4:
			    controller.deletePosition();
			    break;
			case 0:
			    exit = controller.exit();
		}
	}
    }
}
