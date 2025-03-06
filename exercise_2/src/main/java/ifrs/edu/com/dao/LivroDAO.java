package ifrs.edu.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifrs.edu.com.config.Factory;
import ifrs.edu.com.models.Autor;
import ifrs.edu.com.models.Editora;
import ifrs.edu.com.models.Livro;

public class LivroDAO implements DAO<Livro, Integer> {

    @Override
    public void insert(Livro model) {
        // TODO: VALIDATION

        String query = "INSERT INTO \"Livro\" (\"titulo\", \"anoLancamento\", \"idEditora\") VALUES (?, ?, ?)";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getTitulo());
            ps.setInt(2, model.getAnoLancamento());
            ps.setInt(3, model.getEditora().getId());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public void update(Integer key, Livro model) {
        // TODO: VALIDATION

        String query = "UPDATE \"Livro\" SET \"titulo\"=?, \"anoLancamento\"=?, \"idEditora\"=? WHERE \"id\"=?";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getTitulo());
            ps.setInt(2, model.getAnoLancamento());
            ps.setInt(3, model.getEditora().getId());
            ps.setInt(4, model.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public void delete(Integer key) {
        // TODO: VALIDATION

        String query = "DELETE FROM \"Livro\" WHERE \"id\"=?";

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, key);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }
    }

    @Override
    public Livro get(Integer key) {
        // TODO: VALIDATION

        String query = """
                    SELECT
                        l.\"id\" AS idLivro, l.\"titulo\", l.\"anoLancamento\", l.\"idEditora\",
                        e.\"id\", e.nome, e.endereco
                    FROM \"Livro\" l
                    INNER JOIN \"Editora\" e on e.id=l.\"idEditora\"
                    WHERE \"id\"=?
                    LIMIT 1
                """;

        Livro livro = null;

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, key);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Integer id = res.getInt("id");
                String titulo = res.getString("titulo");
                Integer anoLancamento = res.getInt("anoLancamento");

                Editora editora = new Editora(
                        res.getInt("idEditora"),
                        res.getString("titulo"),
                        res.getString("endereco"),
                        null);

                livro = new Livro(id, titulo, anoLancamento, editora, null);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return livro;
    }

    @Override
    public List<Livro> list(int limit, int offset) {
        // TODO: VALIDATION

        String query = """
                    SELECT
                        l.\"id\" AS idLivro, l.\"titulo\", l.\"anoLancamento\", l.\"idEditora\",
                        e.\"id\", e.nome, e.endereco
                    FROM \"Livro\" l
                    INNER JOIN \"Editora\" e on e.id=l.\"idEditora\"
                    LIMIT ? OFFSET ?
                """;

        List<Livro> list = null;

        AutorDAO dao = new AutorDAO();

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            list = new ArrayList<>();

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Integer id = res.getInt("id");
                String titulo = res.getString("titulo");
                Integer anoLancamento = res.getInt("anoLancamento");

                Editora editora = new Editora(
                        res.getInt("idEditora"),
                        res.getString("titulo"),
                        res.getString("endereco"),
                        null);

                List<Autor> autores = dao.listByLivro(id);

                list.add(new Livro(id, titulo, anoLancamento, editora, autores));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return list;
    }

    public List<Livro> listByEditora(Integer keyEditora) {
        // TODO: VALIDATION

        String query = """
                    SELECT
                        l.\"id\" AS idLivro, l.\"titulo\", l.\"anoLancamento\", l.\"idEditora\",
                    FROM \"Livro\" l
                    WHERE l.idEditora=?
                """;

        List<Livro> list = null;

        AutorDAO dao = new AutorDAO();

        try (Connection connection = Factory.connect(); PreparedStatement ps = connection.prepareStatement(query)) {
            list = new ArrayList<>();

            ps.setInt(1, keyEditora);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Integer id = res.getInt("id");
                String titulo = res.getString("titulo");
                Integer anoLancamento = res.getInt("anoLancamento");

                List<Autor> autores = dao.listByLivro(id);

                list.add(new Livro(id, titulo, anoLancamento, null, autores));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Query Error: ", e);
        }

        return list;
    }
}
