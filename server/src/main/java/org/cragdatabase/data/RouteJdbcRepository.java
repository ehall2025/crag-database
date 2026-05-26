package org.cragdatabase.data;

import org.cragdatabase.data.mappers.RouteMapper;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteJdbcRepository implements RouteRepository {

    @Autowired
    private final JdbcClient jdbcClient;

    public RouteJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public boolean userPostRoute(Route route) {
        String sql = """
                insert into Route_Staging (name, area_id, description, start_position) values
                    (:name, :area_id, :description, :start_position);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        return jdbcClient.sql(sql)//TODO repeated code B
                .param("name", route.getName())
                .param("area_id", route.getAreaId())
                .param("description", route.getDescription())
                .param("start_position", route.getStartPosition())
                .update(keyHolder, "id") > 0;
    }

    @Override
    public boolean adminPostRoute(Route route) {
        String sql = """
                insert into Route (name, area_id, description, start_position) values
                    (:name, :area_id, :description, :start_position);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        return jdbcClient.sql(sql)//TODO repeated code B
                .param("name", route.getName())
                .param("area_id", route.getAreaId())
                .param("description", route.getDescription())
                .param("start_position", route.getStartPosition())
                .update(keyHolder, "id") > 0;
    }

    @Override
    public boolean adminUpdateRoute(Route route) {
        String sql = """
                UPDATE Route SET
                name = :name,
                area_id = :area_id,
                description = :description,
                start_position = :start_position
                WHERE id = :id;
                """;

        return jdbcClient.sql(sql)
                .param("name", route.getName())
                .param("area_id", route.getAreaId())
                .param("description", route.getDescription())
                .param("start_position", route.getStartPosition())
                .param("id", route.getId())
                .update() > 0;
    }

    @Override
    public boolean adminDeleteRoute(int routeId) {
        String sql = """
            delete from Route where id = ?;
            """;
        return jdbcClient.sql(sql) //Make new method so service can tell who fails
                .param(routeId)
                .update() > 0;
    }

    @Override
    public List<Route> getStagedRoutes() {
        String sql = """
                SELECT r.id , r.name , r.area_id , r.description , r.start_position FROM route_staging r;
                """;

        return jdbcClient.sql(sql)
                .query(new RouteMapper())
                .list();
    }

    @Override
    public boolean adminDeleteStagedRoute(int stagedRouteId) {
        String sql = """
            delete from Route_Staging where id = ?;
            """;
        return jdbcClient.sql(sql) //Make new method so service can tell who fails
                .param(stagedRouteId)
                .update() > 0;
    }
}
