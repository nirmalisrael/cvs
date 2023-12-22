package com.algoriant.cvs.repository;

import com.algoriant.cvs.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public interface CandidateRepository extends JpaRepository<Candidate, String> {

    @Query("SELECT c FROM Candidate c " +
            "LEFT JOIN c.votes v " +
            "WHERE c.election.electionName = :electionName " +
            "GROUP BY c " +
            "ORDER BY COUNT(v) DESC")
    List<Candidate> findCandidatesByElectionIdOrderByVoteCountDesc(@Param("electionName") String electionName);
}
