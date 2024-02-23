package com.algoriant.cvs.entity;

import com.algoriant.cvs.dto.DegreeType;
import com.algoriant.cvs.dto.Department;
import com.algoriant.cvs.dto.Gender;
import com.algoriant.cvs.dto.StudentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student {

    @Id
    @NotNull
    private String deptNo;

    @NotNull
    private String studentName;

    @NotNull
    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull
    private Gender gender;

    @NotNull
    private int admissionYear;

    @NotNull
    private DegreeType degreeType;

    @NotNull
    private Department department;

    @NotNull
    private String emailId;

    @NotNull
    private long phoneNumber;

    @NotNull
    private String address;

    private byte[] profileImage;

    @OneToMany(mappedBy = "student")
    private List<Vote> votes;

    public Student() {
    }

    public Student(StudentRequest studentRequest) {
        if (studentRequest.getStudentName() != null)
            studentName = studentRequest.getStudentName();
        if (studentRequest.getDateOfBirth() != null)
            dateOfBirth = studentRequest.getDateOfBirth();
        if (studentRequest.getGender() != null)
            gender = studentRequest.getGender();
        if (studentRequest.getAdmissionYear() != 0)
            admissionYear = studentRequest.getAdmissionYear();
        if (studentRequest.getDegreeType() != null)
            degreeType = studentRequest.getDegreeType();
        if (studentRequest.getDepartment() != null)
            department = studentRequest.getDepartment();
        if (studentRequest.getEmailId() != null)
            emailId = studentRequest.getEmailId();
        if (studentRequest.getPhoneNumber() != 0)
            phoneNumber = studentRequest.getPhoneNumber();
        if (studentRequest.getAddress() != null)
            address = studentRequest.getAddress();
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
