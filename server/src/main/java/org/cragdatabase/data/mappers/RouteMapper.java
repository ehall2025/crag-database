package org.cragdatabase.data.mappers;

import org.cragdatabase.models.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper  implements RowMapper<Route> {

    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Route(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("area_id"),
                rs.getString("description"),
                rs.getString("start_position")
        );
    }
}
