package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.ElectionResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectionService {

    ElectionResponse createElection(String electionName);

    String removeElection(String electionName);

    ElectionResponse getElectionById(String electionName);

    List<ElectionResponse> getAllElections();

    ElectionResponse startElection(String electionName, LocalDateTime startTime, int durationHours);
}
