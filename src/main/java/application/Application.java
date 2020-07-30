package application;

import config.VocabularyConfig;
import controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import view.View;

import java.util.Scanner;

@PropertySource("classpath:database.properties")
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(VocabularyConfig.class);
        View view = (View) context.getBean("view");
        Controller controller;
        Controller engController = (Controller) context.getBean("engController");
        Controller numController = (Controller) context.getBean("numController");
        Scanner scanner = new Scanner(System.in);

        view.showVocabularies();

        switch (scanner.nextLine()) {
            case "1":
                controller = engController; break;
            case "2":
                controller = numController; break;
            default:
                controller = engController;
        }

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