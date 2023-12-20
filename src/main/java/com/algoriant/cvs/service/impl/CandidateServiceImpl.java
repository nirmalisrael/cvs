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
import java.util.Optional;

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

        Candidate candidate = new Candidate();
        candidate.setLotNo(candidateDTO.getLotNo());
        candidate.setCandidateName(candidateDTO.getCandidateName());
        candidate.setElection(election);
        candidate.setStudent(student);
        return candidateRepository.save(candidate);
    }

    @Override
    public Long removeCandidate(Long lotNo) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(lotNo);
        if (optionalCandidate.isPresent()) {
            candidateRepository.deleteById(lotNo);
            return lotNo;
        }
        return null;
    }

    @Override
    public Candidate getCandidateById(Long lotNo) {
        return candidateRepository.findById(lotNo).orElse(null);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}
