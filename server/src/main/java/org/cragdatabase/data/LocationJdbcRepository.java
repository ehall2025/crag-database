package org.cragdatabase.data;

import org.cragdatabase.data.mappers.AreaMapper;
import org.cragdatabase.data.mappers.CragMapper;
import org.cragdatabase.data.mappers.LocationMapper;
import org.cragdatabase.data.mappers.RouteMapper;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationJdbcRepository implements LocationRepository {//TODO repeated code throughout whole file

    private final JdbcClient jdbcClient;

    private final String LOCATION_SELECT = "SELECT l.id , l.country , l.region , l.description FROM location l";
    private final String CRAG_SELECT = "SELECT c.id , c.name , c.location_id , c.description FROM crag c";
    private final String AREA_SELECT = "SELECT a.id , a.name , a.crag_id , a.super_area_id , a.description FROM area a";
    private final String ROUTE_SELECT = "SELECT r.id , r.name , r.area_id , r.description , r.start_position FROM route r";


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
        Location location = jdbcClient.sql(LOCATION_SELECT + " where l.id = ?;")
                .param(locationId)
                .query(new LocationMapper())
                .optional().orElse(null);

        if (location != null) {
            location.setCrags(findCragsByLocation(locationId));
        }

        return location;
    }

    @Override
    public List<Crag> findCragsByLocation(int locationId) {
        return jdbcClient.sql(CRAG_SELECT + " where c.location_id = ?;")
                .param(locationId)
                .query(new CragMapper())
                .list();
    }

    @Override
    public Crag findCragById(int cragId) {
        Crag crag = jdbcClient.sql(CRAG_SELECT + " where c.id = ?;")
                .param(cragId)
                .query(new CragMapper())
                .optional().orElse(null);

        if (crag != null) {
            crag.setAreas(findAreasByCrag(cragId));
        }

        return crag;
    }

    @Override
    public List<Area> findAreasByCrag(int cragId) {
        return jdbcClient.sql(AREA_SELECT + " where a.crag_id = ?;")
                .param(cragId)
                .query(new AreaMapper())
                .list();
    }

    @Override
    public List<Area> findAreasBySuperArea(int superAreaId) {
        return jdbcClient.sql(AREA_SELECT + " where a.super_area_id = ?;")
                .param(superAreaId)
                .query(new AreaMapper())
                .list();
    }

    @Override
    public Area findAreaById(int areaId) {
        Area area = jdbcClient.sql(AREA_SELECT + " where a.id = ?;")
                .param(areaId)
                .query(new AreaMapper())
                .optional().orElse(null);

        if (area != null) {
            area.setSubareas(findAreasBySuperArea(areaId));
            if (area.getSubareas().isEmpty()) area.setRoutes(findRoutesByArea(areaId));
        }

        return area;
    }

    @Override
    public List<Route> findRoutesByArea(int areaId) {
        return jdbcClient.sql(ROUTE_SELECT + " where r.area_id = ?;")
                .param(areaId)
                .query(new RouteMapper())
                .list();
    }

    @Override
    public Route findRouteById(int routeId) {
        return jdbcClient.sql(ROUTE_SELECT + " where r.id = ?;")
                .param(routeId)
                .query(new RouteMapper())
                .optional().orElse(null);
    }
}
