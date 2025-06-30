package com.example.websample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WebSampleException extends RuntimeException { // RuntimeException을 상속받은 클래스
    private ErrorCode errorCode;
    private String message;
}
