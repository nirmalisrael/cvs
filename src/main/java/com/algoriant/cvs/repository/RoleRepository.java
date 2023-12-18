package com.algoriant.cvs.repository;

import com.algoriant.cvs.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface RoleRepository extends JpaRepository<Role, String> {
}
