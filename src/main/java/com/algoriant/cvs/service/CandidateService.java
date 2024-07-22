package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.CandidateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CandidateService {

    CandidateDTO createCandidate(CandidateDTO candidate);

    List<CandidateDTO> createCandidates(List<CandidateDTO> candidateDTOS);
    String removeCandidate(String candidateId);

    CandidateDTO getCandidateById(String candidateId);

    List<CandidateDTO> getCandidatesByElectionName(String electionName);

    List<CandidateDTO> getAllCandidates();
}
