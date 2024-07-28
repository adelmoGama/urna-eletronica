package com.adelmo.apiUrnaEletronica.controllers;

import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.services.ElectionSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/election-session")
public class ElectionSessionController {
    @Autowired
    private ElectionSessionService electionSessionService;

    @PostMapping("/create")
    public ResponseEntity<ElectionSession> createElectionSession(@RequestBody ElectionSession electionSession) {
        return new ResponseEntity<>(electionSessionService.createElectionSession(electionSession), HttpStatus.CREATED);
    }

    @PatchMapping("/close/{id}")
    public ResponseEntity<Void> closeElectionSession(@PathVariable Long id) {
        electionSessionService.closeElectionSession(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(ElectionExceptions.class)
    public ResponseEntity<String> handleElectionException(ElectionExceptions exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
