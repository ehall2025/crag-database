package org.cragdatabase.data;

import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;

import java.util.List;

public interface LocationRepository {

    List<Location> findAllLocations();

    Location findLocationById(int locationId);

    List<Crag> findCragsByLocation(int locationId);

    Crag findCragById(int cragId);

    List<Area> findAreasByCrag(int cragId);

    Crag findAreaById(int areaId);

    List<Route> findRoutesByArea(int areaId);

    Crag findRouteById(int routeId);
}
