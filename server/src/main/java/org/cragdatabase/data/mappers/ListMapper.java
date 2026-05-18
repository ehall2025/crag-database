package org.cragdatabase.data.mappers;

import org.cragdatabase.models.RouteList;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListMapper implements RowMapper<RouteList> {

    @Override
    public RouteList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
