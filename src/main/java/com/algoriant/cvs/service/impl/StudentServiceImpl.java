package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.dto.DegreeType;
import com.algoriant.cvs.dto.Department;
import com.algoriant.cvs.dto.StudentRequest;
import com.algoriant.cvs.dto.StudentResponse;
import com.algoriant.cvs.entity.Role;
import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.entity.Vote;
import com.algoriant.cvs.repository.StudentRepository;
import com.algoriant.cvs.repository.UserRepository;
import com.algoriant.cvs.service.StudentService;
import com.algoriant.cvs.util.StudentImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            studentRepository.save(student);

            User user = new User();
            user.setUsername(student.getDeptNo());
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

    @Override
    public byte[] uploadProfileImage(MultipartFile file, String deptNo) throws IOException {
        Optional<Student> optionalStudent = studentRepository.findById(deptNo);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setProfileImage(StudentImageUtil.compressImage(file.getBytes()));
            studentRepository.save(student);
            return StudentImageUtil.decompressImage(student.getProfileImage());
        }
        return new byte[0];
    }

    @Override
    public byte[] getStudentImageByDeptNo(String deptNo) {
        Optional<Student> optionalStudent = studentRepository.findById(deptNo);
        return optionalStudent.map(student -> StudentImageUtil.decompressImage(student.getProfileImage())).orElseGet(()
                -> new byte[0]);
    }

    @Override
    public Map<String, String> hasVoted(String deptNo, String electionName) {
        Optional<Student> optionalStudent = studentRepository.findById(deptNo);
        boolean hasVoted = false;
        String candidateId = null;
        if (optionalStudent.isPresent()) {
            List<Vote> votes = optionalStudent.get().getVotes();
            for (Vote vote : votes) {
                if (electionName.equals(vote.getElection().getElectionName())) {
                    hasVoted = true;
                    candidateId = vote.getCandidate().getCandidateId();
                    break;
                }
            }
        }
        Map<String, String> response = new HashMap<>();
        response.put("hasVoted", String.valueOf(hasVoted));
        response.put("candidateId", candidateId);
        return response;
    }

    @Override
    public List<StudentResponse> getStudentsByFiler(String degreeType, String department, int admissionYear) {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();
        if (Objects.equals(degreeType, "null") && Objects.equals(department, "null") && admissionYear == 0) {
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (!Objects.equals(degreeType, "null") && Objects.equals(department, "null") && admissionYear == 0) {
            students = students.stream().filter(student -> student.getDegreeType().equals(DegreeType.valueOf(degreeType)))
                    .collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (Objects.equals(degreeType, "null") && !Objects.equals(department, "null") && admissionYear == 0) {
            students = students.stream().filter(student -> student.getDepartment().equals(Department.valueOf(department)))
                    .collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (Objects.equals(degreeType, "null") && Objects.equals(department, "null") && admissionYear != 0) {
            students = students.stream().filter(student -> student.getAdmissionYear() == admissionYear).collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (!Objects.equals(degreeType, "null") && !Objects.equals(department, "null") && admissionYear == 0) {
            students = students.stream().filter(student -> student.getDegreeType().equals(DegreeType.valueOf(degreeType)))
                    .filter(student -> student.getDepartment().equals(Department.valueOf(department)))
                    .collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (!Objects.equals(degreeType, "null") && Objects.equals(department, "null") && admissionYear != 0) {
            students = students.stream().filter(student -> student.getDegreeType().equals(DegreeType.valueOf(degreeType)))
                    .filter(student -> student.getAdmissionYear() == admissionYear)
                    .collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (Objects.equals(degreeType, "null") && !Objects.equals(department, "null") && admissionYear != 0) {
            students = students.stream().filter(student -> student.getDepartment().equals(Department.valueOf(department)))
                    .filter(student -> student.getAdmissionYear() == admissionYear)
                    .collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        } else if (!Objects.equals(degreeType, "null") && !Objects.equals(department, "null") && admissionYear != 0) {
            students = students.stream().filter(student -> student.getDegreeType().equals(DegreeType.valueOf(degreeType)))
                    .filter(student -> student.getDepartment().equals(Department.valueOf(department)))
                    .filter(student -> student.getAdmissionYear() == admissionYear)
                    .collect(Collectors.toList());
            for (Student student : students) {
                studentResponses.add(new StudentResponse(student));
            }
            return studentResponses;
        }
        return Collections.emptyList();
    }
}
