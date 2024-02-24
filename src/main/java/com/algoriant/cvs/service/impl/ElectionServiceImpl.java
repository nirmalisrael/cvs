package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.ElectionResponse;
import com.algoriant.cvs.dto.ElectionStatus;
import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.repository.ElectionRepository;
import com.algoriant.cvs.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    ElectionRepository electionRepository;

    @Override
    public ElectionResponse createElection(String electionName) {
        Election election = new Election();
        election.setElectionName(electionName);
        election.setCreationTime(LocalDateTime.now());
        election.setElectionStatus(ElectionStatus.UPCOMING);
        return new ElectionResponse(electionRepository.save(election));
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
    public ElectionResponse getElectionById(String electionName) {
        return new ElectionResponse(Objects.requireNonNull(electionRepository.findById(electionName).orElse(null)));
    }

    @Override
    public List<ElectionResponse> getAllElections() {
        List<ElectionResponse> electionResponses = new ArrayList<>();
        List<Election> elections = electionRepository.findAll();
        for (Election election: elections) {
            electionResponses.add(new ElectionResponse(election));
        }
        return electionResponses;
    }

    @Override
    public ElectionResponse startElection(String electionName, LocalDateTime startTime, int durationHours) {
        Optional<Election> optionalElection = electionRepository.findById(electionName);

        if (optionalElection.isPresent()) {
            Election election = optionalElection.get();
            election.setStartTime(startTime);
            election.setDurationHours(durationHours);
            election.setEndTime(startTime.plusHours(durationHours));
            updateElectionStatus(election);
            return new ElectionResponse(electionRepository.save(election));
        }
        return null;
    }

    private void updateElectionStatus(Election election) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isBefore(election.getStartTime())) {
            election.setElectionStatus(ElectionStatus.UPCOMING);
        } else if (currentTime.isAfter(election.getStartTime()) && currentTime.isBefore(election.getEndTime())) {
            election.setElectionStatus(ElectionStatus.LIVE);
        } else {
            election.setElectionStatus(ElectionStatus.COMPLETED);
        }
    }
}
