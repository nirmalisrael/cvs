package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.CandidateDTO;
import com.algoriant.cvs.entity.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {

    Candidate createCandidate(CandidateDTO candidate);

    String removeCandidate(String candidateId);

    CandidateDTO getCandidateById(String candidateId);

    List<CandidateDTO> getCandidatesByElectionName(String electionName);

    List<CandidateDTO> getAllCandidates();
}
