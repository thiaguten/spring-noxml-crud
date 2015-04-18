package br.com.thiaguten.spring.dao.base;

import br.com.thiaguten.spring.model.base.Persistable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends Persistable<? extends Serializable>, PK extends Serializable> implements DAO<T, PK> {

    private final Class<T> entidadeClazz;
    private final Class<PK> chavePrimariaEntidadeClazz;

    private EntityManager entityManager;

    public AbstractDAO() {
        java.lang.reflect.ParameterizedType genericSuperClass = (java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass();
        this.entidadeClazz = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
        this.chavePrimariaEntidadeClazz = (Class<PK>) genericSuperClass.getActualTypeArguments()[1];
    }

    // metodos convenientes, caso queira utilizar criteria do hibernate

    protected final Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    protected List<T> pesquisarPorCriteria(List<Criterion> criterios) {
        Criteria criteria = getSession().createCriteria(entidadeClazz);
        if (criterios != null) {
            for (Criterion criterio : criterios) {
                criteria.add(criterio);
            }
        }
        return criteria.list();
    }

    protected long contarPorCriteria(List<Criterion> criterios) {
        Criteria criteria = getSession().createCriteria(entidadeClazz);
        criteria.setProjection(Projections.rowCount());
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (criterios != null) {
            for (Criterion criterio : criterios) {
                criteria.add(criterio);
            }
        }
        return ((Long) criteria.uniqueResult());
    }

    // metodos sobrescritos

    @Override
    public Class<T> getEntidadeClazz() {
        return entidadeClazz;
    }

    @Override
    public Class<PK> getChavePrimariaClazz() {
        return chavePrimariaEntidadeClazz;
    }

    @Override
    public T salvar(T t) {
        if (t.getId() != null) {
            t = entityManager.merge(t);
        } else {
            entityManager.persist(t);
        }
        return t;
    }

    @Override
    public T recuperar(PK pk) {
        return entityManager.find(entidadeClazz, pk);
    }

    @Override
    public T alterar(T t) {
        return salvar(t);
    }

    @Override
    public void deletar(T t) {
        deletarPorEntidadeOuPorID(t, null);
    }

    @Override
    public void deletarPorId(PK pk) {
        deletarPorEntidadeOuPorID(null, pk);
    }

    @Override
    public List<T> listar() {
        Criteria criteria = getSession().createCriteria(entidadeClazz);
        return criteria.list();
    }

    // metodos privados

    private void deletarPorEntidadeOuPorID(T t, PK pk) {
        if (pk == null && (t == null || t.getId() == null)) {
            throw new PersistenceException("Não foi possível deletar. O ID da entidade está nulo.");
        }
        PK id = pk;
        if (id == null) {
            id = (PK) t.getId();
        }
        T entidade = getEntityManager().getReference(entidadeClazz, id);
        entityManager.remove(entidade);
    }

    // getters and setters

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Required
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String toString() {
        return "AbstractDAO [entityManager=" + entityManager + "]";
    }
}
