package com.adelmo.apiUrnaEletronica.entities;

import com.adelmo.apiUrnaEletronica.enums.ElectoralPositionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_candidate")
public class Candidate implements Comparable<Candidate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidate")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private ElectoralPositionsEnum position;

    @Column(name = "received_votes")
    private Integer receivedVotes = 0;

    private Integer electionSessionId;

    @Override
    public int compareTo(Candidate candidate) {
        if(this.getReceivedVotes() < candidate.getReceivedVotes()) {
            return -1;
        }
        if(this.getReceivedVotes() > candidate.getReceivedVotes()) {
            return 1;
        }
        return 0;
    }
}
