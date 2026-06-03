package org.cragdatabase.data.mappers;

import org.cragdatabase.models.Suggestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuggestionMapper implements RowMapper<Suggestion> {

    @Override
    public Suggestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Suggestion(
                rs.getInt("suggestion_id"),
                rs.getInt("route_id"),
                rs.getInt("user_id"),
                rs.getInt("difficulty_rating"),
                rs.getInt("quality_rating"),
                rs.getInt("danger_rating")
        );
    }
}
