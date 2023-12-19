package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.CandidateDTO;
import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.repository.CandidateRepository;
import com.algoriant.cvs.repository.ElectionRepository;
import com.algoriant.cvs.repository.StudentRepository;
import com.algoriant.cvs.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Candidate createCandidate(CandidateDTO candidateDTO) {
        Student student = studentRepository.findById(candidateDTO.getDeptNo()).orElseThrow(()
                -> new RuntimeException("Student not found"));
        Election election = electionRepository.findById(candidateDTO.getElectionName()).orElseThrow(()
                -> new RuntimeException("Election not found"));


        return null;
    }

    @Override
    public String removeCandidate(Long lotNo) {
        return null;
    }

    @Override
    public Candidate getCandidateById(Long lotNo) {
        return null;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return null;
    }
}
