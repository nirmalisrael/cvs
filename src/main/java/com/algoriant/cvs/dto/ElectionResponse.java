package com.algoriant.cvs.dto;

import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ElectionResponse {

    private String electionName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private int durationHours;

    private ElectionStatus electionStatus;

    private List<CandidateDTO> candidateDTOS = new ArrayList<>();

    public ElectionResponse(Election election) {
        if (election.getElectionName() != null)
            this.electionName = election.getElectionName();
        if (election.getStartTime() != null)
            this.startTime = election.getStartTime();
        if (election.getEndTime() != null)
            this.endTime = election.getEndTime();
        if (election.getDurationHours() != 0)
            this.durationHours = election.getDurationHours();
        if (election.getElectionStatus() != null)
            this.electionStatus = election.getElectionStatus();
        if (election.getCandidates() != null) {
            for (Candidate candidate: election.getCandidates()) {
                this.candidateDTOS.add(new CandidateDTO(candidate));
            }
        }
    }

    public ElectionResponse() {
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ElectionStatus getElectionStatus() {
        return electionStatus;
    }

    public void setElectionStatus(ElectionStatus electionStatus) {
        this.electionStatus = electionStatus;
    }

    public List<CandidateDTO> getCandidateDTOS() {
        return candidateDTOS;
    }

    public void setCandidateDTOS(List<CandidateDTO> candidateDTOS) {
        this.candidateDTOS = candidateDTOS;
    }
}
