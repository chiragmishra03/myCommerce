package com.app.myCommerce.exceptions;

import com.app.myCommerce.utilities.api.APIStructure;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<APIStructure> handleAllResourceNotFoundExceptions(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIStructure.failure(ex.getMessage()));
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<APIStructure> handleBadRequest(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIStructure.failure(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIStructure<Object>> handleDuplicateEntry(DataIntegrityViolationException ex){
        String message = "Item already exists";
        if(ex.getMessage() != null && ex.getMessage().contains("unique_name_active")){
            message = "Item with this name already exists";
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(APIStructure.failure(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIStructure> handleAllGeneralExceptions(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIStructure.failure(ex.getMessage()));
    }

}
