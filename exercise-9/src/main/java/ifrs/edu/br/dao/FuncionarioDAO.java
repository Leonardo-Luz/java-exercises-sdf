package ifrs.edu.br.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ifrs.edu.br.models.Funcionario;

/**
 * FuncionarioDAO
 */
public class FuncionarioDAO extends DAO<Funcionario> {
    public FuncionarioDAO(EntityManager entityManager) {
        super(Funcionario.class, entityManager);
    }

    public List<Funcionario> listar(int limit, int offset) {
        TypedQuery<Funcionario> sql = this.entityManager.createQuery("SELECT e FROM Funcionario e", Funcionario.class);

        return sql.setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
