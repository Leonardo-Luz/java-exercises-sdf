package ifrs.edu.br.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ifrs.edu.br.models.Funcionario;

/**
 * FuncionarioDAO
 */
public class FuncionarioDAO {
    private EntityManager entityManager;

    public FuncionarioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void inserir(Funcionario funcionario) {
        entityManager.getTransaction().begin();
        entityManager.persist(funcionario);
        entityManager.getTransaction().commit();
    }

    public Funcionario buscar(int id) {
        return entityManager.find(Funcionario.class, id);
    }

    public List<Funcionario> listar(int limit, int offset) {
        TypedQuery<Funcionario> sql = entityManager.createQuery("SELECT e FROM Funcionario e", Funcionario.class);

        return sql.setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public void alterar(Funcionario funcionario) {
        entityManager.getTransaction().begin();
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
    }

    public void deletar(int id) {
        entityManager.getTransaction().begin();

        Funcionario remove = this.buscar(id);

        entityManager.remove(remove);
        entityManager.getTransaction().commit();
    }
}
