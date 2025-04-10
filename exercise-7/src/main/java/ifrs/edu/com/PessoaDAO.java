package ifrs.edu.com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Connection conn;

    PessoaDAO() {
        try {
            this.conn = ConnectionFactory.connect(Database.DEVELOPMENT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PessoaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Pessoa p) {
        try {
            String query = "INSERT INTO pessoa (nome, datanasc) VALUES (?, ?)";

            PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, p.getNome());
            ps.setDate(2, Date.valueOf(p.getDataNasc()));

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long key = generatedKeys.getLong(1);
                    p.setId((int) key);
                    System.out.println("key: " + key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pessoa get(int id) {
        try {
            String query = "SELECT * FROM pessoa WHERE id=?";

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet response = ps.executeQuery();

            if (response.next()) {
                return new Pessoa(
                        response.getString("nome"),
                        response.getInt("id"),
                        response.getDate("datanasc").toLocalDate());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Pessoa p) {
        try {
            String query = """
                        UPDATE pessoa SET nome=?, datanasc=?
                        WHERE id = ?
                    """;
            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setString(1, p.getNome());
            ps.setDate(2, Date.valueOf(p.getDataNasc()));

            ps.setInt(3, p.getId());

            ps.executeUpdate();
        } catch (SQLException err) {
            System.out.println(err);
        }
    }

    public void delete(int id) {
        try {
            String query = "DELETE FROM pessoa WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, id);

            ps.execute();
        } catch (SQLException err) {
            System.out.println(err);
        }
    }

    public List<Pessoa> list(int limit, int offset) {
        try {
            List<Pessoa> list = new ArrayList<>();

            String query = """
                        SELECT * FROM pessoa
                        LIMIT ? OFFSET ?
                    """;
            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet response = ps.executeQuery();

            while (response.next()) {
                list.add(new Pessoa(
                        response.getString("nome"),
                        response.getInt("id"),
                        response.getDate("datanasc").toLocalDate()));
            }

            return list;
        } catch (SQLException err) {
            System.out.println(err);
            return null;
        }
    }

    public void save(Pessoa p) {
    }
}
