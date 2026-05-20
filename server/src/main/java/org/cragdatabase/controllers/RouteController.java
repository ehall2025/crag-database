package org.cragdatabase.controllers;

import org.cragdatabase.models.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post_route")
@CrossOrigin
public class RouteController {

    @PostMapping
    public ResponseEntity userPost (@RequestBody Route route) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/admin")
    public ResponseEntity adminPost (@RequestBody Route route) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/admin")
    public ResponseEntity adminPut (@RequestBody Route route) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/admin/{routeId}")
    public ResponseEntity adminDelete (@RequestBody int routeId) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
