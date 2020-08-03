package DAO;

import domain.Position;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VocabularyMapper implements RowMapper<Position>{

    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        Position position = new Position();
        position.setId(rs.getInt("id"));
        position.setSource(rs.getString("source"));
        position.setTranslation(rs.getString("translation"));
        return position;
    }

}
