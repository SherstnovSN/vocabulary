package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import view.View;
import vocabulary.Vocabulary;

public class ControllerImpl implements Controller {
    
    private View view;
    private Vocabulary vocabulary;
    private Scanner scanner = new Scanner(System.in);
    
    public ControllerImpl(View view, Vocabulary vocabulary) {
	this.view = view;
	this.vocabulary = vocabulary;
    }
    
    @Override
    public void start() {
	view.showCommands();
    }
    
    @Override
    public void addNewPosition() {
	String pair = scanner.nextLine();
	String[] pairArray = pair.trim().split(" ");
	String source = pairArray[0];
	String translation = pairArray[1];
	vocabulary.add(source, translation);
    }
    
    @Override
    public void getVocabulary() {
	HashMap<String, String> vocabularyMap = vocabulary.getAll();
	Set<Map.Entry<String, String>> vocabularyEntry = vocabularyMap.entrySet();
	view.showVocabulary(vocabularyEntry);
    }
    
    @Override
    public void getTranslation() {
	String source = scanner.nextLine();
	String translation = vocabulary.get(source);
	view.showTranslation(translation);
    }
    
    @Override
    public void deletePosition() {
	String source = scanner.nextLine();
	vocabulary.delete(source);
    }
    
    @Override
    public boolean exit() {
	return true;
    }

}
