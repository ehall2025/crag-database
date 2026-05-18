package org.cragdatabase.data;

import org.cragdatabase.data.mappers.LocationMapper;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationJdbcRepository implements LocationRepository {

    private final JdbcClient jdbcClient;

    private final String LOCATION_SELECT = "SELECT l.id , l.country , l.region , l.description FROM location l";
    private final String CRAG_SELECT = "SELECT c.id , c.name , c.location_id , c.description FROM crag c";
    private final String AREA_SELECT = "SELECT a.id , a.name , a.crag_id , a.super_area_id , a.description FROM area a";
    private final String ROUTE_SELECT = "SELECT r.id , r.name , r.difficulty , r.description FROM route r";//TODO


    public LocationJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Location> findAllLocations() {
        return jdbcClient.sql(LOCATION_SELECT + ";")
                .query(new LocationMapper())
                .list();
    }

    @Override
    public Location findLocationById(int locationId) {
        return jdbcClient.sql(LOCATION_SELECT + ";")
                .query(new LocationMapper())
                .optional().orElse(null);
    }

    @Override
    public List<Crag> findCragsByLocation(int locationId) {
        return List.of();
    }

    @Override
    public Crag findCragById(int cragId) {
        return null;
    }

    @Override
    public List<Area> findAreasByCrag(int cragId) {
        return List.of();
    }

    @Override
    public Crag findAreaById(int areaId) {
        return null;
    }

    @Override
    public List<Route> findRoutesByArea(int areaId) {
        return List.of();
    }

    @Override
    public Crag findRouteById(int routeId) {
        return null;
    }
}
