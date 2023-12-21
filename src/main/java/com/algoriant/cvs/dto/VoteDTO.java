package com.algoriant.cvs.dto;


import com.algoriant.cvs.entity.Vote;

public class VoteDTO {

    private String candidateId;

    private String deptNo;

    private String electionName;

    public VoteDTO(){}

    public VoteDTO(Vote vote) {
        this.candidateId = vote.getCandidate().getCandidateId();
        this.deptNo = vote.getStudent().getDeptNo();
        this.electionName = vote.getElection().getElectionName();
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }
}
