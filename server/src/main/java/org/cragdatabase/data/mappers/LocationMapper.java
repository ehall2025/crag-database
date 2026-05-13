package org.cragdatabase.data.mappers;

import org.cragdatabase.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Location(
                rs.getInt("id"),
                rs.getString("country"),
                rs.getString("region"),
                rs.getString("description")
        );
    }
}
