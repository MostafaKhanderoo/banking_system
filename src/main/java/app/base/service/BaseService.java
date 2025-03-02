package app.base.service;

import app.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService <ID extends Serializable,T extends BaseEntity<ID>>{

    T save (T t);
    T update(T t);
    List<T>findAll();
    Optional<T> findById(Long id);
    void deleteById(Long id);
}
