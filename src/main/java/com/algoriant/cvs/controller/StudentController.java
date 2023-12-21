package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.StudentRequest;
import com.algoriant.cvs.dto.StudentResponse;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping(value = "/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest) {
        try {
            return new ResponseEntity<>(studentService.createStudent(studentRequest), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/modifyStudent")
    public ResponseEntity<StudentResponse> modifyStudent(@RequestParam String deptNo,
                                                 @RequestBody StudentRequest studentRequest) {
        try {
            return new ResponseEntity<>(studentService.modifyStudent(deptNo, studentRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/removeStudent")
    public ResponseEntity<String> removeStudent(@RequestParam String deptNo) {
        try {
            return new ResponseEntity<>(studentService.removeStudent(deptNo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(studentService.removeStudent(deptNo), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getStudentById")
    public ResponseEntity<StudentResponse> getStudentById(String deptNo) {
        try {
            return new ResponseEntity<>(studentService.getStudentById(deptNo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllStudents")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        try {
            List<StudentResponse> students = studentService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
