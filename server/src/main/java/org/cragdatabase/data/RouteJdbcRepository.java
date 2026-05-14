package org.cragdatabase.data;

import org.cragdatabase.models.Location;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteJdbcRepository implements RouteRepository {
    private final JdbcClient jdbcClient;

    public RouteJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private final String BASE_SELECT = """
            """;

    @Override
    public List<Location> findByLocation() {
        return List.of();
    }
}
