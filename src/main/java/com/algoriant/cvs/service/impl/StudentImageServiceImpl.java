package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.entity.StudentImage;
import com.algoriant.cvs.repository.StudentImageRepository;
import com.algoriant.cvs.service.StudentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentImageServiceImpl implements StudentImageService {

    @Autowired
    private StudentImageRepository imageRepository;

    @Override
    public StudentImage uploadStudentImage(StudentImage studentImage) {
        return imageRepository.save(studentImage);
    }

    @Override
    public StudentImage getStudentImageByDeptNo(String deptNo) {
        Optional<StudentImage> optionalStudentImage = imageRepository.findById(deptNo);
        return optionalStudentImage.orElse(null);
    }
}
