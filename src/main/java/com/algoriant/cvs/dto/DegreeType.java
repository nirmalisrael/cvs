package com.algoriant.cvs.dto;

public enum DegreeType {

    UG("U"),

    PG("P");

    private final String code;

    private DegreeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
