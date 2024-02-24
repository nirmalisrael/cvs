package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.StudentImageDTO;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.StudentImage;
import com.algoriant.cvs.repository.StudentImageRepository;
import com.algoriant.cvs.repository.StudentRepository;
import com.algoriant.cvs.service.StudentImageService;
import com.algoriant.cvs.util.StudentImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class StudentImageServiceImpl implements StudentImageService {

    @Autowired
    private StudentImageRepository imageRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentImageDTO uploadStudentImage(MultipartFile file, String deptNo) {
        try {
            Student student = studentRepository.findById(deptNo).orElseThrow(()
                    -> new RuntimeException("Student not found"));


            student.setProfileImage(StudentImageUtil.compressImage(file.getBytes()));
            StudentImage studentImage = new StudentImage();
//            studentImage.setStudent(student);
//            studentImage.setFilename(file.getOriginalFilename());
//            studentImage.setFileType(file.getContentType());
//            studentImage.setFileData(StudentImageUtil.compressImage(file.getBytes()));

//            imageRepository.save(studentImage);
            return new StudentImageDTO(studentImage);
        } catch (Exception ex) {
            throw new RuntimeException("Image Cannot upload ", ex);
        }
    }

    @Override
    public StudentImage getStudentImageByDeptNo(String deptNo) {
        Optional<StudentImage> optionalStudentImage = imageRepository.findById(deptNo);
        return optionalStudentImage.orElse(null);
    }
}
