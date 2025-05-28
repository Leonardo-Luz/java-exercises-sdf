package ifrs.edu.br.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ifrs.edu.br.models.Projeto;

/**
 * ProjetoDAO
 */
public class ProjetoDAO extends DAO<Projeto> {
    public ProjetoDAO(EntityManager entityManager) {
        super(Projeto.class, entityManager);
    }

    public List<Projeto> listar(int limit, int offset) {
        TypedQuery<Projeto> sql = this.entityManager.createQuery("SELECT p FROM Projeto p", Projeto.class);

        return sql.setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public Projeto buscarByNomeWithEmpregados(String nome) {
        TypedQuery<Projeto> sql = this.entityManager.createQuery(
                "SELECT DISTINCT p FROM Projeto p LEFT JOIN p.participantes e WHERE p.nome = :nome",
                Projeto.class);

        sql.setParameter("nome", nome);

        return sql.getResultList().get(0);
    }
}
