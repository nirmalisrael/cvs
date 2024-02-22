package com.algoriant.cvs.dto;

import com.algoriant.cvs.entity.StudentImage;

public class StudentImageDTO {

    private String filename;

    private String fileType;

    private byte[] fileData;

    public StudentImageDTO(StudentImage studentImage) {
        setFilename(studentImage.getFilename());
        setFileType(studentImage.getFileType());
        setFileData(studentImage.getFileData());
    }

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
