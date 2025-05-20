package ifrs.edu.br.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ifrs.edu.br.models.Dependente;

/**
 * DependenteDAO
 */
public class DependenteDAO extends DAO<Dependente> {
    public DependenteDAO(EntityManager entityManager) {
        super(Dependente.class, entityManager);
    }

    public List<Dependente> listar(int limit, int offset) {
        TypedQuery<Dependente> sql = this.entityManager.createQuery("SELECT d FROM Dependente d", Dependente.class);

        return sql.setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}

