package org.cragdatabase.controllers;

import org.cragdatabase.domain.LocationService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
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

    @GetMapping("/{locationId}")
    public ResponseEntity findLocation (@PathVariable int locationId) {
        Result<Location> result = locationService.findLocationById(locationId);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

    @GetMapping("/crag/{cragId}")
    public ResponseEntity findCrag (@PathVariable int cragId) {
        Result<Crag> result = locationService.findCragById(cragId);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity findArea (@PathVariable int areaId) {
        Result<Area> result = locationService.findAreaById(areaId);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity findRoute (@PathVariable int routeId) {
        Result<Route> result = locationService.findRouteById(routeId);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }

}
