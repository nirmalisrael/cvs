package com.algoriant.cvs.dto;

public enum
Department {

    TAMIL("TA"),

    ENGLISH("EN"),

    MATHEMATICS("MA"),

    COMPUTER_SCIENCE("CS"),

    COMPUTER_APPLICATION("CA"),

    COMMERCE("CO"),

    BUSINESS_ADMINISTRATION("BA"),

    PHYSICAL_EDUCATION("PE");

    private final String code;

    private Department(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
