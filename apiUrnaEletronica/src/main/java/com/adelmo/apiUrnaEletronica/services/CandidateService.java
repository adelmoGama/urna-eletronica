package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate createCandidate (Candidate candidate) {
        try {
            return candidateRepository.save(candidate);
        } catch (DataIntegrityViolationException exception) {
            throw new ElectionExceptions("Candidate with the name, " + candidate.getName() + ", is already registered for the election.");
        }
    }

    public Optional<Candidate> findCandidateById (Long id) {
        return candidateRepository.findById(id);
    }

    public List<Candidate> findAllCandidates () {
        return candidateRepository.findAll();
    }

    public void deleteCandidate (Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            if (candidate.get().getReceivedVotes() == 0) {
                candidateRepository.deleteById(id);
            } else {
                throw new ElectionExceptions(candidate.get().getName()+" already has a score calculated and can't be deleted.");
            }
        }
    }

    public void countingReceivedVotes (Candidate candidate) {
        if(candidate.getReceivedVotes() != null) {
            candidate.setReceivedVotes(candidate.getReceivedVotes()+1);
        } else {
            candidate.setReceivedVotes(1);
        }
    }
}
