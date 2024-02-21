package com.algoriant.cvs.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_images")
public class StudentImage {

    @Id
    private String filename;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Student student;

    private String fileType;

    @Lob
    @Column(length = 1000)
    private byte[] fileData;
}
