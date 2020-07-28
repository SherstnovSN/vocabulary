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
    private String getCommand = "SELECT translation FROM %s WHERE source = '%s'";
    private String deleteCommand = "DELETE FROM %s WHERE source = '%s'";

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

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            if (get(source).equals("Not found"))
                statement.executeUpdate(String.format(addCommand, tableName, source, translation));
            else statement.executeUpdate(String.format(addIfExistsCommand, tableName, translation, source));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public HashMap<String, String> getAll() {

        HashMap<String, String> vocabulary = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(getAllCommand, tableName));
            while (resultSet.next())
                vocabulary.put(resultSet.getString("source"), resultSet.getString("translation"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return vocabulary;
    }

    @Override
    public String get(String source) {

        String translation = "Not found";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(getCommand, tableName, source));
            if (resultSet.next()) translation = resultSet.getString("translation");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return translation;
    }

    @Override
    public void delete(String source) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(deleteCommand, tableName, source));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}