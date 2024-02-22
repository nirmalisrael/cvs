package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.StudentImageDTO;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.StudentImage;
import com.algoriant.cvs.service.StudentImageService;
import com.algoriant.cvs.util.StudentImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
public class StudentImageController {

    @Autowired
    StudentImageService imageService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/uploadStudentImage")
    public ResponseEntity<StudentImageDTO> uploadStudentImage(@RequestParam MultipartFile file, @RequestParam String deptNo) {
        try {

            return new ResponseEntity<>(imageService.uploadStudentImage(file, deptNo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "getImageByFilename")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<StudentImageDTO> getImageByFilename(@RequestParam String deptNo) {
        try {
            StudentImage studentImage = imageService.getStudentImageByDeptNo(deptNo);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentImageDTO(studentImage));
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
