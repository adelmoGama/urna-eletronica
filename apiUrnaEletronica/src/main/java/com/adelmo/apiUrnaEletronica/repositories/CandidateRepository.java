package com.adelmo.apiUrnaEletronica.repositories;

import com.adelmo.apiUrnaEletronica.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM tb_candidate WHERE election_session_id = ?1"
            , nativeQuery = true)
    List<Candidate> findByElectionSessionId(Long sessionId);
}
