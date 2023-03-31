package com.dailycodebuffer.SpringBootDemo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    //constructor
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
