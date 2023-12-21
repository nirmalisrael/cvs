package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.entity.Vote;
import com.algoriant.cvs.repository.VoteRepository;
import com.algoriant.cvs.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    VoteRepository voteRepository;

    @Override
    public List<VoteDTO> getVotesByElectionNameAndCandidateId(String electionName, String candidateId) {
        List<Vote> votes = voteRepository.findByElectionElectionNameAndCandidateCandidateId(electionName, candidateId);
        List<VoteDTO> voteDTOS = new ArrayList<>();
        for (Vote vote: votes) {
            voteDTOS.add(new VoteDTO(vote));
        }
        return voteDTOS;
    }

    @Override
    public List<VoteDTO> getAllVotes() {
        List<Vote> votes = voteRepository.findAll();
        List<VoteDTO> voteDTOS = new ArrayList<>();
        for (Vote vote: votes) {
            voteDTOS.add(new VoteDTO(vote));
        }
        return voteDTOS;
    }


}
