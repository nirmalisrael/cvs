package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.VoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {

    List<VoteDTO> getVotesByElectionNameAndCandidateId(String electionName, String candidateId);

    List<VoteDTO> getAllVotes();
}
