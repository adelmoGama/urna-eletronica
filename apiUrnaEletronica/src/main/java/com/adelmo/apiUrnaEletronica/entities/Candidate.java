package com.adelmo.apiUrnaEletronica.entities;

import com.adelmo.apiUrnaEletronica.enums.ElectoralPositionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidate")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ElectoralPositionsEnum position;


    @OneToMany(mappedBy = "candidate")
    private Set<Voter> votesReceived = new HashSet<>();
}
