package data_access;

import java.io.*;
import java.util.*;

public class DataAccessFile implements DataAccess {

    private File file;

    public DataAccessFile(File file) {
        this.file = file;
        try {
            if(!file.exists()) file.createNewFile();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(String source, String translation) {
        try(FileOutputStream fos = new FileOutputStream(file, true);
            PrintStream writer = new PrintStream(fos)) {
            writer.println(source + " " + translation);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public HashMap<String, String> getAll() {

        HashMap<String, String> vocabulary = new HashMap<>();

        try(FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis)) {
            while(scanner.hasNextLine()) {
                String str[] = scanner.nextLine().trim().split(" ");
                vocabulary.put(str[0], str[1]);
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return vocabulary;
    }

    @Override
    public String get(String source) {

        try(FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis)) {
            while(scanner.hasNextLine()) {
                String str[] = scanner.nextLine().trim().split(" ");
                if(str[0].equals(source)) return str[1];
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "Слово отсутствует";
    }

    @Override
    public void delete(String source) {

        HashMap<String, String> vocabulary = getAll();

        if(vocabulary.containsKey(source)) {
            vocabulary.remove(source);
            file.delete();

            Set<Map.Entry<String, String>> vocabularyEntry = vocabulary.entrySet();

            try(FileOutputStream fos = new FileOutputStream(file, true);
                PrintStream writer = new PrintStream(fos)) {
                for(Map.Entry<String, String> me : vocabularyEntry) writer.println(me.getKey() + " " + me.getValue());
            } catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}