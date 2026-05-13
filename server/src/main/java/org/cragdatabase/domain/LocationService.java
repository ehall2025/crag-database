package org.cragdatabase.domain;

import org.cragdatabase.data.LocationRepository;
import org.cragdatabase.models.Location;
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
}
