package com.algoriant.cvs.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Candidate {

    @Id
    @NotNull
    private String candidateId;

    @NotNull
    private String candidateName;

    @ManyToOne
    @JoinColumn(name = "election_name")
    private Election election;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Student student;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Vote> votes;

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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
