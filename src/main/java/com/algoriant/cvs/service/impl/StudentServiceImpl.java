package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.StudentRequest;
import com.algoriant.cvs.dto.StudentResponse;
import com.algoriant.cvs.entity.Role;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.repository.StudentRepository;
import com.algoriant.cvs.repository.UserRepository;
import com.algoriant.cvs.service.StudentImageService;
import com.algoriant.cvs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired

    private StudentImageService imageService;

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        try {
            Student student = new Student(studentRequest);

            String prefixDeptNo = String.valueOf(student.getAdmissionYear()).substring(2)
                    + student.getDegreeType().getCode()
                    + student.getDepartment().getCode();

            String lastStudentId = studentRepository.findLastStudentDeptNoByDepartmentAndDegreeType(
                    student.getDepartment(), student.getDegreeType());

            String newDeptNo;

            if (lastStudentId == null) {
                newDeptNo = prefixDeptNo + "01";
            } else {
                int lastNum = Integer.parseInt(lastStudentId.substring(5));
                newDeptNo = prefixDeptNo + String.format("%02d", ++lastNum);
            }
            student.setDeptNo(newDeptNo);
//            student.setStudentImage(imageService.uploadStudentImage(student));
            studentRepository.save(student);

            User user = new User();
            user.setUsername(student.getDeptNo());
            System.out.println(student.getDateOfBirth());
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            user.setPassword(passwordEncoder.encode(formatter.format(student.getDateOfBirth())));

            Role role = new Role();
            role.setRoleName("user");
            role.setRoleDescription("Default role for student");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);

            user.setRoles(roleSet);
            userRepository.save(user);
            return student;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public StudentResponse modifyStudent(String deptNo, StudentRequest studentRequest) {
        Optional<Student> optionalStudent = studentRepository.findById(deptNo);
        if (optionalStudent.isPresent()) {
            Student student = new Student(studentRequest);
            student.setDeptNo(optionalStudent.get().getDeptNo());
            return new StudentResponse(studentRepository.save(student));
        } else {
            return null;
        }
    }

    @Override
    public String removeStudent(String deptNo) {
        try {
            studentRepository.deleteById(deptNo);
            userRepository.deleteById(deptNo);
            return deptNo;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public StudentResponse getStudentById(String deptNo) {
        Optional<Student> optionalStudent = studentRepository.findById(deptNo);
        return new StudentResponse(optionalStudent.orElseThrow(null));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = new ArrayList<>(studentRepository.findAll());
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }
}
