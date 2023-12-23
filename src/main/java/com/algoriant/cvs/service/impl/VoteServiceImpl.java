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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public VoteDTO createVote(VoteDTO voteDTO) {
        Student student = studentRepository.findById(voteDTO.getDeptNo()).orElseThrow(()
                -> new RuntimeException("Student not found"));
        Candidate candidate = candidateRepository.findById(voteDTO.getCandidateId()).orElseThrow(()
                -> new RuntimeException("Candidate not found"));
        Election election = electionRepository.findById(voteDTO.getElectionName()).orElseThrow(()
                -> new RuntimeException("Election not found"));

        if (voteRepository.hasStudentVotedForElection(student, election)) {
            LOGGER.error("Student already voted");
            return null;
        }
        Vote vote = new Vote();
        vote.setStudent(student);
        vote.setElection(election);
        vote.setCandidate(candidate);
        return new VoteDTO(voteRepository.save(vote));
    }

    @Override
    public String removeAllVotes() {
        voteRepository.deleteAll();
        return "All votes are removed";
    }
}
