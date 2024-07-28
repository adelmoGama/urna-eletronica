package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import com.adelmo.apiUrnaEletronica.exceptions.ElectionExceptions;
import com.adelmo.apiUrnaEletronica.repositories.CandidateRepository;
import com.adelmo.apiUrnaEletronica.repositories.ElectionSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionSessionService {

    @Autowired
    private ElectionSessionRepository electionSessionRepository;
    @Autowired
    private CandidateRepository candidateRepository;

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

        }else if (electionSession.get().getNumberOfVotes().equals(0)) {

            throw new ElectionExceptions("As there was no vote, we are canceling this election.");

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

    public List<Candidate> getCandidatesBySessionId (Long electionSessionId) {
        return candidateRepository.findByElectionSessionId(electionSessionId);
    }

    public String getWinner (List<Candidate> candidates) {
        String winnerName = "";
        int actualVotes = 0;
        for (Candidate candidate : candidates) {
            if(candidate.getReceivedVotes() > actualVotes) {
                winnerName = candidate.getName();
            }
        }
        return winnerName;
    }

    public String report(List<Candidate> candidates) {
        StringBuilder report = new StringBuilder();
        int allVotes = 0;

        report.append("----------------------------------------").append("\n");

        String positionLine = String.format("%-30s %-10s", "Position", candidates.get(0).getPosition());
        report.append(positionLine).append("\n").append("\n");

        String headLine = String.format("%-30s %-10s", "Candidates", "Votes");
        report.append(headLine).append("\n").append("\n");

        for (Candidate candidate : candidates) {
            allVotes += candidate.getReceivedVotes();
            String line = String.format("%-30s %-10d", candidate.getName(), candidate.getReceivedVotes());
            report.append(line).append("\n");
        }

//        if(allVotes <= 1) {
//            throw new ElectionExceptions("As the votes didn't reach the minimum number, we are canceling the election.");
//
//        }

        report.append("\n");

        String amountVotes = String.format("%-30s %-10s", "Votes Amount", allVotes);
        report.append(amountVotes).append("\n").append("\n");

        String winnerName = getWinner(candidates);

        String winner = String.format("%-30s %-10s", "Winner", winnerName);
        report.append(winner).append("\n").append("\n");

        report.append("----------------------------------------").append("\n");

        return report.toString();
    }

}
