package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDAO<T> {
    private final Class<T> entityType;

    protected AbstractDAO(Class<T> entityType) {   // REMOVE
        this.entityType = entityType;              // REMOVE
    }                                              // REMOVE

    protected abstract EntityManager getEntityManager();

    public long count() {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = builder.createQuery();
        final Root<T> rt = cq.from(entityType);

        cq.select(builder.count(rt));

        final Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult());
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    // TODO: !!!
    public void update(T entity, String column, String newValue) {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        // create update
        final CriteriaUpdate<T> update = builder.createCriteriaUpdate(entityType);
        // set the root class
        final Root<T> rt = update.from(entityType);
        // set update and where clause
        update.set(column, newValue);
        update.where(builder.greaterThanOrEqualTo(rt.get("amount"), oldAmount));
        // perform update
        getEntityManager().createQuery(update).executeUpdate();
    }
}