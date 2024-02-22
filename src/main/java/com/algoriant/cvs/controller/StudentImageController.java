package com.algoriant.cvs.controller;

import com.algoriant.cvs.dto.StudentImageDTO;
import com.algoriant.cvs.entity.StudentImage;
import com.algoriant.cvs.service.StudentImageService;
import com.algoriant.cvs.util.StudentImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
public class StudentImageController {

    @Autowired
    StudentImageService imageService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/uploadStudentImage")
    public ResponseEntity<StudentImageDTO> uploadStudentImage(@RequestParam MultipartFile file) {
        try {
            StudentImage studentImage = new StudentImage();
            studentImage.setFilename("sample");
            studentImage.setFileType(file.getContentType());
            studentImage.setFileData(StudentImageUtil.compressImage(file.getBytes()));
            studentImage = imageService.uploadStudentImage(studentImage);
            return new ResponseEntity<>(new StudentImageDTO(studentImage), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
