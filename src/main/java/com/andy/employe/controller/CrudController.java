package com.andy.employe.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<T>{
    ResponseEntity<Long> save(T t);
    ResponseEntity<T> update(T t);
    ResponseEntity<T> findById(Long id);
    ResponseEntity<List<T>> findAll();
    ResponseEntity<Void> deleteById(Long id );
    ResponseEntity<Boolean> isExist(Long id);
    ResponseEntity<String> home();
}
