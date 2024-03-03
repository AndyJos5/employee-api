package com.andy.employe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("rawtypes")
public class Utils {
 public static ResponseEntity buildErrorResponse(final String message){
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
    
    public static ResponseEntity buildSuccessResponse(final String message){
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
    public static final String SAVE = "save";
    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_ID = "findById/{id}";
    public static final String DELETE_BY_ID = "deleteById/{id}";
    public static final String IS_EXIST = "isExist/{id}";
    public static final String UPDATE = "update";
}
