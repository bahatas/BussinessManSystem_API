package com.cybertek.businessmansystem_api.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {
    private boolean success;
    private String message;
    private Integer code;
    private Object object;

    public ResponseWrapper(String message, Object object) {
        this.message = message;
        this.object = object;
        this.code = HttpStatus.OK.value();
        this.success=true;
    }

    public ResponseWrapper(String message) {
        this.message = message;
    }
}
