package ifrs.edu.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifrs.edu.com.config.Factory;
import ifrs.edu.com.models.Editora;
import ifrs.edu.com.models.Livro;

public class EditoraDAO implements DAO<Editora, Integer> {

    @Override
    public void insert(Editora model) {
        // TODO: VALIDATION

        String query = "INSERT INTO \"Editora\" (\"nome\", \"endereco\") VALUES (?, ?)";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getNome());
            ps.setString(2, model.getEndereco());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public void update(Integer key, Editora model) {
        // TODO: VALIDATION

        String query = "UPDATE \"Editora\" SET \"nome\"=?, \"endereco\"=? WHERE \"id\"=?";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getNome());
            ps.setString(2, model.getEndereco());
            ps.setInt(3, model.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public void delete(Integer key) {
        // TODO: VALIDATION

        String query = "DELETE FROM \"Editora\" WHERE \"id\"=?";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, key);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public Editora get(Integer key) {
        // TODO: VALIDATION

        String query = "SELECT \"id\", \"nome\", \"endereco\" FROM \"Editora\" WHERE \"id\"=? LIMIT 1";

        Editora editora = null;

        LivroDAO dao = new LivroDAO();

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, key);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Integer id = res.getInt("id");
                String nome = res.getString("nome");
                String endereco = res.getString("endereco");

                List<Livro> livros = dao.listByEditora(id);

                editora = new Editora(id, nome, endereco, livros);
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return editora;
    }

    @Override
    public List<Editora> list(int limit, int offset) {
        // TODO: VALIDATION

        String query = "SELECT \"id\", \"nome\", \"endereco\" FROM \"Editora\" LIMIT ? OFFSET ?";

        List<Editora> list = null;

        LivroDAO dao = new LivroDAO();

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            list = new ArrayList<>();

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Integer id = res.getInt("id");
                String nome = res.getString("nome");
                String endereco = res.getString("endereco");

                List<Livro> livros = dao.listByEditora(id);

                list.add(new Editora(id, nome, endereco, livros));
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return list;
    }
}
