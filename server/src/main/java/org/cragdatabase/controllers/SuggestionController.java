package org.cragdatabase.controllers;

import org.cragdatabase.data.SuggestionRepository;
import org.cragdatabase.models.Suggestion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
@CrossOrigin
public class SuggestionController {

    private final SuggestionRepository suggestionRepository;

    public SuggestionController(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @GetMapping("/{routeId}")
    public ResponseEntity getByRouteId(@PathVariable int routeId) {
        List<Suggestion> suggestions = suggestionRepository.findByRouteId(routeId);

        if (suggestions == null || suggestions.isEmpty()) {
            return new ResponseEntity<>("No suggestions found for route " + routeId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Suggestion suggestion) {
        if (suggestion.getRouteId() <= 0 || suggestion.getUserId() <= 0) {
            return new ResponseEntity<>("routeId and userId are required.", HttpStatus.BAD_REQUEST);
        }

        Suggestion created = suggestionRepository.add(suggestion);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{suggestionId}")
    public ResponseEntity update(@PathVariable int suggestionId, @RequestBody Suggestion suggestion) {
        if (suggestionId != suggestion.getSuggestionId()) {
            return new ResponseEntity<>("Path suggestionId must match body suggestionId.", HttpStatus.CONFLICT);
        }

        boolean updated = suggestionRepository.update(suggestion);

        if (!updated) {
            return new ResponseEntity<>("Suggestion " + suggestionId + " not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{suggestionId}")
    public ResponseEntity delete(@PathVariable int suggestionId) {
        boolean deleted = suggestionRepository.deleteById(suggestionId);

        if (!deleted) {
            return new ResponseEntity<>("Suggestion " + suggestionId + " not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
