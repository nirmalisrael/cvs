package com.algoriant.cvs.repository;

import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface VoteRepository extends JpaRepository<Vote, Candidate> {
}
