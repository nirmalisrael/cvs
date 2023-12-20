package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.VoteDTO;
import com.algoriant.cvs.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    Vote createVote(VoteDTO voteDTO);

    String removeAllVotes();

    Boolean getVoteByDeptNo(String deptNo);

    List<Vote> getAllVotes();
}
