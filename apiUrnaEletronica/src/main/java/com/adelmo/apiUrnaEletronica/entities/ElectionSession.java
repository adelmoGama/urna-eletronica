package com.adelmo.apiUrnaEletronica.entities;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_election_session")
public class ElectionSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_election_session")
    private Long id;

    private Integer openElectionSession = 1;

    private Integer numberOfVotes = 0;
}