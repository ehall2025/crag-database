package org.cragdatabase.data;

import org.cragdatabase.data.mappers.LocationMapper;
import org.cragdatabase.models.Location;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationJdbcRepository implements LocationRepository {

    private final JdbcClient jdbcClient;

    private final String BASE_SELECT = "SELECT l.country , l.region , l.description FROM location l";

    public LocationJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Location> findAllLocations() {
        return jdbcClient.sql(BASE_SELECT).query(new LocationMapper()).list();
    }
}
