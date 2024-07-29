package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.repositories.ElectionSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ElectionSessionService {

    @Autowired
    private ElectionSessionRepository electionSessionRepository;
    @Autowired
    private CandidateService candidateService;

    public ElectionSession createElectionSession (ElectionSession electionSession) {
        electionSession.setOpenElectionSession(1);
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
        }else if (electionSession.get().getNumberOfVotes().equals(0)) {

            throw new ElectionExceptions("As there was no vote, we are canceling this election.");
        } else {
            electionSession.get().setOpenElectionSession(0);
            electionSessionRepository.save(electionSession.get());

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

    public String getSessionWinner (Long electionSessionId) {
        List<Candidate> candidates = candidateService.findCandidatesBySessionId(electionSessionId);

        Collections.sort(candidates);

        String winnerName = candidates.get(candidates.size()-1).getName();

        int actualVotes = candidates.get(candidates.size()-1).getReceivedVotes();

        for (Candidate candidate : candidates) {
            if(candidate.getName().equals(winnerName)) {
                return winnerName;
            }
            if(candidate.getReceivedVotes() == actualVotes) {
                throw new ElectionExceptions("We have a draw on this election.");
            }
        }
        return winnerName;
    }

    public void checkingTheMinimumNumberOfVotes (Long id) {
        Optional<ElectionSession> electionSession = electionSessionRepository.findById(id);

        if(electionSession.get().getNumberOfVotes() <= 1) {
            throw new ElectionExceptions("As the votes didn't reach the minimum number, we are canceling the election.");
        }
    }
}
