package org.cragdatabase.controllers;

import org.cragdatabase.domain.RouteSummaryService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.RouteSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route-summaries")
@CrossOrigin
public class RouteSummaryController {

    private final RouteSummaryService routeSummaryService;

    public RouteSummaryController(RouteSummaryService routeSummaryService) {
        this.routeSummaryService = routeSummaryService;
    }

    @GetMapping("/{routeId}")
    public ResponseEntity getByRouteId(@PathVariable int routeId) {
        Result<RouteSummary> result = routeSummaryService.findByRouteId(routeId);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result.getpayload(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody RouteSummary routeSummary) {
        Result<RouteSummary> result = routeSummaryService.add(routeSummary);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getpayload(), HttpStatus.CREATED);
        }

        return switch (result.getResultType()) {
            case INVALID -> new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        };
    }

    @PutMapping("/{routeSummaryId}")
    public ResponseEntity update(@PathVariable int routeSummaryId, @RequestBody RouteSummary routeSummary) {
        if (routeSummaryId != routeSummary.getRouteSummaryId()) {
            return new ResponseEntity<>("Path routeSummaryId must match body routeSummaryId.", HttpStatus.CONFLICT);
        }

        Result<RouteSummary> result = routeSummaryService.update(routeSummary);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return switch (result.getResultType()) {
            case INVALID -> new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        };
    }

    @DeleteMapping("/{routeSummaryId}")
    public ResponseEntity delete(@PathVariable int routeSummaryId) {
        if (!routeSummaryService.deleteById(routeSummaryId)) {
            return new ResponseEntity<>("Route summary not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
