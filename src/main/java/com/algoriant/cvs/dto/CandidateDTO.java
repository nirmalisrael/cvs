package com.algoriant.cvs.dto;

import com.algoriant.cvs.entity.Candidate;

public class CandidateDTO {

    private String candidateId;

    private String candidateName;

    private String electionName;

    private String deptNo;

    public CandidateDTO(Candidate candidate) {
        this.candidateId = candidate.getCandidateId();
        this.candidateName = candidate.getCandidateName();
        this.electionName = candidate.getElection().getElectionName();
        this.deptNo = candidate.getStudent().getDeptNo();
    }

    public CandidateDTO() {
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
}
