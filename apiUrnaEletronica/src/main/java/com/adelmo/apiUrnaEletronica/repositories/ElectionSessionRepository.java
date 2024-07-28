package com.adelmo.apiUrnaEletronica.repositories;

import com.adelmo.apiUrnaEletronica.entities.ElectionSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionSessionRepository extends JpaRepository<ElectionSession, Long> {
}
