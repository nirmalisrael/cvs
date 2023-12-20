package com.algoriant.cvs.repository;

import com.algoriant.cvs.dto.DegreeType;
import com.algoriant.cvs.dto.Department;
import com.algoriant.cvs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT MAX(s.deptNo) FROM Student s WHERE s.department = ?1 AND s.degreeType = ?2")
    String findLastStudentDeptNoByDepartmentAndDegreeType(Department department, DegreeType degreeType);
}
