package com.example.websample.exception;

import com.example.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(IllegalAccessException.class)
//    public ErrorResponse handleException(IllegalAccessException e) {
//        log.error("IllegalAccessException is occurred.",e);
//
////        return "INVALID_ACCESS";
//        return new ErrorResponse("INVALID_ACCESS",
//                "IllegalAccessException is occurred.");
//    }

    //ResponseEntity 활용한 예시
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(
            IllegalAccessException e) {
        log.error("IllegalAccessException is occurred.", e);

//        return "INVALID_ACCESS";
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR,
                        "IllegalAccessException is occurred."));
    }

    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(
            WebSampleException e) {
        log.error("WebSampleException is occurred.", e);

//        return "INVALID_ACCESS";
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ErrorResponse(e.getErrorCode(),
                        "WebSampleException is occurred."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception e) {
        log.error("WebSampleException is occurred.", e);

//        return "INVALID_ACCESS";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,
                        "Exception is occurred."));
    }
}
