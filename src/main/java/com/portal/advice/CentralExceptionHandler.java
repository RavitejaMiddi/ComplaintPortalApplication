package com.portal.advice;

import com.portal.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;





/*@ControllerAdvice is a specialization of the @Component annotation
which allows to handle exceptions across the whole application in one global handling component.*/

@ControllerAdvice
public class CentralExceptionHandler {

    //@ExceptionHandler is an annotation for handling exceptions in specific handler classes or handler methods
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleInvalidData(MethodArgumentNotValidException ex) {

        List<String> errorList =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fe -> fe.getDefaultMessage())
                        .collect(Collectors.toList());

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("DataError", "Validation failed");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("errors", errorList);

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<?> handleDuplicateProductException(DuplicateProductException ex) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "Duplicates Found");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InValidModelNumberException.class)
    public ResponseEntity<?> handleInValidModelNumberException(InValidModelNumberException ex) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "ModelNumber Invalid");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoComplaintFoundException.class)
    public ResponseEntity<?> handleNoComplaintFoundException(NoComplaintFoundException ex) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", " No Complaints Retrieved");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoProductsException.class)
    public ResponseEntity<?> handleNoProductsException(NoProductsException ex) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", " No Products Retrieved");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoEngineerFoundException.class)
    public ResponseEntity<?> handleNoEngineerFoundException(NoEngineerFoundException ex) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "Engineers not found in database");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InValidEngineerIdException.class)
    public ResponseEntity<?> handleInValidEngineerIdException(InValidEngineerIdException ex) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "Invalid Engineer Id");
        errorBody.put("timestamp", LocalDate.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InValidDomainException.class)
    public ResponseEntity<?> handleInValidDomainException(InValidDomainException ex) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "Invalid Domain");
        errorBody.put("timestamp", LocalDate.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InValidComplaintIdException.class)
    public ResponseEntity<?> handleInvalidComplaintIdException(InValidComplaintIdException ex) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "Invalid Complaint ID");
        errorBody.put("timestamp", LocalDate.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateFoundException.class)
    public ResponseEntity<?> handleDuplicateFoundException(DuplicateFoundException exception) {
        Map<String, Object> errorBody = new LinkedHashMap<>();

        errorBody.put("errorMessage", "Duplicate Client id");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", exception.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(OutOfWarrantyException.class)
    public ResponseEntity<?> handleOutOfWarrantyException(OutOfWarrantyException exception) {
        Map<String, Object> errorBody = new LinkedHashMap<>();

        errorBody.put("errorMessage", "Warranty is Expired");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", exception.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InValidStatusException.class)
    public ResponseEntity<?> handleInValidStatusException(InValidStatusException ex) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("errorMessage", "Updation Failed");
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("details", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }


}