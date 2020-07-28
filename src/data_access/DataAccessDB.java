package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

public class DataAccessDB implements DataAccess {

    private String tableName;
    private String url;
    private String username;
    private String password;

    public DataAccessDB(String tableName) {
        this.tableName = tableName;

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("src/resources/database.properties")) {
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

        String sqlCommand = "INSERT " + tableName + " (source, translation) VALUES ('" + source + "', '" + translation + "')";
        String translationExistsSqlCommand = "UPDATE " + tableName + " SET translation = '" + translation + "' WHERE source = '" + source + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                if (get(source).equals("Not found")) statement.executeUpdate(sqlCommand);
                else statement.executeUpdate(translationExistsSqlCommand);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public HashMap<String, String> getAll() {

        HashMap<String, String> vocabulary = new HashMap<>();

        String sqlCommand = "SELECT * FROM " + tableName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlCommand);
                while(resultSet.next()) {
                    vocabulary.put(resultSet.getString("source"), resultSet.getString("translation"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return vocabulary;
    }

    @Override
    public String get(String source) {

        String translation = "Not found";
        String sqlCommand = "SELECT translation FROM " + tableName + " WHERE source = '" + source + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlCommand);
                if(resultSet.next()) translation = resultSet.getString("translation");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return translation;
    }

    @Override
    public void delete(String source) {

        String sqlCommand = "DELETE FROM " + tableName + " WHERE source = '" + source + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sqlCommand);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}