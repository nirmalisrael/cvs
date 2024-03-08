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

import java.util.ArrayList;
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
    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        Student student = studentRepository.findById(candidateDTO.getDeptNo()).orElseThrow(()
                -> new RuntimeException("Student not found"));

        Election election = electionRepository.findById(candidateDTO.getElectionName()).orElseThrow(()
                -> new RuntimeException("Election not found"));
        Candidate candidate = new Candidate();

        candidate.setCandidateId(candidateDTO.getCandidateId());
        candidate.setCandidateName(student.getStudentName());
        candidate.setElection(election);
        candidate.setStudent(student);
        return new CandidateDTO(candidateRepository.save(candidate));
    }

    @Override
    public List<CandidateDTO> createCandidates(List<CandidateDTO> candidateDTOS) {
        List<CandidateDTO> candidateDTOResponse = new ArrayList<>();
        for(CandidateDTO candidateDTO: candidateDTOS) {
            if (candidateDTO != null) {
                candidateDTOResponse.add(createCandidate(candidateDTO));
            }
        }
        return candidateDTOResponse;
    }

    @Override
    public String removeCandidate(String candidateId) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
        if (optionalCandidate.isPresent()) {
            candidateRepository.deleteById(candidateId);
            return candidateId;
        }
        return null;
    }

    @Override
    public CandidateDTO getCandidateById(String candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);

        return candidate.map(CandidateDTO::new).orElse(null);
    }

    @Override
    public List<CandidateDTO> getCandidatesByElectionName(String electionName) {
        List<Candidate> candidates = candidateRepository.findCandidatesByElectionIdOrderByVoteCountDesc(electionName);
        List<CandidateDTO> candidateDTOS = new ArrayList<>();
        for (Candidate candidate : candidates) {
            candidateDTOS.add(new CandidateDTO(candidate));
        }
        return candidateDTOS;
    }

    @Override
    public List<CandidateDTO> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<CandidateDTO> candidateDTOS = new ArrayList<>();
        for (Candidate candidate : candidates) {
            candidateDTOS.add(new CandidateDTO(candidate));
        }
        return candidateDTOS;
    }
}
