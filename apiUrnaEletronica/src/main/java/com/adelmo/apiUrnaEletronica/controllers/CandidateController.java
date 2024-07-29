package com.adelmo.apiUrnaEletronica.controllers;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return new ResponseEntity<>(candidateService.createCandidate(candidate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Candidate>> findCandidateById(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.findCandidateById(id);
        return ResponseEntity.status(HttpStatus.OK).body(candidate);
    }

    @GetMapping("")
    public ResponseEntity<List<Candidate>> findAllCandidates() {
        List<Candidate> candidates = candidateService.findAllCandidates();
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
