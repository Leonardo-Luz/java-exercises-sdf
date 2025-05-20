package ifrs.edu.br.dao;

import javax.persistence.EntityManager;

public abstract class DAO<T> {
    protected EntityManager entityManager;
    private Class<T> entityClass;

    public DAO(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    public void inserir(T object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    public T buscar(int id) {
        return entityManager.find(entityClass, id);
    }

    public void alterar(T object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    public void deletar(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.getReference(entityClass, id));
        entityManager.getTransaction().commit();
    }
}
