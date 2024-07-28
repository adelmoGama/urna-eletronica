package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.entities.Voter;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.repositories.CandidateRepository;
import com.adelmo.apiUrnaEletronica.repositories.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;

    public Voter createVoter (Voter voter) {
        return voterRepository.save(voter);
    }

    public Optional<Voter> findVoterById (Long id) {
        return voterRepository.findById(id);
    }

    public List<Voter> findAllVoters () {
        return voterRepository.findAll();
    }

    public void deleteVoter (Long id) {
        voterRepository.deleteById(id);
    }

    public void vote (Long voterId, Long candidateId) {
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found on this session"));

        if(voter.getCandidate() != null) {
            throw new ElectionExceptions("You already voted.");
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found on this session"));

        voter.setCandidate(candidate);

        candidateService.countingReceivedVotes(candidate);

        voterRepository.save(voter);
    }
}
