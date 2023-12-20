package com.algoriant.cvs.repository;

import com.algoriant.cvs.entity.Candidate;
import com.algoriant.cvs.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.student.deptNo = :deptNo")
    Optional<Vote> findByDeptNo(@Param("deptNo") String deptNo);
}
