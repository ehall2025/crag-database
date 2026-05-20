package org.cragdatabase.controllers;

import org.cragdatabase.domain.RouteService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post_route")
@CrossOrigin
public class RouteController {

    @Autowired
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity userPost (@RequestBody Route route) {
        Result<Route> result = routeService.userPost(route);

        if (!result.isSuccess() && result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        } else if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity adminPost (@RequestBody Route route) {
        Result<Route> result = routeService.adminPost(route);

        if (!result.isSuccess() && result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        } else if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/admin")
    public ResponseEntity adminPut (@RequestBody Route route) {
        Result<Route> result = routeService.adminPut(route);

        if (!result.isSuccess() && result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        } else if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{routeId}")
    public ResponseEntity adminDelete (@PathVariable int routeId) {
        Result<Route> result = routeService.adminDelete(routeId);

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
