package controller;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import vocabulary.Vocabulary;

public class ControllerImpl implements Controller {
    
    private Vocabulary vocabulary;
    private Scanner scanner = new Scanner(System.in);
    
    public ControllerImpl(Vocabulary vocabulary) {
	this.vocabulary = vocabulary;
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
    public Set<Map.Entry<String, String>> getVocabulary() {
	return vocabulary.getAll().entrySet();
    }
    
    @Override
    public String getTranslation() {
	String source = scanner.nextLine();
	return vocabulary.get(source);
    }
    
    @Override
    public void deletePosition() {
	String source = scanner.nextLine();
	vocabulary.delete(source);
    }
}
