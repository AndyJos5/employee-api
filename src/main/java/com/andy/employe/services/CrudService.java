package com.andy.employe.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    Long save(T t);
    T update(T t);
    Optional<T> findById(Long id);
    boolean isExist(Long id);
    List<T> findAll();
    void delete(Long id);
}
