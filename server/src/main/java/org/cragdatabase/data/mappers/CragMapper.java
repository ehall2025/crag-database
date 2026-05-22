package org.cragdatabase.data.mappers;

import org.cragdatabase.models.Crag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CragMapper  implements RowMapper<Crag> {

    @Override
    public Crag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Crag(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("location_id"),
                rs.getString("description")
        );
    }
}
