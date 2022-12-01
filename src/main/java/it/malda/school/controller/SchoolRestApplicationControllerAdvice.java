package it.malda.school.controller;

import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.ForbiddenInputException;
import it.malda.school.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SchoolRestApplicationControllerAdvice {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String entityNotFoundHandler(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidInputHandler(InvalidInputException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenInputException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String forbiddenInputHandler(ForbiddenInputException ex) {
        return ex.getMessage();
    }
}
