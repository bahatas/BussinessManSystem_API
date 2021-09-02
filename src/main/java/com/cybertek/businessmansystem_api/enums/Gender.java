package com.cybertek.businessmansystem_api.enums;


import lombok.Getter;

@Getter
public enum Gender {
    Male("Male"), Female("Female"),MALE("Male"), FEMALE("Female");

    private final String value;

    Gender(String value){
        this.value = value;
    }

    String getValue(){
        return value;
    }

}
