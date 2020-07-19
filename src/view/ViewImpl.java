package view;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import controller.Controller;

public class ViewImpl implements View {
    
    private Scanner scanner;
    private Controller controller;
    
    public ViewImpl(Scanner scanner, Controller controller) {
	this.scanner = scanner;
	this.controller = controller;
    }
    
    @Override
    public void showCommands() {
	System.out.println("1 - добавить слово (пример - book книга), "
		+ "2 - отобразить словарь, "
		+ "3 - найти слово, "
		+ "4 - удалить слово, "
		+ "0 - выход");
    }
    
    @Override
    public void addNewPositionToVocabulary() {
	String newPosition = scanner.nextLine();
	String[] newPositionArray = newPosition.trim().split(" ");
	String source = newPositionArray[0];
	String translation = newPositionArray[1];
	controller.addNewPosition(source, translation);
    }
    
    @Override
    public void showVocabulary() {
	Set<Map.Entry<String, String>> vocabularyEntry = controller.getVocabulary();
	for(Map.Entry<String, String> me : vocabularyEntry) 
	    System.out.println(me.getKey() + " " + me.getValue());
    }
    
    @Override
    public void showTranslation() {
	String source = scanner.nextLine();
	String translation = controller.getTranslation(source);
	System.out.println(translation);
    }
    
    @Override
    public void deletePositionFromVocabulary() {
	String source = scanner.nextLine();
	controller.deletePosition(source);
    }
    
    @Override
    public void showExit() {
	System.out.println("Выход из программы");
    }

}
