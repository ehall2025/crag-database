package org.cragdatabase.data.mappers;

import org.cragdatabase.models.Area;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaMapper  implements RowMapper<Area> {

    @Override
    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Area(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("super_area_id"),
                rs.getInt("crag_id"),
                rs.getString("description")
        );
    }
}
