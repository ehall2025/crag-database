package org.cragdatabase.domain;

import org.cragdatabase.data.LocationRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
import org.cragdatabase.models.enums.GeographicLayers;
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

    public Result<Object> findById(int id, GeographicLayers layer) {
        Result<Object> result = new Result<>();

        switch (layer) {
            case LOCATION -> result.setpayload(locationRepository.findLocationById(id));
            case CRAG -> result.setpayload(locationRepository.findCragById(id));
            case AREA -> result.setpayload(locationRepository.findAreaById(id));
            case ROUTE -> result.setpayload(locationRepository.findRouteById(id));
        }

        if (result.getpayload() == null) {
            result.addErrorMessage("Could not find " + layer, ResultType.NOT_FOUND);
        }

        return result;
    }
}
