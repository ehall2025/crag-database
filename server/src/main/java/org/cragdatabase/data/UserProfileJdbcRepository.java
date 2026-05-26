package org.cragdatabase.data;

import org.cragdatabase.data.mappers.RouteMapper;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileJdbcRepository implements UserProfileRepository {

    @Autowired
    private final JdbcClient jdbcClient;

    private final String SELECT_ROUTES = """
                                    select r.id , r.name , r.area_id , r.description , r.start_position
                                    from route r
                                    join list_route lr on r.id = lr.route_id
                                    join list l on l.id = lr.list_id
                                    where l.id = :list_id;
                                    """;

    public UserProfileJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Route> addListEntry(int listId, int routeId) {
        String insertSql = """
                insert into list_route (list_id , route_id) values
                    (:list_id , :route_id);
                """;

        boolean success = jdbcClient.sql(insertSql)
                .param("list_id", listId)
                .param("route_id", routeId)
                .update() > 0;

        if (!success) return List.of();

        return jdbcClient.sql(SELECT_ROUTES)//TODO repeated code A
                .param("list_id", listId)
                .param("route_id", routeId)
                .query(new RouteMapper())
                .list();
    }

    @Override
    public List<Route> removeListEntry(int listId, int routeId) {
        String deleteSql = """
                delete from list_route where list_id = :list_id and route_id = :route_id;
                """;

        boolean success = jdbcClient.sql(deleteSql)
                .param("list_id", listId)
                .param("route_id", routeId)
                .update() > 0;

        if (!success) return List.of();

        //return updated list of routes
        return jdbcClient.sql(SELECT_ROUTES)//TODO repeated code A
                .param("list_id", listId)
                .param("route_id", routeId)
                .query(new RouteMapper())
                .list();
    }

    @Override
    public List<Route> findRouteList(int listId) {
        return jdbcClient.sql(SELECT_ROUTES)
                .param("list_id", listId)
                .query(new RouteMapper())
                .list();
    }
}
