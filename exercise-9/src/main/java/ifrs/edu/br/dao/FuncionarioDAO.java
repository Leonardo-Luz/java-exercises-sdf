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

    public List<Funcionario> listarDependentes(int limit, int offset) {
        TypedQuery<Funcionario> sql = this.entityManager.createQuery(
                "SELECT DISTINCT e FROM Funcionario e LEFT JOIN e.dependentes d",
                Funcionario.class);

        return sql.setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
