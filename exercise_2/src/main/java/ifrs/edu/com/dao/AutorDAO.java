package ifrs.edu.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifrs.edu.com.config.Factory;
import ifrs.edu.com.models.Autor;

public class AutorDAO implements DAO<Autor, String> {

    @Override
    public void insert(Autor model) {
        // TODO: VALIDATION

        String query = "INSERT INTO \"Autor\" (\"cpf\", \"nome\", \"dataNasc\") VALUES (?, ?, ?)";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getCpf());
            ps.setString(2, model.getNome());
            ps.setDate(3, model.getDataNasc());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public void update(String key, Autor model) {
        // TODO: VALIDATION

        String query = "UPDATE \"Autor\" SET \"nome\"=?, \"dataNasc\"=? WHERE \"cpf\"=?";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getNome());
            ps.setDate(2, model.getDataNasc());
            ps.setString(3, model.getCpf());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public void delete(String key) {
        // TODO: VALIDATION

        String query = "DELETE FROM \"Autor\" WHERE \"cpf\"=?";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, key);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public Autor get(String key) {
        // TODO: VALIDATION

        String query = "SELECT \"cpf\", \"nome\", \"dataNasc\" FROM \"Autor\" WHERE \"cpf\"=? LIMIT 1";

        Autor autor = null;

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, key);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                String cpf = res.getString("cpf");
                String nome = res.getString("nome");
                Date dataNasc = res.getDate("dataNasc");

                autor = new Autor(cpf, nome, dataNasc);
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return autor;
    }

    @Override
    public List<Autor> list(int limit, int offset) {
        // TODO: VALIDATION

        String query = "SELECT \"cpf\", \"nome\", \"dataNasc\" FROM \"Autor\" LIMIT ? OFFSET ?";

        List<Autor> list = null;

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            list = new ArrayList<>();

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                String cpf = res.getString("cpf");
                String nome = res.getString("nome");
                Date dataNasc = res.getDate("dataNasc");

                list.add(new Autor(cpf, nome, dataNasc));
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return list;
    }

    public List<Autor> listByLivro(Integer keyLivro) {
        // TODO: VALIDATION

        String query = """
                    SELECT a.\"cpf\", a.\"nome\", a.\"dataNasc\" FROM \"Autor\" a
                    INNER JOIN \"Autor_Livro\" al ON al.idLivro=?
                """;

        List<Autor> list = null;

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            list = new ArrayList<>();

            ps.setInt(1, keyLivro);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                String cpf = res.getString("cpf");
                String nome = res.getString("nome");
                Date dataNasc = res.getDate("dataNasc");

                list.add(new Autor(cpf, nome, dataNasc));
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return list;
    }
}
