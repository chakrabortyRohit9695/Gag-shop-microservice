package com.dailycodebuffer.SpringBootDemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * allows us to consolidate our multiple, scattered @ExceptionHandlers from before into a single,
 * global error handling component. ControllerAdvice kicks in when any of the created exceptions occur,
 * it creates necessary object and send as a response.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage employeeNotFoundHandler(EmployeeNotFoundException exception) {
        ExceptionMessage message = new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessage genericExceptionHandler(Exception exception) {
        ExceptionMessage message = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        return message;
    }
}
