package com.algoriant.cvs.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_images")
public class StudentImage {

    @Id
    private String filename;

    @OneToOne
    @JoinColumn(name = "deptNo")
    private Student student;

    private String fileType;

    @Lob
    @Column(length = 1000)
    private byte[] fileData;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
