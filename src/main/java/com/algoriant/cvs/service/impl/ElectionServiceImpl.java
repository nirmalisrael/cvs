package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.repository.ElectionRepository;
import com.algoriant.cvs.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    ElectionRepository electionRepository;

    @Override
    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    @Override
    public String removeElection(String electionName) {
        Optional<Election> optionalElection = electionRepository.findById(electionName);
        if (optionalElection.isPresent()) {
            electionRepository.deleteById(electionName);
            return electionName;
        }
        return null;
    }

    @Override
    public Election getElectionById(String electionName) {
        return electionRepository.findById(electionName).orElse(null);
    }

    @Override
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }
}
