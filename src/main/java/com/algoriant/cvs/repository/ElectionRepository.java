package com.algoriant.cvs.repository;

import com.algoriant.cvs.dto.ElectionStatus;
import com.algoriant.cvs.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public interface ElectionRepository extends JpaRepository<Election, String> {

    List<Election> findByElectionStatus(ElectionStatus electionStatus);
}
