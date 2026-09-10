package com.hdfc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public String handleCourseNotFound(CourseNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public String handleEnrollmentNotFound(EnrollmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateEnrollmentException.class)
    public String handleDuplicate(DuplicateEnrollmentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CourseCapacityFullException.class)
    public String handleCapacityFull(CourseCapacityFullException ex) {
        return ex.getMessage();
    }
}