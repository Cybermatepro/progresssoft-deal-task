package com.enwerevincent.progresssofttask.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ApiResponse<T>{

    private String message;
    private HttpStatus status;
    private T data;


}
