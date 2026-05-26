package org.cragdatabase.controllers;

import org.cragdatabase.domain.UserProfileService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.ListEntry;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class UserProfileController {

    @Autowired
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity addListEntry (@RequestBody ListEntry listEntry) {//TODO repeated code E
        Result<List<Route>> result = userProfileService.addListEntry(listEntry.getListId(), listEntry.getRouteId());

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity removeListEntry (@RequestBody ListEntry listEntry) {//TODO repeated code E
        Result<List<Route>> result = userProfileService.removeListEntry(listEntry.getListId(), listEntry.getRouteId());

        if (!result.isSuccess()) {
            return new ResponseEntity(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(result.getpayload(), HttpStatus.OK);
    }
}
