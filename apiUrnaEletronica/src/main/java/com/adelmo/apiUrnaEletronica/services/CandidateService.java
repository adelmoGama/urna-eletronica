package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate createCandidate (Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Optional<Candidate> findCandidateById (Long id) {
        return candidateRepository.findById(id);
    }

    public List<Candidate> findAllCandidates () {
        return candidateRepository.findAll();
    }

    public void deleteCandidate (Long id) {
        candidateRepository.deleteById(id);
    }

    public void countingReceivedVotes (Candidate candidate) {
        if(candidate.getReceivedVotes() != null) {
            candidate.setReceivedVotes(candidate.getReceivedVotes()+1);
        } else {
            candidate.setReceivedVotes(1);
        }
    }
}
