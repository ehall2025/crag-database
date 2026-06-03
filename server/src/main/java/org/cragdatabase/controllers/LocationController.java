package org.cragdatabase.controllers;

import org.cragdatabase.domain.LocationService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.GeographicLayer;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.enums.Layers;
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

    @GetMapping("/{locationId}")
    public ResponseEntity findLocation (@PathVariable int locationId) {
        Result<GeographicLayer> result = locationService.findById(locationId, Layers.LOCATION);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

    @GetMapping("/crag/{cragId}")
    public ResponseEntity findCrag (@PathVariable int cragId) {
        Result<GeographicLayer> result = locationService.findById(cragId, Layers.CRAG);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity findArea (@PathVariable int areaId) {
        Result<GeographicLayer> result = locationService.findById(areaId, Layers.AREA);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity findRoute (@PathVariable int routeId) {
        Result<GeographicLayer> result = locationService.findById(routeId, Layers.ROUTE);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }
}
