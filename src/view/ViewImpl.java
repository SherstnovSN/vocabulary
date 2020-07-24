package view;

import java.util.Map;
import java.util.Set;

public class ViewImpl implements View {

    @Override
    public void showCommands() {
        System.out.println("1 - добавить слово, "
                + "2 - отобразить словарь, "
                + "3 - найти слово, "
                + "4 - удалить слово, "
                + "0 - выход");
    }

    @Override
    public void showVocabulary(Set<Map.Entry<String, String>> vocabularyEntry) {
        for (Map.Entry<String, String> me : vocabularyEntry)
            System.out.println(me.getKey() + " " + me.getValue());
    }

    @Override
    public void showTranslation(String translation) {
        System.out.println(translation);
    }

    @Override
    public void showExit() {
        System.out.println("Выход из программы");
    }
}
