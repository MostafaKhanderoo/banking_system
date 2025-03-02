package app.base.service;

import app.base.entity.BaseEntity;
import app.base.repository.BaseRepository;
import app.cfg.SessionFactoryInstance;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class BaseServiceImpl<ID extends Serializable,
        T extends BaseEntity<ID>,
        R extends BaseRepository<ID, T>>
        implements BaseService<ID, T> {

    private final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public T save(T t) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                repository.save(session, t);
                session.getTransaction().commit();
                return t;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public T update(T t) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                repository.update(session, t);
                session.getTransaction().commit();
                return t;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public List<T> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
            var result =    repository.findAll(session);
                session.getTransaction().commit();
                return result;

            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                var result = repository.findById(session, id);
                session.getTransaction().commit();
                return result;

            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;

            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();

            try {
                repository.deleteById(session, id);
                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
