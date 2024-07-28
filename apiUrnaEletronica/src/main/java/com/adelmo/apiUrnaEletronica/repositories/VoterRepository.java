package com.adelmo.apiUrnaEletronica.repositories;

import com.adelmo.apiUrnaEletronica.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
