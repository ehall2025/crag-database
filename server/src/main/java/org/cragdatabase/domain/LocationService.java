package org.cragdatabase.domain;

import org.cragdatabase.data.LocationRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAllLocations();
    }

    public Result<Location> findLocationById(int id) {
        Result<Location> result = new Result<>();

        result.setpayload(locationRepository.findLocationById(id));

        if (result.getpayload() == null) {
            result.addErrorMessage("Could not find location", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Crag> findCragById(int cragId) {
        Result<Crag> result = new Result<>();

        result.setpayload(locationRepository.findCragById(cragId));

        if (result.getpayload() == null) {
            result.addErrorMessage("Could not find crag", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Area> findAreaById(int areaId) {
        Result<Area> result = new Result<>();

        result.setpayload(locationRepository.findAreaById(areaId));

        if (result.getpayload() == null) {
            result.addErrorMessage("Could not find area", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Route> findRouteById(int routeId) {
        Result<Route> result = new Result<>();

        result.setpayload(locationRepository.findRouteById(routeId));

        if (result.getpayload() == null) {
            result.addErrorMessage("Could not find route", ResultType.NOT_FOUND);
        }

        return result;
    }
}
