package com.algoriant.cvs.service;

import com.algoriant.cvs.entity.StudentImage;
import org.springframework.stereotype.Service;

@Service
public interface StudentImageService {

    StudentImage uploadStudentImage(StudentImage studentImage);

    StudentImage getStudentImageByDeptNo(String deptNo);
}
