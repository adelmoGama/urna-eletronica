package com.adelmo.apiUrnaEletronica.controllers;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.services.CandidateService;
import com.adelmo.apiUrnaEletronica.services.ElectionSessionService;
import com.adelmo.apiUrnaEletronica.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/election-session")
public class ElectionSessionController {
    @Autowired
    private ElectionSessionService electionSessionService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<ElectionSession> createElectionSession(@RequestBody ElectionSession electionSession) {
        return new ResponseEntity<>(electionSessionService.createElectionSession(electionSession), HttpStatus.CREATED);
    }

    @PatchMapping("/close/{id}")
    public ResponseEntity<Void> closeElectionSession(@PathVariable Long id) {
        electionSessionService.closeElectionSession(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<List<Candidate>> findCandidates(@PathVariable Long sessionId) {
        List<Candidate> candidates = candidateService.findCandidatesBySessionId(sessionId);
        System.out.println(reportService.report(candidates));
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
    }

    @ExceptionHandler(ElectionExceptions.class)
    public ResponseEntity<String> handleElectionException(ElectionExceptions exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
