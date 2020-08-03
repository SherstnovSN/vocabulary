package DAO;

import domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class VocabularyDAOImpl implements VocabularyDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String addCommand = "INSERT eng_rus (source, translation) VALUES ('%s', '%s')";
    private final String addIfExistsCommand = "UPDATE eng_rus SET translation = '%s' WHERE source = '%s'";
    private final String getAllCommand = "SELECT * FROM eng_rus";
    private final String getCommand = "SELECT * FROM eng_rus WHERE source = '%s'";
    private final String deleteCommand = "DELETE FROM eng_rus WHERE source = '%s'";

    @Override
    public void add(String source, String translation) {
        if (get(source).equals("Not found")) jdbcTemplate.update(String.format(addCommand, source, translation));
        else jdbcTemplate.update(String.format(addIfExistsCommand, translation, source));
    }

    @Override
    public HashMap<String, String> getAll() {
        HashMap<String, String> vocabulary = new HashMap<>();
        List<Position> positions = jdbcTemplate.query(getAllCommand, new VocabularyMapper());
        for (Position position : positions) {
            vocabulary.put(position.getSource(), position.getTranslation());
        }
        return vocabulary;
    }

    @Override
    public String get(String source) {
        Position position;
        try {
            position = jdbcTemplate.queryForObject(String.format(getCommand, source), new VocabularyMapper());
        } catch (EmptyResultDataAccessException ex) { return "Not found"; }
        return position != null ? position.getTranslation() : "Not found";
    }

    @Override
    public void delete(String source) {
        jdbcTemplate.update(String.format(deleteCommand, source));
    }

}
