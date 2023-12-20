package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.Vote;
import com.algoriant.cvs.repository.CandidateRepository;
import com.algoriant.cvs.repository.ElectionRepository;
import com.algoriant.cvs.repository.StudentRepository;
import com.algoriant.cvs.repository.VoteRepository;
import com.algoriant.cvs.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Vote createVote(VoteDTO voteDTO) {
        Student student = studentRepository.findById(voteDTO.getDeptNo()).orElseThrow(()
                -> new RuntimeException("Student not found"));
        Candidate candidate = candidateRepository.findById(voteDTO.getLotNo()).orElseThrow(()
                -> new RuntimeException("Candidate not found"));
        Election election = electionRepository.findById(voteDTO.getElectionName()).orElseThrow(()
                -> new RuntimeException("Election not found"));

        Vote vote = new Vote();
        vote.setElection(election);
        vote.setCandidate(candidate);
        vote.setStudent(student);
        vote = voteRepository.save(vote);
//        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);
        return vote;
    }

    @Override
    public String removeAllVotes() {
        voteRepository.deleteAll();
        return "All votes are removed";
    }

    @Override
    public Boolean getVoteByDeptNo(String deptNo) {
        return voteRepository.findByDeptNo(deptNo).isPresent();
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }
}
