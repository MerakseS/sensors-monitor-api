package com.innowisegroup.sensorsmonitorapi.repository;

import java.util.List;

import jakarta.ejb.Local;

@Local
public interface Repository<T> {

    T create(T t);

    List<T> findAll();

    T findById(long id);

    T update(T t);

    void delete(T t);
}
