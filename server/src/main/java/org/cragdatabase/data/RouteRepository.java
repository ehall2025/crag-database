package org.cragdatabase.data;

import org.cragdatabase.models.Location;

import java.util.List;

public interface RouteRepository {

    public List<Location> findByLocation();

}
