package com.cybertek.businessmansystem_api.enums;


import lombok.Getter;

@Getter
public enum Status {
    OPEN("Open"),IN_PROGRESS("In Progress"),UAT_TEST("UAT Testing"), COMPLETE("Completed");

    private final String value;

    Status(String value){
        this.value=value;
    }

    String getValue(){
        return value;
    }


}

