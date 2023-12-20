package com.algoriant.cvs.dto;


public class VoteDTO {

    private Long lotNo;

    private String deptNo;

    private String electionName;

    public Long getLotNo() {
        return lotNo;
    }

    public void setLotNo(Long lotNo) {
        this.lotNo = lotNo;
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
