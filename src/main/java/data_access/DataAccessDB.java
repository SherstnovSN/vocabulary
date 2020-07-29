package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DataAccessDB implements DataAccess {

    private String tableName;

    private String url;
    private String username;
    private String password;

    private String addCommand = "INSERT %s (source, translation) VALUES ('%s', '%s')";
    private String addIfExistsCommand = "UPDATE %s SET translation = '%s' WHERE source = '%s'";
    private String getAllCommand = "SELECT * FROM %s";
    private String getCommand = "SELECT source, translation FROM %s WHERE source = '%s'";
    private String deleteCommand = "DELETE FROM %s WHERE source = '%s'";

    public DataAccessDB(String tableName) {
        this.tableName = tableName;

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/java/resources/database.properties")) {
            properties.load(fis);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    @Override
    public void add(String source, String translation) {
        if (get(source).equals("Not found")) execute(String.format(addCommand, tableName, source, translation));
        else execute(String.format(addIfExistsCommand, tableName, translation, source));
    }

    @Override
    public HashMap<String, String> getAll() {
        return execute(String.format(getAllCommand, tableName));
    }

    @Override
    public String get(String source) {
        HashMap<String, String> vocabulary = execute(String.format(getCommand, tableName, source));
        if (!(vocabulary.size() == 0)) return vocabulary.get(source);
        return "Not found";
    }

    @Override
    public void delete(String source) {
        execute(String.format(deleteCommand, tableName, source));
    }

    private HashMap<String, String> execute(String query) {
        ResultSet resultSet;
        HashMap<String, String> vocabulary = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            boolean checkQueryResult = statement.execute(query);
            if (checkQueryResult) {
                resultSet = statement.getResultSet();
                while (resultSet.next())
                    vocabulary.put(resultSet.getString("source"), resultSet.getString("translation"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vocabulary;
    }

}