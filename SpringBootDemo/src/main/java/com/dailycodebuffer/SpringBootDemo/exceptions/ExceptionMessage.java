package com.dailycodebuffer.SpringBootDemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private HttpStatus httpStatus;
    private String message;
}
