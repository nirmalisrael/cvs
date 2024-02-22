package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.StudentRequest;
import com.algoriant.cvs.dto.StudentResponse;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.service.StudentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(value = "/createStudent")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest) {
        try {
            return new ResponseEntity<>(studentService.createStudent(studentRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PutMapping(value = "/modifyStudent")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<StudentResponse> modifyStudent(@RequestParam String deptNo,
                                                         @RequestBody StudentRequest studentRequest) {
        try {
            return new ResponseEntity<>(studentService.modifyStudent(deptNo, studentRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/removeStudent")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> removeStudent(@RequestParam String deptNo) {
        try {
            return new ResponseEntity<>(studentService.removeStudent(deptNo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(studentService.removeStudent(deptNo), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getStudentById")
    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    public ResponseEntity<StudentResponse> getStudentById(String deptNo) {
        try {
            return new ResponseEntity<>(studentService.getStudentById(deptNo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllStudents")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        try {
            return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
