package app.base.repository;

import app.base.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepositoryImpl<ID extends Serializable, T extends BaseEntity<ID>>
        implements BaseRepository<ID, T> {
    private final Class<T> entity;


    @Override
    public T save(Session session, T t) {
        session.persist(t);
        return t;
    }

    @Override
    public T update(Session session, T t) {
        session.merge(t);
        return t;
    }

    @Override
    public Optional<T> findById(Session session, Long id) {
        return session.byId(entity).loadOptional(id);
    }

    @Override
    public List<T> findAll(Session session) {
        return session.createQuery("from " + entity.getName(), entity).list();
    }

    @Override
    public void deleteById(Session session, Long id) {
        session.createMutationQuery("delete from " + entity.getClasses() + " e where e.id = :id").
                setParameter("id" , id).executeUpdate();
    }
}
