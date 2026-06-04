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

        if (!result.isSuccess() && result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        } else if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result.getpayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{routeSummaryId}")
    public ResponseEntity update(@PathVariable int routeSummaryId, @RequestBody RouteSummary routeSummary) {
        if (routeSummaryId != routeSummary.getRouteSummaryId()) {
            return new ResponseEntity<>("Path routeSummaryId must match body routeSummaryId.", HttpStatus.CONFLICT);
        }

        Result<RouteSummary> result = routeSummaryService.update(routeSummary);

        if (!result.isSuccess() && result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        } else if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{routeSummaryId}")
    public ResponseEntity delete(@PathVariable int routeSummaryId) {
        Result<RouteSummary> result = routeSummaryService.deleteById(routeSummaryId);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
