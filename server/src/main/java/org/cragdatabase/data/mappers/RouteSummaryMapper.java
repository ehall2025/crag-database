package org.cragdatabase.data.mappers;

import org.cragdatabase.models.RouteSummary;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteSummaryMapper implements RowMapper<RouteSummary> {

    @Override
    public RouteSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RouteSummary(
                rs.getInt("id"),
                rs.getInt("route_id"),
                rs.getInt("user_id"),
                rs.getInt("difficulty"),
                rs.getInt("quality"),
                rs.getInt("danger_level")
        );
    }
}
