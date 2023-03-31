package com.dailycodebuffer.OrderService.exception;

import com.dailycodebuffer.OrderService.external.client.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getStatus()));
    }
}
