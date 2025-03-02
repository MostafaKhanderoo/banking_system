package app.base.repository;

import app.base.entity.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository <ID extends Serializable,T extends BaseEntity<ID>> {
    T save (Session session, T t);
    T update(Session session,T t);
    Optional<T> findById(Session session, Long id);
    List<T>findAll(Session session);
    void deleteById(Session session, Long id);
}
