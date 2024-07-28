package com.adelmo.apiUrnaEletronica.repositories;

import com.adelmo.apiUrnaEletronica.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
