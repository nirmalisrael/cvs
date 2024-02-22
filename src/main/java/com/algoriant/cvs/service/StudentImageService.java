package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.StudentImageDTO;
import com.algoriant.cvs.entity.StudentImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StudentImageService {

    StudentImageDTO uploadStudentImage(MultipartFile multipartFile, String deptNo);

    StudentImage getStudentImageByDeptNo(String deptNo);
}
