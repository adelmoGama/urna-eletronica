package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import com.adelmo.apiUrnaEletronica.repositories.ElectionSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElectionSessionService {

    @Autowired
    private ElectionSessionRepository electionSessionRepository;

    public ElectionSession createElectionSession (ElectionSession electionSession) {
        return electionSessionRepository.save(electionSession);
    }

    public Optional<ElectionSession> closeElectionSession (Long id) {
        Optional<ElectionSession> electionSession = electionSessionRepository.findById(id);

        electionSession.ifPresent(session -> session.setClosedElectionSession(false));

        return electionSession;
    }
}
