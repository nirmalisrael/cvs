package com.algoriant.cvs.dto;

import com.algoriant.cvs.entity.Student;
import com.algoriant.cvs.entity.Vote;
import com.algoriant.cvs.util.StudentImageUtil;
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

    private byte[] profileImage;

    private List<VoteDTO> voteDTOS;

    public StudentResponse(Student student) {
        if (student.getDeptNo() != null)
            this.deptNo = student.getDeptNo();
        if (student.getStudentName() != null)
            this.studentName = student.getStudentName();
        if (student.getDateOfBirth() != null)
            this.dateOfBirth = student.getDateOfBirth();
        if (student.getGender() != null)
            this.gender = student.getGender();
        if (student.getAdmissionYear() != 0)
            this.admissionYear = student.getAdmissionYear();
        if (student.getDegreeType() != null)
            this.degreeType = student.getDegreeType();
        if (student.getDepartment() != null)
            this.department = student.getDepartment();
        if (student.getEmailId() != null)
            this.emailId = student.getEmailId();
        if (student.getPhoneNumber() != 0)
            this.phoneNumber = student.getPhoneNumber();
        if (student.getAddress() != null)
            this.address = student.getAddress();
        if (student.getProfileImage() != null)
            this.profileImage = StudentImageUtil.decompressImage(student.getProfileImage());
        if (student.getVotes() != null) {
            List<VoteDTO> voteDTOList = new ArrayList<>();
            for (Vote vote : student.getVotes()) {
                voteDTOList.add(new VoteDTO(vote));
            }
            this.voteDTOS = voteDTOList;
        }
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

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = StudentImageUtil.decompressImage(profileImage);
    }
}
