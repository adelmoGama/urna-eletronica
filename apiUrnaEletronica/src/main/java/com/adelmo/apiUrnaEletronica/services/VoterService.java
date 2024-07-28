package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Voter;
import com.adelmo.apiUrnaEletronica.repositories.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

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
}
