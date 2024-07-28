package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
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

    public Integer isOpen (Long id) {
        Optional<ElectionSession> electionSession = electionSessionRepository.findById(id);
        if(electionSession.isPresent()) {
            return electionSession.get().getOpenElectionSession();
        }
        throw new ElectionExceptions("The session was not founded.");
    }

    public void closeElectionSession (Long id) {
        Optional<ElectionSession> electionSession = electionSessionRepository.findById(id);

        if(electionSession.get().getNumberOfVotes().equals(1)) {
            electionSession.get().setNumberOfVotes(0);
            throw new ElectionExceptions("As there was only 1 vote, we are canceling this election.");
        }

        if(electionSession.get().getOpenElectionSession().equals(0)) {
            throw new ElectionExceptions("The session is not open to be closed.");
        } else {
            electionSession.get().setOpenElectionSession(0);
        }
    }

    public void countingAllVotes (Long electionSessionId) {
        Optional<ElectionSession> electionSession = electionSessionRepository.findById(electionSessionId);

        if(electionSession.get().getNumberOfVotes() != null) {
            electionSession.get().setNumberOfVotes(electionSession.get().getNumberOfVotes()+1);
        } else {
            electionSession.get().setNumberOfVotes(1);
        }
    }
}
