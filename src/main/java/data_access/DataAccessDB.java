package data_access;

import domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;

public class DataAccessDB implements DataAccess {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String tableName;

    private String addCommand = "INSERT %s (source, translation) VALUES ('%s', '%s')";
    private String addIfExistsCommand = "UPDATE %s SET translation = '%s' WHERE source = '%s'";
    private String getAllCommand = "SELECT * FROM %s";
    private String getCommand = "SELECT * FROM %s WHERE source = '%s'";
    private String deleteCommand = "DELETE FROM %s WHERE source = '%s'";

    public DataAccessDB(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void add(String source, String translation) {
        if (get(source).equals("Not found")) jdbcTemplate.update(String.format(addCommand, tableName, source, translation));
        else jdbcTemplate.update(String.format(addIfExistsCommand, tableName, translation, source));
    }

    @Override
    public HashMap<String, String> getAll() {
        HashMap<String, String> vocabulary = new HashMap<>();
        List<Position> positions = jdbcTemplate.query(String.format(getAllCommand, tableName), new VocabularyMapper());
        for (Position position : positions) {
            vocabulary.put(position.getSource(), position.getTranslation());
        }
        return vocabulary;
    }

    @Override
    public String get(String source) {
        Position position;
        try {
            position = (Position) jdbcTemplate.queryForObject(String.format(getCommand, tableName, source), new VocabularyMapper());
        } catch (EmptyResultDataAccessException ex) { return "Not found"; }
        return position.getTranslation();
    }

    @Override
    public void delete(String source) {
        jdbcTemplate.update(String.format(deleteCommand, tableName, source));
    }

}