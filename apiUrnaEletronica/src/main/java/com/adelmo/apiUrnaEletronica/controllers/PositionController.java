package com.adelmo.apiUrnaEletronica.controllers;

import com.adelmo.apiUrnaEletronica.entities.Position;
import com.adelmo.apiUrnaEletronica.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping("/create")
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        return new ResponseEntity<>(positionService.createPosition(position), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Position>> findPositionById(@PathVariable Long id) {
        Optional<Position> position = positionService.findPositionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(position);
    }

    @GetMapping("")
    public ResponseEntity<List<Position>> findAllPositions() {
        List<Position> positions = positionService.findAllPositions();
        return ResponseEntity.status(HttpStatus.OK).body(positions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
