package com.montonurb.demo_park_api.Web.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.montonurb.demo_park_api.Exceptions.EntityNotFoundException;
import com.montonurb.demo_park_api.Exceptions.PasswordViolationException;
import com.montonurb.demo_park_api.Exceptions.UsernameUniqueViolationException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex,HttpServletRequest request) {
        log.error("API Error: " + ex);
        return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex,HttpServletRequest request) {
        log.error("API Error: " + ex);
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(PasswordViolationException.class)
    public ResponseEntity<ErrorMessage> passwordViolationException(RuntimeException ex,HttpServletRequest request) {
        log.error("API Error: " + ex);
        return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request, BindingResult result) {
        log.error("API Error: " + ex);
        return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s) !", result));
    }
}
