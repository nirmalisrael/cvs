package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.ElectionResponse;
import com.algoriant.cvs.dto.ElectionStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectionService {

    ElectionResponse createElection(String electionName, LocalDateTime electionDateTime, int durationHours);

    String removeElection(String electionName);

    ElectionResponse getElectionById(String electionName);

    List<ElectionResponse> getAllElections();

    List<ElectionResponse> getElectionsByElectionStatus(ElectionStatus electionStatus);

    ElectionResponse startElection(String electionName, LocalDateTime startTime, int durationHours);
}
