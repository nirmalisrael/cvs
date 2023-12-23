package com.algoriant.cvs.dto;

public enum Gender {

    MALE("M"),

    FEMALE("F"),

    TRANSGENDER("TG");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
