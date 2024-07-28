package com.adelmo.apiUrnaEletronica.services;

import com.adelmo.apiUrnaEletronica.entities.Position;
import com.adelmo.apiUrnaEletronica.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position createPosition (Position position) {
        return positionRepository.save(position);
    }

    public Optional<Position> findPositionById (Long id) {
        return positionRepository.findById(id);
    }

    public List<Position> findAllPositions () {
        return positionRepository.findAll();
    }

    public void deletePosition (Long id) {
        positionRepository.deleteById(id);
    }
}
