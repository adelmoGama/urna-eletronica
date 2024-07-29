package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.entities.Voter;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.repositories.CandidateRepository;
import com.adelmo.apiUrnaEletronica.repositories.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @Autowired
    private ElectionSessionService electionSessionService;

    public Voter createVoter (Voter voter) {
        try {
            return voterRepository.save(voter);
        } catch (DataIntegrityViolationException exception) {
            throw new ElectionExceptions("Voter with the name, " + voter.getName() + ", is already registered.");
        }
    }

    public Optional<Voter> findVoterById (Long id) {
        return voterRepository.findById(id);
    }

    public List<Voter> findAllVoters () {
        return voterRepository.findAll();
    }

    public void deleteVoter (Long id) {
        Optional<Voter> voter = voterRepository.findById(id);

        if(voter.isPresent()) {
            if (voter.get().getCandidate() == null) {
                voterRepository.deleteById(id);
            } else {
                throw new ElectionExceptions(voter.get().getName()+" already has voted and can't be deleted.");
            }
        }
    }

    public void vote (Long voterId, Long candidateId, Long electionSessionId) {
        Integer open = electionSessionService.isOpen(electionSessionId);

            if(open.equals(1)) {
                Voter voter = voterRepository.findById(voterId)
                        .orElseThrow(() -> new RuntimeException("Voter not found on this session"));

                if (voter.getCandidate() != null) {
                    throw new ElectionExceptions("You already voted.");
                }

                Candidate candidate = candidateRepository.findById(candidateId)
                        .orElseThrow(() -> new RuntimeException("Candidate not found on this session"));

                voter.setCandidate(candidate);

                candidateService.countingReceivedVotes(candidate);
                electionSessionService.countingAllVotes(electionSessionId);

                voterRepository.save(voter);
            } else {
                throw new ElectionExceptions("The session is not open");
            }
    }
}
