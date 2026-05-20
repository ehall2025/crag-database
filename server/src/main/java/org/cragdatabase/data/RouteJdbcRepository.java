package org.cragdatabase.data;

import org.cragdatabase.models.Route;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class RouteJdbcRepository implements RouteRepository {
    private final JdbcClient jdbcClient;

    public RouteJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private final String BASE_SELECT = """
            """;

    @Override
    public Route userPostRoute(Route route) {
        return null;
    }

    @Override
    public Route adminPostRoute(Route route) {
        return null;
    }

    @Override
    public Route adminUpdateRoute(Route route) {
        return null;
    }

    @Override
    public boolean adminDeleteRoute(int routeId) {
        return false;
    }
}
