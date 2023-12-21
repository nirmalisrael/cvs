package com.algoriant.cvs.repository;

import com.algoriant.cvs.entity.Election;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.student.deptNo = :deptNo")
    Optional<Vote> findByDeptNo(@Param("deptNo") String deptNo);

    @Query("SELECT COUNT(v) > 0 FROM Vote v " +
            "WHERE v.student = :student AND v.election = :election")
    boolean hasStudentVotedForElection(@Param("student") Student student, @Param("election") Election election);

    List<Vote> findByElectionElectionNameAndCandidateCandidateId(String electionName, String candidateId);

//    @Query("SELECT v FROM Vote v " +
//            "WHERE v.election.name = :electionName AND v.candidate.id = :candidateId")
//    List<Vote> findVotesByElectionNameAndCandidateId(@Param("electionName") String electionName,
//                                                     @Param("candidateId") Long candidateId);
}
