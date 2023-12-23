package com.algoriant.cvs.dto;

import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentResponse {

    private String deptNo;

    private String studentName;

    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private Gender gender;

    private int admissionYear;

    private DegreeType degreeType;

    private Department department;

    private String emailId;

    private long phoneNumber;

    private String address;

    private List<VoteDTO> voteDTOS;

    public StudentResponse(Student student) {
        this.deptNo = student.getDeptNo();
        this.studentName = student.getStudentName();
        this.dateOfBirth = student.getDateOfBirth();
        this.gender = student.getGender();
        this.admissionYear = student.getAdmissionYear();
        this.degreeType = student.getDegreeType();
        this.department = student.getDepartment();
        this.emailId = student.getEmailId();
        this.phoneNumber = student.getPhoneNumber();
        this.address = student.getAddress();
        List<VoteDTO> voteDTOList = new ArrayList<>();
        for (Vote vote : student.getVotes()) {
            voteDTOList.add(new VoteDTO(vote));
        }
        this.voteDTOS = voteDTOList;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<VoteDTO> getVoteDTOS() {
        return voteDTOS;
    }

    public void setVoteDTOS(List<VoteDTO> voteDTOS) {
        this.voteDTOS = voteDTOS;
    }
}
