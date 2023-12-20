package com.algoriant.cvs.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "candidate_details")
public class Candidate {

    @Id
    @NotNull
    private Long lotNo;

    @NotNull
    private String candidateName;

    @ManyToOne
    @JoinColumn(name = "election_name")
    private Election election;

    @NotNull
    @ManyToOne
    private Student student;

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

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
