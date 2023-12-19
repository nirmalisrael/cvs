package com.algoriant.cvs.service;

import com.algoriant.cvs.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    Vote createVote(Vote candidate);

    String removeVote(Long lotNo);

    Vote getVoteById(Vote lotNo);

    List<Vote> getAllVotes();
}
