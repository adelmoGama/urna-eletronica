package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ElectionSessionService electionSessionService;

    public String report(List<Candidate> candidates) {
        electionSessionService.checkingTheMinimumNumberOfVotes(Long.valueOf(candidates.get(0).getElectionSessionId()));

        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        StringBuilder report = new StringBuilder();

        int allVotes = 0;

        report.append("----------------------------------------").append("\n");

        String reportDate = String.format("%-30s %-10s", "Report date:", formatterDateTime.format(LocalDateTime.now()));
        report.append(reportDate).append("\n").append("\n");

        String positionLine = String.format("%-30s %-10s", "Position", candidates.get(0).getPosition());
        report.append(positionLine).append("\n").append("\n");

        String headLine = String.format("%-30s %-10s", "Candidates", "Votes");
        report.append(headLine).append("\n").append("\n");

        for (Candidate candidate : candidates) {
            allVotes += candidate.getReceivedVotes();
            String line = String.format("%-30s %-10d", candidate.getName(), candidate.getReceivedVotes());
            report.append(line).append("\n");
        }

        report.append("\n");

        String amountVotes = String.format("%-30s %-10s", "Votes Amount", allVotes);
        report.append(amountVotes).append("\n").append("\n");

        String winnerName = electionSessionService.getSessionWinner(Long.valueOf(candidates.get(0).getElectionSessionId()));

        String winner = String.format("%-30s %-10s", "Winner", winnerName);
        report.append(winner).append("\n").append("\n");

        report.append("----------------------------------------").append("\n");

        return report.toString();
    }

}
