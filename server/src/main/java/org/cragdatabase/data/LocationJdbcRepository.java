package org.cragdatabase.data;

import org.cragdatabase.data.mappers.AreaMapper;
import org.cragdatabase.data.mappers.CragMapper;
import org.cragdatabase.data.mappers.LocationMapper;
import org.cragdatabase.data.mappers.RouteMapper;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationJdbcRepository implements LocationRepository {

    private final JdbcClient jdbcClient;

    private final String LOCATION_SELECT = "SELECT l.id , l.country , l.region , l.description FROM location l";
    private final String CRAG_SELECT = "SELECT c.id , c.name , c.location_id , c.description FROM crag c";
    private final String AREA_SELECT = "SELECT a.id , a.name , a.crag_id , a.super_area_id , a.description FROM area a";
    private final String ROUTE_SELECT = "SELECT r.id , r.name , r.area_id , r.description , r.start_position FROM route r";


    public LocationJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private List findChildrenOfParentById(String sql, int parentId, RowMapper mapper) {
        return jdbcClient.sql(sql)
                .param(parentId)
                .query(mapper)
                .list();
    }

    private Optional findById(String sql, int id, RowMapper mapper) {
        return jdbcClient.sql(sql)
                .param(id)
                .query(mapper)
                .optional();
    }

    @Override
    public List<Location> findAllLocations() {
        return jdbcClient.sql(LOCATION_SELECT + ";")
                .query(new LocationMapper())
                .list();
    }

    @Override
    public Location findLocationById(int locationId) {
        String sql = LOCATION_SELECT + " where l.id = ?;";

        Optional result = findById(sql, locationId, new LocationMapper());
        Location location = null;
        if (result.isPresent()) {
            location = (Location) result.get();
            location.setCrags(findCragsByLocation(locationId));
        }

        return location;
    }

    @Override
    public List<Crag> findCragsByLocation(int locationId) {
        String sql = CRAG_SELECT + " where c.location_id = ?;";

        return findChildrenOfParentById(sql, locationId, new CragMapper());
    }

    @Override
    public Crag findCragById(int cragId) {
        String sql = CRAG_SELECT + " where c.id = ?;";

        Optional result = findById(sql, cragId, new CragMapper());
        Crag crag = null;
        if (result.isPresent()) {
            crag = (Crag) result.get();
            crag.setAreas(findAreasByCrag(cragId));
        }

        return crag;
    }

    @Override
    public List<Area> findAreasByCrag(int cragId) {
        String sql = AREA_SELECT + " where a.crag_id = ?;";

        return findChildrenOfParentById(sql, cragId, new AreaMapper());
    }

    @Override
    public List<Area> findAreasBySuperArea(int superAreaId) {
        String sql = AREA_SELECT + " where a.super_area_id = ?;";

        return findChildrenOfParentById(sql, superAreaId, new AreaMapper());
    }

    @Override
    public Area findAreaById(int areaId) {
        String sql = AREA_SELECT + " where a.id = ?;";

        Optional result = findById(sql, areaId, new AreaMapper());
        Area area = null;
        if (result.isPresent()) {
            area = (Area) result.get();
            area.setSubareas(findAreasBySuperArea(areaId));
            if (area.getSubareas().isEmpty()) area.setRoutes(findRoutesByArea(areaId));
        }

        return area;
    }

    @Override
    public List<Route> findRoutesByArea(int areaId) {
        String sql = ROUTE_SELECT + " where r.area_id = ?;";

        return findChildrenOfParentById(sql, areaId, new RouteMapper());
    }

    @Override
    public Route findRouteById(int routeId) {
        String sql = ROUTE_SELECT + " where r.id = ?;";

        Optional result = findById(sql, routeId, new RouteMapper());
        return result.isPresent() ? (Route) result.get() : null;
    }
}
