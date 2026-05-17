package org.cragdatabase.controllers;

import org.cragdatabase.domain.LocationService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.Location;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> findAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity findLocation (@PathVariable int id) {
        Result<Location> result = locationService.findLocationById(id);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

}
