package com.adelmo.apiUrnaEletronica.controllers;

import com.adelmo.apiUrnaEletronica.entities.Voter;
import com.adelmo.apiUrnaEletronica.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/voter")
public class VoterController {
    @Autowired
    private VoterService voterService;

    @PostMapping("/create")
    public ResponseEntity<Voter> createVoter(@RequestBody Voter voter) {
        return new ResponseEntity<>(voterService.createVoter(voter), HttpStatus.CREATED);
    }

    @PostMapping("/vote/{voterId}/{candidateId}/{electionSessionId}")
    public ResponseEntity<String> vote(@PathVariable Long voterId, @PathVariable Long candidateId, @PathVariable Long electionSessionId) {
        voterService.vote(voterId, candidateId, electionSessionId);

        return ResponseEntity.status(HttpStatus.OK).body("Vote completed successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Voter>> findVoterById(@PathVariable Long id) {
        Optional<Voter> voter = voterService.findVoterById(id);
        return ResponseEntity.status(HttpStatus.OK).body(voter);
    }

    @GetMapping("")
    public ResponseEntity<List<Voter>> findAllVoters() {
        List<Voter> voters = voterService.findAllVoters();
        return ResponseEntity.status(HttpStatus.OK).body(voters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
