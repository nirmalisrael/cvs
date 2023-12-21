package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.ElectionDTO;
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
    public Election createElection(ElectionDTO electionDTO) {
        return electionRepository.save(new Election(electionDTO.getElectionName(), electionDTO.getEndDate()));
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
