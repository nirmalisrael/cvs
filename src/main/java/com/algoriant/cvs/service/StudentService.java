package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.StudentRequest;
import com.algoriant.cvs.dto.StudentResponse;
import com.algoriant.cvs.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface StudentService {

    Student createStudent(StudentRequest studentRequest);

    StudentResponse modifyStudent(String deptNo, StudentRequest studentRequest);

    String removeStudent(String deptNo);

    StudentResponse getStudentById(String deptNo);

    List<StudentResponse> getAllStudents();

    byte[] uploadProfileImage(MultipartFile file, String deptNo) throws IOException;

    byte[] getStudentImageByDeptNo(String deptNo);
}
