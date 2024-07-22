package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    VoteDTO createVote(VoteDTO voteDTO);

    String removeAllVotes();
}
