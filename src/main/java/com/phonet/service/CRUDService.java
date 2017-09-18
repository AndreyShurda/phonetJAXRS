package com.phonet.service;

import java.util.List;

public interface CRUDService<T> {
    T findById(Long id);

    void save(T entity);

    void update(T entity);

    void deleteById(Long id);

    List<T> findAll();

    boolean isExist(T entity);

//    T findByName(String name);
}
