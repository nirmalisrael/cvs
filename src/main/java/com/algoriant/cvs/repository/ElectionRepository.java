package com.algoriant.cvs.repository;

import com.algoriant.cvs.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface ElectionRepository extends JpaRepository<Election, String> {
}
