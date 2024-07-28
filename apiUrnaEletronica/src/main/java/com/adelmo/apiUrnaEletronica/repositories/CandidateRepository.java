package com.adelmo.apiUrnaEletronica.repositories;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
