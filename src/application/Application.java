package application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import vocabulary.EngRusVocabulary;
import vocabulary.Vocabulary;

public class Application {

    public static void main(String[] args) {
	
	Vocabulary vocabulary = new EngRusVocabulary();
	Scanner commandScanner = new Scanner(System.in);
	Scanner pairScanner = new Scanner(System.in);
	
	boolean exit = false;
	
	while(!exit) {
	    System.out.println("1 - добавить слово (пример - book книга), "
			+ "2 - отобразить словарь, "
			+ "3 - найти слово, "
			+ "4 - удалить слово, "
			+ "0 - выход");
		
		int command = commandScanner.nextInt();
		
		switch(command) {
			case 1:
			    String pair = pairScanner.nextLine();
			    String[] pairArray = pair.trim().split(" ");
			    String source = pairArray[0];
			    String translation = pairArray[1];
			    vocabulary.add(source, translation);
			    break;
			case 2:
			    HashMap<String, String> vocabularyMap = vocabulary.getAll();
			    Set<Map.Entry<String, String>> vocabularyEntry = vocabularyMap.entrySet();
			    for(Map.Entry<String, String> me : vocabularyEntry) 
				System.out.println(me.getKey() + " " + me.getValue());
			    break;
			case 3:
			    source = pairScanner.nextLine();
			    translation = vocabulary.get(source);
			    System.out.println(translation);
			    break;
			case 4:
			    source = pairScanner.nextLine();
			    vocabulary.delete(source);
			    break;
			case 0:
			    System.out.println("Выход из программы");
			    exit = true;
			    break;
			default:
			    System.out.println("Неверная команда");
		}
	}
	commandScanner.close();
	pairScanner.close();
    }

}
