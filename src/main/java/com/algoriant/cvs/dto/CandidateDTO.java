package com.algoriant.cvs.dto;

public class CandidateDTO {

    private Long lotNo;

    private String candidateName;

    private String electionName;

    private String deptNo;

    public Long getLotNo() {
        return lotNo;
    }

    public void setLotNo(Long lotNo) {
        this.lotNo = lotNo;
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
